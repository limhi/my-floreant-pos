package com.floreantpos.main;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;

import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.Player;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.floreantpos.bo.ui.BackOfficeWindow;
import com.floreantpos.config.ApplicationConfig;
import com.floreantpos.config.ui.DatabaseConfigurationDialog;
import com.floreantpos.model.PrinterConfiguration;
import com.floreantpos.model.Restaurant;
import com.floreantpos.model.Shift;
import com.floreantpos.model.Terminal;
import com.floreantpos.model.User;
import com.floreantpos.model.dao.PrinterConfigurationDAO;
import com.floreantpos.model.dao.RestaurantDAO;
import com.floreantpos.model.dao.TerminalDAO;
import com.floreantpos.model.dao._RootDAO;
import com.floreantpos.swing.GlassPane;
import com.floreantpos.ui.dialog.NumberSelectionDialog;
import com.floreantpos.ui.dialog.POSDialog;
import com.floreantpos.ui.views.LoginScreen;
import com.floreantpos.ui.views.order.RootView;
import com.floreantpos.util.TicketActiveDateSetterTask;
import com.jgoodies.looks.plastic.PlasticXPLookAndFeel;
import com.jgoodies.looks.plastic.theme.ExperienceBlue;
public class Application {
	private static Log logger = LogFactory.getLog(Application.class);

	private Timer autoDrawerPullTimer;

	private Terminal terminal;
	private PosWindow posWindow;
	private User currentUser;
	private RootView rootView;
	private BackOfficeWindow backOfficeWindow;
	private Shift currentShift;
	public PrinterConfiguration printConfiguration;
	private Restaurant restaurant;

	private static Application instance;

	private static DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM, yyyy");
	private static ImageIcon applicationIcon;
	
	public final static String VERSION = ApplicationConfig.getConfiguration().getString("floreantpos.version");
	
	
	//2012/02/11 gush
	private MediaLocator ml;
	private Player player;
	
	private Application() {
		
		//2012/02/12 gush
		//web cam
		ml=new MediaLocator("vfw://0");
		try{
			player= Manager.createRealizedPlayer(ml);
		}catch (Exception e) {
			
		}
		
		//gush end
				
				
		//luke 20120107 start
		//設定內定字型
		Font font = new Font("mingliu", Font.PLAIN, 14);
		Enumeration<Object> keys = UIManager.getDefaults().keys();
		while (keys.hasMoreElements()) {
		    Object key = keys.nextElement();
		    if (UIManager.get(key) instanceof FontUIResource){
		        UIManager.put(key, new FontUIResource(font));
		    }
		    else if(UIManager.get(key) instanceof Font){
		     UIManager.put(key, font);
		    }
		    //System.out.println("key:"+key+"  value:"+UIManager.get(key));
		}
		//luke 20120107 end
		
		applicationIcon = new ImageIcon(getClass().getResource("/icons/icon.png"));
		posWindow = new PosWindow();
		posWindow.setGlassPaneVisible(true);
		posWindow.setTitle(getTitle());
		posWindow.setIconImage(applicationIcon.getImage());
		posWindow.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				shutdownPOS();

			}
		});
	}

	public void start() {
		setApplicationLook();

		rootView = RootView.getInstance();

		posWindow.setContentPane(rootView);
		posWindow.setSize(ApplicationConfig.getPreferences().getInt("wwidth", 900), ApplicationConfig.getPreferences().getInt("wheight", 650));
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		posWindow.setLocation(ApplicationConfig.getPreferences().getInt("wlocx", ((screenSize.width - posWindow.getWidth()) >> 1)), ApplicationConfig.getPreferences().getInt("wlocy", ((screenSize.height - posWindow.getHeight()) >> 1)));
		posWindow.setMinimumSize(new Dimension(800, 600));
		posWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		posWindow.setVisible(true);

		initDatabase();

		
	}

	private void setApplicationLook() {
		try {
			PlasticXPLookAndFeel.setPlasticTheme(new ExperienceBlue());
			UIManager.setLookAndFeel(new PlasticXPLookAndFeel());
			UIManager.put("ComboBox.is3DEnabled", Boolean.FALSE);
		} catch (Exception ignored) {
		}
	}

	public void initDatabase() {
		if(!ApplicationConfig.checkDatabaseConnection()) {
			DatabaseConfigurationDialog dialog = new DatabaseConfigurationDialog(getPosWindow(), false );
			dialog.setTitle(com.floreantpos.POSConstants.DATABASE_CONNECTION_ERROR);
			dialog.setExitOnClose(false);
			dialog.pack();
			dialog.open();
		}


		try {
			((GlassPane) posWindow.getGlassPane()).setMessage(com.floreantpos.POSConstants.LOADING);
			_RootDAO.initialize();

			int terminalId = ApplicationConfig.getTerminalId();
			logger.info("Terminal ID from configuration=" + terminalId);

			if (terminalId == -1) {
				NumberSelectionDialog dialog = new NumberSelectionDialog();
				dialog.setDecimalAllowed(false);
				dialog.setTitle(com.floreantpos.POSConstants.ENTER_ID_FOR_TERMINAL);
				dialog.setVisible(true);
				terminalId = (int) dialog.getValue();
			}

			TerminalDAO terminalDAO = new TerminalDAO();
			Terminal terminal2 = terminalDAO.get(new Integer(terminalId));
			if (terminal2 == null) {
				terminal2 = new Terminal();
				terminal2.setId(terminalId);
				terminal2.setOpeningBalance(new Double(500));
				terminal2.setCurrentBalance(new Double(500));
				terminal2.setName(com.floreantpos.POSConstants.TERMINAL + " - " + terminalId);
				terminalDAO.saveOrUpdate(terminal2);
			}
			ApplicationConfig.setTerminalId(terminalId);
			this.terminal = terminal2;

			printConfiguration = new PrinterConfigurationDAO().get(PrinterConfiguration.ID);
			if(printConfiguration == null) {
				printConfiguration = new PrinterConfiguration();
			}

			refreshRestaurant();

			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 1);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);

			//SimpleDateFormat format = new SimpleDateFormat("yyyy MMM dd HH:mm:s a");
			Date time = calendar.getTime();
			//System.out.println("expected next launch: " + format.format(time));

			TicketActiveDateSetterTask ticketActiveDateSetterTask = new TicketActiveDateSetterTask();
			ticketActiveDateSetterTask.run();

			java.util.Timer activeDateScheduler = new java.util.Timer();
			activeDateScheduler.scheduleAtFixedRate(ticketActiveDateSetterTask, time, 86400*1000);
		} finally {
			getPosWindow().setGlassPaneVisible(false);
		}
	}

	public void refreshRestaurant() {
		RestaurantDAO restaurantDAO = RestaurantDAO.getInstance();
		this.restaurant = restaurantDAO.get(Integer.valueOf(1));
		if(restaurant.isAutoDrawerPullEnable() && autoDrawerPullTimer == null) {
			autoDrawerPullTimer = new Timer(60 * 1000, new AutoDrawerPullAction());
			autoDrawerPullTimer.start();
		}
		else {
			if(autoDrawerPullTimer != null) {
				autoDrawerPullTimer.stop();
				autoDrawerPullTimer = null;
			}
		}
	}

	public static String getCurrencyName() {
		Application application = getInstance();
		if(application.restaurant == null) {
			application.refreshRestaurant();
		}
		return application.restaurant.getCurrencyName();
	}

	public static String getCurrencySymbol() {
		Application application = getInstance();
		if(application.restaurant == null) {
			application.refreshRestaurant();
		}
		return application.restaurant.getCurrencySymbol();
	}

	public synchronized static Application getInstance() {
		if (instance == null) {
			instance = new Application();

		}
		return instance;
	}

	public void shutdownPOS() {
		int option = JOptionPane.showOptionDialog(getPosWindow(), com.floreantpos.POSConstants.SURE_SHUTDOWN_, com.floreantpos.POSConstants.CONFIRM_SHUTDOWN, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		if(option != JOptionPane.YES_OPTION) {
			return;
		}

		int width = posWindow.getWidth();
		int height = posWindow.getHeight();
		ApplicationConfig.getPreferences().putInt("wwidth", width);
		ApplicationConfig.getPreferences().putInt("wheight", height);

		Point locationOnScreen = posWindow.getLocationOnScreen();
		ApplicationConfig.getPreferences().putInt("wlocx", locationOnScreen.x);
		ApplicationConfig.getPreferences().putInt("wlocy", locationOnScreen.y);

		System.exit(0);
	}

	public void logout() {
		if (backOfficeWindow != null) {
			backOfficeWindow.setVisible(false);
			backOfficeWindow = null;
			currentShift = null;
		}

		setCurrentUser(null);
		RootView.getInstance().showView(LoginScreen.VIEW_NAME);
	}

	public static User getCurrentUser() {
		return getInstance().currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	public RootView getRootView() {
		return rootView;
	}

	public void setRootView(RootView rootView) {
		this.rootView = rootView;
	}

	public static PosWindow getPosWindow() {
		return getInstance().posWindow;
	}

	public BackOfficeWindow getBackOfficeWindow() {
		return backOfficeWindow;
	}

	public void setBackOfficeWindow(BackOfficeWindow backOfficeWindow) {
		this.backOfficeWindow = backOfficeWindow;
	}

	public Terminal getTerminal() {

		TerminalDAO.getInstance().refresh(terminal);

		return terminal;
	}

//	public static PrinterConfiguration getPrinterConfiguration() {
//		return getInstance().printConfiguration;
//	}

	public static String formatNumber(Double number) {
		if(number == null) {
			return "0.00";
		}

		String value = decimalFormat.format(number);
		if(value.equals("-0.00")) {
			return "0.00";
		}
		return value;
	}

	public static String getTitle() {
		return "Floreant POS - Version " + VERSION;
	}

	public static ImageIcon getApplicationIcon() {
		return applicationIcon;
	}

	public static void setApplicationIcon(ImageIcon applicationIcon) {
		Application.applicationIcon = applicationIcon;
	}

	public static String formatDate(Date date) {
		return dateFormat.format(date);
	}

	public Shift getCurrentShift() {
		return currentShift;
	}

	public void setCurrentShift(Shift currentShift) {
		this.currentShift = currentShift;
	}

	public void setAutoDrawerPullEnable(boolean enable) {
		if(enable) {
			if(autoDrawerPullTimer != null) {
				return;
			}
			else {
				autoDrawerPullTimer = new Timer(60 * 1000, new AutoDrawerPullAction());
				autoDrawerPullTimer.start();
			}
		}
		else {
			autoDrawerPullTimer.stop();
			autoDrawerPullTimer = null;
		}
	}

	public MediaLocator getMl() {
		return Application.getInstance().ml;
	}

	public void setMl(MediaLocator ml) {
		this.ml = ml;
	}

	public Player getPlayer() {
		return Application.getInstance().player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
	
}

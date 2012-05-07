package com.gama.table.client.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.gama.tablews.EventMeterRequest;
import com.gama.tablews.EventMeterResult;
import com.gama.tablews.ObjectFactory;



public class KIOSKView extends JFrame implements ActionListener,MessageListener{
	private WebServiceTemplate eventMeterWSTemplate;
	
	private JButton billInButton = null;
	private JButton billInALLButton = null;
	private JButton doorOpenButton = null;
	private JButton doorCloseButton = null;
	private JButton cashBoxInstallButton = null;
	private JButton cashBoxRemoveButton = null;
	Logger logger=Logger.getLogger(this.getClass());
	private JLabel note1Label = new JLabel("佰元鈔");
	private JLabel note2Label = new JLabel("伍佰元鈔");
	private JLabel note3Label = new JLabel("千元鈔");
	private JLabel note4Label = new JLabel("兩千元鈔");
	private JTextField note1Text=new JTextField(2);
	private JTextField note2Text=new JTextField(2);
	private JTextField note3Text=new JTextField(2);
	private JTextField note4Text=new JTextField(2);
	private JTextArea textArea=new JTextArea();

	int note1=0;
	int note2=0;
	int note3=0;
	int note4=0;
	long billIn=0;
	Integer MNum=12345;
	public KIOSKView() {
		JPanel flowPanel=new JPanel();
		flowPanel.setLayout(new FlowLayout());
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setTitle("KIOSK");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getDefaultCloseOperation();
		
		billInButton=new JButton("入鈔");
		billInALLButton=new JButton("6000個Transaction");
		doorOpenButton=new JButton("主門開啟");
		doorCloseButton=new JButton("主門關閉");
		cashBoxInstallButton=new JButton("鈔盒已安裝");
		cashBoxRemoveButton=new JButton("鈔盒已移除");
		
		flowPanel.add(note1Label);
		flowPanel.add(note1Text);
		flowPanel.add(note2Label);
		flowPanel.add(note2Text);
		flowPanel.add(note3Label);
		flowPanel.add(note3Text);
		flowPanel.add(note4Label);
		flowPanel.add(note4Text);
		flowPanel.add(billInButton);
//		getContentPane().add(billInALLButton);
		flowPanel.add(doorOpenButton);
		flowPanel.add(doorCloseButton);
		flowPanel.add(cashBoxInstallButton);
		flowPanel.add(cashBoxRemoveButton);
		add(flowPanel,BorderLayout.NORTH);
		
//		getContentPane().add(textArea);
		billInButton.addActionListener( this);
		billInALLButton.addActionListener( this);
		doorOpenButton.addActionListener( this);
		doorCloseButton.addActionListener( this);
		cashBoxInstallButton.addActionListener( this);
		cashBoxRemoveButton.addActionListener( this);
		ScrollPane scrollPane=new ScrollPane();
		scrollPane.add(textArea);
		add(scrollPane ,BorderLayout.CENTER);
	}
	

	
	public static void main(String[] argv){
//		KIOSKView view= new KIOSKView();
		
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-applicationContext.xml" );
//		KIOSKView controller=(KIOSKView) applicationContext.getBean("KIOSKView");
		
	}
	
	private EventMeterRequest makeEventMeter(int MNum,int EventId,Date date,int note1,int note2,int note3,int note4,long billIn){
		EventMeterRequest request=new ObjectFactory().createEventMeterRequest();
		request.setBillIn(billIn);
		request.setNote1(note1);
		request.setNote2(note2);
		request.setNote3(note3);
		request.setNote4(note4);
		request.setEventId(EventId);
		request.setMNum(MNum);
		
		GregorianCalendar c = new GregorianCalendar();
		c.setTime(date);
		XMLGregorianCalendar date2=null;
		try {
			date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
		} catch (DatatypeConfigurationException e) {		
			e.printStackTrace();
		}		
		
		request.setEventTime(date2);
		return request;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		EventMeterRequest request=null;
		if(ae.getSource()==billInButton){
			int oneHundred=0;
			int fiveHundred=0;
			int oneThousand=0;
			int twoThousand=0;
			long total=0;
			try{
			 oneHundred=Integer.parseInt(note1Text.getText());
			}catch (Exception e) {
				
			}
			try{
				 fiveHundred=Integer.parseInt(note2Text.getText());
			}catch (Exception e) {
						
			}
				 try{
					 oneThousand=Integer.parseInt(note3Text.getText());
				 }catch (Exception e) {
							
				}
			
				 try{
					 twoThousand=Integer.parseInt(note4Text.getText());
				 }catch (Exception e) {
							
				}
			 
			 total=oneHundred*100 + fiveHundred*500+oneThousand*1000+twoThousand*2000;
			note1+=oneHundred;
			note2+=fiveHundred;
			note3+=oneThousand;
			note4+=twoThousand;
			
			
			billIn+=total;
			request=makeEventMeter(MNum, 79, new Date(), note1, note2, note3, note4, billIn);
			
		}else if(ae.getSource()==doorOpenButton){
			request=makeEventMeter(MNum, 17, new Date(), note1, note2, note3, note4, billIn);
			
		}else if(ae.getSource()==doorCloseButton){
			request=makeEventMeter(MNum, 18, new Date(), note1, note2, note3, note4, billIn);
			
		}else if(ae.getSource()==cashBoxInstallButton){
			request=makeEventMeter(MNum, 28, new Date(), note1, note2, note3, note4, billIn);
			
		}else if(ae.getSource()==cashBoxRemoveButton){
			request=makeEventMeter(MNum, 27, new Date(), note1, note2, note3, note4, billIn);
			
		}
		try{
			EventMeterResult response = (EventMeterResult) eventMeterWSTemplate.marshalSendAndReceive(request);
			if(response.isSuccess()){
				textArea.append("current BillIn:"+String.valueOf(billIn)+"\n");
			}
		}catch(Exception e){
			e.printStackTrace();
			textArea.append("server error\n");
		}
		
	}

	

	









	@Override
	public void onMessage(Message message) {
		 if (message instanceof TextMessage) {

		      try {
		         System.out.println("Received Message-client:["+
		                      ((TextMessage) message).getText()+"]");
		         textArea.append("Received Message:["+
	                      ((TextMessage) message).getText()+"]\n");
		         if("currentMeter".equals(((TextMessage) message).getText())){
		        
			      }
		      } 
		      catch (Exception ex) {    
		         System.out.println("Exception in onMessage " + ex.toString() + "\n" +
		                     ex.getStackTrace());
		         
		         textArea.append("Exception in onMessage " + ex.toString() + "\n" +
	                     ex.getStackTrace()+"\n");
		         
		      }
		     
		    } 
		    else {
		      System.out.println("Message must be of type TextMessage");
		      textArea.append("Message must be of type TextMessage\n");
		    }
		
	}
	
	

	
	public WebServiceTemplate getEventMeterWSTemplate() {
		return eventMeterWSTemplate;
	}



	public void setEventMeterWSTemplate(WebServiceTemplate eventMeterWSTemplate) {
		this.eventMeterWSTemplate = eventMeterWSTemplate;
	}

	
	
}

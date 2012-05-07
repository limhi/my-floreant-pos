package com.floreantpos.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumnModel;

import com.floreantpos.bo.ui.explorer.ListTableModel;
import com.floreantpos.model.Ticket;
import com.floreantpos.model.dao.TicketDAO;

public class TicketListView extends JPanel {
	private JTable table;
	private ListTableModel tableModel;
	private ImagePanel imgpanel;
	private JFrame frame = new JFrame();
	public TicketListView() {
		table = new TicketListTable();
		table.setModel(tableModel = new TicketListTableModel());
		table.setRowHeight(40);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		table.setDefaultRenderer(Object.class, new PosTableRenderer());
		table.setGridColor(Color.LIGHT_GRAY);
		
		TableColumnModel columnModel = table.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(20);
		columnModel.getColumn(1).setPreferredWidth(20);
		columnModel.getColumn(2).setPreferredWidth(200);
		columnModel.getColumn(3).setPreferredWidth(100);
		
		JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		JScrollBar scrollBar = scrollPane.getVerticalScrollBar();
		scrollBar.setPreferredSize(new Dimension(30, 60));
		
		setLayout(new BorderLayout());
		
		add(scrollPane);
	}
	
	public TicketListView(ListTableModel listTableModel) {
		table = new JTable();
		table.setModel(tableModel = listTableModel);
		table.setRowHeight(40);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		table.setDefaultRenderer(Object.class, new PosTableRenderer());
		table.setGridColor(Color.LIGHT_GRAY);
		
		TableColumnModel columnModel = table.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(20);
		columnModel.getColumn(1).setPreferredWidth(20);
		columnModel.getColumn(2).setPreferredWidth(200);
		columnModel.getColumn(3).setPreferredWidth(100);
		
		JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		JScrollBar scrollBar = scrollPane.getVerticalScrollBar();
		scrollBar.setPreferredSize(new Dimension(30, 60));
		
		setLayout(new BorderLayout());
		
		add(scrollPane);
		
		
		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				checkImg(e);
				Point p = new Point(e.getPoint());
			}

			public void mouseReleased(MouseEvent e) {
				checkImg(e);
			}

			private void checkImg(MouseEvent e) {
				int row = table.rowAtPoint(e.getPoint());
				int col = table.columnAtPoint(e.getPoint());
				if (col == 0 && e.getClickCount() == 1) {

					Integer id = (Integer) table
							.getValueAt(table.getSelectedRow(), 0);
					
					Ticket ticket = TicketDAO.getInstance().get(id);
					
				
					imgpanel = new ImagePanel(ticket.getPhotoPath());

					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					frame.setVisible(true);
					frame.setSize(660, 500);
					frame.addMouseListener(new MouseAdapter() {
						public void mousePressed(MouseEvent e) {
							frame.dispose();
						}
					});
					Point p = new Point(e.getPoint());
//					log.debug(e.getPoint().toString());
					if ((int) p.getY() > 245) {
						frame.setLocation(245, 190);
					} else if ((int) p.getY() < 245) {
						frame.setLocation(245, (int) p.getY());
					}

					frame.getContentPane().add(imgpanel);

				} else {
					frame.dispose();
				}
			}
		});
	}
	
	
	public void setTickets(List<Ticket> tickets) {
		tableModel.setRows(tickets);
	}
	
	public void addTicket(Ticket ticket) {
		tableModel.addItem(ticket);
	}
	
	public Ticket getSelectedTicket() {
		int selectedRow = table.getSelectedRow();
		if(selectedRow < 0) {
			return null;
		}
		
		return (Ticket) tableModel.getRowData(selectedRow);
	}
	
	public List<Ticket> getSelectedTickets(){
		int[] selectedRows = table.getSelectedRows();
		
		ArrayList<Ticket> tickets = new ArrayList<Ticket>(selectedRows.length);
		
		for (int i = 0; i < selectedRows.length; i++) {
			Ticket ticket = (Ticket) tableModel.getRowData(selectedRows[i]);
			tickets.add(ticket);
		}
		return tickets;
	}
	
	//public void removeTicket(Ticket ticket) {
		//tableModel.
	//}
	
	private class TicketListTable extends JTable {
		@Override
		public void changeSelection(int rowIndex, int columnIndex, boolean toggle, boolean extend) {
			ListSelectionModel selectionModel = getSelectionModel();
			boolean selected = selectionModel.isSelectedIndex(rowIndex);
			if(selected) {
				selectionModel.removeSelectionInterval(rowIndex, rowIndex);
			}
			else {
				selectionModel.addSelectionInterval(rowIndex, rowIndex);
			}
		}
	}
	

	
	
	public class TicketListTableModel extends ListTableModel {
		public TicketListTableModel() {
			super(new String[] {com.floreantpos.POSConstants.ID, com.floreantpos.POSConstants.TABLE, com.floreantpos.POSConstants.SERVER, com.floreantpos.POSConstants.CREATED, com.floreantpos.POSConstants.TOTAL, com.floreantpos.POSConstants.DUE});
		}
		
		public Object getValueAt(int rowIndex, int columnIndex) {
			Ticket ticket = (Ticket) rows.get(rowIndex);
			
			switch(columnIndex) {
				case 0:
					return Integer.valueOf(ticket.getId());
					
				case 1:
					return Integer.valueOf(ticket.getTableNumber());
					
				case 2:
					return String.valueOf(ticket.getOwner());
					
				case 3:
					return ticket.getCreateDate();
					
				case 4:
					return ticket.getTotalAmount();
					
				case 5:
					return ticket.getDueAmount();
					
			}
			
			return null;
		}
		
	}
	
	public class ShrimpTicketListTableModel extends ListTableModel {
		public ShrimpTicketListTableModel() {
			super(new String[] {com.floreantpos.POSConstants.ID, com.floreantpos.POSConstants.SERVER,  com.floreantpos.POSConstants.CREATED,com.floreantpos.POSConstants.END_DATE, com.floreantpos.POSConstants.TOTAL});
		} 
		
		public Object getValueAt(int rowIndex, int columnIndex) {
			Ticket ticket = (Ticket) rows.get(rowIndex);
			
			switch(columnIndex) {
				case 0:
					return Integer.valueOf(ticket.getId());
					
				case 1:
					return String.valueOf(ticket.getOwner());
					
				case 2:
					return ticket.getStartDate();
					
				case 3:
					return ticket.getEndDate();
					
				case 4:
					return ticket.getTotalAmount();
					
				
					
			}
			
			return null;
		}
		
	}
	
	public class ImagePanel extends JPanel {

		private BufferedImage image;

		public ImagePanel(String file) {
			try {

				image = ImageIO.read(new File(file));
				repaint();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			g.drawImage(image, 0, 0, null); // see javadoc for more info on the
			// parameters

		}
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}
	
	
}

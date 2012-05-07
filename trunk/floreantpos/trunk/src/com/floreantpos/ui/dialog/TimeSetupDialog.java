/*
 * TimeSetupDialog.java
 *
 * Created on Jan 31, 2012, 16:00
 */

package com.floreantpos.ui.dialog;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;

/**
 * 
 * @author Luke Lai
 */
public class TimeSetupDialog extends POSDialog implements ActionListener {
	// private Ticket ticket;

	/** Creates new form VoidTicketDialog */
	public TimeSetupDialog(java.awt.Frame parent, boolean modal) {
		super( parent, modal, true);
		initComponents();
		btnSelectHoursPlus.addActionListener( this);
		btnSelectHoursMinus.addActionListener( this);
		btnStartTimeHPlus.addActionListener( this);
		btnStartTimeHMinus.addActionListener( this);
		btnStartTimeMPlus.addActionListener( this);
		btnStartTimeMMinus.addActionListener( this);
		btnEndTimeHPlus.addActionListener( this);
		btnEndTimeHMinus.addActionListener( this);
		btnEndTimeMPlus.addActionListener( this);
		btnEndTimeMMinus.addActionListener( this);

		setSize( 800, 450);
	}

	// <editor-fold defaultstate="collapsed"
	// desc=" Generated Code ">//GEN-BEGIN:initComponents
	private void initComponents() {
		contentPane = new com.floreantpos.swing.TransparentPanel();
		titlePanel = new com.floreantpos.ui.TitlePanel();

		transparentPanel1 = new com.floreantpos.swing.TransparentPanel();
		jPanel1 = new javax.swing.JPanel();
		lSelectHours = new javax.swing.JLabel();
		lSelectHoursText = new javax.swing.JLabel();
		btnSelectHoursPlus = new com.floreantpos.swing.PosButton();
		btnSelectHoursMinus = new com.floreantpos.swing.PosButton();

		// ticketView = new com.floreantpos.ui.views.order.TicketView();

		transparentPanel2 = new com.floreantpos.swing.TransparentPanel();
		jPanel21 = new javax.swing.JPanel();
		lStartTimeText = new javax.swing.JLabel();
		lEndTimeText = new javax.swing.JLabel();
		lStartTime = new javax.swing.JLabel();
		lEndTime = new javax.swing.JLabel();

		// transparentPanel3 = new com.floreantpos.swing.TransparentPanel();
		jPanel22 = new javax.swing.JPanel();
		lHours = new javax.swing.JLabel();
		lMins = new javax.swing.JLabel();
		btnStartTimeHPlus = new com.floreantpos.swing.PosButton();
		btnStartTimeHMinus = new com.floreantpos.swing.PosButton();
		btnStartTimeMPlus = new com.floreantpos.swing.PosButton();
		btnStartTimeMMinus = new com.floreantpos.swing.PosButton();
		btnEndTimeHPlus = new com.floreantpos.swing.PosButton();
		btnEndTimeHMinus = new com.floreantpos.swing.PosButton();
		btnEndTimeMPlus = new com.floreantpos.swing.PosButton();
		btnEndTimeMMinus = new com.floreantpos.swing.PosButton();

		transparentPanel3 = new com.floreantpos.swing.TransparentPanel();
		jPanel3 = new javax.swing.JPanel();
		jSeparator = new javax.swing.JSeparator();
		btnCancel = new com.floreantpos.swing.PosButton();
		btnOk = new com.floreantpos.swing.PosButton();

		setDefaultCloseOperation( javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

		contentPane.setLayout( new java.awt.BorderLayout());

		titlePanel.setPreferredSize( new java.awt.Dimension( 400, 60));
		// titlePanel.setTitle(com.floreantpos.POSConstants.VOID_TICKET);
		titlePanel.setTitle( "時間設定");
		contentPane.add( titlePanel, java.awt.BorderLayout.NORTH);

		transparentPanel1.setLayout( new java.awt.BorderLayout());
		transparentPanel1.setBorder( javax.swing.BorderFactory.createEmptyBorder( 0, 20, 0, 0));

		jPanel1.setOpaque( false);
		// jPanel1.setLayout(new java.awt.BorderLayout());
		jPanel1.setLayout( new GridLayout( 1, 0));

		selectHours = 1;
		lSelectHours.setText( new Integer( selectHours).toString());
		lSelectHoursText.setText( "小時");
		btnSelectHoursPlus.setIcon( new javax.swing.ImageIcon( getClass().getResource( "/images/up_32.png"))); // NOI18N
		btnSelectHoursPlus.setText( "加");
		btnSelectHoursPlus.setPreferredSize( new java.awt.Dimension( 140, 60));
		btnSelectHoursMinus.setIcon( new javax.swing.ImageIcon( getClass().getResource( "/images/down_32.png"))); // NOI18N
		btnSelectHoursMinus.setText( "減");
		btnSelectHoursMinus.setPreferredSize( new java.awt.Dimension( 140, 60));

		jPanel1.add( lSelectHours);
		jPanel1.add( lSelectHoursText);
		jPanel1.add( btnSelectHoursPlus);
		jPanel1.add( btnSelectHoursMinus);
		// rbHours

		// ticketView.setControlsVisible(false);
		// jPanel1.add(ticketView, java.awt.BorderLayout.CENTER);
		transparentPanel1.add( jPanel1, java.awt.BorderLayout.NORTH);

		transparentPanel2.setPreferredSize( new java.awt.Dimension( 400, 400));
		transparentPanel2.setBorder( javax.swing.BorderFactory.createEmptyBorder( 0, 20, 0, 0));

		jPanel21.setBorder( javax.swing.BorderFactory.createEmptyBorder( 0, 20, 0, 0));
		jPanel21.setOpaque( false);
		jPanel21.setLayout( new GridLayout( 3, 2));
		lStartTimeText.setText( "開始時間");
		lEndTimeText.setText( "結束時間");
		Stime = System.currentTimeMillis();
		time = new Date( Stime);
		lStartTime.setText( dateFormat.format( time));
		//
		Etime=System.currentTimeMillis()+ (1000*60*60);
		time = new Date( Etime);
		lEndTime.setText( dateFormat.format( time));
		SH = SM = EH = EM = 0;
		jPanel21.add( lStartTimeText);
		jPanel21.add( lStartTime);
		jPanel21.add( new JLabel());
		jPanel21.add( new JLabel());
		jPanel21.add( lEndTimeText);
		jPanel21.add( lEndTime);
		transparentPanel2.add( jPanel21, java.awt.BorderLayout.WEST);

		jPanel22.setBorder( javax.swing.BorderFactory.createEmptyBorder( 0, 20, 0, 0));
		jPanel22.setOpaque( false);
		jPanel22.setLayout( new GridLayout( 3, 4));
		// row1
		lHours.setText( "時");
		lMins.setText( "分");
		jPanel22.add( new JLabel());
		jPanel22.add( lHours);
		jPanel22.add( new JLabel());
		jPanel22.add( lMins);
		// row2
		btnStartTimeHPlus.setIcon( new javax.swing.ImageIcon( getClass().getResource( "/images/add_user_32.png"))); // NOI18N
		btnStartTimeHPlus.setText( "加");
		btnStartTimeHPlus.setPreferredSize( new java.awt.Dimension( 140, 60));

		btnStartTimeHMinus.setIcon( new javax.swing.ImageIcon( getClass().getResource( "/images/minus_32.png"))); // NOI18N
		btnStartTimeHMinus.setText( "減");
		btnStartTimeHMinus.setPreferredSize( new java.awt.Dimension( 140, 60));

		btnStartTimeMPlus.setIcon( new javax.swing.ImageIcon( getClass().getResource( "/images/add_user_32.png"))); // NOI18N
		btnStartTimeMPlus.setText( "加");
		btnStartTimeMPlus.setPreferredSize( new java.awt.Dimension( 140, 60));

		btnStartTimeMMinus.setIcon( new javax.swing.ImageIcon( getClass().getResource( "/images/minus_32.png"))); // NOI18N
		btnStartTimeMMinus.setText( "減");
		btnStartTimeMMinus.setPreferredSize( new java.awt.Dimension( 140, 60));

		jPanel22.add( btnStartTimeHPlus);
		jPanel22.add( btnStartTimeHMinus);
		jPanel22.add( btnStartTimeMPlus);
		jPanel22.add( btnStartTimeMMinus);
		// row3
		btnEndTimeHPlus.setIcon( new javax.swing.ImageIcon( getClass().getResource( "/images/add_user_32.png"))); // NOI18N
		btnEndTimeHPlus.setText( "加");
		btnEndTimeHPlus.setPreferredSize( new java.awt.Dimension( 140, 60));

		btnEndTimeHMinus.setIcon( new javax.swing.ImageIcon( getClass().getResource( "/images/minus_32.png"))); // NOI18N
		btnEndTimeHMinus.setText( "減");
		btnEndTimeHMinus.setPreferredSize( new java.awt.Dimension( 140, 60));

		btnEndTimeMPlus.setIcon( new javax.swing.ImageIcon( getClass().getResource( "/images/add_user_32.png"))); // NOI18N
		btnEndTimeMPlus.setText( "加");
		btnEndTimeMPlus.setPreferredSize( new java.awt.Dimension( 140, 60));

		btnEndTimeMMinus.setIcon( new javax.swing.ImageIcon( getClass().getResource( "/images/minus_32.png"))); // NOI18N
		btnEndTimeMMinus.setText( "減");
		btnEndTimeMMinus.setPreferredSize( new java.awt.Dimension( 140, 60));

		jPanel22.add( btnEndTimeHPlus);
		jPanel22.add( btnEndTimeHMinus);
		jPanel22.add( btnEndTimeMPlus);
		jPanel22.add( btnEndTimeMMinus);

		transparentPanel2.add( jPanel22, java.awt.BorderLayout.EAST);
		transparentPanel1.add( transparentPanel2, java.awt.BorderLayout.CENTER);
		contentPane.add( transparentPanel1, java.awt.BorderLayout.CENTER);

		// --transparentPanel3--
		transparentPanel3.add( jSeparator, java.awt.BorderLayout.NORTH);

		jPanel3.setOpaque( false);
		btnOk.setIcon( new javax.swing.ImageIcon( getClass().getResource( "/images/finish_32.png"))); // NOI18N
		btnOk.setText( com.floreantpos.POSConstants.FINISH);
		btnOk.setPreferredSize( new java.awt.Dimension( 140, 60));
		btnOk.addActionListener( new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnOkActionPerformed( evt);
			}
		});
		jPanel3.add( btnOk);

		btnCancel.setIcon( new javax.swing.ImageIcon( getClass().getResource( "/images/cancel_32.png"))); // NOI18N
		btnCancel.setText( com.floreantpos.POSConstants.CANCEL);
		btnCancel.setPreferredSize( new java.awt.Dimension( 140, 60));
		btnCancel.addActionListener( new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnCancelActionPerformed( evt);
			}
		});
		jPanel3.add( btnCancel);
		transparentPanel3.add( jPanel3, java.awt.BorderLayout.CENTER);
		contentPane.add( transparentPanel3, java.awt.BorderLayout.SOUTH);

		getContentPane().add( contentPane, java.awt.BorderLayout.CENTER);

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnCancelActionPerformed
		System.out.println( "btnCancelActionPerformed");
		canceled = true;
		dispose();
	}// GEN-LAST:event_btnCancelActionPerformed

	private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {
		System.out.println( "btnOkActionPerformed");
		canceled = false;
		dispose();
	}

	private void CountTimeLabel() {
		Stime += SH * ( 60 * 60000) + SM * 60000;
		time = new Date( Stime);
		lStartTime.setText( dateFormat.format( time));
		Etime = Stime + selectHours * ( 60 * 60000) + EH * ( 60 * 60000) + EM * 60000;
		time = new Date( Etime);
		lEndTime.setText( dateFormat.format( time));
		SH = SM = 0;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == btnStartTimeHPlus) {
			SH += 1;
		} else if (source == btnStartTimeHMinus) {
			SH -= 1;
		} else if (source == btnStartTimeMPlus) {
			SM += 1;
		} else if (source == btnStartTimeMMinus) {
			SM -= 1;
		} else if (source == btnEndTimeHPlus) {
			EH += 1;
		} else if (source == btnEndTimeHMinus) {
			EH -= 1;
		} else if (source == btnEndTimeMPlus) {
			EM += 1;
		} else if (source == btnEndTimeMMinus) {
			EM -= 1;
		} else if (source == btnSelectHoursPlus) {
			selectHours += 1;
			if (selectHours > 10)
				selectHours = 10;
			lSelectHours.setText( new Integer( selectHours).toString());
		} else if (source == btnSelectHoursMinus) {
			selectHours -= 1;
			if (selectHours < 1)
				selectHours = 1;
			lSelectHours.setText( new Integer( selectHours).toString());
		}

		CountTimeLabel();
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JLabel	                   lSelectHours;
	private javax.swing.JLabel	                   lSelectHoursText;
	private com.floreantpos.swing.PosButton	       btnSelectHoursPlus;
	private com.floreantpos.swing.PosButton	       btnSelectHoursMinus;
	private javax.swing.JLabel	                   lHours;
	private javax.swing.JLabel	                   lMins;
	private javax.swing.JLabel	                   lStartTimeText;
	private javax.swing.JLabel	                   lEndTimeText;
	private javax.swing.JLabel	                   lStartTime;
	private javax.swing.JLabel	                   lEndTime;
	private com.floreantpos.swing.PosButton	       btnStartTimeHPlus;
	private com.floreantpos.swing.PosButton	       btnStartTimeHMinus;
	private com.floreantpos.swing.PosButton	       btnStartTimeMPlus;
	private com.floreantpos.swing.PosButton	       btnStartTimeMMinus;
	private com.floreantpos.swing.PosButton	       btnEndTimeHPlus;
	private com.floreantpos.swing.PosButton	       btnEndTimeHMinus;
	private com.floreantpos.swing.PosButton	       btnEndTimeMPlus;
	private com.floreantpos.swing.PosButton	       btnEndTimeMMinus;
	private com.floreantpos.swing.PosButton	       btnCancel;
	private com.floreantpos.swing.PosButton	       btnOk;

	private com.floreantpos.swing.TransparentPanel	contentPane;
	private javax.swing.JPanel	                   jPanel1;
	private javax.swing.JPanel	                   jPanel21;
	private javax.swing.JPanel	                   jPanel22;
	private javax.swing.JPanel	                   jPanel3;
	private javax.swing.JSeparator	               jSeparator;
	private com.floreantpos.ui.TitlePanel	         titlePanel;
	private com.floreantpos.swing.TransparentPanel	transparentPanel1;
	private com.floreantpos.swing.TransparentPanel	transparentPanel2;
	private com.floreantpos.swing.TransparentPanel	transparentPanel3;

	private DateFormat	                           dateFormat	= new SimpleDateFormat( "HH:mm");
	private Date	                                 time;
	private long	                                 Stime;
	private long	                                 Etime;
	private int	                                   selectHours;
	private int	                                   SH;
	private int	                                   SM;
	private int	                                   EH;
	private int	                                   EM;
	// End of variables declaration//GEN-END:variables
	public long getStime() {
		return Stime;
	}

	public void setStime(long stime) {
		Stime = stime;
	}

	public long getEtime() {
		return Etime;
	}

	public void setEtime(long etime) {
		Etime = etime;
	}
	
	
	

}

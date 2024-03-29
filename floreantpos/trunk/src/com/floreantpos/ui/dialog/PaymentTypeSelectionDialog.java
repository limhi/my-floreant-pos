/*
 * PaymentTypeSelectionDialog.java
 *
 * Created on August 25, 2006, 3:47 PM
 */

package com.floreantpos.ui.dialog;

import com.floreantpos.main.Application;
import com.floreantpos.ui.views.CardPaymentView;
import com.floreantpos.ui.views.PaymentView;
import com.floreantpos.ui.views.payment.CashPaymentView;
import com.floreantpos.ui.views.payment.GiftCertificatePaymentView;

/**
 *
 * @author  MShahriar
 */
public class PaymentTypeSelectionDialog extends POSDialog {
    //Ticket ticket;
    
	private PaymentView selectedPaymentView;
	
    /** Creates new form PaymentTypeSelectionDialog */
    public PaymentTypeSelectionDialog() {
        super(Application.getPosWindow(), true);
        initComponents();
        
        btnBankCheck.setVisible(false);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        titlePanel1 = new com.floreantpos.ui.TitlePanel();
        transparentPanel1 = new com.floreantpos.swing.TransparentPanel();
        transparentPanel2 = new com.floreantpos.swing.TransparentPanel();
        transparentPanel4 = new com.floreantpos.swing.TransparentPanel();
        btnCancel = new com.floreantpos.swing.PosButton();
        jSeparator1 = new javax.swing.JSeparator();
        transparentPanel3 = new com.floreantpos.swing.TransparentPanel();
        btnCredit = new com.floreantpos.swing.PosButton();
        btnCash = new com.floreantpos.swing.PosButton();
        btnGiftCert = new com.floreantpos.swing.PosButton();
        btnDebit = new com.floreantpos.swing.PosButton();
        btnBankCheck = new com.floreantpos.swing.PosButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        titlePanel1.setTitle(com.floreantpos.POSConstants.SELECT_PAYMENT_TYPE);
        getContentPane().add(titlePanel1, java.awt.BorderLayout.NORTH);

        transparentPanel1.setLayout(new java.awt.BorderLayout(5, 5));

        transparentPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        transparentPanel1.setOpaque(true);
        transparentPanel2.setLayout(new java.awt.BorderLayout(5, 5));

        transparentPanel2.setPreferredSize(new java.awt.Dimension(100, 60));
        transparentPanel4.setLayout(new java.awt.GridLayout(1, 0, 5, 5));

        btnCancel.setText(com.floreantpos.POSConstants.CANCEL);
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        transparentPanel4.add(btnCancel);

        transparentPanel2.add(transparentPanel4, java.awt.BorderLayout.CENTER);

        transparentPanel2.add(jSeparator1, java.awt.BorderLayout.NORTH);

        transparentPanel1.add(transparentPanel2, java.awt.BorderLayout.SOUTH);

        transparentPanel3.setLayout(new java.awt.GridLayout(0, 1, 5, 5));

        btnCash.setText(com.floreantpos.POSConstants.CASH_TRANSACTION);
        btnCash.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCashActionPerformed(evt);
            }
        });
        transparentPanel3.add(btnCash);
        
        btnGiftCert.setText(com.floreantpos.POSConstants.GIFT_CERTIFICATE);
        btnGiftCert.addActionListener(new java.awt.event.ActionListener() {
        	public void actionPerformed(java.awt.event.ActionEvent evt) {
        		btnGiftCertActionPerformed();
        	}
        });

		// luke 20120129
		// 隱藏：除現金付款外的其他三種付款方式
		// transparentPanel3.add(btnGiftCert);

        btnCredit.setText(com.floreantpos.POSConstants.CREDIT_CARD_TRANSACTION);
        btnCredit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreditActionPerformed(evt);
            }
        });
		// luke 20120129
		// 隱藏：除現金付款外的其他三種付款方式
		// transparentPanel3.add(btnCredit);

        btnDebit.setText(com.floreantpos.POSConstants.DEBIT_CARD_TRANSACTION);
        btnDebit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDebitActionPerformed(evt);
            }
        });
		// luke 20120129
		// 隱藏：除現金付款外的其他三種付款方式
		// transparentPanel3.add(btnDebit);

        btnBankCheck.setText(com.floreantpos.POSConstants.BANK_CHECK);
        btnBankCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBankCheckActionPerformed(evt);
            }
        });

        transparentPanel3.add(btnBankCheck);

        transparentPanel1.add(transparentPanel3, java.awt.BorderLayout.CENTER);

        getContentPane().add(transparentPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
    	selectedPaymentView = null;
    	canceled = true;
    	dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnBankCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBankCheckActionPerformed
    }//GEN-LAST:event_btnBankCheckActionPerformed

    private void btnDebitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDebitActionPerformed
//    	CardPaymentView paymentView = new CardPaymentView();
//    	paymentView.setCardType(CardPaymentView.CARD_TYPE_DEBIT);
//    	SettleTicketView transactionView = SettleTicketView.getInstance();
//    	transactionView.setPaymentView(paymentView);
//    	transactionView.setCurrentTicket(getTicket());
//    	dispose();
    	
    	CardPaymentView paymentView = new CardPaymentView();
    	paymentView.setCardType(CardPaymentView.CARD_TYPE_DEBIT);
    	selectedPaymentView = paymentView;
    	dispose();
    }//GEN-LAST:event_btnDebitActionPerformed

    private void btnCreditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreditActionPerformed
//    	CardPaymentView paymentView = new CardPaymentView();
//    	paymentView.setCardType(CardPaymentView.CARD_TYPE_CREDIT);
//    	SettleTicketView transactionView = SettleTicketView.getInstance();
//    	transactionView.setPaymentView(paymentView);
//    	transactionView.setCurrentTicket(getTicket());
//    	dispose();
    	
    	CardPaymentView paymentView = new CardPaymentView();
    	paymentView.setCardType(CardPaymentView.CARD_TYPE_CREDIT);
    	selectedPaymentView = paymentView;
    	dispose();
    }//GEN-LAST:event_btnCreditActionPerformed

    private void btnCashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCashActionPerformed
    	CashPaymentView paymentView = new CashPaymentView();
    	selectedPaymentView = paymentView;
    	dispose();
    }//GEN-LAST:event_btnCashActionPerformed
    private void btnGiftCertActionPerformed() {//GEN-FIRST:event_btnCashActionPerformed
    	GiftCertificatePaymentView paymentView = new GiftCertificatePaymentView();
    	selectedPaymentView = paymentView;
    	dispose();
    }//GEN-LAST:event_btnCashActionPerformed
    
    public PaymentView getSelectedPaymentView() {
		return selectedPaymentView;
	}
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.floreantpos.swing.PosButton btnBankCheck;
    private com.floreantpos.swing.PosButton btnCancel;
    private com.floreantpos.swing.PosButton btnCash;
    private com.floreantpos.swing.PosButton btnGiftCert;
    private com.floreantpos.swing.PosButton btnCredit;
    private com.floreantpos.swing.PosButton btnDebit;
    private javax.swing.JSeparator jSeparator1;
    private com.floreantpos.ui.TitlePanel titlePanel1;
    private com.floreantpos.swing.TransparentPanel transparentPanel1;
    private com.floreantpos.swing.TransparentPanel transparentPanel2;
    private com.floreantpos.swing.TransparentPanel transparentPanel3;
    private com.floreantpos.swing.TransparentPanel transparentPanel4;
    // End of variables declaration//GEN-END:variables
}

/*
 * CashDrawerReportDialog.java
 *
 * Created on August 24, 2006, 11:20 PM
 */

package com.floreantpos.ui.dialog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;

import net.miginfocom.swing.MigLayout;

import org.apache.ecs.Document;
import org.apache.ecs.html.BR;
import org.apache.ecs.html.HR;
import org.apache.ecs.html.P;
import org.apache.ecs.html.TD;
import org.apache.ecs.html.TR;
import org.apache.ecs.html.Table;

import com.floreantpos.main.Application;
import com.floreantpos.model.DrawerPullReport;
import com.floreantpos.model.DrawerPullVoidTicketEntry;
import com.floreantpos.model.Terminal;
import com.floreantpos.model.User;
import com.floreantpos.model.dao.TerminalDAO;
import com.floreantpos.print.PosPrintService;
import com.floreantpos.report.services.ReportService;
import com.floreantpos.swing.PosButton;
import com.floreantpos.ui.TitlePanel;

/**
 *
 * @author  MShahriar
 */
public class DrawerPullReportDialog extends POSDialog {
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM, yyyy");
    private DecimalFormat decimalFormat = new DecimalFormat("0.00");
    
    private DrawerPullReport drawerPullReport;
    private Terminal terminal;
    
    public DrawerPullReportDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    
    public void initialize() throws Exception {
    	terminal = Application.getInstance().getTerminal();
    	
    	drawerPullReport = ReportService.buildDrawerPullReport();
    	
    	taReport.setContentType("text/html");
    	taReport.setEditable(false);
    	taReport.setMargin(new Insets(0,10,0,10));
    	taReport.setText(createReport());
    	taReport.setCaretPosition(0);
    	
    	taReport.setPreferredSize(new Dimension(360,100));
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        setLayout(new BorderLayout(5,5));
        
        titlePanel1 = new TitlePanel();
        add(titlePanel1, BorderLayout.NORTH);
        
        taReport = new JEditorPane();
        taReport.setContentType("text/html");
        
        add(new JScrollPane(taReport));
        
        JPanel buttonPanel = new JPanel(new MigLayout("fill","","[fill, grow][]"));
        
        buttonPanel.add(new JSeparator(), "grow,span,wrap");
        buttonPanel.add(btnPrint = new PosButton(com.floreantpos.POSConstants.PRINT), "w 120, h 50, grow");
        buttonPanel.add(btnResetCashDrawer = new PosButton(com.floreantpos.POSConstants.RESET_DRAWER), "w 120, h 50, grow");
        buttonPanel.add(btnFinish = new PosButton(com.floreantpos.POSConstants.CLOSE), "w 120, h 50, grow");
        
        add(buttonPanel, BorderLayout.SOUTH);
        
        btnFinish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnFinishActionPerformed();
			}
        });
        btnResetCashDrawer.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnResetCashDrawerActionPerformed();
        	}
        });
        btnPrint.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					PosPrintService.printDrawerPullReport(drawerPullReport, terminal);
				} catch (Exception ex) {
					POSMessageDialog.showError(DrawerPullReportDialog.this, "Error while printing\n" + ex.getMessage());
					ex.printStackTrace();
				}
				
			}
        	
        });
    }// </editor-fold>//GEN-END:initComponents

    private void btnResetCashDrawerActionPerformed() {//GEN-FIRST:event_btnResetCashDrawerActionPerformed
    	int option = JOptionPane.showOptionDialog(this, "Sure reset cash drawer?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
    	if(option != JOptionPane.YES_OPTION) return;
    	
    	Application app = Application.getInstance();
    	Terminal terminal = app.getTerminal();
    	User user = Application.getCurrentUser();
    	
    	TerminalDAO dao = new TerminalDAO();
    	try {
			dao.resetCashDrawer(drawerPullReport, terminal, user);
		} catch (Exception e) {
			POSMessageDialog.showError("Cannot save", e);
		}
    }//GEN-LAST:event_btnResetCashDrawerActionPerformed

    private void btnFinishActionPerformed() {//GEN-FIRST:event_btnFinishActionPerformed
        dispose();
    }//GEN-LAST:event_btnFinishActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DrawerPullReportDialog(new javax.swing.JFrame(), true).setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.floreantpos.swing.PosButton btnFinish;
    private com.floreantpos.swing.PosButton btnPrint;
    private com.floreantpos.swing.PosButton btnResetCashDrawer;
    private javax.swing.JEditorPane taReport;
    private com.floreantpos.ui.TitlePanel titlePanel1;
    // End of variables declaration//GEN-END:variables
    
    void createReportHeader(Document document) {
    	P p = new P();
    	//p.addElement(new Font().addAttribute("size", "7"));
		p.addAttribute("align", "center");
		p.addElement("===================================");
		p.addElement(new BR());
		p.addElement("DRAWER PULL");
		p.addElement(new BR());
		p.addElement("===================================");
		document.appendBody(p);
	}
	
	void createSectionHeader(Document document, String headerText) {
		P p = new P();
		p.addAttribute("align", "center");
		p.addElement(headerText);
		p.addElement(new HR());
		document.appendBody(p);
	}
	
	void addSeparator(Document document) {
		P p = new P();
		HR hr = new HR();
		hr.addAttribute("style", "border: dashed;");
		p.addElement(hr);
		document.appendBody(p);
	}
	
	void addTableSeparator(Table table) {
		TR tr = new TR();
		TD td = new TD();
		td.addAttribute("colspan", 2);
		td.addAttribute("align", "right");
		HR hr = new HR();
		hr.addAttribute("style", "border: dashed;");
		td.addElement(hr);
		tr.addElement(td);
		table.addElement(tr);
	}
	
	void addExceptionTableSeparator(Table table) {
		TR tr = new TR();
		TD td = new TD();
		td.addAttribute("colspan", 5);
		td.addAttribute("align", "right");
		HR hr = new HR();
		hr.addAttribute("style", "border: dashed;");
		td.addElement(hr);
		tr.addElement(td);
		table.addElement(tr);
	}
	
	void addTableRow(Table table, String column1, String coulmn2) {
		TR tr = new TR();
		tr.addElement(new TD().addElement(column1));
	    tr.addElement(new TD().addAttribute("align", "right").addElementToRegistry(coulmn2));
	    table.addElement(tr);
	}
	
	void addDiscountTableRow(Table table, String column1, String coulmn2) {
		TR tr = new TR();
		tr.addElement(new TD().addAttribute("style", "padding-left: 50px;").addElementToRegistry(column1));
		tr.addElement(new TD().addAttribute("align", "right").addElementToRegistry(coulmn2));
		table.addElement(tr);
	}
	
	void addExceptionTableRow(Table table, String column1, String coulmn2, String coulmn3, String coulmn4, String column5) {
		TR tr = new TR();
		TD td = new TD();
		td.addAttribute("valign", "top");
		td.addElement(column1);
		tr.addElement(td);
		
		td = new TD();
		td.addAttribute("valign", "top");
		td.addElement(coulmn2);
		tr.addElement(td);
		
		td = new TD();
		td.addAttribute("valign", "top");
		td.addAttribute("align", "right");
		td.addElement(coulmn3);
		tr.addElement(td);
		
		td = new TD();
		td.addAttribute("valign", "top");
		td.addAttribute("align", "right");
		td.addElement(coulmn4);
		tr.addElement(td);
		
		td = new TD();
		td.addAttribute("valign", "top");
		td.addAttribute("align", "right");
		td.addElement(column5);
		tr.addElement(td);
		
		table.addElement(tr);
	}
	
	public String createReport() throws Exception {
		Document document = new Document();
		
		Table table = null;
		
    	createReportHeader(document);
    	
    	P p = new P();
		p.addElement("Time: " + dateFormat.format(new Date()));
		document.appendBody(p);
    	
    	
    	createSectionHeader(document, " SALES BALANCE ");
    	table = new Table();
		table.addAttribute("width", "100%");
		addTableRow(table, "&nbsp;NET SALES", decimalFormat.format(drawerPullReport.getNetSales()));
		addTableRow(table, "+SALES TAX", decimalFormat.format(drawerPullReport.getSalesTax()));
		addTableRow(table, "=TOTAL REVENUES", decimalFormat.format(drawerPullReport.getTotalRevenue()));
		addTableRow(table, "+CHARGED TIPS", decimalFormat.format(drawerPullReport.getChargedTips()));
		addTableSeparator(table);
		addTableRow(table, "=GROSS RECEIPTS", decimalFormat.format(drawerPullReport.getGrossReceipts()));
		document.appendBody(table);
		
		document.appendBody(new BR());
		
		table = new Table();
		table.addAttribute("width", "100%");
		addTableRow(table, "-CASH RECEIPTS (" + drawerPullReport.getCashReceiptNumber() + ")", decimalFormat.format(drawerPullReport.getCashReceiptAmount()));
		addTableRow(table, "-CREDIT CARDS  (" + drawerPullReport.getCreditCardReceiptNumber() + ")", decimalFormat.format(drawerPullReport.getCreditCardReceiptAmount()));
		addTableRow(table, "-DEBIT CARDS   (" + drawerPullReport.getDebitCardReceiptNumber() + ")", decimalFormat.format(drawerPullReport.getDebitCardReceiptAmount()));
		addTableRow(table, "-GIFT RETURNS  (" + drawerPullReport.getGiftCertReturnCount() + ")", decimalFormat.format(drawerPullReport.getGiftCertReturnAmount()));
		addTableRow(table, "+GIFT CERT. CHANGE", decimalFormat.format(drawerPullReport.getGiftCertChangeAmount()));
		addTableRow(table, "+CASH BACK", decimalFormat.format(drawerPullReport.getCashBack()));
		addTableSeparator(table);
		addTableRow(table, "=RECEIPT DIFFERENTIAL", decimalFormat.format(drawerPullReport.getReceiptDifferential()));
		document.appendBody(table);
		
		document.appendBody(new BR());
		
		table = new Table();
		table.addAttribute("width", "100%");
		addTableRow(table, "+CHARGED TIPS", decimalFormat.format(drawerPullReport.getChargedTips()));
		addTableRow(table, "-TIPS PAID", decimalFormat.format(drawerPullReport.getTipsPaid()));
		addTableSeparator(table);
		addTableRow(table, "=TIPS DIFFERENTIAL", decimalFormat.format(drawerPullReport.getTipsDifferential()));
		document.appendBody(table);
		
		document.appendBody(new BR());
		
		createSectionHeader(document, " CASH BALANCE ");
		table = new Table();
		table.addAttribute("width", "100%");
		addTableRow(table, "CASH  (" + drawerPullReport.getCashReceiptNumber() + ")", decimalFormat.format(drawerPullReport.getCashReceiptAmount()));
		//addTableRow(table, "CASH TAX", decimalFormat.format(drawerPullReport.getCashTax()));
		addTableRow(table, "-TIPS PAID", decimalFormat.format(drawerPullReport.getTipsPaid()));
		addTableRow(table, "-PAY OUT       (" + drawerPullReport.getPayOutNumber() + ")", decimalFormat.format(drawerPullReport.getPayOutAmount()));
		addTableRow(table, "-CASH BACK", decimalFormat.format(drawerPullReport.getCashBack()));
		addTableRow(table, "+BEGIN CASH", decimalFormat.format(terminal.getOpeningBalance()));
		addTableRow(table, "-DRAWER BLEED  (" + drawerPullReport.getDrawerBleedNumber() + ")", decimalFormat.format(drawerPullReport.getDrawerBleedAmount()));
		addTableSeparator(table);
		addTableRow(table, "=DRAWER ACCOUNTABLE", decimalFormat.format(drawerPullReport.getDrawerAccountable()));
		addTableRow(table, ">CASH TO DEPOSIT", decimalFormat.format(drawerPullReport.getCashToDeposit()));
		document.appendBody(table);
		
		createSectionHeader(document, "=== EXCEPTIONS ===");
		createSectionHeader(document, "=== VOIDS/REFUNDS (Without Tax) ===");
		table = new Table();
		table.addAttribute("width", "100%");
		addExceptionTableRow(table, "CODE", "REASON", "WAST", "QTY", "AMOUNT");
		addExceptionTableSeparator(table);
		
		//CONDITIONAL
		Set<DrawerPullVoidTicketEntry> voidTickets = drawerPullReport.getVoidTickets();
		if(voidTickets != null) {
			for (DrawerPullVoidTicketEntry entry : voidTickets) {
				addExceptionTableRow(table, String.valueOf(entry.getCode()), entry.getReason(), " ", String.valueOf(entry.getQuantity()), Application.formatNumber(entry.getAmount()));
			}
		}
		addExceptionTableSeparator(table);
		document.appendBody(table);
		
		table = new Table();
		table.addAttribute("width", "100%");
		addTableRow(table, "TOTAL VOIDS W/WST", decimalFormat.format(drawerPullReport.getTotalVoidWst()));
		addTableRow(table, "TOTAL VOIDS", decimalFormat.format(drawerPullReport.getTotalVoid()));
		
		document.appendBody(table);
		
		createSectionHeader(document, "=== DISCOUNTS/COUPONS ===");
		table = new Table();
		document.appendBody(table);
		table.addAttribute("width", "100%");
		addTableRow(table, "TOTAL DISCOUNT/COUPON", "");
		addDiscountTableRow(table, "TOTAL COUNT", String.valueOf(drawerPullReport.getTotalDiscountCount()));
		addDiscountTableRow(table, "TOTAL Dsct", Application.formatNumber(drawerPullReport.getTotalDiscountAmount()));
		addDiscountTableRow(table, "TOTAL Sales", Application.formatNumber(drawerPullReport.getTotalDiscountSales()));
		addDiscountTableRow(table, "TOTAL Guest", String.valueOf(drawerPullReport.getTotalDiscountGuest()));
		addDiscountTableRow(table, "Party Size", String.valueOf(drawerPullReport.getTotalDiscountPartySize()));
		addDiscountTableRow(table, "Check Size", String.valueOf(drawerPullReport.getTotalDiscountCheckSize()));
		addDiscountTableRow(table, "Count [%]", String.valueOf(" "));
		addDiscountTableRow(table, "Ration", String.valueOf(" "));
		
		return document.toString();
	}
	
	public void setTitle(String title) {
		titlePanel1.setTitle(title);
		super.setTitle(title);
	}
}

/*
 * ExplorerButtonPanel.java
 *
 * Created on August 24, 2006, 7:35 PM
 */

package com.floreantpos.bo.ui.explorer;

import javax.swing.JButton;

import com.floreantpos.swing.TransparentPanel;

/**
 *
 * @author  rajib
 */
public class ExplorerButtonPanel extends TransparentPanel {

	private JButton editButton, addButton, deleteButton;

	/** Creates new form ExplorerButtonPanel */
	public ExplorerButtonPanel() {
		initComponents();
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	// <editor-fold defaultstate="collapsed" desc=" Generated Code ">                          
	private void initComponents() {

		//TransparentPanel panel = new TransparentPanel();
		editButton = new JButton();
		addButton = new JButton();
		deleteButton =new JButton();

		editButton.setText(com.floreantpos.POSConstants.EDIT);
		addButton.setText(com.floreantpos.POSConstants.ADD);
		deleteButton.setText(com.floreantpos.POSConstants.DELETE);
	}

	public JButton getAddButton() {
		return addButton;
	}

	public JButton getDeleteButton() {
		return deleteButton;
	}

	public JButton getEditButton() {
		return editButton;
	}

	// </editor-fold>    
	

	// Variables declaration - do not modify                     
	// End of variables declaration                   

}

/*
 * ModofierGroupEditor.java
 *
 * Created on August 4, 2006, 12:28 AM
 */

package com.floreantpos.ui.model;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import com.floreantpos.model.MenuModifierGroup;
import com.floreantpos.model.dao.ModifierDAO;
import com.floreantpos.model.dao.ModifierGroupDAO;
import com.floreantpos.swing.FixedLengthDocument;
import com.floreantpos.swing.MessageDialog;
import com.floreantpos.ui.BeanEditor;
import com.floreantpos.util.POSUtil;

/**
 *
 * @author  MShahriar
 */
public class MenuModifierGroupForm extends BeanEditor {

	/** Creates new form ModofierGroupEditor */
	public MenuModifierGroupForm() throws Exception {
		this(new MenuModifierGroup());
	}

	public MenuModifierGroupForm(MenuModifierGroup group) throws Exception {
		initComponents();
		
		tfName.setDocument(new FixedLengthDocument(60));
		
		setBean(group);
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        tfName = new com.floreantpos.swing.FixedLengthTextField();

        jLabel1.setText(com.floreantpos.POSConstants.NAME + ":");

        tfName.setText("fixedLengthTextField1");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(tfName, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(tfName, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private com.floreantpos.swing.FixedLengthTextField tfName;
    // End of variables declaration//GEN-END:variables
	@Override
	public boolean save() {
		try {
			if(!updateModel()) return false;
			
			MenuModifierGroup group = (MenuModifierGroup) getBean();

			ModifierGroupDAO dao = new ModifierGroupDAO();
			dao.saveOrUpdate(group);
		} catch (Exception e) {
			MessageDialog.showError(e);
			return false;
		}
		return true;
	}

	@Override
	public void dispose() {
	}

	@Override
	protected void updateView() {
		MenuModifierGroup group = (MenuModifierGroup) getBean();
		
		if(group.getId() != null && !Hibernate.isInitialized(group.getModifiers())) {
			ModifierDAO dao = new ModifierDAO();
			Session session = dao.getSession();
			group = (MenuModifierGroup) session.merge(group);
			Hibernate.initialize(group.getModifiers());
			session.close();
		}

		tfName.setText(group.getName());
//		if(group.getId() == null) {
//			chkEnable.setSelected(true);
//		}
//		else {
//			chkEnable.setSelected(group.isEnable());
//		}
	}

	@Override
	protected boolean updateModel() {
		MenuModifierGroup group = (MenuModifierGroup) getBean();

		String name = tfName.getText();
    	if(POSUtil.isBlankOrNull(name)) {
    		MessageDialog.showError(com.floreantpos.POSConstants.NAME_REQUIRED);
    		return false;
    	}
    	
		group.setName(name);
//		group.setEnable(chkEnable.isSelected());
		
		return true;
	}

	public String getDisplayText() {
    	MenuModifierGroup modifierGroup = (MenuModifierGroup) getBean();
    	if(modifierGroup.getId() == null) {
    		return com.floreantpos.POSConstants.NEW_MODIFIER_GROUP;
    	}
    	return com.floreantpos.POSConstants.EDIT_MODIFIER_GROUP;
    }
}
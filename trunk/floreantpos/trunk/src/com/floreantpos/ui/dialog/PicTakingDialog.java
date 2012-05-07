package com.floreantpos.ui.dialog;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;

import javax.media.control.FrameGrabbingControl;
import javax.media.format.VideoFormat;
import javax.media.util.BufferToImage;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;

import net.miginfocom.swing.MigLayout;

import com.floreantpos.POSConstants;
import com.floreantpos.swing.PosButton;
import com.floreantpos.ui.TitlePanel;
import com.floreantpos.ui.webcam.CameraCapture;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class PicTakingDialog extends POSDialog implements ActionListener {
	
	
	private TitlePanel titlePanel;
	private CameraCapture camera;
	PosButton okButton=null;
	PosButton cancelButton=null;
	PosButton picButton=null;
	String photoPath=null;
	public PicTakingDialog() {
		setResizable(false);
		
		Container contentPane = getContentPane();

		MigLayout layout = new MigLayout("fillx", "[60][60][60]", "");
		contentPane.setLayout(layout);

		titlePanel = new TitlePanel();
		contentPane.add(titlePanel, "span, grow, wrap, height 60");
		
			camera= new CameraCapture();
			
			PosButton posButton1 = new PosButton(POSConstants.TAKE_A_PICTURE);
			
			posButton1.addActionListener(this);
			
			contentPane.add(posButton1, "span, grow, wrap, height 60");
		contentPane.add(camera, "span, grow, wrap, height 370,width 1000");
		
		contentPane.add(new JSeparator(), "newline, grow, span, gaptop 20");

		okButton= new PosButton(POSConstants.OK);
		okButton.setEnabled(false);
		okButton.addActionListener(this);
		contentPane.add(okButton, "skip 1, grow, height 55");

		cancelButton = new PosButton(POSConstants.CANCEL);
		cancelButton.addActionListener(this);
		contentPane.add(cancelButton, "grow, height 55");
		
		

	}
	
	
	
	
	
	
	

	public void setTitle(String title) {
		titlePanel.setTitle(title);
		
		super.setTitle(title);
	}


	
	private void doCancel() {
		setCanceled(true);
		dispose();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		
		if(POSConstants.CANCEL.equalsIgnoreCase(actionCommand)) {
			doCancel();
		}
		else if(POSConstants.OK.equalsIgnoreCase(actionCommand)) {
				setCanceled(false);
				photoPath=camera.photoPath;
				dispose();
		}else if(POSConstants.TAKE_A_PICTURE.equalsIgnoreCase(actionCommand)) {

			camera.processPic();
			okButton.setEnabled(true);
		}
		
		
	}








	public String getPhotoPath() {
		return photoPath;
	}








	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}
	
	

	
}

package com.floreantpos.ui.webcam;

import java.awt.Graphics; 
import java.awt.Image;
import java.awt.Panel;

class ImagePanel extends Panel {
		public Image myimg = null;

		public ImagePanel() {
			setLayout(null);
			setSize(500, 200);
		}

		public void setImage(Image img) {
			this.myimg = img;
			repaint();
		}

		@Override
		public void paint(Graphics g) {
			if (myimg != null) {
				g.drawImage(myimg, 0, 0, this);
			}
		}
	}
	
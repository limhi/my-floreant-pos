package com.floreantpos.ui.webcam;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Transparency;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.util.UUID;

import javax.media.Buffer;
import javax.media.MediaLocator;
import javax.media.Player;
import javax.media.control.FrameGrabbingControl;
import javax.media.format.VideoFormat;
import javax.media.util.BufferToImage;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import com.floreantpos.main.Application;

public class CameraCapture extends Panel {
	public Buffer buf = null;
	
	public VideoFormat vf = null;
	public BufferToImage btoi = null;
		
		public Image img = null;
		public static Player player = null;
		
		public MediaLocator ml = null;
		
		public ImagePanel imgpanel = null;
		
		public String photoPath = null;
		public void processPic(){
			// Grab a frame
			FrameGrabbingControl fgc = (FrameGrabbingControl) player
					.getControl("javax.media.control.FrameGrabbingControl");
			buf = fgc.grabFrame();

			// Convert it to an image
			btoi = new BufferToImage((VideoFormat) buf.getFormat());
			img = btoi.createImage(buf);

			// show the image
			
			BufferedImage reSizeImage = reduce(toBufferedImage(img),500);
//			ByteArrayOutputStream   bs   =new   ByteArrayOutputStream(); 
//			ImageOutputStream   imOut   = ImageIO.createImageOutputStream(bs); 
//			ImageIO.write(reSizeImage, "jpg",imOut);
//			bs.writeTo(out);
//			bs.close();
			
			imgpanel.setImage(reSizeImage);
//			imgpanel.setImage(img.getScaledInstance(500, 400, Image.SCALE_DEFAULT));

			// save image
//			saveJPG(img, "c:\\test.jpg");
						UUID uuid = UUID.randomUUID();
						photoPath= "c:\\shrimping\\" + uuid.toString() + ".jpg";
						saveJPG(img, photoPath);
						
		}
	


		public CameraCapture() {
			setLayout(new BorderLayout());
//			setSize(1000, 400);

			imgpanel = new ImagePanel();
	
//			String str1 = "vfw:Logitech USB Video Camera:0";
//			String str2 = "vfw:Microsoft WDM Image Capture (Win32):0";
//			di = CaptureDeviceManager.getDevice(str2);
			// ml = di.getLocator();
			
			try {
				ml = Application.getInstance().getMl();
//				player = Manager.createRealizedPlayer(ml);
				player= Application.getInstance().getPlayer();
				player.start();
				Component comp;

				if ((comp = player.getVisualComponent()) != null) {
					
					add(comp, BorderLayout.CENTER);
				}
				
				add(imgpanel, BorderLayout.EAST);
				
			} catch (Exception e) {
				e.printStackTrace();
				removeAll();
				JLabel label=new JLabel("無法開啟攝影機");
				add(label, BorderLayout.CENTER);
			}
		}
		
		public static void saveJPG(Image img, String s) {
			BufferedImage bi = new BufferedImage(img.getWidth(null), img
					.getHeight(null), BufferedImage.TYPE_INT_RGB);
			Graphics2D g2 = bi.createGraphics();
			g2.drawImage(img, null, null);

			FileOutputStream out = null;
			try {
				out = new FileOutputStream(s);
			} catch (java.io.FileNotFoundException io) {
				System.out.println("File Not Found");
			}

			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bi);
			param.setQuality(0.5f, false);
			encoder.setJPEGEncodeParam(param);

			try {
				encoder.encode(bi);
				out.close();
			} catch (java.io.IOException io) {
				System.out.println("IOException");
			}
		}

		
		//圖片等例壓縮
		private BufferedImage reduce(final BufferedImage original, int maxSize) {
			Double scale=1.0;
			Double maxSized=new Double(maxSize);

			if (original.getWidth()>original.getHeight()){
//				log.debug("original.getWidth():"+original.getWidth());
				scale=maxSized/original.getWidth();
//				log.debug("scale:"+scale);
			} else {
				scale=maxSized/original.getHeight();	
//				log.debug("scale:"+scale);
			}
			if (scale==0) scale=1.0;		
		    return this.reduce(original,scale);  
		}	
		
		
		//圖片比例壓縮
		private BufferedImage reduce(final BufferedImage original, double scale) {  
		    final int w = new Double(original.getWidth() * scale).intValue();  
		    final int h = new Double(original.getHeight() * scale).intValue();  
		    final Image rescaled = original.getScaledInstance(w, h,  
		            Image.SCALE_AREA_AVERAGING);  
		    final BufferedImage result = new BufferedImage(w, h,  
		            BufferedImage.TYPE_INT_RGB);  
		    final Graphics2D g = result.createGraphics();  
		    g.drawImage(rescaled, 0, 0, null);  
		    g.dispose();  
		    return result;
		}
		
		public static BufferedImage toBufferedImage(Image image) {
		    if (image instanceof BufferedImage) {
		        return (BufferedImage)image;
		    }

		    // This code ensures that all the pixels in the image are loaded
		    image = new ImageIcon(image).getImage();

		    // Determine if the image has transparent pixels; for this method's
		    // implementation, see Determining If an Image Has Transparent Pixels
		    boolean hasAlpha = false;

		    // Create a buffered image with a format that's compatible with the screen
		    BufferedImage bimage = null;
		    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		    try {
		        // Determine the type of transparency of the new buffered image
		        int transparency = Transparency.OPAQUE;
		        if (hasAlpha) {
		            transparency = Transparency.BITMASK;
		        }

		        // Create the buffered image
		        GraphicsDevice gs = ge.getDefaultScreenDevice();
		        GraphicsConfiguration gc = gs.getDefaultConfiguration();
		        bimage = gc.createCompatibleImage(
		            image.getWidth(null), image.getHeight(null), transparency);
		    } catch (HeadlessException e) {
		        // The system does not have a screen
		    }

		    if (bimage == null) {
		        // Create a buffered image using the default color model
		        int type = BufferedImage.TYPE_INT_RGB;
		        if (hasAlpha) {
		            type = BufferedImage.TYPE_INT_ARGB;
		        }
		        bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), type);
		    }

		    // Copy image to buffered image
		    Graphics g = bimage.createGraphics();

		    // Paint the image onto the buffered image
		    g.drawImage(image, 0, 0, null);
		    g.dispose();

		    return bimage;
		}
	}
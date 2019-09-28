import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageSavingThread implements Runnable{
	File imageIndex = new File("1.png");
	
	BufferedImage[] bis = new BufferedImage[100];
	int currentIndex = 0;
	int currentProcIndex = 0;

	@Override
	public void run() {
		int num = 1;
		while(imageIndex.exists()) {
			imageIndex = new File(String.valueOf(num)+".png");
			num++;
		}		
		while(Main.running && ((Main.tempRun && mainThread.imagesSavedThisSession < 10)||!Main.tempRun)) {
			if(bis[currentProcIndex] != null) {
				try {
					ImageIO.write(bis[currentProcIndex], "png", imageIndex);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				imageIndex = new File(String.valueOf(num)+".png");
				num++;
				
				
				
				bis[currentProcIndex] = null;
				currentProcIndex = (currentProcIndex + 1) % bis.length;
			}
			
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void addImage(Image i) {

		BufferedImage bi = imageToBufferedImage(i);
		bis[currentIndex] = bi;
		currentIndex= (currentIndex + 1) % bis.length;
	}
	

	
	  public static BufferedImage imageToBufferedImage(Image im) {
		     BufferedImage bi = new BufferedImage
		        (im.getWidth(null),im.getHeight(null),BufferedImage.TYPE_INT_RGB);
		     Graphics bg = bi.getGraphics();
		     bg.drawImage(im, 0, 0, null);
		     bg.dispose();
		     return bi;
		  }

}

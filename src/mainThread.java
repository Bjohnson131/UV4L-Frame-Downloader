import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

public class mainThread implements Runnable{
	Image i;
	public static int imagesSavedThisSession = 0;
	
	@Override
	public void run() {
		URL imageURL = null;
		try {
		imageURL = new URL("http://"+Main.ip+":"+Main.port+"/stream/snapshot.jpeg");
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
			return;
		}
		
		ImageSavingThread t = new ImageSavingThread();
		Thread imageSaver = new Thread(t);
		imageSaver.start();
		
		while(Main.running && ((Main.tempRun && imagesSavedThisSession < 10)||!Main.tempRun)) {
			try {
				Thread.sleep(Main.intervalMs);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			try {
				i = ImageIO.read(imageURL);
				t.addImage(i);
				imagesSavedThisSession++;
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}
		
	
}

package ihm.Authentification;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class imagePan extends JPanel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferedImage img;
	
	
	public imagePan() {
		try {
			img= ImageIO.read(new File("C:/Users/lktj/Desktop/img2.jpg"));
		} catch (IOException e) {
			
			e.printStackTrace();
			System.out.println(10);
		}
		this.setPreferredSize(new Dimension(900,700));
	}
	
	protected void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		g.drawImage(img, 0, 0, this.getWidth(),this.getHeight(),this);
//		g.drawImage(img, 0, 0, this);
	}
}

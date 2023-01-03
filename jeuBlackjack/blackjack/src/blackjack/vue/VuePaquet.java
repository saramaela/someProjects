package blackjack.vue;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;  

/**
 * Classe qui gère les spritzs(images)
 *
 */
public abstract class VuePaquet extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public abstract Dimension setSize();
	
	/**
	 * Methode qui transforme un fichier image en BufferedImage
	 * @param fileName fichier image
	 * @return image  BufferedImage
	 * @throws IOException
	 */
	public BufferedImage transformImage(String fileName) throws IOException{
		File file = new File(fileName);
		System.out.println(file.getAbsolutePath());
		FileInputStream fis = new FileInputStream(file);
		BufferedImage image = ImageIO.read(fis); // reading the image file
		return image;	
	}
	
	
}

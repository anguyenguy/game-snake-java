package Snake;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Data {
	 public static BufferedImage sprite; 
	
	
     public static Image imageHead;
     public static Image imageHeadGoLeft;
     public static Image imageHeadGoRight;
     public static Image imageHeadGoDown;
     public static Image imageHeadGoUp;
     
     
     
     public static Image imageBody;
     
     
     public static Image imageWorm;
     public static Image imageWorm1;
     public static Image imageWorm2;
     
     public static void loadImage() {
    	 try {
    		 sprite = ImageIO.read(new File("res/sprite1.png"));
    		 
    		 
    		 imageHead = sprite.getSubimage(2, 3, 30, 30);
    		 imageHeadGoLeft = sprite.getSubimage(75, 3, 30, 30);
    		 imageHeadGoRight = sprite.getSubimage(110, 3, 30, 30);
    		 imageHeadGoDown = sprite.getSubimage(38, 3, 30, 30);
    		 imageHeadGoUp = sprite.getSubimage(145, 3, 30, 30);
    		 
    		 
    		 
    		 imageBody = sprite.getSubimage(6, 79, 20, 20);   		 
    		 
    		 
    		 imageWorm  = sprite.getSubimage(2, 40, 30, 30);
    		 imageWorm1 = sprite.getSubimage(32, 40, 30, 30);
    		 imageWorm2 = sprite.getSubimage(63, 40, 30, 30);
    		 
    		 
    	 }catch(Exception e) {}
    	 
     }
}

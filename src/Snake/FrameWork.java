package Snake;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;

public class FrameWork extends JFrame{
	
	
	   static FrameWork frame;
	   BackGround backGround;
	   static int padding=10;
	   static int WEIGHT=400;
	   static int HIGHT=400;
	   public static ArrayList<Users> users;
	   
	   
	   
       public FrameWork() {
    	   
           this.setSize(595,HIGHT+padding*6);
           
           users = new ArrayList<Users>();
           readData();
           
           this.setDefaultCloseOperation(EXIT_ON_CLOSE);
           backGround = new BackGround();
           add(backGround);   
           this.addKeyListener(new handler());
           this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent arg0) {
				updateData();
			}
        	   
           });
           this.setVisible(true);
           
       }
       
       
       
       
       public static void main(String as[]) {
    	   frame = new FrameWork();
       }
       
       
       private class handler implements KeyListener{

		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()==e.VK_UP) {
				if(backGround.snake.Vector != backGround.snake.GO_DOWN&&backGround.snake.canChange==true )
				backGround.snake.Vector = backGround.snake.GO_UP;
				backGround.snake.canChange=false;
			}
			if(e.getKeyCode()==e.VK_DOWN) {
				if(backGround.snake.Vector != backGround.snake.GO_UP&&backGround.snake.canChange==true )
				backGround.snake.Vector = backGround.snake.GO_DOWN;
				backGround.snake.canChange=false;
			}
			if(e.getKeyCode()==e.VK_LEFT) {
				if(backGround.snake.Vector != backGround.snake.GO_RIGHT&&backGround.snake.canChange==true )
				backGround.snake.Vector = backGround.snake.GO_LEFT;
				backGround.snake.canChange=false;
			}
			if(e.getKeyCode()==e.VK_RIGHT) {
				if(backGround.snake.Vector != backGround.snake.GO_LEFT&&backGround.snake.canChange==true )
				backGround.snake.Vector = backGround.snake.GO_RIGHT;
				backGround.snake.canChange=false;
			}
			if(e.getKeyCode()==e.VK_SPACE) {			
				backGround.isPlaying= -backGround.isPlaying;
				if((backGround.snake.dieCauseWall()==true)||backGround.snake.dieCauseIt()==true){
					backGround.isGameOver=true;
				}
			}
		}

		@Override
		public void keyReleased(KeyEvent arg0) {}

		@Override
		public void keyTyped(KeyEvent arg0) {}
    	   
       }
       
       public static void updateData() {
    	   BufferedWriter bw= null;
    	   try {
			FileWriter fw= new FileWriter("data/data.txt");
			bw = new BufferedWriter(fw);
			for(Users u:users) {
				bw.write(u.getName()+" "+u.getScore());
				bw.newLine();
			}
			
			
		} catch (IOException e) {}
    	   try {
			bw.close();
		} catch (IOException e) {}
       }
       
	   public static void readData() {
	    	   try {
				FileReader fr= new FileReader("data/data.txt");
				BufferedReader br = new BufferedReader(fr);
				
				String line= null;
				while ((line=br.readLine())  != null){
					String[] str = line.split(" ");
					users.add(new Users(str[0],str[1]));
				}
				
				br.close();
			} catch (IOException e) {}
	       }
       
}

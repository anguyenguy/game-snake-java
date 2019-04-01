package Snake;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class BackGround extends JPanel implements Runnable{
	
	
	  Snake snake;
	  Thread thread;
      int isPlaying=-4;
      int currentPause=0;
      boolean isGameOver=false;
	  
	  
      public BackGround() {
    	  snake = new Snake();
          add(snake);
    	  thread = new Thread(this);
    	  thread.start();   	  
  
      }
   
      public void run() {
    	  
    	  while(true) {
    		  if(isPlaying==Snake.PLAY) {
    		        while(snake.testFood() == true) {
    			       snake.createFood();
    		        } 
    	  		    snake.runSnake(snake.Vector);
    	  		    snake.eatFood();
   	          }
    		  repaint();

    	  }
      }	  
  
      public void paint(Graphics g) {  	
    	  currentPause++;
    	  if(currentPause==200)  currentPause=0;
    	  paintShape(g);
    	  paintBackGround(g);    	  
  		  snake.paintSnake(g);
  		  snake.paintFood(g); 
  		  paintScore(g);
  		  paintUsers(g);
  		  
  		  if((snake.dieCauseWall()==true)||snake.dieCauseIt()==true) {
  			  isPlaying=-Snake.PLAY;
  			  if(currentPause<100)
    			  paintOver(g); 
  			  if(isGameOver) { isGameOver=false;       snake.reSnake();}
		  } 
  		  
  		  if(isPlaying==-Snake.PLAY) {
  			  if(currentPause<100)
  			  paintPause(g);  
  		  }
      }

      
      public void paintBackGround(Graphics g) {
    	  g.setColor(Color.LIGHT_GRAY);
    	  g.fillRect(2, 2, FrameWork.WEIGHT+FrameWork.padding*2, FrameWork.HIGHT+FrameWork.padding*2);
      }
      
      
      
      public void paintOver(Graphics g) {
    	  g.setColor(Color.YELLOW);
    	  g.setFont(g.getFont().deriveFont(32.0f));
    	  g.drawString("GAME OVER", 150, 100);
      }
      
      
      private void paintShape(Graphics g) {
    	  g.setColor(Color.green);
    	  g.fillRect(0, 0 , FrameWork.WEIGHT+FrameWork.padding*2+6, FrameWork.HIGHT+FrameWork.padding*2+4);
    	  
      }
      
      
      public void paintPause(Graphics g) {
    	  g.setColor(Color.WHITE);
    	  g.setFont(g.getFont().deriveFont(20.0f));
    	  g.drawString("PRESS SPACE TO PLAY", 100, 220);
      }
      
      public void paintScore(Graphics g) {
    	  g.setColor(Color.WHITE);
    	  g.fillRect(425, 0, 150, 460);
    	  g.setColor(Color.orange);
    	  g.fillRect(425, 0, 150, 150);
    	  g.setColor(Color.black);
    	  g.setFont(g.getFont().deriveFont(18.0f));
    	  g.drawString("SCORE", 430, 15);
    	  g.setFont(g.getFont().deriveFont(50.0f));
    	  g.drawString(""+snake.score, 450, 65);
    	  g.setColor(Color.GREEN);
    	  g.fillRect(425, 150, 150, 150);
    	  g.setColor(Color.black);
    	  g.setFont(g.getFont().deriveFont(18.0f));
    	  g.drawString("LEVEL", 430, 170);
    	  g.setFont(g.getFont().deriveFont(50.0f));
    	  g.drawString(""+snake.level, 450, 220);
    	  	  
      }
      
      
      public void paintUsers(Graphics g) {
    	  g.setColor(Color.black);
    	  g.setFont(g.getFont().deriveFont(18.0f));
    	  for(int i=0; i<FrameWork.users.size();i++) {
    		  g.drawString(""+FrameWork.users.get(i), 425, 320+i*20);
    	  }
      }
      
      
      

      
      
 
      
      
}

package Snake;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Snake extends JPanel{
	
	
	    int [] x;
	    int [] y;
	    int length;
        long time = System.currentTimeMillis();
        static int GO_UP     =  1;
        static int GO_DOWN   = -1;
        static int GO_LEFT   =  2;
        static int GO_RIGHT  = -2; 
        static int PLAY      =  4;
	    public int Vector;
	    int foodx;
	    int foody;
		int currentHead =0;
	    int currentWorm =0;
	    int level=1;
	    int speed = 300;
	    int supLevel=0;
	    boolean canChange=true;
	    int score;
	    
	    
        public Snake() {
        	x= new int[400];
        	y= new int[400];
        	x[0]= 2;
        	y[0]= 2;
        	x[1]= 1;
        	y[1]= 2;
        	x[2]= 0;
        	y[2]= 2;
        	length= 3;
        	score=0;
        	Vector = GO_RIGHT;
        	foodx = (int)(Math.random()*(20+1)+0);
        	foody = (int)(Math.random()*(20+1)+0);
        	
        }
        
        
        public void runSnake( int Vector ) {
        	if(System.currentTimeMillis()-time > speed) {
        	    for(int i=length-1; i>=0;i--) {
            		x[i+1]=x[i];
            		y[i+1]=y[i];
            	}
        	    if (Vector == GO_RIGHT) {
        		x[0]++; canChange=true;            
        	    }
        	    if (Vector == GO_LEFT) {
        		x[0]--; canChange=true;
        	    }
        	    if (Vector == GO_UP) {
        		y[0]--; canChange=true;
                }        	
        	    if (Vector == GO_DOWN) {
        		y[0]++; canChange=true;
                }
        	    time = System.currentTimeMillis();
        	    }   
        	     	
        }
        
        
        public void paintSnake(Graphics g) {
        	currentHead++;
        	if(currentHead>=200)  currentHead=0;
        	Data.loadImage();
        	for(int i=1; i<length; i++) {
        		g.drawImage(Data.imageBody,20*x[i]+1, 20*y[i]+1, null);
        	}
        	
        	if(Vector==GO_DOWN)
        	g.drawImage(currentHead>=100?Data.imageHead:Data.imageHeadGoDown, 20*x[0]+1-5, 20*y[0]+1-5, null);
        	
        	if(Vector==GO_UP)
            	g.drawImage(currentHead>=100?Data.imageHead:Data.imageHeadGoUp, 20*x[0]+1-5, 20*y[0]+1-5, null);
        	
        	if(Vector==GO_LEFT)        		
            	g.drawImage(currentHead>=100?Data.imageHead:Data.imageHeadGoLeft, 20*x[0]+1-5, 20*y[0]+1-5, null);
        	
        	if(Vector==GO_RIGHT)
            	g.drawImage(currentHead>=100?Data.imageHead:Data.imageHeadGoRight, 20*x[0]+1-5, 20*y[0]+1-5, null);
        	
            			
        }
        
        
        public void createFood() {
        	foodx = (int)(Math.random()*(20+1)+0);
        	foody = (int)(Math.random()*(20+1)+0);
        }
        
        
        public void paintFood(Graphics g) {
        	currentWorm++;
        	if(currentWorm==400) currentWorm = 0;
        	Data.loadImage();
        	if(currentWorm<100)
        		g.drawImage(Data.imageWorm, 20*foodx+1, 20*foody+1-5, null);
        	if(currentWorm<200&&currentWorm>=100)
        		g.drawImage(Data.imageWorm1, 20*foodx+1, 20*foody+1-5, null);
        	if(currentWorm<300&&currentWorm>=200)
        		g.drawImage(Data.imageWorm2, 20*foodx+1, 20*foody+1-5, null);
        	if(currentWorm>300)
        		g.drawImage(Data.imageWorm1, 20*foodx+1, 20*foody+1-5, null);
        	
        }
        
        
        public void eatFood() {
        	if ((x[0]==foodx)&&(y[0]==foody)) {
        		supLevel++;
        		if(level==1) {
        			score=score+5;
        			if(supLevel==3) {
        				supLevel=0;
        				level++;
        				speed= 200;
        			}
        		}
        		if(level==2) {
        			score=score+15;
        			if(supLevel==5) {
        				supLevel=0;
        				level++;
        				speed= 150;
        			}
        		}
        		if(level==3) {
        			score=score+25;
        			if(supLevel==5) {
        				supLevel=0;
        				level++;
        				speed= 110;
        			}
        		}
        		if(level==4) {
        			score=score+35;
        			if(supLevel==5) {
        				supLevel=0;
        				level++;
        				speed= 70;
        			}
        		}
        		if(level==5) {
        			score=score+50;
        			if(supLevel==10) {
        				supLevel=0;
        				level++;
        				speed= 40;
        			}
        		}
        		if(level==6) {
        			score=score+100;
        			if(supLevel==20) {
        				supLevel=0;
        				level++;
        				speed= 30;
        			}
        		}
        		if(level==7) {
        			    score=score+200;
        				speed= 20;
        			
        		}
        		
        		
        	
        		length++;
        		createFood();
        	}  	
        }
        
        
        public boolean dieCauseWall() {
        	if ((x[0]>20)||(x[0]<0)||(y[0]>20)||(y[0]<0))
        		return true;
			return false;
        }
        
        
        public boolean dieCauseIt() {
        	for(int i=0; i<length; i++) {
        		for(int j=0; j<length/2;j++)
        		if((x[i]==x[j])&&(y[i]==y[j])&&(i!=j))
        				return true;        		
        	}
        	return false;
        }
        
        
        public boolean testFood() {
        	for(int i=0; i<length; i++) {
        		if((foodx==x[i])&&(foody==y[i]))
        				return true;        		
        	}
        	return false;        	
        }
        
        public void reSnake() {
        	String name = JOptionPane.showInputDialog("Please! Type your name:");
        	FrameWork.users.add(new Users(name,String.valueOf(score)));
        	for(int i=1; i<length; i++) {
        		x= new int[21];
            	y= new int[21];
        		x[0]= 2;
            	y[0]= 2;
            	x[1]= 1;
            	y[1]= 2;
            	x[2]= 0;
            	y[2]= 2;
            	score=0;
            	level=1;
            	length= 3;
            	speed= 200;
            	Vector = GO_RIGHT;
        	}
        	
        }
}

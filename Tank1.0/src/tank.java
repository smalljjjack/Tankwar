import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class tank {
	private int x ;
	private int y ;
	private Dir dir = Dir.L;
	private static final int TSize = 80;
	private static final int XSPEED = 20;
	private static final int YSPEED = 20;
	
	private boolean bl, bu, br, bd = false;
	
	private bullet b;
	
	private int height = 10;
	private int width = 80;
	
	private enemy e;
	
	private yardClient yard = null;
	
	public boolean Alive = true;

	public static int blood = 10;
	
	public List<bullet> bullets = new ArrayList<bullet>();
	
	public tank(int x, int y, Dir dir){
		this.x = x;
		this.y = y;
		this.dir = dir;
	}
	
	public void draw(Graphics g){
		Color c = g.getColor();
		g.setColor(Color.green);
		g.fillOval(x, y, TSize, TSize);
		Graphics2D g2=(Graphics2D) g;  
	     
	    Rectangle2D r2=new Rectangle2D.Double(x, y-30, width, height);  
	    g2.setColor(Color.BLACK);  
	    g2.draw(r2);  
	        //Painting life accorrding blood  
	    Rectangle2D r=new Rectangle2D.Double(x+1, y-30+1, width*((double)blood/10)-1, height-1);
        g2.setColor(Color.RED);  
        g2.fill(r);  
		for(int i = 0; i < bullets.size(); i++){
			b = bullets.get(i);
			b.draw(g);	
		}
		g.setColor(Color.GREEN);
		checkBullet();
		g.setFont(new Font("Jack", 1 ,30));
		g.drawString("Bullet Count:" +" "+bullets.size(), 100, 100);
		g.setColor(Color.RED);
		g.drawString("Blood: "+ blood, 700, 100);
		g.setColor(c);
		move();
	}
	
	public void move(){
		
		if((this.x < 0+ TSize/2 && (dir == Dir.L || dir == Dir.DL || dir == Dir.LU) )|| (this.y < 0+TSize/2 && (dir == Dir.U||dir == Dir.LU || dir == Dir.UR))|| (this.x > yardClient.xSize-TSize/2 && (dir == Dir.R||dir == Dir.UR || dir == Dir.RD))|| (this.y > yardClient.ySize-TSize/2 && (dir == Dir.D||dir == Dir.DL || dir == Dir.RD))) dir = Dir.STOP; 
		switch(dir){
		case L :
			x -= XSPEED;
			break;
		case LU :
			x -= XSPEED;
			y -= YSPEED;
			break;
		case U:
			y -= YSPEED;
			break;
		case UR :
			x += XSPEED;
			y -= YSPEED;
			break;
		case R :
			x += XSPEED;
			break;
		case RD :
			x += XSPEED;
			y += YSPEED;
			break;
		case D :
			y += YSPEED;
			break;
		case DL :
			x -= XSPEED;
			y += YSPEED;
			break;
		case STOP :
			break;
		}
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch(key){
	
		case KeyEvent.VK_LEFT :
			bl = true;
			break;
		case KeyEvent.VK_UP :
			bu = true;
			break;
		case KeyEvent.VK_RIGHT :
			br = true;
			break;
		case KeyEvent.VK_DOWN :
			bd = true;
			break;
		}
		locateDirection();
	}
	
	public  void shoot(){ 		
		if(this.dir != Dir.STOP){
			b = new bullet(this.x + tank.TSize/2 - bullet.BSize/2, this.y + tank.TSize/2 - bullet.BSize/2, this.dir);
			bullets.add(b);
		}else{
			b = new bullet(this.x + tank.TSize/2 - bullet.BSize/2, this.y + tank.TSize/2 - bullet.BSize/2, Dir.L);
			bullets.add(b);
		}
	}
	
	public void checkBullet(){
		for(int i = 0; i < bullets.size(); i++){
			b = bullets.get(i);
			if(b.disapper() == true) bullets.remove(b);
		}
	}
	
	public boolean checkAlive(){
		if(blood == 0) return true;
		else return false;
	}
	
	public boolean checkHurt(enemy e){
		if((this.getRect()).intersects(e.getRect()))/*(this.x <= e.getX()+50 && this.x >= e.getX()-50) && (this.y <= e.getY()+50 && this.y >= e.getY()-50)*/{
			blood--;
			return true;
		}else return false;
	}
	
	public boolean checkBeHitted(enemyBullet eb){
		if((this.getRect()).intersects(eb.getRect())){
			blood--;
			return true;
		}
		else return false;
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_SPACE) shoot();
		switch(key){
		case KeyEvent.VK_LEFT :
			bl = false;
			break;
		case KeyEvent.VK_UP :
			bu = false;
			break;
		case KeyEvent.VK_RIGHT :
			br = false;
			break;
		case KeyEvent.VK_DOWN :
			bd = false;
			break;
		}
		locateDirection();
	}
	
	void locateDirection(){
		if (bl && !bu && !br &&!bd) dir = Dir.L;
		else if (bl && bu && !br &&!bd) dir = Dir.LU;
		else if (!bl && bu && !br &&!bd) dir = Dir.U;
		else if (!bl && bu && br &&!bd) dir = Dir.UR;
		else if (!bl && !bu && br &&!bd) dir = Dir.R;
		else if (!bl && !bu && br && bd) dir = Dir.RD;
		else if (!bl && !bu && !br && bd) dir = Dir.D;
		else if (bl && !bu && !br &&bd) dir = Dir.DL;
		else if (!bl && !bu && !br &&!bd) dir = Dir.STOP;
	}
	
	public Dir getDir(){
		return dir;
	}

	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public Rectangle getRect(){
		return new Rectangle(this.x, this.y, TSize, TSize);
	}

}


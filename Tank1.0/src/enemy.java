import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class enemy {
	private int x ;
	private int y ;
	private Dir dir;
	private static final int TSize = 80;
	private static final int XSPEED = 10;
	private static final int YSPEED = 10;
	public static int Score = 0;
	private bullet b;
	
	private static Random r = new Random();
	
	private enemyBullet eb;
	
	public enemy(int x, int y, Dir dir){
		this.x = x;
		this.y = y;
		this.dir = dir;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public Dir getDir(){
		return dir;
	}
	
	public Rectangle getRect(){
		return new Rectangle(this.x, this.y, this.TSize, this.TSize);
	}
	
	public void draw(Graphics g){
		Color c = g.getColor();
		g.setColor(Color.BLUE);
		g.fillOval(x, y, TSize, TSize);
		g.setColor(Color.RED);
		g.drawString("Score :" + Score , 400, 100);
		g.setColor(c);
		
		move();
	}
	
	public enemyBullet fire(){
		if(r.nextInt(40)> 38) {
			return(new enemyBullet(this.x, this.y, this.dir));
		}
		return null;
	};
	
	public void move(){
		if(r.nextInt(40) > 38){
			int a = r.nextInt(8);
			switch(a){
			case 1: dir = Dir.L;
			break;
			case 2: dir = Dir.LU;
			break;
			case 3: dir = Dir.U;
			break;
			case 4: dir = Dir.UR;
			break;
			case 5: dir = Dir.R;
			break;
			case 6: dir = Dir.RD;
			break;
			case 7: dir = Dir.D;
			break;
			case 8: dir = Dir.DL;
			break;
			}
		}	
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
		case STOP:
			break;
		}
	}
	
	public boolean CheckDead(){
		if(this.x >= yardClient.xSize || this.y >= yardClient.ySize || this.x <= 0  || this.y <= 0) return true; 
		else return false;
	}
	
	public boolean CheckHit(bullet b){
		if(/*this.x >= b.getX()-40 && this.x <= b.getX()+40) && ( this.y >= b.getY()-40 && this.y <= b.getY()+40)*/
				(this.getRect()).intersects(b.getRect())){
			Score++;
			return true;
		}else return false;
	}
}

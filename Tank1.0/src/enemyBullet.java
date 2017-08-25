import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class enemyBullet {
	public static final int BSize = 30;
		
	private int x;
	private int y;
	private int BSPEED = 20;
	private Dir dir = null;

		
	public enemyBullet(int x, int y, Dir dir){
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
		
	public Rectangle getRect(){
		return new Rectangle(this.x, this.y, this.BSize, this.BSize);
	}
		
	public void draw(Graphics g){
		Color c = g.getColor();
		g.setColor(Color.BLACK);
		g.fillOval(x, y, BSize, BSize);
		g.setColor(c);
			
		move();
	}
		
	public boolean disapper(){
		if(this.x >= yardClient.xSize || this.x <= 0 || this.y >= yardClient.ySize || this.y <= 0) return true;
			else return false;
	}
		
	public void move(){
		/*dir = player1.getDir();*/
		switch(dir){
		case L :
			x -= BSPEED;
			break;
		case LU :
			x -= BSPEED;
			y -= BSPEED;
			break;
		case U:
			y -= BSPEED;
			break;
		case UR :
			x += BSPEED;
			y -= BSPEED;
			break;
		case R :
			x += BSPEED;
			break;
		case RD :
			x += BSPEED;
			y += BSPEED;
			break;
		case D :
			y += BSPEED;
			break;
		case DL :
			x -= BSPEED;
			y += BSPEED;
			break;
		}
	}
}


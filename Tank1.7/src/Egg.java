import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Egg {
	private int x, y;
	private Random r;
	public static final int eSize = 25;
	
	public Egg(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public Rectangle getRect(){
		return new Rectangle(this.x, this.y, Egg.eSize, Egg.eSize);
	}
	
	public void draw(Graphics g){
		Color c = g.getColor();
		g.setColor(new Color(200, 0, 200));
		g.fillOval(x, y, eSize, eSize);
	}
}

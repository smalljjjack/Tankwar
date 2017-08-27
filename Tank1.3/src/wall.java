import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class wall {
	int x, y;
	int width, height;
	
	public wall(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void draw(Graphics g){
		Color c = g.getColor();
		g.setColor(Color.DARK_GRAY);
		g.fillRect(x, y, width, height);
	}
	
	public Rectangle getRect(){
		return new Rectangle(this.x, this.y, this.width, this.height);
	}
}

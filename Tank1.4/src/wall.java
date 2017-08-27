import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class wall {
	int x, y;
	int width, height;
	
	public wall(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	/*private static Toolkit tk = Toolkit.getDefaultToolkit();
	
	private static Image walls = tk.getImage(wall.class.getClassLoader().getResource("walls.gif"));*/
	
	public void draw(Graphics g){
		Color c = g.getColor();
		g.setColor(Color.DARK_GRAY);
		g.fillRect(x, y, width, height);
		g.setColor(c);
	}
	
	public Rectangle getRect(){
		return new Rectangle(this.x, this.y, this.width, this.height);
	}
}

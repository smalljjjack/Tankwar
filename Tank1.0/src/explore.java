import java.awt.Color;
import java.awt.Graphics;

public class explore {
	private boolean live  = true;
	private int x, y;
	
	private int[] diameter = {5, 29, 52, 80, 50, 20};
	
	private int step = 0;
	
	public explore(int x, int y){
		this.x = x;
		this.y = y;
	} 
	
	public void draw(Graphics g){
		if(!live) return;
		
		if(step == diameter.length) {
			live = false;
			step = 0;
			return;
		}
		
		Color C = g.getColor();
		g.setColor(Color.ORANGE);
		g.fillOval(x, y, diameter[step], diameter[step]);
		g.setColor(C);
		step++;

	}
}

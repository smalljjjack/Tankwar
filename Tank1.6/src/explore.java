
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class explore {
	private boolean live  = true;
	private int x, y;
	
	private int step = 0;
	
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	
	private static Image[] imgs = {
									tk.getImage(explore.class.getClassLoader().getResource("image/blast1.gif")),
									tk.getImage(explore.class.getClassLoader().getResource("image/blast3.gif")),
									tk.getImage(explore.class.getClassLoader().getResource("image/blast5.gif")),
									tk.getImage(explore.class.getClassLoader().getResource("image/blast6.gif")),
									tk.getImage(explore.class.getClassLoader().getResource("image/blast7.gif")),
									};
	
	public explore(int x, int y){
		this.x = x;
		this.y = y;
	} 
	
	public void draw(Graphics g){
		if(!live) return;
		
		if(step == imgs.length) {
			live = false;
			step = 0;
			return;
		}
		
		
		g.drawImage(imgs[step], x, y, null);
		step++;

	}
}

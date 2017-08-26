import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class yardClient extends Frame{
	public static void main(String args[]){
		new yardClient("TankWar").launch();
	}
	public static final int xSize = 1500;
	public static final int ySize = 1300;
	public Image offScreenIamge = null;
	
	private yCThread yc = new yCThread();

	private Dir dir;
	
	private  tank player1 = new tank(50, 50, Dir.L);
	
	private bullet b;
	
	private enemy e;
	
	private explore boom;
	
	private enemyBullet eb;
	
	private List<explore> explores = new ArrayList<explore>();
	
	private List<enemyBullet> enemyBullets = new ArrayList<enemyBullet>();
	
	private boolean live = false;
	
	private List<enemy> enemys = new ArrayList<enemy>();
	 
	private static Random r = new Random(); 
	
	private static final int enemysAmount = 8;
	
	public static wall w1 = new wall(300, 200, 400, 100);
	
	public static wall w2 = new wall( 800, 900, 150, 300);

	public yardClient(String str){
		super(str);
	}
	
	public void launch(){
		this.setBackground(Color.WHITE);
		this.setBounds(300, 300, xSize, ySize);
		this.setResizable(false);
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		
		this.addKeyListener(new yMonitor());
		this.setVisible(true);
		live = true;
		new Thread(yc).start();
	}
	 
	public void paint(Graphics g){
		Color c = g.getColor();
		g.setColor(Color.YELLOW);
		player1.draw(g);
		w1.draw(g);
		w2.draw(g);
		
		if(enemys.size() != 0) for(int i = 0; i < enemys.size(); i++){
			enemy e = enemys.get(i);
			e.draw(g);
		}
		
		for(int i = 0; i< explores.size(); i++){
			boom = explores.get(i);
			boom.draw(g);
		}
		
		for(int i = 0; i < enemyBullets.size(); i++){
			eb = enemyBullets.get(i);
			if(eb.disapper(w1) || eb.disapper(w2)) enemyBullets.remove(eb);
			else eb.draw(g);
		}
		
		if(live == false){
			g.setFont(new Font("Dead", 10, 60));
			g.setColor(Color.RED);
			g.drawString("GameOver", 600, 500);
		}
		g.setColor(c);	
	}
	
	public void checkEnemy(){
		for(int i = 0; i < enemys.size(); i++){
			if(enemys.get(i) != null){
				e = enemys.get(i);
			}
			if((w1.getRect()).intersects(e.getRect()) || (w2.getRect()).intersects(e.getRect())) enemys.remove(e);
			for(int j = 0; j < player1.bullets.size(); j++){
				b = (player1.bullets).get(j);
				if(e.CheckDead()) {
					enemys.remove(e);
				}

				if(e.CheckHit(b)) {
					explores.add(new explore(e.getX(),e.getY()));
					enemys.remove(e);
					(player1.bullets).remove(b);
				}
			}
		}
	}
	
	public void createEnemy(){
		
		checkEnemy();
	
		while(enemys.size() < enemysAmount){
			
			e = new  enemy(r.nextInt(yardClient.xSize-50) +50 , r.nextInt(yardClient.ySize), Dir.U);
			enemys.add(e);
		}
		for(int i = 0; i < enemysAmount; i++){
			e = enemys.get(i);
			eb = e.fire();
			if(eb != null){
				enemyBullets.add(eb);
			}
		}
	}	
		
	private class yCThread implements Runnable{
		
		public void run() {

			while(live){
				createEnemy();
				if(player1.checkAlive()) {
					live = false;
				}
				for(int i = 0; i<enemys.size(); i++){
					e = enemys.get(i);
					if(player1.checkHurt(e)) {
						explores.add(new explore(e.getX(),e.getY()));
						enemys.remove(e);
					}	
				}
				
				for(int i = 0; i < enemyBullets.size(); i++){
					eb = enemyBullets.get(i);
					if(player1.checkBeHitted(eb)) enemyBullets.remove(eb);
				}
				repaint();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}	
		}
	}
	
	  private class yMonitor extends KeyAdapter{
		  @Override
		public void keyReleased(KeyEvent e) {
			  player1.keyReleased(e);
		} 

		@Override
		public void keyPressed(KeyEvent e) {
			 player1.keyPressed(e); 
		}
	 }
	 
	public void update(Graphics g) {
		if( offScreenIamge == null){
			offScreenIamge = this.createImage(xSize, ySize);
		}
		Graphics gOffScreen = offScreenIamge.getGraphics();
		Color c = gOffScreen.getColor();
		gOffScreen.setColor(Color.WHITE);
		gOffScreen.fillRect(0, 0, xSize, ySize);
		gOffScreen.setColor(c);	
		paint(gOffScreen);
		g.drawImage(offScreenIamge, 0, 0, null);
	}
}

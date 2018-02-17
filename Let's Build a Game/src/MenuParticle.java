import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class MenuParticle extends GameObject{

	private Handler handler;
	private Random ran = new Random();
	
	//Design of object
	private int width = 20;
	private int height = 20;
	private Color color = new Color(ran.nextInt(255),ran.nextInt(255),ran.nextInt(255));
	
	public MenuParticle(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
		//Speed of basic enemy
		velY = ran.nextInt(10) - 5;
		velX = ran.nextInt(10) - 5;
		
		if(velX == 0)
			velX++;
		if(velY == 0)
			velY++;
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		
		//To hit the screen and don't go any further
		if(y <=0 || y >= Game.HEIGHT-40)
			velY *= -1;		
		if(x <=0 || x >= Game.WIDTH-20)
			velX *= -1;
		
		handler.addObject(new Trail((int)x, (int)y, ID.Trail, handler, width, height, color, 0.07f));
			
	}

	@Override
	public void render(Graphics g) {
		g.setColor(color);
		g.fillRect((int)x, (int)y, width, height);
		
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, width, height);
	}
	
	
	

}

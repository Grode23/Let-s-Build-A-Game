import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class BasicEnemy extends GameObject {

	private Handler handler;
	private HUD hud;
	
	// Design of object
	private int width = 20;
	private int height = 20;
	private Color color = Color.red;

	public BasicEnemy(float x, float y, ID id, Handler handler, HUD hud) {
		super(x, y, id);
		this.handler = handler;
		this.hud = hud;

		// Speed of basic enemy	
		velY = 3 + hud.getLevel()/10;
		velX = 3 + hud.getLevel()/10;

	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		

		// To hit the screen and don't go any further
		if (Game.gameState != STATE.GameOver) {
			if (y <= 0 || y >= Game.HEIGHT - 40)
				velY *= -1;
			if (x <= 0 || x >= Game.WIDTH - 20)
				velX *= -1;
			
		}
		
		if (y < -100 || y >= Game.HEIGHT + 100 || x < -100 || x >= Game.WIDTH + 100)
			handler.removeObject(this);
		
		//0.001f is going to increase velocity by 1
		
		if(Game.hard) {
			if(velX > 0)
				velX += 0.0015f;
			else
				velX -= 0.0015f;
			
			if(velY > 0)
				velY += 0.0015f;
			else
				velY -= 0.0015f;
		}
				
		handler.addObject(new Trail((int) x, (int) y, ID.Trail, handler, width, height, color, 0.1f));

	}

	@Override
	public void render(Graphics g) {
		g.setColor(color);
		g.fillRect((int) x, (int) y, width, height);

	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, width, height);
	}

}

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class BossEnemy extends GameObject {

	private Handler handler;
	private Random random = new Random();
	private HUD hud;

	private int firstTimer = 70;
	private int secondTimer = 50;

	// Design of object
	private int width = 96;
	private int height = 96;
	private Color color = Color.red;
	private int frequency;

	public BossEnemy(float x, float y, ID id, Handler handler, HUD hud) {
		super(x, y, id);
		this.handler = handler;
		this.hud = hud;

		// Speed of basic enemy
		velY = 2;
		velX = 0;
		frequency = 6 + hud.getLevel() / 5;
		

	}

	@Override
	public void tick() {
		x += velX;
		y += velY;

		// Makes the Boss to go dow a little bit

		if (firstTimer <= 0) {
			velY = 0;
			secondTimer--;
		} else
			firstTimer--;

		if (secondTimer <= 0) {
			// Does it only once
			if (velX == 0)
				velX = -3;

			// How many bullets will shot
			if (random.nextInt(frequency) == 0)
				handler.addObject(new Bullets((int) x + (width / 2), (int) y + height, ID.BasicEnemy, handler, 6));
		}

		// To hit the screen and don't go any further
		if (Game.gameState != STATE.GameOver) {
			if (x <= 0 || x >= Game.WIDTH - width)
				velX *= -1;
		}

		if(Game.hard && secondTimer <= 0) {
			if(velX > 0)
				velX += 0.001f;
			else
				velX -= 0.001f;
						
		}
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

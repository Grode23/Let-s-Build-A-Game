import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject {

	Handler handler;
	
	//Design of object
	private int width = 32;
	private int height = 32;
	private Color color = Color.blue;

	public Player(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
	}

	// Movement of player (it actually change the place the player is, what's why
	// the x and y are changed)
	@Override
	public void tick() {
		x += velX;
		y += velY;

		x = Game.clamp(x, 0, Game.WIDTH - 38);
		y = Game.clamp(y, 0, Game.HEIGHT - 60);

		collision();// To Lower the health

	}

	// Creation of the Player
	@Override
	public void render(Graphics g) {
		g.setColor(color);
		g.fillRect((int)x, (int)y, width, height);
	}

	private void collision() {
		for (GameObject tempObj : handler.getObject()) {

			//if (tempObj.getId() != ID.Player) { I WOULD USE THIS IF NOT THE ID.BASICENEMY ONE
				
			//Since I remove some objects (liek trails), i need to make sure that rectangles are not null
				if (getBounds() != null && tempObj.getBounds() != null) {
					
					if (getBounds().intersects(tempObj.getBounds())) {
						
						if(tempObj.getId() == ID.BasicEnemy)
							HUD.HEALTH--;
						else if(tempObj.getId() == ID.SmartEnemy)
							HUD.HEALTH -= 2;
						else if(tempObj.getId() == ID.FastEnemy)
							HUD.HEALTH--;
						else if (tempObj.getId() == ID.BossEnemy)
							HUD.HEALTH -= 4;
					}
				}
			//}
		}//END OF for loop
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, width, height);
	}

}

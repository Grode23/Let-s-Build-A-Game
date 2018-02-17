import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

	private Handler handler;
	private float speed = 4;

	public KeyInput(Handler handler) {
		this.handler = handler;
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		
		//Move the player
		for (int i = 0; i < handler.getObject().size(); i++) {
			GameObject tempObject = handler.getObject().get(i);

			if (tempObject.getId() == ID.Player) {
				// key events for player

				if (key == KeyEvent.VK_W)
					tempObject.setVelY(tempObject.getVelY() - handler.getSpeed());
				if (key == KeyEvent.VK_A)
					tempObject.setVelX(tempObject.getVelX() - handler.getSpeed());
				if (key == KeyEvent.VK_S)
					tempObject.setVelY(tempObject.getVelY() + handler.getSpeed());
				if (key == KeyEvent.VK_D)
					tempObject.setVelX(tempObject.getVelX() + handler.getSpeed());
				tempObject.setVelY(Game.clamp(tempObject.getVelY(), -handler.getSpeed(), handler.getSpeed()));
				tempObject.setVelX(Game.clamp(tempObject.getVelX(), -handler.getSpeed(), handler.getSpeed()));
			}
		}

		//Pause the game
		if (Game.gameState == STATE.Game)
			if (key == KeyEvent.VK_P) {
				if (Game.paused)
					Game.paused = false;
				else
					Game.paused = true;
			}

		//Exit the game
		if (key == KeyEvent.VK_ESCAPE)
			System.exit(1);
		
		
		//Upgrades
		if(key == KeyEvent.VK_SPACE) {
			if(Game.gameState == STATE.Game) 
				Game.gameState = STATE.Upgrade;
			else if(Game.gameState == STATE.Upgrade)
				Game.gameState = STATE.Game;
		}
			
			
		

	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		for (int i = 0; i < handler.getObject().size(); i++) {
			GameObject tempObject = handler.getObject().get(i);

			if (tempObject.getId() == ID.Player) {
				// key events for player

				if (key == KeyEvent.VK_W)
					tempObject.setVelY(tempObject.getVelY() + handler.getSpeed());
				if (key == KeyEvent.VK_A)
					tempObject.setVelX(tempObject.getVelX() + handler.getSpeed());
				if (key == KeyEvent.VK_S)
					tempObject.setVelY(tempObject.getVelY() - handler.getSpeed());
				if (key == KeyEvent.VK_D)
					tempObject.setVelX(tempObject.getVelX() - handler.getSpeed());
				tempObject.setVelY(Game.clamp(tempObject.getVelY(), -handler.getSpeed(), handler.getSpeed()));
				tempObject.setVelX(Game.clamp(tempObject.getVelX(), -handler.getSpeed(), handler.getSpeed()));
			}
		}

	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	
	
}

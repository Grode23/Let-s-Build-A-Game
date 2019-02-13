import java.awt.Color;
import java.awt.Graphics;

public class HUD {
	
	private int bounds = 0;
	public static float HEALTH = 100;
	private float maxHealth = HEALTH;//needed for the colorValues
	private float greenValue = 255;
	private float redValue = 0;
	private float score = 0;
	private int level = 1;
	
	
	public void tick() {
		HEALTH = Game.clamp(HEALTH, 0, 100 + bounds);	

		
		greenValue = (HEALTH * 255)/maxHealth;
		greenValue = Game.clamp(greenValue, 0, 255);
		
		redValue = Math.abs(greenValue -255);		
		redValue = Game.clamp(redValue, 0, 255);
		
		score += 0.4f;
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillRect(15, 15, 200 + (bounds*2), 35);
		
		g.setColor(new Color((int)redValue, (int)greenValue, 0));
		g.fillRect(15, 15, (int)HEALTH*2, 35);
		
		g.setColor(Color.white);
		g.drawRect(15, 15, (int)HEALTH*2, 35);
		
		g.drawString("Score: " + (int)score, 15, 68);
		g.drawString("Level: " + level, 15, 84);
		
		
	}


	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getBounds() {
		return bounds;
	}

	public void setBounds(int bounds) {
		this.bounds = bounds;
	}

	public float getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(float maxHealth) {
		this.maxHealth = maxHealth;
	}
	
	

}

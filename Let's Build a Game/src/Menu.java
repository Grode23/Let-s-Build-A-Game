import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Menu extends MouseAdapter {

	private Handler handler;
	private Random random = new Random();
	private HUD hud;
	private int[] price = new int[3];

	public Menu(Handler handler, HUD hud) {
		this.handler = handler;
		this.hud = hud;

		price[0] = 1500;
		price[1] = 2000;
		price[2] = 1000;

	}

	@Override
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();

		// WHILE ON MENU
		if (Game.gameState == STATE.Menu) {

			// Play button
			if (mouseOver(mx, my, Game.WIDTH / 2 - 150, 120, 300, 70)) {
				Game.gameState = STATE.Difficulty;
				//AudioPlayer.getSound("click").play();
			}
			// Help button
			else if (mouseOver(mx, my, Game.WIDTH / 2 - 150, 240, 300, 70)) {
				Game.gameState = STATE.Help;
				//AudioPlayer.getSound("click").play();

			}
			// Exit button
			else if (mouseOver(mx, my, Game.WIDTH / 2 - 150, 360, 300, 70)) {
				System.exit(1);
			}
		}

		// WHILE ON HELP
		else if (Game.gameState == STATE.Help) {
			// Back button
			if (mouseOver(mx, my, Game.WIDTH / 2 - 150, 360, 300, 70)) {
				Game.gameState = STATE.Menu;
				//AudioPlayer.getSound("click").play();

			}
		}

		// WHILE THE GAME IS OVER
		else if (Game.gameState == STATE.GameOver) {
			if (mouseOver(mx, my, Game.WIDTH / 2 - 150, 360, 300, 70)) {
				Game.gameState = STATE.Menu;
				hud.setLevel(1);
				hud.setScore(0);
				hud.setBounds(0);
				HUD.HEALTH = 100;
				hud.setMaxHealth(HUD.HEALTH);
				//AudioPlayer.getSound("click").play();

			}
		}

		// WHILE ON DIFFICULTY
		else if (Game.gameState == STATE.Difficulty) {

			// Normal button
			if (mouseOver(mx, my, Game.WIDTH / 2 - 150, 120, 300, 70)) {
				Game.hard = false;
				Game.gameState = STATE.Game;
				handler.addObject(new Player(Game.WIDTH / 2 - 25, Game.HEIGHT / 2 - 25, ID.Player, handler));
				handler.clearEnemies();// To remove menu particles
				handler.addObject(new BasicEnemy(random.nextInt(Game.WIDTH - 20), random.nextInt(Game.HEIGHT - 40),
						ID.BasicEnemy, handler, hud));
				//AudioPlayer.getSound("click").play();

			}

			// Hard button
			if (mouseOver(mx, my, Game.WIDTH / 2 - 150, 240, 300, 70)) {
				Game.hard = true;
				Game.gameState = STATE.Game;
				handler.addObject(new Player(Game.WIDTH / 2 - 25, Game.HEIGHT / 2 - 25, ID.Player, handler));
				handler.clearEnemies();// To remove menu particles
				handler.addObject(new BasicEnemy(random.nextInt(Game.WIDTH - 20), random.nextInt(Game.HEIGHT - 40),
						ID.BasicEnemy, handler, hud));
				//AudioPlayer.getSound("click").play();

			}

			// Back button
			if (mouseOver(mx, my, Game.WIDTH / 2 - 150, 360, 300, 70)) {
				Game.gameState = STATE.Menu;
				//AudioPlayer.getSound("click").play();

			}
		}

		// WHILE ON UPGRADES
		else if (Game.gameState == STATE.Upgrade) {

			// Health Bar
			if (mouseOver(mx, my, Game.WIDTH / 2 - 150, 120, 300, 70)) {
				if (hud.getScore() >= price[0]) {
					hud.setBounds(hud.getBounds() + 20);
					hud.setMaxHealth(hud.getMaxHealth() + 20);
					HUD.HEALTH += 20;

					hud.setScore(hud.getScore() - price[0]);
					price[0] += 200;
					price[1] += 100;

					//AudioPlayer.getSound("click").play();

				}

			}

			// Refill Health
			if (mouseOver(mx, my, Game.WIDTH / 2 - 150, 240, 300, 70)) {
				if (hud.getScore() >= price[1]) {
					HUD.HEALTH = hud.getMaxHealth();

					hud.setScore(hud.getScore() - price[1]);

					//AudioPlayer.getSound("click").play();

				}

			}

			// Speed
			if (mouseOver(mx, my, Game.WIDTH / 2 - 150, 360, 300, 70)) {
				if (hud.getScore() >= price[2]) {
					handler.setSpeed(handler.getSpeed() + 0.5f);
					
					hud.setScore(hud.getScore() - price[2]);
					price[2] += 400;

					//AudioPlayer.getSound("click").play();

				}
			}
		}

	}

	public void tick() {

	}

	public void render(Graphics g) {
		Font big = new Font("Arial", 1, 50);
		Font normal = new Font("Arial", 1, 40);
		Font small = new Font("arial", 1, 25);
		g.setColor(Color.white);

		if (Game.gameState == STATE.Menu) {

			g.setFont(big);
			g.drawString("MENU", Game.WIDTH / 2 - 75, 64);

			g.setFont(normal);
			g.drawRect(Game.WIDTH / 2 - 150, 120, 300, 70);
			g.drawString("Play", Game.WIDTH / 2 - 40, 170);

			g.drawRect(Game.WIDTH / 2 - 150, 240, 300, 70);
			g.drawString("Help", Game.WIDTH / 2 - 40, 290);

			g.drawRect(Game.WIDTH / 2 - 150, 360, 300, 70);
			g.drawString("Exit", Game.WIDTH / 2 - 40, 410);

		} else if (Game.gameState == STATE.Help) {
			g.setFont(big);
			g.drawString("HELP", Game.WIDTH / 2 - 75, 64);

			g.setFont(small);
			g.drawString("Use WASD to move and dodge enemies", 90, 200);

			g.setFont(normal);
			g.drawRect(Game.WIDTH / 2 - 150, 360, 300, 70);
			g.drawString("Back", Game.WIDTH / 2 - 40, 410);

		} else if (Game.gameState == STATE.GameOver) {
			g.setColor(Color.white);
			g.setFont(big);
			g.drawString("GAME OVER", Game.WIDTH / 2 - 150, 64);

			g.setFont(normal);
			g.drawString("Score:" + (int) hud.getScore(), 90, 200);
			g.drawString("Level: " + hud.getLevel(), 90, 250);

			g.setFont(normal);
			g.drawRect(Game.WIDTH / 2 - 150, 360, 300, 70);
			g.drawString("Try again", Game.WIDTH / 2 - 90, 410);

		} else if (Game.gameState == STATE.Difficulty) {
			g.setFont(big);
			g.drawString("DIFFICTULTY", Game.WIDTH / 2 - 100, 64);

			g.setFont(normal);
			g.drawRect(Game.WIDTH / 2 - 150, 120, 300, 70);
			g.drawString("Normal", Game.WIDTH / 2 - 40, 170);

			g.drawRect(Game.WIDTH / 2 - 150, 240, 300, 70);
			g.drawString("Hard", Game.WIDTH / 2 - 40, 290);

			g.drawRect(Game.WIDTH / 2 - 150, 360, 300, 70);
			g.drawString("Back", Game.WIDTH / 2 - 40, 410);

		} else if (Game.gameState == STATE.Upgrade) {
			g.setFont(big);
			g.drawString("UPGRADES", Game.WIDTH / 2 - 130, 64);

			g.setFont(normal);
			g.drawRect(Game.WIDTH / 2 - 150, 120, 300, 70);
			g.drawString("Health Bar", Game.WIDTH / 2 - 70, 150);
			g.setFont(small);
			g.drawString("Cost: " + price[0], Game.WIDTH / 2 - 30, 180);

			g.setFont(normal);
			g.drawRect(Game.WIDTH / 2 - 150, 240, 300, 70);
			g.drawString("Refill health", Game.WIDTH / 2 - 110, 270);
			g.setFont(small);
			g.drawString("Cost: " + price[1], Game.WIDTH / 2 - 30, 300);

			g.setFont(normal);
			g.drawRect(Game.WIDTH / 2 - 150, 360, 300, 70);
			g.drawString("Speed", Game.WIDTH / 2 - 40, 390);
			g.setFont(small);
			g.drawString("Cost: " + price[2], Game.WIDTH / 2 - 30, 420);

		}

	}

	public boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if (mx > x && mx < x + width)
			if (my > y && my < y + height)
				return true;
		return false;
	}

}

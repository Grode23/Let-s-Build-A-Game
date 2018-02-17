import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {

	// FINALS
	private static final long serialVersionUID = -1442798787354930462L;
	public static final int WIDTH = 640;
	public static final int HEIGHT = WIDTH / 12 * 9;

	// Objects
	private boolean running = false;// True if the game is running
	private Thread thread;// This is a single threaded game (not preferred)
	private Handler handler;
	private HUD hud;
	private Spawn spawn;
	private Menu menu;
	
	public static STATE gameState = STATE.Menu;
	public static boolean paused = false;
	public static boolean hard = true;
	private Random random = new Random();

	public Game() {
		hud = new HUD();
		handler = new Handler();
		menu = new Menu(handler, hud);
		spawn = new Spawn(hud, handler);

		// Listeners for mouse (menu) and kayboard (in-game)
		this.addKeyListener(new KeyInput(handler));// Listener for the keyboard
		this.addMouseListener(menu);

		//AudioPlayer.load();
		
		// Creation of the window
		new Window(WIDTH, HEIGHT, "Let's Build a Game", this);

		if (gameState == STATE.Menu) {
			for (int i = 0; i < 20; i++)
				handler.addObject(new MenuParticle(random.nextInt(Game.WIDTH - 20), random.nextInt(Game.HEIGHT - 40),
						ID.MenuParticle, handler));
		}

	}

	public synchronized void start() {
		thread = new Thread(this);// The thread is going to run THIS game
		thread.start();
		running = true;
	}

	public synchronized void stop() {
		try {
			thread.join();// Kill the thread
			running = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Game loop
	public void run() {
		this.requestFocus();// I don't have to click on the game to access the keyboard
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		@SuppressWarnings("unused")
		int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}
			if (running)
				render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				// System.out.println("FPS: " +frames);
				frames = 0;
			}
		}
		stop();
	}

	private void tick() {

		if (gameState == STATE.Game) {

			if (!paused) {

				handler.tick();//For GameObjects
				hud.tick();
				spawn.tick();

				if (HUD.HEALTH <= 0)
					gameState = STATE.GameOver;
				

			}
		} else if (gameState == STATE.Menu || gameState == STATE.GameOver || gameState == STATE.Help || gameState == STATE.Difficulty) {
			handler.tick();//Only for MenuParticles
			menu.tick();
			HUD.HEALTH = 100;
			spawn.setKeepScore(0);// Because the level up doesn't work in 100 points

		}
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();// THIS refers to Canvas

		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		// Everything I'm going to draw, will be displayed here
		g.setColor(new Color(30, 30, 30));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		
		if(Game.paused) {
			g.setColor(Color.white);
			g.drawString("Paused", 100, 100);		
		}

		handler.render(g);

		if (gameState == STATE.Game) {
			hud.render(g);
		} else if (gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.GameOver || gameState == STATE.Difficulty || gameState == STATE.Upgrade)
			menu.render(g);

		g.dispose();
		bs.show();

	}

	public static float clamp(float var, float min, float max) {
		if (var <= min)
			return min;
		else if (var >= max)
			return max;
		else
			return var;
	}

	public static void main(String args[]) {
		new Game();
	}

}

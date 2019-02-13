import java.util.Random;

public class Spawn {

	private HUD hud;
	private Handler handler;
	private Random random;
	private int limit = 100;

	private float keepScore = 0;

	public Spawn(HUD hud, Handler handler) {
		this.hud = hud;
		this.handler = handler;

		random = new Random();

	}

	public void tick() {

		keepScore += 0.4f;	
		
		int number = random.nextInt(10);

		if (keepScore >= limit) {
			if (hud.getLevel() % 10 != 0) {
				// Delete boss if needed
				handler.removeBoss();
				
				
				if (number == 0)
					handler.addObject(new SmartEnemy(random.nextInt(Game.WIDTH - 20), random.nextInt(Game.HEIGHT - 40),
							ID.SmartEnemy, handler));
				else if (number > 0 && number < 7)
					handler.addObject(new BasicEnemy(random.nextInt(Game.WIDTH - 20), random.nextInt(Game.HEIGHT - 40),
							ID.BasicEnemy, handler, hud));
				else
					handler.addObject(new FastEnemy(random.nextInt(Game.WIDTH - 20), random.nextInt(Game.HEIGHT - 40),
							ID.FastEnemy, handler, hud));
				
				limit = 100;
			} else {
				handler.clearEnemies();
				handler.addObject(new BossEnemy(random.nextInt(Game.WIDTH - 96), -120, ID.BossEnemy, handler, hud));
				limit = 1000;


			}

			hud.setLevel(hud.getLevel() + 1);
			keepScore = 0;

		}

	}

	public void setKeepScore(int keepScore) {
		this.keepScore = keepScore;
	}

}

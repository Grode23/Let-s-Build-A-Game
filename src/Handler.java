import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Handler {

	private List<GameObject> object = new ArrayList<>();
	private float speed = 4;

	public void tick() {
		for (int i = 0; i < object.size(); i++) {
			GameObject tempobj = object.get(i);

			tempobj.tick();
		}

	}

	public void render(Graphics g) {
		try {
			for (int i = 0; i < object.size(); i++) {
				GameObject tempObject = object.get(i);

				tempObject.render(g);
			}
		} catch (Exception e) {

			System.out.println("Error: " + e.toString());

		}
	}

	public void addObject(GameObject object) {
		this.object.add(object);
	}

	public void removeObject(GameObject object) {
		this.object.remove(object);
	}

	public void clearEnemies() {
		for (GameObject tempObj : object) {
			if (tempObj.getId() == ID.Player) {
				object.clear();
				addObject(new Player((int) tempObj.getX(), tempObj.getY(), ID.Player, this));
				break;
			}

		}

	}

	public void removeBoss() {
		for (GameObject tempObj : object) {
			if (tempObj.getId() == ID.BossEnemy) {
				removeObject(tempObj);
				break;
			}

		}

	}

	public List<GameObject> getObject() {
		return object;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	
}

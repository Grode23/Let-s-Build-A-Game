import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Trail extends GameObject{

	private float alpha = 1, lifeTime;
	private Color color;
	private int height, width;
	
	private Handler handler;
	
	public Trail(int x, int y, ID id, Handler handler, int width, int height, Color color, float lifeTime) {
		super(x, y, id);
		this.handler = handler;
		this.color = color;
		this.width = width;
		this.height = height;
		this.lifeTime = lifeTime;
	}

	@Override
	public void tick() {
		if(alpha > lifeTime)
			alpha -= lifeTime;
		else
			handler.removeObject(this);
		
	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
				
		g2d.setComposite(makeTransparent(alpha));
		g.setColor(color);
		g.fillRect((int)x, (int)y, width, height);
	
		g2d.setComposite(makeTransparent(1));

	}
	
	public AlphaComposite makeTransparent(float alpha) {
		int type = AlphaComposite.SRC_OVER;
		return AlphaComposite.getInstance(type, alpha);
	}

	//This will never going to be used
	@Override
	public Rectangle getBounds() {
		return null;
	}

}

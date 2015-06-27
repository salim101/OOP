package ie.dit;

import processing.core.PApplet;
import processing.core.PVector;

public class Bullet extends GameObject {
	
	public float speed;
	
	Bullet(PApplet applet, float x, float y, float theta){
		super(applet);
		speed = 14.0f;
		this.theta = theta;
		location = new PVector(x, y);
		w = 2;
		h= 8;
	} // End constructor.
	
	public void display(){
		applet.noStroke();
		applet.pushMatrix();
		applet.fill(0);
		applet.translate(location.x, location.y);
		applet.rotate(theta);
		applet.rect(0, - 5, w, h);
		applet.noFill();
		applet.popMatrix();
	} // End display.
	
	public void update() {
		move();
		out_of_bounds();
	} // End update.
	
	private void move() {
		float lx = (float) Math.sin(theta);
	    float ly = (float) -Math.cos(theta);
	    location.x += lx * speed;
	    location.y += ly * speed;
	} // End move.
	
	private void out_of_bounds() {
		if (location.y > applet.height || location.y < -h || location.x < -w || location.x > applet.width)
			alive = false;
	} // End out_of_bounds.
	
} // End Bullet class.
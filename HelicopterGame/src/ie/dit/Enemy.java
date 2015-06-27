package ie.dit;

import java.util.Random;
import processing.core.PApplet;
import processing.core.PVector;

public class Enemy extends Helicopter {
	
	public PVector forward;
	public float acceleration, speed, min_speed, max_speed;
	
	Enemy(PApplet applet) {
		super(applet);
		location = new PVector ( (applet.width/2)-(w/2), applet.height-(h*2) );
		speed = 2.0f;
		forward = new PVector (0.0f, 1.0f);
		
	} // End constructor.
	
	public void update() {
		// By calling super.update(), we are calling the Helicopter
		// classes update method which rotates the helicopter rotor.
		super.update();
		move();
		keep_in_bounds();
	} // End update
	
	private void move() {
		forward.x = (float) -Math.sin(theta) * speed;
		forward.y = (float)  Math.cos(theta) * speed;
		location.sub(forward);
	} // End move.
	
	private void keep_in_bounds() {
		// If the player passes the bottom of the screen.
		if (location.y > 700){
			Random random = new Random();
			float value = random.nextFloat() * 2 - 1;
			theta = value;
			location.y = -60; // Put the player at the top of the screen.
		}
		// If the player passes the top of the screen.
		if (location.y < -60){
			Random random = new Random();
			float value = random.nextFloat() * 2 - 1;
			theta = value;
			location.y = 700; // Put the player at the bottom of the screen.
		}
		// If the player passes the left side of the screen.
		if (location.x < -40){
			Random random = new Random();
			float value = random.nextFloat() * 2 - 1;
			theta = value;
			location.x = 720; // Put the player at the right side of the screen.
		}
		// If the player passes the right side of the screen.
		if (location.x > 720){
			Random random = new Random();
			float value = random.nextFloat() * 2 - 1;
			theta = value;
			location.x = -40; // Put the player at the left side of the screen.
		}
	} // End inBounds.
	
} // End Enemy class.
package ie.dit;

import java.awt.Color;
import java.util.Random;
import processing.core.PApplet;
import processing.core.PVector;

public class Smoke extends GameObject {
	
	public Random random;
	public float lifespan, radius;
	public PVector location, velocity, acceleration;
	
	Smoke(PApplet applet, float x, float y) {
		super(applet);
	    radius = 8;
	    lifespan = 255.0f;
	    random = new Random();
	    location = new PVector(x, y);
	    velocity = new PVector(random.nextFloat() * 2 - 1, random.nextFloat() * 2 - 1);
	    colour = new Color(50, 50, 50);
	} // End constructor.
	
	public void update() {
	    location.add(velocity);
	    lifespan -= 5.0;
	    if(lifespan <= 0)
	    	alive = false;
	} // End update.

	public void display() {
		applet.noStroke();
	    applet.fill( colour.getRed(), colour.getGreen(), colour.getBlue(), lifespan);
	    applet.ellipse(location.x,location.y, radius, radius);
	    applet.noFill();
	} // End display.
  
} // End Smoke class.
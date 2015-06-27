package ie.dit;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class Map extends GameObject{
	
	public PImage img;
	
	Map(PApplet applet){
		super(applet);
		location = new PVector(0, 0);
		w = this.applet.width;
		h= this.applet.height;
		img = this.applet.loadImage("data/map.jpg");
	} // End constructor.
	
	// We're overriding the GameObject display method.
	public void display() {
		applet.image(img, location.x, location.y, w, h);
	} // End display.
} // End Map.
package ie.dit;

import ddf.minim.Minim;
import ddf.minim.AudioPlayer;
import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PVector;

public class Helicopter extends GameObject {
	
	public int health;
	public PVector forward;
	public ArrayList<GameObject> objects;
	public float rotor_theta, shadow_scale, fireRate = 1f, timeSinceLastEvent = 0;
	public Minim minim; // Required to use Minim.
	public AudioPlayer snd_shoot;
	
	Helicopter(PApplet applet) {
		super(applet);
		w = 30;
		h = 40;
		health = 50;
		rotor_theta = 0.0f;
		shadow_scale = 0.6f;
		location = new PVector(300, 300);
		objects = new ArrayList<GameObject>();
		minim = new Minim(this.applet); // Required to use Minim.
		snd_shoot = minim.loadFile("data/shoot.wav");
	} // End Constructor.
	
	public void update() {
		rotor_theta += 0.5f;
		fire_bullet();
		for (int i = 0; i < objects.size(); ++i) {
			objects.get(i).run();
			if (!objects.get(i).alive)
				objects.remove(i);
		} // End loop.
	} // End update.
	
	public void display() {
		applet.noStroke();
		draw_helicopter_shadow();
		draw_rotor_shadow();
		draw_helicopter();
		draw_rotor();
		draw_health_bar();
		applet.noFill();
		//applet.noStroke();
	} // End Display.
	
	private void draw_helicopter_shadow() {
		applet.pushMatrix();
		applet.translate( (location.x  + (w/2)) + 15, (location.y  + (h/2)) + 10);
		applet.rotate(theta);
		applet.scale(shadow_scale);
		applet.fill( 0, 0, 0, 120 );
		applet.rect(-15, h, 30, 3);    // Tail fin.
		applet.rect(-3,h-20, 6, h-20); // Tail.
		applet.ellipse(0,0, w, h);     // Body.
		applet.popMatrix();
	} // End draw_helicopter_shadow.
	
	private void draw_rotor_shadow() {
		applet.pushMatrix();
		applet.translate( (location.x  + (w/2)) + 15, (location.y  + (h/2)) + 10);
		applet.rotate(rotor_theta);
		applet.scale(shadow_scale);
		applet.fill(0);
		applet.rect(-1.5f, -40, 3, 80); // Vertical rotor.
		applet.rect(-40, -1.5f, 80, 3); // Horizontal rotor.
		applet.popMatrix();
	} // End draw_rotor_shadow.
	
	private void draw_helicopter() {
		applet.pushMatrix();
		applet.translate(location.x  + (w/2), location.y  + (h/2));
		applet.rotate(theta);
		applet.fill( colour.getRed(), colour.getGreen(), colour.getBlue(), colour.getAlpha() );
		applet.rect(-15, h, 30, 3);    // Tail fin.
		applet.rect(-3,h-20, 6, h-20); // Tail.
		applet.ellipse(0,0, w, h);     // Body.
		applet.popMatrix();
	} // End draw_helicopter.
	
	private void draw_rotor() {
		applet.pushMatrix();
		applet.translate(location.x  + (w/2), location.y  + (h/2));
		applet.rotate(rotor_theta);
		applet.fill(0);
		applet.rect(-1.5f, -40, 3, 80); // Vertical rotor.
		applet.rect(-40, -1.5f, 80, 3); // Horizontal rotor.
		applet.popMatrix();
	} // End draw_rotor.
	
	public void draw_health_bar() {
		applet.pushMatrix();
		applet.translate(location.x, location.y);
		applet.fill(255, 0, 0, 255);
		applet.rect(-health/3, 20, health, 5);
		applet.popMatrix();
	} // End draw_health_bar.
	
	public void fire_bullet() {
		if(applet.millis() - timeSinceLastEvent >= 1000/fireRate){
			snd_shoot.rewind();
			snd_shoot.play();
			objects.add( new Bullet(applet, location.x+(w/2), location.y+(h/2), theta) );
            timeSinceLastEvent = applet.millis();
        }
	} // End fire_bullet.
	
} // End Helicopter Class.
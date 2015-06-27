package ie.dit;

//import ddf.minim.Minim;
//import ddf.minim.AudioPlayer;
import processing.core.PApplet;
import processing.core.PVector;

public class Player extends Helicopter {
	
	//public Minim minim; // Required to use Minim.
	//public AudioPlayer snd_shoot;
	public boolean up, down, left, right, bullet_ready;
	public float acceleration, speed, min_speed, max_speed, fireRate = 5f, timeSinceLastEvent = 0;
	
	Player(PApplet applet) {
		super(applet);
		location = new PVector ( (applet.width/2)-(w/2), applet.height-(h*2) );
		speed = 4.0f;
		acceleration = 0.1f;
		min_speed = 1.0f;
		max_speed = 8.0f;
		forward = new PVector (0.0f, 1.0f);
		up = down = left = right = bullet_ready = false; // Set to true on KeyPressed.
		
		
		//minim = new Minim(this.applet); // Required to use Minim.
		//snd_shoot = minim.loadFile("data/shoot.wav");
		
		
	} // End constructor.
	
	public void update() {
		// By calling super.update(), we are calling the Helicopter
		// classes update method which rotates the helicopter rotor.
		super.update();
		move();
		keep_in_bounds();
	} // End update.
	
	public void keyPressed(){
		load_bullets(applet.keyCode, true);
		setDirection(applet.keyCode, true);
	} // End keyPressed.
	
	public void keyReleased(){
		load_bullets(applet.keyCode, false);
		setDirection(applet.keyCode, false);
	} // End keyPressed.
	
	public void fire_bullet() {
		if (bullet_ready){
			if(applet.millis() - timeSinceLastEvent >= 1000/fireRate){
				snd_shoot.rewind();
				snd_shoot.play();
				objects.add( new Bullet(applet, location.x+(w/2), location.y+(h/2), theta) );
	            timeSinceLastEvent = applet.millis();
	        }
		}
	} // End fire_bullet.
	
	private void load_bullets(int k, boolean decision) {
		if (k == 69) bullet_ready = decision;
	} // End load_bullets.
	
	private void setDirection(int k, boolean decision) {
		if     (k == 87 || k == 38) up    = decision; // 87=w || 38=arrow-up
		else if(k == 83 || k == 40) down  = decision; // 83=s || 40=arrow-down
		else if(k == 65 || k == 37) left  = decision; // 65=a || 37=arrow-left
		else if(k == 68 || k == 39) right = decision; // 68=d || 39=arrow-right
	} // End setDirection.
	
	private void move() {
		// These booleans are set to true when the keys are pressed.
		if(up)    speed_up();
		if(down)  slow_down();
		if(left)  turn_left();
		if(right) turn_right();
		// This will make the player move in the direction it is facing.
		forward.x = (float) -Math.sin(theta) * speed;
		forward.y = (float)  Math.cos(theta) * speed;
		location.sub(forward);
	} // End move.
	
	private void keep_in_bounds() {
		// If the player passes the bottom of the screen.
		if (location.y > 700)
			location.y = -60; // Put the player at the top of the screen.
		// If the player passes the top of the screen.
		if (location.y < -60)
			location.y = 700; // Put the player at the bottom of the screen.
		// If the player passes the left side of the screen.
		if (location.x < -40)
			location.x = 720; // Put the player at the right side of the screen.
		// If the player passes the right side of the screen.
		if (location.x > 720)
			location.x = -40; // Put the player at the left side of the screen.
	} // End inBounds.
	
	private void speed_up() {
		if(speed < max_speed)
			speed +=acceleration;
	} // End speed_up.
	
	private void slow_down() {
		if(speed > min_speed)
			speed -=acceleration;
	} // End slow_down.
	
	private void turn_left() {
		theta -= acceleration;
	} // End slow_down.
	
	private void turn_right() {
		theta += acceleration;
	} // End turn_right.
} // End Player class.
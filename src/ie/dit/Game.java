package ie.dit;

/*
 * This is our game class.
 * Our game objects will be created here.
 */

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;

public class Game {
	
	String state = "ready";
	PImage ready_img;
	PApplet applet; // A reference to the PApplet class.
	ArrayList<GameObject> objects;
	Map map;
	Player player;
	int level = 0, lives = 3, score = 0, number_of_enemies = 0;
	Minim minim; // Required to use Minim.
	AudioPlayer snd_explode;
	
	// We initialize the game by doing Game game = new Game(this)
	// the 'this' argument is our PApplet window.
	public Game(PApplet applet){
		this.applet = applet;
		this.applet.size(700, 700);
		ready_img = applet.loadImage("data/start.jpg");
		minim = new Minim(this.applet); // Required to use Minim.
		snd_explode = minim.loadFile("data/explosion.wav");
		// Our game objects.
		map = new Map(this.applet);
		player = new Player(this.applet);
		// An ArrayList for our game objects.
		objects = new ArrayList<GameObject>();
		// Add the game objects to the ArrayList.
		objects.add(map);
		objects.add(player);
	} // End constructor.
	
	public void run(){
		
		// ready.
	    if ( state == "ready" )
	    {
	      readyGame();
	    } // End ready.

	    // run.
	    if ( state == "run" )
	    {
	      runGame();
	    } // End run.

	    // over.
	    if ( state == "over" )
	    {
	    	overGame();
	    } // End over.
		
	} // End run.
	
	public void keyPressed(){
		
	    if ( state == "ready" )
	    {
	      if(applet.keyCode == 83){ // Press 's' to start.
	    	  state = "run";
	      }
	    } // End ready.

	    if ( state == "run" )
	    {
	    	player.keyPressed();
	    } // End run.
	    
	    if ( state == "over" )
	    {
	    	if(applet.keyCode == 82){ // Press 'r' to restart.
		    	applet.setup();
		    }
	    } // End over.
	} // End keyPressed.
	
	public void keyReleased(){
		player.keyReleased();
	} // End keyPressed.
	
	
	public void readyGame() {
		applet.image(ready_img, 0, 0, applet.width, applet.height);
		applet.textAlign(PApplet.CENTER);
		
    	applet.textSize( 26 );
    	applet.fill(0, 0, 0, 255);
		applet.text("Instructions", (applet.width/2) + 3, 53);
		applet.text("Use the arrow keys to control the player", (applet.width/2) + 3, 103);
		applet.text("Press 'E' to fire", (applet.width/2) + 3, 153);
		
    	applet.fill(255, 255, 255, 255);
		applet.text("Instructions", applet.width/2, 50);
		applet.text("Use the arrow keys to control the player", applet.width/2, 100);
		applet.text("Press 'E' to fire", applet.width/2, 150);
		applet.noFill();
	} // End readyGame.
	
	public void runGame() {
		
		set_level();
		
		// Run all the game objects.
		for (int i = 0; i < objects.size(); ++i) {	
			
			// The current object is an Enemy.
			if(objects.get(i) instanceof Enemy){
				
				Enemy enemy = (Enemy) objects.get(i);
				
				// Enemy collides with Player.
				if( collide(enemy, player) ){
					float tmp = enemy.theta;
					enemy.theta = player.theta;
					player.theta = tmp;
					player.health -= 10;
				} // End Enemy collides with Player.
				
				// Loop through the player's objects.
				for (GameObject bullet : player.objects) {
					// Player's bullet hits enemy.
					if(collide(enemy, bullet)){
						bullet.alive = false;
						enemy.health -= 10;
						score += 10;
						break;
					} // End Player's bullet hits enemy.
				} // End Loop through the player's ammunition.
				
				
				// Enemies bullet hits Player.
				for (GameObject bullet : enemy.objects) {
					if(collide(player, bullet)){
						bullet.alive = false;
						player.health -= 10;
						break;
					}
				} // End Player's bullet hits enemy.
				
				
				
				if(enemy.health <= 15)
					enemy.objects.add(new Smoke(applet, enemy.location.x + (enemy.w/2), enemy.location.y + (enemy.h/2) ));
				if (enemy.health <= 0) {
					snd_explode.rewind();
					snd_explode.play();
					enemy.alive = false;
					--number_of_enemies;
				}
				
				
			} // End The current object is an Enemy.
			
			
			if(player.health <= 15)
				player.objects.add(new Smoke(applet, player.location.x + (player.w/2), player.location.y + (player.h/2) ));
			if (player.health <= 0 && lives > 0) {
				--lives;
				player.health = 50;
			}
			
			if(lives <= 0){
				state = "over";
				player.alive = false;
			}
			
			objects.get(i).run();
			if (!objects.get(i).alive)
				objects.remove(i);
			
		} // End Run all the game objects.
		
		
		draw_scores();
		
		
	} // End runGame.
	
	public void overGame(){
		map.run();
		draw_scores();
		applet.textAlign(PApplet.CENTER);
		applet.textSize( 36 );
		
		applet.fill(0, 0, 0, 255);
		applet.text("Game Over", (applet.width/2) + 3, 303);
		applet.text("Press 'R' to Restart", (applet.width/2) + 3, 353);
		
		applet.fill(255, 255, 255, 255);
		applet.text("Game Over", applet.width/2, 300);
		applet.text("Press 'R' to Restart", applet.width/2, 350);
		applet.noFill();
	} // End overGame.
	
	public void draw_scores() {
		applet.textAlign(PApplet.LEFT);
		applet.textSize( 26 );
		applet.fill(255, 255, 255, 255);
		applet.text("Lives " + lives, 30, 50);
		applet.text("Level " + level, 30, 100);
		applet.text("Score " + score, 30, 150);
		applet.noFill();
	} // End draw_scores.
	
	public void set_level() {
		if(number_of_enemies == 0){
			++level;
			createEnemies(level);
		}
	} // End set_level.
	
	public void createEnemies( int amount ){
		for ( int i = 0; i < amount; i++ ) {
			Enemy enemy = new Enemy(this.applet);
			Random random = new Random();
			enemy.fireRate = random.nextFloat();
			enemy.theta = random.nextFloat() * 2 - 1;
			enemy.colour = new Color(0, 100, 0, 255);
			enemy.location = new PVector(-500, -500);
			objects.add(enemy);
			++number_of_enemies;
		}
	} // End createEnemies method.
	
	private boolean collide(GameObject obj1, GameObject obj2) {
		if(obj1.location.x + obj1.w > obj2.location.x &&
		   obj1.location.x < obj2.location.x + obj2.w &&
		   obj1.location.y + obj1.h > obj2.location.y &&
		   obj1.location.y < obj2.location.y + obj2.h){
			return true;
		}
	    return false;
	} // End collide.
	
} // End Game class.
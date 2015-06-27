package ie.dit;

/*
 * This is our main sketch window, it extends the PApplet class.
 */


import processing.core.PApplet;




@SuppressWarnings("serial")
public class HelicopterAttack extends PApplet{
	
	// The game itself is encapsulated into the Game class.
	
	// Declare an instance of the Game class.
	Game game;
	
	
	
	public void setup(){
		/* Here we initialize the game.
		 * Passing 'this' as an argument to the instance,
		 * of the game gives us access to the PApplet.
		 */
		
		game = new Game(this);
	} // End setup.
		
	public void draw(){
		background(0,255,0);
		stroke(255);
		game.run();
	} // End draw.
		
	public void keyPressed(){
		
		// Hook onto the keyPressed event.
		game.keyPressed();
	} // End keyPressed.
		
	public void keyReleased(){
		// Hook onto the keyReleased event.
		game.keyReleased();
	} // End keyReleased.
	
} // End HelicopterAttack class.
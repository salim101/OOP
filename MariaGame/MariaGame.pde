// Sound Variables 
import ddf.minim.*; // import library for sound 
Minim minim; // provides library of classes that work with sound files
AudioPlayer Background_Sound; // set background sound while game in play
AudioPlayer Coin_Sound; // set coin sound
AudioPlayer Lives_Down_Sound; // set lives down sound
AudioPlayer Lives_Up_Sound; // set bonus live sound
 

//--------------------------------------------
// Fonts and Images
PFont Game_Name_Font, font1, font2;
PImage Main_Background_Image, Game_Background_Image, Super_Mario_image, Coin_Image, Turtle_Image, Flower_Image, Game_Over_Image;
//----------------------------------------------

//Game Possition
String Game_Possition = "Game_Begin_Mode";

//-----------------------------------------------

// Mario Variable 
int Mario_W;//width
int Mario_H;//height
int Mario_X;//position 
int Mario_Y;//position
int Mario_Speed;//speed

//----------------------------------------------

// Turtle Variables
int Turtle_W;//width
int Turtle_H;//height
float Turtle_X;//position
float Turtle_Y;//position
int Turtle_Speed = 3;//speed

float [] Turtle_Array_X = new float [5];//Turtle Array
float [] Turtle_Array_Y = new float [5];//Turtle Array

//------------------------------------------------

// Coin Variables
int Coin_W; // coin width
int Coin_H; // coin height
float Coin_X; // possiton
float Coin_Y; // possition
int Coin_Speed = 3; // speed

float [] Coin_Array_X = new float [5]; // coin array
float [] Coin_Array_Y = new float [5]; // coin array

//--------------------------------------------------

// Flower Variables
int Flower_W; // flower width
int Flower_H; // flower height
float Flower_X; // possition
float Flower_Y;// possition
int Flower_Speed = 8; //flower speed

float [] Flower_Array_X = new float [1]; //flower array
float [] Flower_Array_Y = new float [1]; //flower array

//--------------------------------------------------

//Extra Variables Needed
int Score = 0; // score of user
int Lives = 3; // lives of user
int Level = 1; // level of user
int Cent_X; // possition
int Cent_Y; // possition
int Main_Mario_W; //width variable of maria pic on main and game over screen
int Main_Mario_H; //height variable of maria pic on main and game over screen

//--------------------------------------------------

void setup() {
  size(500, 500);//size of the screen
  minim = new Minim(this);
  Background_Sound= minim.loadFile("Game_Background.wav");//load the background sound
  Coin_Sound= minim.loadFile("Coin_Collection.wav");//load the coin collection sound
  Lives_Down_Sound= minim.loadFile("Live_Down.wav");//load the lives down sound
  Lives_Up_Sound= minim.loadFile("Live_Up.wav");//load the bonus live sound
  //player.play();
  //background(0);//black background

  Main_Background_Image=loadImage("Main_Image.jpg");//load the main background image
  Game_Background_Image=loadImage("Game_Background_Image.png");//load the game background image
  Super_Mario_image=loadImage("Super_Mario_Image.png");//load the mario image
  Coin_Image=loadImage("Coin_Image.png");//load the coin image
  Turtle_Image=loadImage("Turtle_Image.png");//load the turtle image
  Flower_Image=loadImage("Flower_Image.png");//load the flower image
  Game_Over_Image=loadImage("Game_Over_Image.jpg");//load the game over image

    Game_Name_Font=createFont("Wide Latin", 20);//create a font for game name
  font1=createFont("Britannic Bold", 25);//create a font
  font2=createFont("Agency FB", 18);//create a font

  Cent_X = width / 2; // divide the width
  Cent_Y = height / 2; // divide the height

  Main_Mario_W = 150;  //width of maria pic on main and game over screen
  Main_Mario_H = 150; //height of maria pic on main and game over screen

  //Mario Setup 
  Mario_W = 30; // width
  Mario_H = 30; // height
  Mario_X = (width/2)-(Mario_W/2); // width possiton to be right in center 
  Mario_Y = (height-Mario_H); // height possiton to be right in center
  Mario_Speed = 20; // mario movement speed

  //------ Turtle Setup ----------------
  for (int i = 0; i < Turtle_Array_X.length; i++) {
    Turtle_W = 30;//width
    Turtle_H = 30;//height
    Turtle_X = random(0, width - Turtle_W); // width possition
    Turtle_Y = random(-height, - Turtle_H); // height possition
    Turtle_Array_X[i] = Turtle_X; // give the turtle possition to the turtle array
    Turtle_Array_Y[i] = Turtle_Y; // give the turtle possition to the turtle array
  }

  //------------Coin Setup -----------------
  for (int i = 0; i < Coin_Array_X.length; i++) {
    Coin_W = 30; // width
    Coin_H = 30; // height
    Coin_X = random(0, width - Coin_W); // width possition
    Coin_Y = random(-height, - Coin_H); // height possition
    Coin_Array_X[i] = Coin_X; // give the coin possition to the coin array
    Coin_Array_Y[i] = Coin_Y; // give the coin possition to the coin array
  }

  //----------Flower Setup ------------------
  for (int i = 0; i < Flower_Array_X.length; i++) {
    Flower_W = 30; //width
    Flower_H = 30; //height
    Flower_X = random(0, width - Coin_W); //width possition
    Flower_Y = random(-height, - Coin_H); // height possition
    Flower_Array_X[i] =  Flower_X; // give the coin possition to the coin array
    Flower_Array_Y[i] =  Flower_Y; // give the coin possition to the coin array
  }
}//end setup


void draw() {

  if (Game_Possition == "Game_Begin_Mode") { 
    image(Main_Background_Image, 0, 0); //display the main background image
    textAlign(CENTER);
    textFont(Game_Name_Font);
    fill(0);//black colour
    stroke(0); //black colour
    text("Saving Mario from Turtle's", Cent_X, Cent_Y-210);// display the game name
    textFont(font1);
    stroke(255, 0, 0); //red colour
    fill(255, 0, 0); // red colour
    text("Instruction", Cent_X, Cent_Y-160); // display the instruction title

    textFont(font2);
    fill(0); //black colour
    text("1) Left and Right arrow keys to move the Mario", Cent_X, Cent_Y-120); // display instruction 1
    text("2) Avoid touching Turtles !", Cent_X-60, Cent_Y-90); // display instruction 2
    text("3) Get many Coins as possible to increase your Score", Cent_X+18, Cent_Y-60); // display instruction 3
    text("4) Get a Flower for a Bonus live", Cent_X-45, Cent_Y-30); // display instruction 4

    textFont(font1);
    text("Press Space to Play the Game", Cent_X, Cent_Y+20); //Display to user to press space to play the game 

    image(Super_Mario_image, Cent_X-80, Cent_Y+50, Main_Mario_W, Main_Mario_H); // Display mario image on the main screen
  } else if (Game_Possition == "Game_Play_Mode") { //if game is in play mode
    //Background_Sound.play(); 
    //Background_Sound.rewind();

    if (!Background_Sound.isPlaying()) { // check if background sound is playing or not, if not then 
      Background_Sound.play(); // play the background sound 
      Background_Sound.rewind(); // when background sound is finish, re-play the sound
    }

    image(Game_Background_Image, 0, 0);//background image for game in play mode

    textFont(font2);//font for score, lives and level
    stroke(255); // white colour
    fill(255); // white colour
    text("Score : ", Cent_X-220, Cent_Y-220); // Display the score text 
    text(Score, Cent_X-180, Cent_Y-220); // display the user score
    text("Lives : ", Cent_X-220, Cent_Y-190); // Display the lives text 
    text(Lives, Cent_X-180, Cent_Y-190); // display the user lives
    text("Level : ", Cent_X-220, Cent_Y-160); // Display the level text 
    text(Level, Cent_X-180, Cent_Y-160); // display the user level

    //fill(255);
    //stroke(255);
    //rect(Mario_X, Mario_Y, Mario_W, Mario_H);
    image(Super_Mario_image, Mario_X, Mario_Y, Mario_W, Mario_H);//Draw Mario
    GameInPlay();// call gameInPlay method
  } else if (Game_Possition == "Game_Over_Mode") { // game possition is game over then

    //background(255);
    image(Game_Over_Image, 0, 0); // display game over background image 
    textAlign(CENTER);
    textFont(Game_Name_Font);
    fill(0); // black colour
    stroke(0); // black colour
    text("Saving Mario from Turtle's", Cent_X, Cent_Y-210); // Display game name 
    textFont(font1);
    fill(255, 0, 0);// red colour
    stroke(255, 0, 0);// red colour
    text("Game Over", Cent_X, Cent_Y-150); // Display game over text
    text("Score : ", Cent_X-10, Cent_Y-100); // Display score text on game over screen
    text(Score, Cent_X+50, Cent_Y-100); // Display user score on game over screen
    text("Level  : ", Cent_X-10, Cent_Y-60); // Display level text on game over screen
    text(Level, Cent_X+50, Cent_Y-60); // Display user level on game over screen
    fill(0); // black colour
    stroke(0); // black colour
    text("Press Space to Restart the Game", Cent_X, Cent_Y+10); // Display to user to press space to restart the game
    image(Super_Mario_image, Cent_X-80, Cent_Y+50, Main_Mario_W, Main_Mario_H); // display the mario image on game ove scren
  }
}//end draw

void GameInPlay() {

  //-------------Displaying Turtles ---------------
  //fill(255, 0, 0);
  //stroke(255, 0, 0);
  for (int i=0; i < Turtle_Array_X.length; i++) { //loop to display the turtles in game 
    //rect(Turtle_Array_X[i], Turtle_Array_Y[i], Turtle_W, Turtle_H);
    image(Turtle_Image, Turtle_Array_X[i], Turtle_Array_Y[i], Turtle_W, Turtle_H); //display the turtle
    Turtle_Array_Y[i] += Turtle_Speed; // give the turtle speed to turtle array 
    if (Turtle_Array_Y[i] > height) { // check if each turtle is greater than height, if it does then
      Turtle_Array_X[i] = random(0, width - Turtle_W); //display random turle between the width of the screen minus the width of the turtle
      Turtle_Array_Y[i] = random(-height, - Turtle_H); //display random turle between the minus height of the screen minus the height of the turtle
    }
    if (Turtle_Array_Y[i]+Turtle_H > Mario_Y && Turtle_Array_X[i] + Turtle_W > Mario_X && Turtle_Array_X[i] < Mario_X + Mario_W) { // check if the turtle touch the mario, if it does then 
      Lives_Down_Sound.play(); // play a sound when the turtle touch the mario
      Lives_Down_Sound.rewind(); // re-play a sound when the turtle touch the mario
      //rect(Turtle_Array_X[i], Turtle_Array_Y[i], Turtle_W, Turtle_H);
      image(Turtle_Image, Turtle_Array_X[i], Turtle_Array_Y[i], Turtle_W, Turtle_H); //display the turtle
      Turtle_Array_X[i] = random(0, width - Turtle_W); // give it a random width between the width of the screen minus the turtle width
      Turtle_Array_Y[i] = random(-height, - Turtle_H); // give it a random height between the minus height of the screen minus the turtle height

      Lives--; // take one lives away from the user

      if (Lives == 0) { // check if the lives is equal to 0, if it does then
        Game_Possition = "Game_Over_Mode"; // calll the game over mode
        Background_Sound.pause(); // pause the game background sound
      }//end if
    }
  }//end for

  //-------------Displaying Coins ---------------
  //fill(255, 255, 0);
  //stroke(255, 255, 0);
  for (int i=0; i < Coin_Array_X.length; i++) { //loop to display the turtles in game 
    //rect(Coin_Array_X[i], Coin_Array_Y[i], Coin_W, Coin_H);
    image(Coin_Image, Coin_Array_X[i], Coin_Array_Y[i], Coin_W, Coin_H); //display the coin
    Coin_Array_Y[i] += Coin_Speed; // give the coin speed to coin array
    if (Coin_Array_Y[i] > height) { // check if each coin is greater than height, if it does then
      Coin_Array_X[i] = random(0, width - Coin_W); //display random coin between the width of the screen minus the width of the coin
      Coin_Array_Y[i] = random(-height, - Coin_H); //display random coin between minus the height of the screen minus the height of the coin
    }//end if
    if (Coin_Array_Y[i]+Coin_H > Mario_Y && Coin_Array_X[i] + Coin_W > Mario_X && Coin_Array_X[i] < Mario_X + Mario_W) { // check if the coin touch the mario, if it does then
      //player.play();
      Coin_Sound.play(); // play a sound when the coin touch the mario
      Coin_Sound.rewind(); // re-play a sound when the coin touch the mario
      //rect(Coin_Array_X[i], Coin_Array_Y[i], Coin_W, Coin_H);
      image(Coin_Image, Coin_Array_X[i], Coin_Array_Y[i], Coin_W, Coin_H); //display the coin
      Coin_Array_X[i] = random(0, width - Coin_W); // give it a random width between the width of the screen minus the coin width
      Coin_Array_Y[i] = random(-height, - Coin_H); // give it a random height between minus the height of the screen minus the coin height

      Score++; //increment the user score 

      if (Score % 4 == 0) { // check if the user is dividable by 4, if it does then
        Level++; // increment the user level
        Turtle_Speed++; // increment the turtle's speed
        Coin_Speed++; // increment the coins speed
      }//end if
      /*
  
       if(Score >= 4){
       Level = 2;
       Turtle_Speed = 4;//speed
       Coin_Speed = 4;
       }
       if(Score >= 8){
       Level = 3;
       Turtle_Speed = 5;//speed
       Coin_Speed = 5;
       }
       if(Score >= 12){
       Level = 4;
       Turtle_Speed = 6;//speed
       Coin_Speed = 6;
       }
       if(Score >= 16){
       Level = 5;
       Turtle_Speed = 7;//speed
       Coin_Speed = 7;
       }
       if(Score >= 20){
       Level = 6;
       Turtle_Speed = 8;//speed
       Coin_Speed = 8;
       }
       if(Score >= 24){
       Level = 7;
       Turtle_Speed = 9;//speed
       Coin_Speed = 9;
       }
       if(Score >= 28){
       Level = 8;
       Turtle_Speed = 10;//speed
       Coin_Speed = 10;
       }
       if(Score >= 32){
       Level = 9;
       Turtle_Speed =11;//speed
       Coin_Speed =11;
       }
       if(Score >= 35){
       Level = 10;
       Turtle_Speed =12;//speed
       Coin_Speed =12;
       }
       if(Score >= 40){
       Level = 11;
       Turtle_Speed =14;//speed
       Coin_Speed =14;
       }
       if(Score >= 40){
       Level = 12;
       Turtle_Speed =17;//speed
       Coin_Speed =17;
       }*/
    }
  }//end for

  //-------------Displaying Flower ---------------

  for (int i=0; i < Flower_Array_X.length; i++) { //loop to display the flower in game
    //rect(Flower_Array_X[i], Flower_Array_Y[i], Flower_W, Flower_H);
    image(Flower_Image, Flower_Array_X[i], Flower_Array_Y[i], Flower_W, Flower_H); //display the flower
    Flower_Array_Y[i] += Flower_Speed; // give the flower speed to flower array
    if (Flower_Array_Y[i] > height) { // check if each flower is greater than height, if it does then
      Flower_Array_X[i] = random(0, width - Flower_W); //display random flower between the width of the screen minus the width of the flower
      Flower_Array_Y[i] = random(-height*10, - height*7); //display random flower between minus the height of the screen multiply by 10 and  minus the height of the coin and multply by 7 to delay the flower
    }//end if
    if (Flower_Array_Y[i]+Flower_H > Mario_Y && Flower_Array_X[i] + Flower_W > Mario_X && Flower_Array_X[i] < Mario_X + Mario_W) { // check if the flower touch the mario, if it does then
      Lives_Up_Sound.play(); // play a sound when the flower touch the mario
      Lives_Up_Sound.rewind(); // re-play a sound when the coin touch the mario
      //rect(Flower_Array_X[i],Flower_Array_Y[i], Flower_W, Flower_H);
      image(Flower_Image, Flower_Array_X[i], Flower_Array_Y[i], Flower_W, Flower_H); //display the coin
      Flower_Array_X[i] = random(0, width - Flower_W); // give it a random width between the width of the screen minus the flower width
      Flower_Array_Y[i] = random(-height*10, - height*7); // give it a random height between minus the height of the screen multiply by 10 and  minus the height of the coin and multply by 7 to delay the flower

      if (Lives < 3) {//check if user lives is less then 3, if it is then 
        Lives ++; //increment user live by 1
      }//end if
    }
  }//end for
}// end GameInPlay

void keyPressed() {
  if (key==CODED) {
    if (keyCode == RIGHT) { // check if right arraw key is pressed, if it is then
      if (Mario_X < width-Mario_W) { //check if mario possition is less then width minus mario width, if it does then
        Mario_X += Mario_Speed;//move the mario to right using mario speed
      }
    } else if (keyCode == LEFT) { // check if left arraw key is pressed, if it is then
      if (Mario_X > 0) { //check if mario possition is greater then 0, if it does then
        Mario_X -= Mario_Speed; //move the mario to the left using mario speed
      }
    }
  } else if (key == ' ') { // check if space key is pressed, if it is then
    if (Game_Possition == "Game_Begin_Mode" || Game_Possition == "Game_Over_Mode") { //check if game possition is equal to game begin mode or game over mode, 
      Game_Possition = "Game_Play_Mode";// call the game play mode
      //Background_Sound.play(); 
      //Background_Sound.rewind();
      if (!Background_Sound.isPlaying()) { // check if background sound is playing or not, if not then 
        Background_Sound.play(); // play the background sound 
        Background_Sound.rewind(); // when background sound is finish, re-play the sound
      }
      Score = 0; //reset the score to 0
      Level = 1; //reset the level to 1
      Lives = 3; //reset the lives to 3
      Turtle_Speed = 3; //reset the Turtle speed
      Coin_Speed = 3; //reset the Coin speed

      for (int i = 0; i <Turtle_Array_Y.length; i ++ ) {//loop to reset the coin and turtle 
        Turtle_Array_Y[i] = random(-height, - Turtle_H);
        Coin_Array_Y[i] = random(-height, - Coin_H);
      }//end for
    }//end if
  }//end else if
}//end keyPressed


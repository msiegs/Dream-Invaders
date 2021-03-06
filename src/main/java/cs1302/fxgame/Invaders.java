// Created by Matt Siegel
// Contact info: msiegs@uga.edu

package cs1302.fxgame;

import java.lang.Runnable;
import javafx.scene.text.Font;
import com.michaelcotterell.game.Game;
import com.michaelcotterell.game.GameTime;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.control.Label;
import javafx.scene.text.FontWeight;
import java.util.ArrayList;
import java.util.EventListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.Node;
import java.util.Random;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.text.TextAlignment;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Invaders extends Game {

    // booleans for which screen should be displayed
    boolean mainMenu = true; // if true, loads the main menu
    boolean playGame = false; // if true, starts running the game engine
    boolean instructions = false; // if true, loads the instructions menu 
    boolean mysteryEvent = false; // if true, mystery ship appears
    int lives = 3;
    Random random = new Random();
    boolean keyPressed = false; // helps keep track of the UP key -- allows a delay between bullets shot
    int score = 0;
    int numBarriers = 8;
    boolean moveRight = true;
    boolean restart = false;
    int round = 0;
    double invaderSpeed = 0.1; // this increases with every round
    int farRightCol = 10;
    int farLeftCol = 0;
    int bottomRow = 0;
    int waitTime = 0; // used to create a delay between user shots
    int invaderWait; // used to create a delay between invader shots
    int mysteryTime; // user to create a random time for mystery ship to show up
    //Runnable r = player.seek(Duration.ZERO);
    String songPath = getClass().getResource("/domo23.mp3").toString();
    Media domo23 = new Media(songPath);
    MediaPlayer player = new MediaPlayer(domo23);
    //player.setOnEndOfMedia(r);
    public Image nerdImg = new Image("/tylernerd.png", 40, 40, true, true); // source: <http://s3-us-west-2.amazonaws.com/hypebeast-wordpress/image/2013/03/tyler-the-creator-wolf-album-stream-0.jpg> edited by: Matt Siegel
    public Image goblinImg = new Image("/goblin.png", 40, 40, true, true); // source: <http://i.imgur.com/m2Bhh4s.png> edited by: Matt Siegel
    public Image cherryImg = new Image("/cherrybomb.png", 40, 40, true, true); // source: <http://ih0.redbubble.net/image.64205676.9858/sticker,220x200-pad,220x200,ffffff.u3.jpg> edited by: Matt Siegel
    public Image barrierImg = new Image("/donutBarrier.gif", 52, 52, true, true); // source: <https://media.giphy.com/media/prnfp4oaU0Zby/giphy.gif>
    public Image barrier2 = new Image("/donut2.png", 52, 52, true, true); // source: <https://media.giphy.com/media/prnfp4oaU0Zby/giphy.gif> edited by: Matt Siegel
    public Image barrier3 = new Image("/donut3.png", 52, 52, true, true); // source: <https://media.giphy.com/media/prnfp4oaU0Zby/giphy.gif> edited by: Matt Siegel
    public Image invaderBullet = new Image("/sleep.png", 25, 25, true, true); // source: iOS Emoji Library
    public Image tylerImg = new Image("/tyler.png", 98, 91, true, true); // source: <https://thehunt.insnw.net/app/public/system/note_images/3905636/original/22a9d368d9b7c3d8ec6115670c306216.png>
    public Image lifeImg = new Image("/tyler.png", 20, 20, true, true); // source: <https://thehunt.insnw.net/app/public/system/note_images/3905636/original/22a9d368d9b7c3d8ec6115670c306216.png>
    public Image tronCat = new Image("/troncat.gif", 50, 50, true, true); // source: <https://45.media.tumblr.com/e29421321141b6e57664b3d4fe3db1a1/tumblr_n98asba2ck1r4vjuso1_500.gif>
    ImageView mysteryShip = new ImageView(tronCat) {{
	setTranslateX(800);
	setTranslateY(30);
    }}; // mysteryShip
    public Image yellowBeanie = new Image("/yellowBeanie.jpg", 800, 600, false, true); // source: <http://photon.101medialablimit.netdna-cdn.com/hypebeast.com/image/2014/11/tyler-the-creator-diaper-0.jpg?w=800>
    ImageView beanie = new ImageView(yellowBeanie) {{
	setTranslateX(0);
	setTranslateY(0);
    }}; // yellowBeanie
    public Image arrowKeys = new Image("/arrowKeys.png", 175, 175, true, true); // source: <http://www.101computing.net/wp/wp-content/uploads/arrowKeys.png> edited by: Matt Siegel
    ImageView arrows = new ImageView(arrowKeys) {{
	setTranslateX(33);
	setTranslateY(200);
    }}; // arrows
    public Image instructionsPage = new Image("/instructionsPage.png", 800, 600, false, true); // created by: Matt Siegel
    ImageView instructionsMenu = new ImageView(instructionsPage) {{
	setTranslateX(0);
	setTranslateY(0);
    }}; // instructionsMenu
    public Image tylerSad = new Image("/gameOver.jpg", 800, 600, false, true); // source: <http://www.flippenmusic.com/wp-content/uploads/2015/05/TylerSad_FlippenMusic.jpg>
    ImageView gameOverPage = new ImageView(tylerSad) {{
	setTranslateX(0);
	setTranslateY(0);
    }}; // gameOverPage
    public Image tylerFloat = new Image("/tylerfloat.png", 100, 100, true, true); // source: <http://25.media.tumblr.com/c34c5b495492ae0a658744908c54835a/tumblr_mwa2ifAGU51snslrwo1_500.png>
    ImageView tylerAnimation = new ImageView(tylerFloat) {{
	setTranslateX(0);
	setTranslateY(475);
    }}; // tylerAnimation
    private Button startButton = new Button("START") {{
	setTranslateX(571.0);
	setTranslateY(18.0);
	setPrefHeight(100.0);
	setPrefWidth(197.0);
	setStyle("-fx-background-color: #FDC68C");
	setTextFill(Color.WHITE);
	setFont(Font.font ("System Bold", FontWeight.BOLD, 24));
    }}; // startButton
    private Button instructionsButton = new Button("INSTRUCTIONS") {{
	setTranslateX(601.0);
	setTranslateY(162.0);
	setPrefHeight(75.0);
	setPrefWidth(135.0);
	setStyle("-fx-background-color: #91DBF9");
	setTextFill(Color.WHITE);
	setFont(Font.font ("System Bold", FontWeight.BOLD, 13));
    }}; // instructionsButton
    private Button backButton = new Button("BACK") {{
	setTranslateX(600);
	setTranslateY(450);
	setPrefHeight(100);
	setPrefWidth(175);
	setStyle("-fx-background-color: #91DBF9");
	setTextFill(Color.WHITE);
	setFont(Font.font("System Bold", FontWeight.BOLD, 28));
    }}; // backButton
    private FlashText flashText = new FlashText() {{
	setText("PRESS ENTER");
	setTranslateX(100);
	setTranslateY(400);
	setPrefHeight(100);
	setPrefWidth(300);
	setTextFill(Color.web("#FF00FF"));
	setFont(Font.font("System Bold", FontWeight.BOLD, 28));
    }}; // flashText
    private Label storyLabel = new Label("Help! Tyler has a big show tonight, but he accidentally took Nyquil instead of Dayquil and now he's fast asleep! Help defeat his dream invaders so that he can wake up in time for the show.") {{
	setTranslateX(33.0);
	setTranslateY(7.0);
	setPrefHeight(167.0);
	setPrefWidth(292.0);
	setTextFill(Color.web("#17136f"));
	setFont(Font.font("System Bold", 18));
	setWrapText(true);
    }}; // storyLabel
    Label scoreLabel = new Label("Score:  " + score) {{
	setTranslateX(5);
	setTranslateY(5);
	setTextFill(Color.web("#13bc11"));
	setFont(Font.font("Chalkduster", FontWeight.BOLD, 19));
    }}; // scoreLabel
    Label livesLabel = new Label("Lives: ") {{
	setTranslateX(650);
	setTranslateY(5);
	setTextFill(Color.web("#13bc11"));
	setFont(Font.font("System", FontWeight.BOLD, 19));
    }}; // livesLabel
    Label gameOverLabel = new Label("GAME OVER!") {{
	setTranslateX(40);
	setTranslateY(200);
	setTextFill(Color.web("#FF00FF"));
	setFont(Font.font("System Bold", FontWeight.BOLD, 48));
    }}; // gameOverLabel

    ImageView[] livesArray = new ImageView[3]; // keeps track of the number of lives that should be displayed
    // array lists for bullets, barriers, and invaders
    public ArrayList<TylerBullet> tylersBullets = new ArrayList<>(); // keeps track of the number of tyler's bullets on the screen. this is useful for collisions and bullet movement.
    public ArrayList<ImageView> invaderBullets = new ArrayList<>(); // keeps track of the number of invader bullets on the screen. this is useful for collisions and bullet movement.
    public ImageView[] barriers = new ImageView[8]; // keeps track of barriers and barrier images based on health
    public int[] barrierHealth = new int[8]; // keeps track of the health of each barrier

    // public Enemy[][] invaderArray = new Enemy[5][11];
    public ImageView[][] invaderArray = new ImageView[5][11]; // keeps track of invaders, both alive and dead

    // separate array list to keep track of each column array
    //public ArrayList<Enemy[]> invaderColumns = new ArrayList<>();
    private Rectangle bkgd = new Rectangle(0, 0, 800, 600) {{
	setFill(Color.ORANGE);
    }}; // background

    ImageView tyler = new ImageView(tylerImg) {{ // this is what you control!
	    setTranslateX(25);
	    setTranslateY(500);
    }};
    
    public Invaders(Stage stage) {
	super(stage, "Dream Invaders", 60, 800, 600);
	startButton.setOnMouseClicked(new EventHandler<MouseEvent>() { // event handler for start button
		@Override
		    public void handle(MouseEvent event) {
		    flashText.stop();
		    mainMenu = false;
		    startGame();
		    playGame = true;
		} // handle MouseEvent
	    }); // onMouseClicked startButton
	player.play();
	instructionsButton.setOnMouseClicked(new EventHandler<MouseEvent>() { // event handler for instructions button
		@Override
		    public void handle(MouseEvent event) {
		    mainMenu = false;
		    instructions = true;
		    try {
		    Thread.sleep(1000); // slight delay between button press and new screen
		    } catch (InterruptedException e) { }
		    instructionsPressed();
		} // handle MouseEvent
	    }); // onMouseClicked instructionsButton
	backButton.setOnMouseClicked(new EventHandler<MouseEvent>() { // event handler for back button
		@Override
		    public void handle(MouseEvent event) {
		    mainMenu = true;
		    instructions = false;
		    try {
			Thread.sleep(1000); // slight delay between button press and new screen
		    } catch (InterruptedException e) { }
		    getSceneNodes().getChildren().clear();
		    getSceneNodes().getChildren().addAll(bkgd, beanie, startButton, instructionsButton, storyLabel, tylerAnimation);
		} // handle MouseEvent
	    }); // onMouseClicked backButton
	getSceneNodes().getChildren().addAll(bkgd, beanie, startButton, instructionsButton, storyLabel, tylerAnimation, flashText);
	flashText.play();
    } // Invaders


    @Override
	public void update(Game game, GameTime gameTime) {
	if (mainMenu) {
	    if (getSceneNodes().getChildren().contains(flashText) == false) getSceneNodes().getChildren().add(flashText);
	    if (tylerAnimation.getTranslateX() < game.getSceneBounds().getMaxX()) tylerAnimation.setTranslateX(tylerAnimation.getTranslateX() + 2);
	    else tylerAnimation.setTranslateX(0);
	    if (game.getKeyManager().isKeyPressed(KeyCode.ENTER)) {
		flashText.stop();
		mainMenu = false;
		startGame();
		playGame = true;
	    } // if ENTER is pressed
	} // moves tyler animation on main menu
	if (playGame) {
	    try {
		invaderLoop(game); // continuously moves the invaders!
	    } catch (InterruptedException e) { }
	    bulletBounds(game); // runs the bullet bounds method to check for any bullets that have ventured off the screen
	    bulletHitsInvader();
	    bulletHitsBarrier();
	    bulletHitsTyler();
	    if (waitTime > 0) waitTime--;
	    if (invaderWait > 0) invaderWait--;
	    if (mysteryTime > 0) mysteryTime--;
	    if (invaderWait == 0) {
		if (round > 16) invaderWait = random.nextInt(100);
		else invaderWait = random.nextInt(400 - (25*round));
		invaderShoot();
	    } // invaderWait
	    if (mysteryTime == 0) {
		mysteryTime = random.nextInt(5000);
		while (mysteryTime <= 1000) {
		    mysteryTime = random.nextInt(5000);
		} // while mysteryTime <= 1000
		mysteryShip(mysteryShip);
	    } // mysteryTime
	    if (mysteryEvent == true) {
		if (mysteryShip.getTranslateX() > game.getSceneBounds().getMinX()) {
		    mysteryShip.setTranslateX(mysteryShip.getTranslateX() - (3*invaderSpeed)); // moves the mystery ship across the screen
		    mysteryHit();
		} // if in bounds
		else {
		    mysteryEvent = false;
		    mysteryShip.setVisible(false);
		    mysteryShip.setDisable(true);
		    getSceneNodes().getChildren().remove(mysteryShip);
		} // when invader hits end of screen
	    } // if mysteryEvent is true
		
	    if (game.getKeyManager().isKeyPressed(KeyCode.UP) && keyPressed == false && waitTime == 0) {
		keyPressed = true;
		waitTime = 50;
		TylerBullet shoot = new TylerBullet();
		shoot.bulletImg.setTranslateX(tyler.getTranslateX() + 35);
		shoot.bulletImg.setTranslateY(475);
		getSceneNodes().getChildren().add(shoot.bulletImg);
		tylersBullets.add(shoot);
	    }
	    if (keyPressed == true && game.getKeyManager().isKeyPressed(KeyCode.UP) == false) {
		keyPressed = false;
	    }
	    if (tylersBullets.size() > 0) {
		for(int i = 0; i < tylersBullets.size(); i++) {
		    tylersBullets.get(i).bulletImg.setTranslateY(tylersBullets.get(i).bulletImg.getTranslateY() - 2.5);
		} // for -- move each bullet      
	    }
	    if (invaderBullets.size() > 0) {
		for (int i = 0; i < invaderBullets.size(); i++) {
		    if (invaderBullets.get(i).getTranslateY() < game.getSceneBounds().getMaxY()) invaderBullets.get(i).setTranslateY(invaderBullets.get(i).getTranslateY() + 2.5);
		    else invaderBullets.remove(invaderBullets.get(i));
		} // for -- move each bullet
	    }
	    if (game.getKeyManager().isKeyPressed(KeyCode.LEFT) && tyler.getTranslateX() > game.getSceneBounds().getMinX()) tyler.setTranslateX(tyler.getTranslateX() - 4);
	    if (game.getKeyManager().isKeyPressed(KeyCode.RIGHT) && tyler.getTranslateX() + 90 < game.getSceneBounds().getMaxX()) tyler.setTranslateX(tyler.getTranslateX() + 4);
	} // if
    } // update

    /* moves the invaders right
     * checks the bounds
     * once the invaders hit the bounds, moves them down, then left
     * lather, rinse, repeat
     */
    public void invaderLoop(Game game) throws InterruptedException {
	farRightCol = 10;
	farLeftCol = 0;
	bottomRow = 4;

	if (restart()) {
	    // removes all of the barriers before adding them
	    for (int i = 0; i < 8; i++) {
		if (barriers[i] != null) {
		    barriers[i].setVisible(false);
		    barriers[i].setDisable(true);
		    barriers[i] = null;
		} // if barrier is still alive
	    } 
	    if (mysteryEvent) {
		mysteryShip.setVisible(false);
		mysteryShip.setDisable(true);
		getSceneNodes().getChildren().remove(mysteryShip);
		mysteryEvent = false; 
	    } // mysteryEvent
	    initialize();
	} // if restart
	// check for the farthest bottom row that is still alive
	for (int row = bottomRow; row >= 0; row--) {
	    if (invaderArray[row][0] == null && invaderArray[row][1] == null && invaderArray[row][2] == null && invaderArray[row][3] == null && invaderArray[row][4] == null && invaderArray[row][5] == null && invaderArray[row][6] == null
		&& invaderArray[row][7] == null && invaderArray[row][8] == null && invaderArray[row][9] == null && invaderArray[row][10] == null) {
		bottomRow--;
	    }
	    else {
		break;
	    }
	} // for bottomRow
	if (moveRight) {
	    // check for empty columns -- finds the farthest right column that is still populated
	    //for(int col = farRightCol; col >= 0; col--) { // checks each column, starting with the farthest right
	    for (int col = farRightCol; col >= 0; col--) {
		if (invaderArray[0][col] == null
		    && invaderArray[1][col] == null
		    && invaderArray[2][col] == null
		    && invaderArray[3][col] == null
		    && invaderArray[4][col] == null) {
		    farRightCol--;
		} // if farthest right column is unpopulated
		else {
		    break;
		}
	    } // for col
	    
	    // if they are still in bounds
	    if ((invaderArray[0][farRightCol] != null && invaderArray[0][farRightCol].getTranslateX() + 45 < game.getSceneBounds().getMaxX())
		| (invaderArray[1][farRightCol] != null && invaderArray[1][farRightCol].getTranslateX() + 45 < game.getSceneBounds().getMaxX())
		| (invaderArray[2][farRightCol] != null && invaderArray[2][farRightCol].getTranslateX() + 45 < game.getSceneBounds().getMaxX())
		| (invaderArray[3][farRightCol] != null && invaderArray[3][farRightCol].getTranslateX() + 45 < game.getSceneBounds().getMaxX())
		| (invaderArray[4][farRightCol] != null && invaderArray[4][farRightCol].getTranslateX() + 45 < game.getSceneBounds().getMaxX())) {
		// for loop to move invaders right if they're still in bounds -- this is within this if statement
		for (int col = 0; col < 11; col++) {
		    for (int row = 0; row < 5; row++) {
			if (invaderArray[row][col] != null) invaderArray[row][col].setTranslateX(invaderArray[row][col].getTranslateX() + invaderSpeed);
		    } // for row
		} // for col
	    } // if still in bounds, move right!
	    // else -- if they hit the right bounds
	    else {
		// for loop to move invaders down if they're out of bounds
		for (int row = 4; row >= 0; row--) {
		    if ((invaderArray[bottomRow][0] != null && invaderArray[bottomRow][0].getTranslateY() + invaderArray[bottomRow][0].getImage().getHeight() < game.getSceneBounds().getMaxY())
			| (invaderArray[bottomRow][1] != null && invaderArray[bottomRow][1].getTranslateY() + invaderArray[bottomRow][1].getImage().getHeight() <game.getSceneBounds().getMaxY())
			| (invaderArray[bottomRow][2] != null && invaderArray[bottomRow][2].getTranslateY() + invaderArray[bottomRow][2].getImage().getHeight() <game.getSceneBounds().getMaxY())
			| (invaderArray[bottomRow][3] != null && invaderArray[bottomRow][3].getTranslateY() + invaderArray[bottomRow][3].getImage().getHeight() <game.getSceneBounds().getMaxY())
			| (invaderArray[bottomRow][4] != null && invaderArray[bottomRow][4].getTranslateY() + invaderArray[bottomRow][4].getImage().getHeight() <game.getSceneBounds().getMaxY())
			| (invaderArray[bottomRow][5] != null && invaderArray[bottomRow][5].getTranslateY() + invaderArray[bottomRow][5].getImage().getHeight() <game.getSceneBounds().getMaxY())
			| (invaderArray[bottomRow][6] != null && invaderArray[bottomRow][6].getTranslateY() + invaderArray[bottomRow][6].getImage().getHeight() <game.getSceneBounds().getMaxY())
			| (invaderArray[bottomRow][7] != null && invaderArray[bottomRow][7].getTranslateY() + invaderArray[bottomRow][7].getImage().getHeight() <game.getSceneBounds().getMaxY())
			| (invaderArray[bottomRow][8] != null && invaderArray[bottomRow][8].getTranslateY() + invaderArray[bottomRow][8].getImage().getHeight() <game.getSceneBounds().getMaxY())
			| (invaderArray[bottomRow][9] != null && invaderArray[bottomRow][9].getTranslateY() + invaderArray[bottomRow][9].getImage().getHeight() <game.getSceneBounds().getMaxY())
			| (invaderArray[bottomRow][10] != null && invaderArray[bottomRow][10].getTranslateY() + invaderArray[bottomRow][10].getImage().getHeight() <game.getSceneBounds().getMaxY())) {
			for (int col = 0; col < 11; col++) {
			    if (invaderArray[row][col] != null) invaderArray[row][col].setTranslateY(invaderArray[row][col].getTranslateY() + 5);
			} // for col
		    } // if still in bounds
		    else {
			gameOver();
		    }
		} // for row
		moveRight = false;
	    } // else -- if not in bounds, move down!
	} // if moveRight
	else { // if moveLeft
	    // for loop to find the farthest left column that is still populated
	    for (int col = 0; col < farLeftCol+1; col++) {
		if (invaderArray[0][col] == null
                    && invaderArray[1][col] == null
                    && invaderArray[2][col] == null
                    && invaderArray[3][col] == null
		    && invaderArray[4][col] == null) {
		    farLeftCol++;
		} // if farthest left column is unpopulated, move one to the right
		else {
		    break;
		} // else -- if farthest left column is populated
	    } // for col
	    if ((invaderArray[0][farLeftCol] != null && invaderArray[0][farLeftCol].getTranslateX() > game.getSceneBounds().getMinX() + 10) 
                | (invaderArray[1][farLeftCol] != null && invaderArray[1][farLeftCol].getTranslateX() > game.getSceneBounds().getMinX() + 10) 
		| (invaderArray[2][farLeftCol] != null && invaderArray[2][farLeftCol].getTranslateX() > game.getSceneBounds().getMinX() + 10)
		| (invaderArray[3][farLeftCol] != null && invaderArray[3][farLeftCol].getTranslateX() > game.getSceneBounds().getMinX() + 10)
		| (invaderArray[4][farLeftCol] != null && invaderArray[4][farLeftCol].getTranslateX() > game.getSceneBounds().getMinX() + 10)) { 
		// for loop to move invaders left if they're still in bounds -- this is within this if statement                                                                                                                       
		for (int c = 0; c < 11; c++) {
		    for (int row = 0; row < 5; row++) {
			if (invaderArray[row][c] != null) invaderArray[row][c].setTranslateX(invaderArray[row][c].getTranslateX() - invaderSpeed);
                    } // for row                                                                                                                                                                                                        
                } // for c                                                                                                                                                                                                            
            } // if still in bounds, move right!                                                                                                                                                                                        
            else {
                // for loop to move invaders down if they're out of bounds                                                                                                                                                              
                for (int row = 4; row >= 0; row--) {
                    for (int col = 0; col < 11; col++) {
                        if (invaderArray[row][col] != null) invaderArray[row][col].setTranslateY(invaderArray[row][col].getTranslateY() + 5);
                    } // for col                                                                                                                                                                                                        
                } // for row      
		moveRight = true;
            } // else -- if not in bounds, move down!         
	} // else -- if moveLeft
    } // invaderLoop    

    // deletes tyler's bullets when they hit the top of the screen
    public void bulletBounds(Game game) {
	for(int b = tylersBullets.size() - 1; b >= 0; b--) {
	    if (tylersBullets.get(b).bulletImg.getTranslateY() <= game.getSceneBounds().getMinY()) {
		tylersBullets.get(b).bulletImg.setVisible(false); // hides the bullet image
		tylersBullets.get(b).bulletImg.setDisable(true); // removes the bullet's functionality
		tylersBullets.remove(tylersBullets.get(b)); // removes the bullet
		break;
	    } // if out of bounds
	} // for b
    } // bulletBounds

    public void bulletHitsInvader() {
	for(int col = 0; col < 11; col++) {
	    for(int row = 0; row < 5; row++) {
		if (invaderArray[row][col] != null) {
		    for (int b = 0; b < tylersBullets.size(); b++) {
			if (invaderArray[row][col] != null && tylersBullets.get(b).bulletImg.getBoundsInParent().intersects(invaderArray[row][col].getBoundsInParent())) {
			    score+=10;
			    scoreLabel.setText("Score:  " + score);
			    invaderArray[row][col].setImage(null);
			    invaderArray[row][col].setDisable(true);
			    invaderArray[row][col].setVisible(false);
			    invaderArray[row][col] = null;
			    tylersBullets.get(b).bulletImg.setVisible(false);
			    tylersBullets.get(b).bulletImg.setDisable(true);
			    tylersBullets.remove(b);
			} // if bullet hits invader
		    } // for each bullet
		} // if invader still exists
	    } // for row
	} // for col
    } // bulletHitsInvader

    public void bulletHitsTyler() {
	for (int bullet = 0; bullet < invaderBullets.size(); bullet++) {
	    if (invaderBullets.get(bullet).getBoundsInParent().intersects(tyler.getBoundsInParent())) {
		lives--;
		switch (lives) {
		case 2:
		    livesArray[2].setVisible(false);
		    break;
		case 1:
		    livesArray[1].setVisible(false);
		    break;
		case 0:
		    gameOver();
		    break;
		} // switch lives
		//////// !!!lives label
		try {
		    Thread.sleep(1000);
		} catch (InterruptedException e) { }
		try {
		invaderBullets.get(bullet).setVisible(false);
		invaderBullets.get(bullet).setDisable(true);
		invaderBullets.remove(bullet);
		} catch (IndexOutOfBoundsException ee) {
		    System.out.println("Exception occured! " + ee);;
		}
	    } // if bullet hits
	} // for bullet
    } // bulletHitsTyler

    public void invaderShoot() {
	int rowdom = random.nextInt(5);
	int randumn = random.nextInt(11);
	while (invaderArray[rowdom][randumn] == null) {
	    rowdom = random.nextInt(5);
	    randumn = random.nextInt(11);
	} // while null
	ImageView bullet = new ImageView(invaderBullet);
	bullet.setTranslateX(invaderArray[rowdom][randumn].getTranslateX() + (invaderArray[rowdom][randumn].getImage().getWidth()/4));
	bullet.setTranslateY(invaderArray[rowdom][randumn].getTranslateY() + invaderArray[rowdom][randumn].getImage().getHeight());
	getSceneNodes().getChildren().add(bullet);
	invaderBullets.add(bullet);
    } // invaderShoot

    public void bulletHitsBarrier() {
	// tyler's bullets
	for (int barrier = 0; barrier < 8; barrier++) {
	    if (barriers[barrier] != null) {
		for (int bullet = 0; bullet < tylersBullets.size(); bullet++) {
		    if (tylersBullets.get(bullet).bulletImg.getBoundsInParent().intersects(barriers[barrier].getBoundsInParent())) {
			switch (barrierHealth[barrier]) {
			case 3:
			    barriers[barrier].setImage(barrier2);
			    barriers[barrier].setTranslateY(barriers[barrier].getTranslateY() + 8);
			    barrierHealth[barrier]--;
			    break;
			case 2:
			    barriers[barrier].setImage(barrier3);
			    barriers[barrier].setTranslateY(barriers[barrier].getTranslateY() + 12);
			    barrierHealth[barrier]--;
			    break;
			case 1:
			    barriers[barrier].setVisible(false);
			    barriers[barrier].setDisable(true);
			    barriers[barrier] = null;
			    barrierHealth[barrier]--;
			    break;
			} // switch -- barrier health
			tylersBullets.get(bullet).bulletImg.setVisible(false);
			tylersBullets.get(bullet).bulletImg.setDisable(true);
			tylersBullets.remove(bullet);
		    } // if intersect
		} // for tyler's bullets
	    } // if barrier is alive
	    if (barriers[barrier] != null) {
		for (int inv = 0; inv < invaderBullets.size(); inv++) {
		    if (invaderBullets.get(inv).getBoundsInParent().intersects(barriers[barrier].getBoundsInParent())) {
			switch (barrierHealth[barrier]) {
			case 3:
                            barriers[barrier].setImage(barrier2);
                            barriers[barrier].setTranslateY(barriers[barrier].getTranslateY() + 8);
                            barrierHealth[barrier]--;
                            break;
                        case 2:
                            barriers[barrier].setImage(barrier3);
                            barriers[barrier].setTranslateY(barriers[barrier].getTranslateY() + 12);
                            barrierHealth[barrier]--;
                            break;
                        case 1:
                            barriers[barrier].setVisible(false);
                            barriers[barrier].setDisable(true);
                            barriers[barrier] = null;
                            barrierHealth[barrier]--;
                            break;
                        } // switch -- barrier health   
			invaderBullets.get(inv).setVisible(false);
			invaderBullets.get(inv).setDisable(true);
			invaderBullets.remove(inv);
		    } // if intersect
		} // for invader bullets
	    } // if barrier is null
	} // for barriers
    } // bulletHitsBarrier

    public void initialize() {
	invaderWait = random.nextInt(300);
	mysteryEvent = false;
        mysteryTime = random.nextInt(5000);
	if (tylersBullets.size() > 0) {
	    for(int b = 0; b < tylersBullets.size(); b++) {
		tylersBullets.get(b).bulletImg.setVisible(false);
		tylersBullets.get(b).bulletImg.setDisable(true);
	    } // for b
	    tylersBullets.clear();
	} // if tyler's bullets exist
	if (invaderBullets.size() > 0) {
	    for (int i = 0; i < invaderBullets.size(); i++) {
		invaderBullets.get(i).setVisible(false);
		invaderBullets.get(i).setDisable(true);
	    } // for i
	    invaderBullets.clear();
	} // if invader's bullets exist
	// add barriers to array                                                                                          
        for (int i = 0; i < 8; i++) {
            ImageView barrier = new ImageView();
	    barrier.setImage(barrierImg);
            barrier.setTranslateX(10 + (104*i));
            barrier.setTranslateY(450);
            getSceneNodes().getChildren().add(barrier);
	    barriers[i] = barrier;
	    barrierHealth[i] = 3;
        }
        // populates the invader array                                                                                  
        for (int col = 0; col < 11; col++) {
            for(int row = 0; row < 5; row++) {
                switch(row) {
                case 0: // row 1 -- top row                                                                                                                                                                                             
                    ImageView cherry = new ImageView();
                    cherry.setImage(cherryImg);
                    cherry.setTranslateX(10 + (70*col));
                    cherry.setTranslateY(85);
                    getSceneNodes().getChildren().add(cherry);
                    invaderArray[row][col] = cherry;
                    break;
                case 1: // row 2                                                                                                                                                                                                        
                    ImageView gob1 = new ImageView();
                    gob1.setImage(goblinImg);
                    gob1.setTranslateX(10 + (70*col));
                    gob1.setTranslateY(140);
                    getSceneNodes().getChildren().add(gob1);
                    invaderArray[row][col] = gob1;
                    break;
                case 2: // row 3                                                                                                                                                                                                        
                    ImageView gob2 = new ImageView();
                    gob2.setImage(goblinImg);
                    gob2.setTranslateX(10 + (70*col));
                    gob2.setTranslateY(205);
                    getSceneNodes().getChildren().add(gob2);
                    invaderArray[row][col] = gob2;
		    break;
		case 3: // row 4                                                                                                                                                                                                        
                    ImageView nerdInvader = new ImageView();
                    nerdInvader.setImage(nerdImg);
                    nerdInvader.setTranslateX(15 + (70*col));
                    nerdInvader.setTranslateY(270);
                    getSceneNodes().getChildren().add(nerdInvader);
                    invaderArray[row][col] = nerdInvader;
                    break;
                case 4: // row 5 -- bottom row                                                                                                                                                                                          
                    ImageView nerd2 = new ImageView();
                    nerd2.setImage(nerdImg);
                    nerd2.setTranslateX(15 + (70*col));
                    nerd2.setTranslateY(345);
                    getSceneNodes().getChildren().add(nerd2);
                    invaderArray[row][col] = nerd2;
                    break;
                } // switch -- rows                                                                                                                                                                                                     
            } // for col                                                                                                                                                                                                                
        } // for row       
	try {
	Thread.sleep(2000);
	} catch (InterruptedException e) { }
    } // initialize

    public boolean restart() {
	boolean doRestart = true;
	for(int row = 0; row < 5; row++) {
	    for(int col = 0; col < 11; col++) {
		if(invaderArray[row][col] != null) doRestart = false;
	    } // for col
	} // for row
	if (doRestart) {
	    round++;
	    invaderSpeed = .1 + (.2*round);
	} // if restart
	return doRestart;
    } // restart

    public void gameOver() {
	getSceneNodes().getChildren().clear();
	getSceneNodes().getChildren().addAll(gameOverPage, gameOverLabel, startButton);
	// reset invaders
	for (int row = 0; row < 5; row++) {
	    for (int col = 0; col < 11; col++) {
		if (invaderArray[row][col] != null) {
		    invaderArray[row][col].setVisible(false);
		    invaderArray[row][col].setDisable(true);
		} // if still alive
		else invaderArray[row][col] = null;
	    } // for col
	} // for row
	// reset barriers
	for (int b = 0; b < 8; b++) {
	    if (barriers[b] != null) {
		barriers[b].setVisible(false);
		barriers[b].setDisable(true);
	    } // if still alive
	    else barriers[b] = null;
	} // for b
	// reset tylers bullets
	for (int i = 0; i < tylersBullets.size(); i++) {
	    tylersBullets.get(i).bulletImg.setVisible(false);
	    tylersBullets.get(i).bulletImg.setDisable(true);
	} // for i
	tylersBullets.clear();
	// reset invaders bullets
	for (int j = 0; j < invaderBullets.size(); j++) {
	    invaderBullets.get(j).setVisible(false);
	    invaderBullets.get(j).setDisable(true);
	} // for j
	invaderBullets.clear();
	playGame = false;
    } // 

    public void mysteryShip(ImageView mysteryShip) {
	if (getSceneNodes().getChildren().contains(mysteryShip)) getSceneNodes().getChildren().remove(mysteryShip); // gets rid of mysteryShip if it's still in the scene
	mysteryShip.setTranslateX(800);
	mysteryShip.setVisible(true);
	mysteryShip.setDisable(false);
	mysteryEvent = true;
	getSceneNodes().getChildren().add(mysteryShip);
    } // mysteryShip

    public void mysteryHit() {
	for (int b = 0; b < tylersBullets.size(); b++) {
	    if(mysteryShip.getBoundsInParent().intersects(tylersBullets.get(b).bulletImg.getBoundsInParent())) { // collision with mysteryShip!!
		mysteryEvent = false;
		score += 100;
		scoreLabel.setText("Score:  " + score);
		mysteryShip.setVisible(false);
		mysteryShip.setDisable(true);
		tylersBullets.get(b).bulletImg.setVisible(false);
		tylersBullets.get(b).bulletImg.setDisable(true);
		tylersBullets.remove(b);
		break;
	    } // if collision
	} // for b
    } // mysteryHit

    public void startGame() {
	livesArray[0] = new ImageView(lifeImg);
        livesArray[0].setTranslateX(725);
        livesArray[0].setTranslateY(5);
        livesArray[1] = new ImageView(lifeImg);
        livesArray[1].setTranslateX(750);
        livesArray[1].setTranslateY(5);
        livesArray[2] = new ImageView(lifeImg);
        livesArray[2].setTranslateX(775);
        livesArray[2].setTranslateY(5);
	lives = 3;
	round = 0;
	score = 0;
	scoreLabel.setText("Score:  " + score);
	invaderSpeed = 0.1;
	getSceneNodes().getChildren().clear();
        getSceneNodes().getChildren().addAll(bkgd, tyler, scoreLabel, livesLabel, livesArray[0], livesArray[1], livesArray[2]);
	try {
	    Thread.sleep(2000);
	} catch (InterruptedException e) { }
        initialize();
	mainMenu = false;
	playGame = true;
    } // startGame

    public void instructionsPressed() {
	instructions = true;
	getSceneNodes().getChildren().clear();
	getSceneNodes().getChildren().addAll(bkgd, instructionsMenu, backButton);
    } // instructionsPressed

} // Invaders
<iframe src="https://player.vimeo.com/video/151681099" width="500" height="281" frameborder="0" webkitallowfullscreen mozallowfullscreen allowfullscreen></iframe>
<p><a href="https://vimeo.com/151681099">Dream Invaders</a> from <a href="https://vimeo.com/user47792733">Matt Siegel</a> on <a href="https://vimeo.com">Vimeo</a>.</p>

### How to Play: 

-- SHOOT: UP Key	
-- MOVE LEFT: LEFT Key
-- MOVE RIGHT: RIGHT Key


### Game Mechanics:

Press the start button to begin. When the game begins, the array of invaders will make their way to the right. When they hit the right border, they will move down, and then begin moving to the left until they hit the left border, at which point they will move down again, and then begin this loop again. As each column is destroyed completely, they will take longer to hit the border *HINT HINT*. If the invaders make it down all the way to the bottom of the screen, the player will get a Game Over. Now, as the invaders move around, they will try shooting at the player. This is completely randomized; the interval between shots is randomized as well as which invader will shoot. The player is protected by eight barriers, each of which has three health points. When the barrier has been hit three times in one round, it will disappear and leave the player more vulnerable to the invaders' bullets. Now, by using the UP Key, Tyler can shoot back at the aliens. Watch out; if Tyler shoots a barrier, it will lose health. Each time one of Tyler's bullets hits an invader, that invader will disappear and the player's score will be incremented by ten points. Occasionally (and randomly) a mystery ship will fly across the screen from right to left. If the player manages to shoot the mystery ship before it hits the left border and disappears then the player's score will be incremented by 100 points. Grab the opportunity while you can, because this mystery ship does not show up often! When an invader's bullet hits Tyler, the game pauses momentarily and the player loses a life. When the player has lost all 3 lives, the game is over. When the player has successfully cleared the screen of all invaders, they will move on to the next round; the barriers will be restored, the invaders will all reappear, and the invaders will now move at a quicker speed. The player's score and number of lives remain the same. This game can (theoretically) loop endlessly, but after a few rounds the player will find it incredibly difficult to keep the invaders from reaching the bottom of the screen. At that point, it's a matter of quick reactions, good strategy, and good predictions on where your bullets will hit. Good luck!
Note: This game is designed after the famous rapper, Tyler the Creator.


### Screenshots:

![Main Menu](http://i.imgur.com/zgVmAwb.png "Main Menu")

![Instructions Page](http://i.imgur.com/f20hYBz.png "Instructions")

![Beginning of Game](http://i.imgur.com/zn3v7cb.png "Initiate Game")

![GamePlay](http://i.imgur.com/lth4epB.png "GamePlay")

![Game Over Screen](http://i.imgur.com/D27OQ7X.png "GameOver")


### Important Notes:

This project was for my CSCI1302 class with Michael Cotterell and the University of Georgia. I received a grade of 110/100.
Grading was based on:

"This project is due on Tuesday 2014-12-09 @ 11:55 PM.
Before you submit your project, you need to perform the following tasks:

1. (20 points) Design your game's GUI interface. You can use 
   a mock-up tool such as [Creatively](http://creately.com/Online-UI-Mockups-and-Wireframes) 
   or [Pencil](http://pencil.evolus.vn/) (or similar) to make this 
   easier. You will include your mockups in a file called <code>MOCKUP.md</code> (a 
   blank version of this file is already created for you).
   Here is the point breakdown:
  * (8 points) Four mock-up images that show the game in different scenarios (e.g., 
    before game starts, durring game, game over, and one other).
  * (8 points) Include explanations for the design of each image.
  * (4 points) Provide a paragraph on how you think the design will differ from
    what you've described in your mock-ups. 
2. (60 points) Implement the GUI and the game logic. Here is the point breakdown:
  * (20 points) The UI looks similar to the mock-ups and is functional.
  * (20 points) The game keeps score as described by this <code>README.md</code> file.
  * (20 points) Game does not crash. If any errors occur, a dialog box or similar 
    should appear to indicates what the problem was.
3. (20 points) Document Everything. Here is the breakdown:
  * (10 points) Include Javadoc comments for every new class and method that you create.
  * (10 points) Include inline comments where appropriate to make your code clear.
4. Update the <code>README.md</code> in your project directory to contain the 
   following information at the top of the file (updating it with your own 
   information:

EXTRA CREDIT:
 1. (10 points) Add an animated intro to your game (animated using the game loop).
    When your application starts, you should display some cool graphics, the 
    title of your game, your name, and some blinking "Press Enter" text. One way
    to accomplish this might be to place your scene nodes into different
    [<code>Group</code>](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/Group.html) objects,
    then switch out which group is in the scene, depending on the state of your game."
  -- quoted from ![FXGame Cotterell Page](https://github.com/mepcotterell-cs1302/cs1302-fxgame)

### Game Engine:

The game engine was written by my professor, Michael Cotterell:
Using the engine relies on the programmer
extending the abstract 
[<code>Game</code>](http://cobweb.cs.uga.edu/~mec/fxgame/com/michaelcotterell/game/Game.html)
class and implemeting the [<code>update</code>](http://cobweb.cs.uga.edu/~mec/fxgame/com/michaelcotterell/game/Updateable.html#update-com.michaelcotterell.game.Game-com.michaelcotterell.game.GameTime-) 
method. The engine abstracts all the details of running the game loop.
Anything that is put in the <code>update</code> method is automatically
executed once per iteration of the game loop after the game starts.
Here is an example of a small game that is written using the engine:

```java
import com.michaelcotterell.game.Game;
import com.michaelcotterell.game.GameTime;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TestGame extends Game {

    private Rectangle bg = new Rectangle(0, 0, 640, 480) {{ 
         setFill(Color.BLACK); 
    }};

    private Text text = new Text() {{
         setTranslateX(10);
         setTranslateY(20);
         setFill(Color.YELLOWGREEN);
    }};

    public TestGame(Stage stage) {
        super(stage, "TestGame", 60, 640, 480);
        getSceneNodes().getChildren().addAll(bg, text);
    } // TestGame

    @Override
    public void update(Game game, GameTime gameTime) {
        text.setText("Hello " + gameTime.getTotalGameTime());
        if (game.getKeyManager().isKeyPressed(KeyCode.UP)) text.setTranslateY(text.getTranslateY() - 4);
        if (game.getKeyManager().isKeyPressed(KeyCode.DOWN)) text.setTranslateY(text.getTranslateY() + 4);
        if (game.getKeyManager().isKeyPressed(KeyCode.LEFT)) text.setTranslateX(text.getTranslateX() - 4);
        if (game.getKeyManager().isKeyPressed(KeyCode.RIGHT)) text.setTranslateX(text.getTranslateX() + 4);
    } // update

} // TestGame
```

To launch the game, you just need to setup a driver for your JavaFX application.
Here is an example of a driver for the <code>TestGame</code> class provided
above:

```java
import com.michaelcotterell.game.Game;
import javafx.application.Application;
import javafx.stage.Stage;

public class Driver extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception { 
        Game game = new TestGame(primaryStage);
        primaryStage.setTitle(game.getTitle());
        primaryStage.setScene(game.getScene());
        primaryStage.show();
        game.run();
    } // start
    
    public static void main(String[] args) {
        launch(args);
    } // main
    
} // Driver
```
The API documentation for this game engine can be found 
[here](http://cobweb.cs.uga.edu/~mec/fxgame/). In particular, pay attention
to the methods that are available to you in the <code>Game</code>
class.

I implicitly agreed to Academic Honesty policy as outlined in the course 
syllabus and course website (available at: http://cs.uga.edu/~mec/cs1302/

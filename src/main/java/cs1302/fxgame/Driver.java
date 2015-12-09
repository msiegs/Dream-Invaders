
package cs1302.fxgame;

import java.io.IOException;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import com.michaelcotterell.game.Game;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.animation.AnimationTimer;
import javafx.event.*;
import javafx.scene.input.*;

public class Driver extends Application {

    @Override
	public void start(Stage primaryStage) { // throws Exception { 

	//	Parent root = null;

	//	try {
	//  System.out.println("loading fxml");
	//  root = FXMLLoader.load(getClass().getResource("/gamePlay.fxml"));
	//  System.out.println("FXML was loaded successfully!");
	//	} catch (IOException e) {
	// System.out.println(e);
	//  System.exit(1);
	//	} // try

        Game game = new Invaders(primaryStage);
	primaryStage.setTitle(game.getTitle());
	primaryStage.setScene(game.getScene());
	primaryStage.show();
        game.run();

    } // start
    
    public static void main(String[] args) {
        launch(args);
    } // main

} // Driver


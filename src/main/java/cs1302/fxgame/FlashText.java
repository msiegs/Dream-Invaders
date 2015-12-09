package cs1302.fxgame;

import javafx.util.Duration;
import javafx.scene.control.Label;
import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class FlashText extends Label {

    public FadeTransition flash;

    public FlashText() {

	flash = new FadeTransition(Duration.millis(500), this);
	flash.setFromValue(1.0);
	flash.setToValue(0);
	flash.setCycleCount(Timeline.INDEFINITE);
	flash.setAutoReverse(true);

    } // FlashText

    public void play() {
	flash.play();
    } // play

    public void stop() {
	flash.stop();
    } // stop	    

} // FlashText
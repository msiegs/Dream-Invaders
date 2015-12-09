package cs1302.fxgame;

import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

public class TylerBullet {

    ImageView bulletImg;

    public TylerBullet() {
	bulletImg = new ImageView(new Image("/bullet.png", 25, 25, true, true)); // source: <http://pngimg.com/upload/small/bullets_PNG1456.png>
    } // Bullet

} // Bullet

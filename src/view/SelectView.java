package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SelectView extends Application{
	MediaPlayer mediaPlayer;
	
	@Override
	public void start(Stage stage) {
		try {

			String[] buttonIds = {"venasaur", "meganium", "sceptile", "torterra",
									"charizard", "typhlosion", "blaziken", "infernape",
									"blastoise", "feraligator", "swampert", "empoleon"};
			
			Button pokemon1 = new Button();
			pokemon1.setShape(new Polygon(new Hexagon(100d).getPoints()));
			pokemon1.setMinSize(100, 90);
			pokemon1.setId(buttonIds[0]);
			
			Button pokemon2 = new Button();
			pokemon2.setShape(new Polygon(new Hexagon(100d).getPoints()));
			pokemon2.setMinSize(100, 90);
			pokemon2.setId(buttonIds[2]);
			
			Button pokemon3 = new Button();
			pokemon3.setShape(new Polygon(new Hexagon(100d).getPoints()));
			pokemon3.setMinSize(100, 90);
			pokemon3.setId(buttonIds[4]);
			
			Button pokemon4 = new Button();
			pokemon4.setShape(new Polygon(new Hexagon(100d).getPoints()));
			pokemon4.setMinSize(100, 90);
			pokemon4.setId(buttonIds[6]);
			
			Button pokemon5 = new Button();
			pokemon5.setShape(new Polygon(new Hexagon(100d).getPoints()));
			pokemon5.setMinSize(100, 90);
			pokemon5.setId(buttonIds[8]);
			
			Button pokemon6 = new Button();
			pokemon6.setShape(new Polygon(new Hexagon(100d).getPoints()));
			pokemon6.setMinSize(100, 90);
			pokemon6.setId(buttonIds[10]);
			
			Button pokemon7 = new Button();
			pokemon7.setShape(new Polygon(new Hexagon(100d).getPoints()));
			pokemon7.setMinSize(100, 90);
			pokemon7.setId(buttonIds[1]);
			
			Button pokemon8 = new Button();
			pokemon8.setShape(new Polygon(new Hexagon(100d).getPoints()));
			pokemon8.setMinSize(100, 90);
			pokemon8.setId(buttonIds[3]);
			
			Button pokemon9 = new Button();
			pokemon9.setShape(new Polygon(new Hexagon(100d).getPoints()));
			pokemon9.setMinSize(100, 90);
			pokemon9.setId(buttonIds[5]);
			
			Button pokemon10 = new Button();
			pokemon10.setShape(new Polygon(new Hexagon(100d).getPoints()));
			pokemon10.setMinSize(100, 90);
			pokemon10.setId(buttonIds[7]);
			
			Button pokemon11 = new Button();
			pokemon11.setShape(new Polygon(new Hexagon(100d).getPoints()));
			pokemon11.setMinSize(100, 90);
			pokemon11.setId(buttonIds[9]);
			
			Button pokemon12 = new Button();
			pokemon12.setShape(new Polygon(new Hexagon(100d).getPoints()));
			pokemon12.setMinSize(100, 90);
			pokemon12.setId(buttonIds[11]);
			
			Button battle = new Button("BATTLE!");
			battle.setMinSize(500, 180);
			
			Media videoFile = new Media("file:///C:/Users/Ramon/workspace/SelectScreen/src/res/selectscreen.mp3");
			mediaPlayer = new MediaPlayer(videoFile);
			//mediaPlayer.setAutoPlay(true);
			mediaPlayer.setVolume(0.1);
			mediaPlayer.setOnEndOfMedia(new Runnable() {
			       public void run() {
			         mediaPlayer.seek(Duration.ZERO);
			       }
			   });
			  mediaPlayer.play();
			
			Image pokemonImage1 = new Image(getClass().getResourceAsStream("/res/venasaur.png"));
			ImageView imageView1 = new ImageView(pokemonImage1);
			pokemon1.setGraphic(imageView1);
			imageView1.setFitHeight(90);
			imageView1.setFitWidth(100);
			
			Image pokemonImage2 = new Image(getClass().getResourceAsStream("/res/sceptile.png"));
			ImageView imageView2 = new ImageView(pokemonImage2);
			pokemon2.setGraphic(imageView2);
			imageView2.setFitHeight(90);
			imageView2.setFitWidth(100);
			
			Image pokemonImage3 = new Image(getClass().getResourceAsStream("/res/charizard.png"));
			ImageView imageView3 = new ImageView(pokemonImage3);
			pokemon3.setGraphic(imageView3);
			imageView3.setFitHeight(90);
			imageView3.setFitWidth(100);
			
			Image pokemonImage4 = new Image(getClass().getResourceAsStream("/res/blaziken.png"));
			ImageView imageView4 = new ImageView(pokemonImage4);
			pokemon4.setGraphic(imageView4);
			imageView4.setFitHeight(90);
			imageView4.setFitWidth(100);
			
			Image pokemonImage5 = new Image(getClass().getResourceAsStream("/res/blastoise.png"));
			ImageView imageView5 = new ImageView(pokemonImage5);
			pokemon5.setGraphic(imageView5);
			imageView5.setFitHeight(90);
			imageView5.setFitWidth(100);
			
			Image pokemonImage6 = new Image(getClass().getResourceAsStream("/res/swampert.png"));
			ImageView imageView6 = new ImageView(pokemonImage6);
			pokemon6.setGraphic(imageView6);
			imageView6.setFitHeight(90);
			imageView6.setFitWidth(100);
			
			Image pokemonImage7 = new Image(getClass().getResourceAsStream("/res/meganium.png"));
			ImageView imageView7 = new ImageView(pokemonImage7);
			pokemon7.setGraphic(imageView7);
			imageView7.setFitHeight(90);
			imageView7.setFitWidth(100);
			
			Image pokemonImage8 = new Image(getClass().getResourceAsStream("/res/torterra.png"));
			ImageView imageView8 = new ImageView(pokemonImage8);
			pokemon8.setGraphic(imageView8);
			imageView8.setFitHeight(90);
			imageView8.setFitWidth(100);
			
			Image pokemonImage9 = new Image(getClass().getResourceAsStream("/res/typhlosion.png"));
			ImageView imageView9 = new ImageView(pokemonImage9);
			pokemon9.setGraphic(imageView9);
			imageView9.setFitHeight(90);
			imageView9.setFitWidth(100);
			
			Image pokemonImage10 = new Image(getClass().getResourceAsStream("/res/infernape.png"));
			ImageView imageView10 = new ImageView(pokemonImage10);
			pokemon10.setGraphic(imageView10);
			imageView10.setFitHeight(90);
			imageView10.setFitWidth(100);
			
			Image pokemonImage11 = new Image(getClass().getResourceAsStream("/res/gator.png"));
			ImageView imageView11 = new ImageView(pokemonImage11);
			pokemon11.setGraphic(imageView11);
			imageView11.setFitHeight(90);
			imageView11.setFitWidth(100);
			
			Image pokemonImage12 = new Image(getClass().getResourceAsStream("/res/empoleon.png"));
			ImageView imageView12 = new ImageView(pokemonImage12);
			pokemon12.setGraphic(imageView12);
			imageView12.setFitHeight(90);
			imageView12.setFitWidth(100);
	        
	        GridPane gridPane = new GridPane();
	        
	        GridPane.setConstraints(battle, 2, 4, 2, 2);
	        
	        GridPane.setRowIndex(pokemon1, 0);
	        GridPane.setColumnIndex(pokemon1, 0);
	        
	        GridPane.setRowIndex(pokemon2, 1);
	        GridPane.setColumnIndex(pokemon2, 0);
	        
	        GridPane.setRowIndex(pokemon3, 2);
	        GridPane.setColumnIndex(pokemon3, 0);
	        
	        GridPane.setRowIndex(pokemon4, 3);
	        GridPane.setColumnIndex(pokemon4, 0);
	        
	        GridPane.setRowIndex(pokemon5, 4);
	        GridPane.setColumnIndex(pokemon5, 0);
	        
	        GridPane.setRowIndex(pokemon6, 5);
	        GridPane.setColumnIndex(pokemon6, 0);
	        
	        GridPane.setRowIndex(pokemon7, 0);
	        GridPane.setColumnIndex(pokemon7, 1);
	        
	        GridPane.setRowIndex(pokemon8, 1);
	        GridPane.setColumnIndex(pokemon8, 1);
	        
	        GridPane.setRowIndex(pokemon9, 2);
	        GridPane.setColumnIndex(pokemon9, 1);
	        
	        GridPane.setRowIndex(pokemon10, 3);
	        GridPane.setColumnIndex(pokemon10, 1);
	        
	        GridPane.setRowIndex(pokemon11, 4);
	        GridPane.setColumnIndex(pokemon11, 1);
	        
	        GridPane.setRowIndex(pokemon12, 5);
	        GridPane.setColumnIndex(pokemon12, 1);
	        
	        gridPane.getChildren().addAll(pokemon1, pokemon2, pokemon3, 
	        		pokemon4, pokemon5, pokemon6, pokemon7, pokemon8, pokemon9, 
	        		pokemon10, pokemon11, pokemon12, battle);

	        Scene scene = new Scene(gridPane);
	        stage.setScene(scene);
	        stage.setTitle("Pokemon Selector");
	        stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
	

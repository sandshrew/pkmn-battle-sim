package view;
	
import java.io.InputStream;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			
			GridPane grid = new GridPane();
			grid.setAlignment(Pos.TOP_CENTER);
			grid.setHgap(10);
			grid.setVgap(10);
			grid.setPadding(new Insets(25, 25, 25, 25));
			Scene scene = new Scene(grid,700,700);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			primaryStage.setTitle("Pokemon");
			
			Image image = new Image(getClass().getResourceAsStream("/res/pokemon.png"));
			ImageView imageView = new ImageView(image);
			imageView.setFitHeight(150);
			imageView.setFitWidth(400);
			grid.add(imageView, 0, 0, 3, 1);
			
			
			Button newGame = new Button();
			Image newGameImage = new Image(getClass().getResourceAsStream("/res/newgame.png"));
			ImageView imageView2 = new ImageView(newGameImage);
			newGame.setGraphic(imageView2);
			imageView2.setFitHeight(70);
			imageView2.setFitWidth(250);
			grid.add(newGame, 1, 1, 2, 1);
			
			Button Continue = new Button();
			Image continueImage = new Image(getClass().getResourceAsStream("/res/continue.png"));
			ImageView imageView3 = new ImageView(continueImage);
			Continue.setGraphic(imageView3);
			imageView3.setFitHeight(70);
			imageView3.setFitWidth(250);
			grid.add(Continue, 1, 2, 2, 1);
			
			Button options = new Button();
			Image optionsImage = new Image(getClass().getResourceAsStream("/res/options.png"));
			ImageView imageView4 = new ImageView(optionsImage);
			options.setGraphic(imageView4);
			imageView4.setFitHeight(70);
			imageView4.setFitWidth(250);
			grid.add(options, 1, 3, 2, 1);
			
			Button credits = new Button();
			Image creditsImage = new Image(getClass().getResourceAsStream("/res/credits.png"));
			ImageView imageView1 = new ImageView(creditsImage);
			credits.setGraphic(imageView1);
			imageView1.setFitHeight(70);
			imageView1.setFitWidth(250);
			grid.add(credits, 1, 4, 2, 1);
			
			root.getChildren().add(grid);
			
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

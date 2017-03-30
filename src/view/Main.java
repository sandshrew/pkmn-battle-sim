package view;

import java.io.InputStream;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.QuadCurveTo;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			intro(primaryStage);

//			BorderPane root = new BorderPane();
//
//			GridPane grid = new GridPane();
//			grid.setAlignment(Pos.TOP_CENTER);
//			grid.setHgap(10);
//			grid.setVgap(10);
//			grid.setPadding(new Insets(25, 25, 25, 25));
//			Scene scene = new Scene(grid,700,700);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//
//			primaryStage.setTitle("Pokemon");
//
//			Image image = new Image(getClass().getResourceAsStream("/res/pokemon.png"));
//			ImageView imageView = new ImageView(image);
//			imageView.setFitHeight(150);
//			imageView.setFitWidth(400);
//			grid.add(imageView, 0, 0, 3, 1);
//
//
//			Button newGame = new Button();
//			Image newGameImage = new Image(getClass().getResourceAsStream("/res/newgame.png"));
//			ImageView imageView2 = new ImageView(newGameImage);
//			newGame.setGraphic(imageView2);
//			imageView2.setFitHeight(70);
//			imageView2.setFitWidth(250);
//			grid.add(newGame, 1, 1, 2, 1);
//
//			Button Continue = new Button();
//			Image continueImage = new Image(getClass().getResourceAsStream("/res/continue.png"));
//			ImageView imageView3 = new ImageView(continueImage);
//			Continue.setGraphic(imageView3);
//			imageView3.setFitHeight(70);
//			imageView3.setFitWidth(250);
//			grid.add(Continue, 1, 2, 2, 1);
//
//			Button options = new Button();
//			Image optionsImage = new Image(getClass().getResourceAsStream("/res/options.png"));
//			ImageView imageView4 = new ImageView(optionsImage);
//			options.setGraphic(imageView4);
//			imageView4.setFitHeight(70);
//			imageView4.setFitWidth(250);
//			grid.add(options, 1, 3, 2, 1);
//
//			Button credits = new Button();
//			Image creditsImage = new Image(getClass().getResourceAsStream("/res/credits.png"));
//			ImageView imageView1 = new ImageView(creditsImage);
//			credits.setGraphic(imageView1);
//			imageView1.setFitHeight(70);
//			imageView1.setFitWidth(250);
//			grid.add(credits, 1, 4, 2, 1);
//
//			root.getChildren().add(grid);
//
//			primaryStage.setScene(scene);
//			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	//not mvc bc lazy/testing, will change
	public void intro(Stage primaryStage){
		AnchorPane root = new AnchorPane();
		Scene scene = new Scene(root,700,700);

		Image profImage = new Image(getClass().getResourceAsStream("/res/profoak.png"));
		ImageView profImageView = new ImageView(profImage);
		profImageView.setX(-100);
		profImageView.setY(100);
		root.getChildren().add(profImageView);	

		Image pkmnImage = new Image(getClass().getResourceAsStream("/res/arbok.png"));
		ImageView pkmnImageView = new ImageView(pkmnImage);
		pkmnImageView.setX(250);
		pkmnImageView.setY(200);

		final Timeline profTimeline = new Timeline();
		profTimeline.setCycleCount(1);
		final KeyValue profKv = new KeyValue(profImageView.xProperty(), 250);
		final KeyFrame profKf = new KeyFrame(Duration.millis(2000), profKv);
		profTimeline.getKeyFrames().add(profKf);
		profTimeline.play();

		final Timeline pkmnTimeline = new Timeline();
		pkmnTimeline.setCycleCount(1);
		final KeyValue pkmnKv = new KeyValue(profImageView.xProperty(), 350);
		final KeyFrame pkmnKf = new KeyFrame(Duration.millis(500), pkmnKv);
		pkmnTimeline.getKeyFrames().add(pkmnKf);

		Path path = new Path();
		path.getElements().add(new MoveTo(pkmnImageView.getX(), pkmnImageView.getY()));
		path.getElements().add(new QuadCurveTo(200, 200, 250, 350));
		PathTransition pathTransition = new PathTransition();
		pathTransition.setDuration(Duration.millis(500));
		pathTransition.setPath(path);
		pathTransition.setNode(pkmnImageView);
		pathTransition.setOrientation(PathTransition.OrientationType.NONE);
		pathTransition.setCycleCount(1);
		pathTransition.setAutoReverse(false);


		//maybe just don't include
//		Image textboxImage = new Image(getClass().getResourceAsStream("/res/textbox.png"));
//		ImageView textboxImageView = new ImageView(textboxImage);
//		textboxImageView.setLayoutX(185);
//		textboxImageView.setLayoutY(585);
//		root.getChildren().add(textboxImageView);	

		
		Label textLabel = new Label();
		changeText(textLabel);
		textLabel.setWrapText(true);
		textLabel.setMaxSize(400, 100);
		textLabel.setLayoutX(200);
		textLabel.setLayoutY(600);
		textLabel.setFont(Font.loadFont(getClass().getResourceAsStream("/res/font.ttf"), 12));
		
		
		root.setOnMouseClicked(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent e){
				changeText(textLabel);
				if (introIndex == 4 && !root.getChildren().contains(pkmnImageView)){
					pkmnTimeline.play();
					root.getChildren().add(pkmnImageView);
					pathTransition.play();
				}
			}
		});
		root.getChildren().add(textLabel);

		primaryStage.setScene( scene ); 
		primaryStage.setResizable(false);
		primaryStage.setTitle( "Pokémon" );
		primaryStage.show();
	}

	int introIndex = 0; 
	String[] introText = {"Welcome to the world of Pokémon!","My name is professor Oak.","People affectionately refer to me as the pokemon professor","This world is inhabited far and wide by creatures called pokemon"};

	
	public void changeText(Label label){

		if (!(introIndex >= introText.length)){
			label.setText(introText[introIndex]);
			introIndex++;
		}

	}


	public static void main(String[] args) {
		launch(args);
	}
}

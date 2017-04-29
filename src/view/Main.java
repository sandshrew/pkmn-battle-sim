package view;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PathTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.Pair;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.QuadCurveTo;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Main extends Application {

	MediaPlayer mediaPlayer;

	Button newGameButton;
	Button continueButton;
	Button optionsButton;
	Button controlsButton;
	Button creditsButton;
	Button exitButton;

	Stage theStage;

	int[] selectionList = new int[7];
	int globalCounter = 0;

	private static final int WIDTH = 1280;
	private static final int HEIGHT = 720;

	@Override
	public void start(Stage primaryStage) {
		try {
			theStage = primaryStage;

			Scene scene = new Scene(createContent());
			primaryStage.setTitle("Pokemon Battle Sim Menu");
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private List<Pair<String, Button>> menuData = Arrays.asList(new Pair<String, Button>("New Game", newGameButton),
			new Pair<String, Button>("Continue", continueButton),
			new Pair<String, Button>("Game Options", optionsButton),
			new Pair<String, Button>("Controls", controlsButton), new Pair<String, Button>("Credits", creditsButton),
			new Pair<String, Button>("Exit", exitButton));

	private Pane root = new Pane();
	private VBox menuBox = new VBox(-5);
	private Line line;

	private Parent createContent() {
		addBackground();
		addTitle();

		double lineX = WIDTH / 2 - 100;
		double lineY = HEIGHT / 3 + 50;

		addLine(lineX, lineY);
		addMenu(lineX + 5, lineY + 5);

		startAnimation();

		return root;
	}

	public int[] getSelectionList() {
		return selectionList;
	}

	private void addBackground() {
		Image image = new Image(getClass().getResourceAsStream("/res/gengar.jpg"));
		ImageView imageView = new ImageView(image);
		imageView.setFitWidth(WIDTH);
		imageView.setFitHeight(HEIGHT);

		root.getChildren().add(imageView);
	}

	private void addTitle() {
		PokemonTitle title = new PokemonTitle("POKEMON");
		title.setTranslateX(WIDTH / 2 - title.getTitleWidth() / 2);
		title.setTranslateY(HEIGHT / 3);

		root.getChildren().add(title);
	}

	private void addLine(double x, double y) {
		line = new Line(x, y, x, y + 300);
		line.setStrokeWidth(3);
		line.setStroke(Color.color(1, 1, 1, 0.75));
		line.setEffect(new DropShadow(5, Color.BLACK));
		line.setScaleY(0);

		root.getChildren().add(line);
	}

	private void startAnimation() {
		ScaleTransition st = new ScaleTransition(Duration.seconds(1), line);
		st.setToY(1);
		st.setOnFinished(e -> {

			for (int i = 0; i < menuBox.getChildren().size(); i++) {
				Node n = menuBox.getChildren().get(i);

				TranslateTransition tt = new TranslateTransition(Duration.seconds(1 + i * 0.15), n);
				tt.setToX(0);
				tt.setOnFinished(e2 -> n.setClip(null));
				tt.play();
			}
		});
		st.play();
	}

	private void addMenu(double x, double y) {
		menuBox.setTranslateX(x);
		menuBox.setTranslateY(y);

		Rectangle clip = new Rectangle(300, 30);
		Rectangle clip2 = new Rectangle(300, 30);
		Rectangle clip3 = new Rectangle(300, 30);
		Rectangle clip4 = new Rectangle(300, 30);
		Rectangle clip5 = new Rectangle(300, 30);
		Rectangle clip6 = new Rectangle(300, 30);

		PokemonMenuItem item1 = new PokemonMenuItem(menuData.get(0).getKey());
		item1.setTranslateX(-300);
		item1.setId(menuData.get(0).getKey());
		item1.setOnMouseClicked(new EventHandler<MouseEvent>() {

			public void handle(MouseEvent event) {
				Platform.exit();
			}

		});
		clip.translateXProperty().bind(item1.translateXProperty().negate());
		item1.setClip(clip);

		PokemonMenuItem item2 = new PokemonMenuItem(menuData.get(1).getKey());
		item2.setTranslateX(-300);
		item2.setId(menuData.get(1).getKey());
		item2.setOnMouseClicked(new EventHandler<MouseEvent>() {

			public void handle(MouseEvent event) {

				/*
				 * 1 = Venasaur 2 = Meganium 3 = Sceptile 4 = Torterra 5 =
				 * Charizard 6 = Typhlosion 7 = Blaziken 8 = Infernape 9 =
				 * Blastoise 10 = Feraligatr 11 = Swampert 12 = Empoleon
				 */
				String[] buttonIds = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" };

				InnerShadow shadow = new InnerShadow();

				Button pokemon1 = new Button();
				pokemon1.setShape(new Polygon(new Hexagon(100d).getPoints()));
				pokemon1.setMinSize(100, 90);
				pokemon1.setId(buttonIds[0]);
				pokemon1.setOnMouseClicked(new EventHandler<MouseEvent>() {

					public void handle(MouseEvent event) {
						pokemon1.setEffect(shadow);
						selectionList[globalCounter] = Integer.parseInt(pokemon1.getId());
						globalCounter++;
					}

				});

				Button pokemon2 = new Button();
				pokemon2.setShape(new Polygon(new Hexagon(100d).getPoints()));
				pokemon2.setMinSize(100, 90);
				pokemon2.setId(buttonIds[2]);
				pokemon2.setOnMouseClicked(new EventHandler<MouseEvent>() {

					public void handle(MouseEvent event) {
						pokemon2.setEffect(shadow);
						selectionList[globalCounter] = Integer.parseInt(pokemon2.getId());
						globalCounter++;
					}

				});

				Button pokemon3 = new Button();
				pokemon3.setShape(new Polygon(new Hexagon(100d).getPoints()));
				pokemon3.setMinSize(100, 90);
				pokemon3.setId(buttonIds[4]);
				pokemon3.setOnMouseClicked(new EventHandler<MouseEvent>() {

					public void handle(MouseEvent event) {
						pokemon3.setEffect(shadow);
						selectionList[globalCounter] = Integer.parseInt(pokemon3.getId());
						globalCounter++;
					}

				});

				Button pokemon4 = new Button();
				pokemon4.setShape(new Polygon(new Hexagon(100d).getPoints()));
				pokemon4.setMinSize(100, 90);
				pokemon4.setId(buttonIds[6]);
				pokemon4.setOnMouseClicked(new EventHandler<MouseEvent>() {

					public void handle(MouseEvent event) {
						pokemon4.setEffect(shadow);
						selectionList[globalCounter] = Integer.parseInt(pokemon4.getId());
						globalCounter++;
					}

				});

				Button pokemon5 = new Button();
				pokemon5.setShape(new Polygon(new Hexagon(100d).getPoints()));
				pokemon5.setMinSize(100, 90);
				pokemon5.setId(buttonIds[8]);
				pokemon5.setOnMouseClicked(new EventHandler<MouseEvent>() {

					public void handle(MouseEvent event) {
						pokemon5.setEffect(shadow);
						selectionList[globalCounter] = Integer.parseInt(pokemon5.getId());
						globalCounter++;
					}

				});

				Button pokemon6 = new Button();
				pokemon6.setShape(new Polygon(new Hexagon(100d).getPoints()));
				pokemon6.setMinSize(100, 90);
				pokemon6.setId(buttonIds[10]);
				pokemon6.setOnMouseClicked(new EventHandler<MouseEvent>() {

					public void handle(MouseEvent event) {
						pokemon6.setEffect(shadow);
						selectionList[globalCounter] = Integer.parseInt(pokemon6.getId());
						globalCounter++;
					}

				});

				Button pokemon7 = new Button();
				pokemon7.setShape(new Polygon(new Hexagon(100d).getPoints()));
				pokemon7.setMinSize(100, 90);
				pokemon7.setId(buttonIds[1]);
				pokemon7.setOnMouseClicked(new EventHandler<MouseEvent>() {

					public void handle(MouseEvent event) {
						pokemon7.setEffect(shadow);
						selectionList[globalCounter] = Integer.parseInt(pokemon7.getId());
						globalCounter++;
					}

				});

				Button pokemon8 = new Button();
				pokemon8.setShape(new Polygon(new Hexagon(100d).getPoints()));
				pokemon8.setMinSize(100, 90);
				pokemon8.setId(buttonIds[3]);
				pokemon8.setOnMouseClicked(new EventHandler<MouseEvent>() {

					public void handle(MouseEvent event) {
						pokemon8.setEffect(shadow);
						selectionList[globalCounter] = Integer.parseInt(pokemon8.getId());
						globalCounter++;
					}

				});

				Button pokemon9 = new Button();
				pokemon9.setShape(new Polygon(new Hexagon(100d).getPoints()));
				pokemon9.setMinSize(100, 90);
				pokemon9.setId(buttonIds[5]);
				pokemon9.setOnMouseClicked(new EventHandler<MouseEvent>() {

					public void handle(MouseEvent event) {
						pokemon9.setEffect(shadow);
						selectionList[globalCounter] = Integer.parseInt(pokemon9.getId());
						globalCounter++;
					}

				});

				Button pokemon10 = new Button();
				pokemon10.setShape(new Polygon(new Hexagon(100d).getPoints()));
				pokemon10.setMinSize(100, 90);
				pokemon10.setId(buttonIds[7]);
				pokemon10.setOnMouseClicked(new EventHandler<MouseEvent>() {

					public void handle(MouseEvent event) {
						pokemon10.setEffect(shadow);
						selectionList[globalCounter] = Integer.parseInt(pokemon10.getId());
						globalCounter++;
					}

				});

				Button pokemon11 = new Button();
				pokemon11.setShape(new Polygon(new Hexagon(100d).getPoints()));
				pokemon11.setMinSize(100, 90);
				pokemon11.setId(buttonIds[9]);
				pokemon11.setOnMouseClicked(new EventHandler<MouseEvent>() {

					public void handle(MouseEvent event) {
						pokemon11.setEffect(shadow);
						selectionList[globalCounter] = Integer.parseInt(pokemon11.getId());
						globalCounter++;
					}

				});

				Button pokemon12 = new Button();
				pokemon12.setShape(new Polygon(new Hexagon(100d).getPoints()));
				pokemon12.setMinSize(100, 90);
				pokemon12.setId(buttonIds[11]);
				pokemon12.setOnMouseClicked(new EventHandler<MouseEvent>() {

					public void handle(MouseEvent event) {
						pokemon12.setEffect(shadow);
						selectionList[globalCounter] = Integer.parseInt(pokemon12.getId());
						globalCounter++;
					}

				});

				Button battle = new Button("BATTLE!");
				battle.setMinSize(500, 180);
				battle.setOnMouseClicked(new EventHandler<MouseEvent>() {

					public void handle(MouseEvent event) {
						System.out.println(selectionList[0]);
						System.out.println(selectionList[1]);
						System.out.println(selectionList[2]);
						System.out.println(selectionList[3]);
						System.out.println(selectionList[4]);
						System.out.println(selectionList[5]);
					}

				});

				Button clear = new Button("CLEAR");
				clear.setShape(new Polygon(new Hexagon(100d).getPoints()));
				clear.setMinSize(100, 90);
				clear.setOnMouseClicked(new EventHandler<MouseEvent>() {

					public void handle(MouseEvent event) {
						for (int i = 0; i < selectionList.length; i++) {
							selectionList[i] = 0;
						}
						globalCounter = 0;

						pokemon1.setEffect(null);
						pokemon2.setEffect(null);
						pokemon3.setEffect(null);
						pokemon4.setEffect(null);
						pokemon5.setEffect(null);
						pokemon6.setEffect(null);
						pokemon7.setEffect(null);
						pokemon8.setEffect(null);
						pokemon9.setEffect(null);
						pokemon10.setEffect(null);
						pokemon11.setEffect(null);
						pokemon12.setEffect(null);
					}

				});

				// Media videoFile = new Media("/res/selectscreen.mp3");
				// mediaPlayer = new MediaPlayer(videoFile);
				// //mediaPlayer.setAutoPlay(true);
				// mediaPlayer.setVolume(0.1);
				// mediaPlayer.setOnEndOfMedia(new Runnable() {
				// public void run() {
				// mediaPlayer.seek(Duration.ZERO);
				// }
				// });
				// mediaPlayer.play();

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

				GridPane.setRowIndex(clear, 1);
				GridPane.setColumnIndex(clear, 3);

				gridPane.getChildren().addAll(pokemon1, pokemon2, pokemon3, pokemon4, pokemon5, pokemon6, pokemon7,
						pokemon8, pokemon9, pokemon10, pokemon11, pokemon12, battle, clear);

				Scene scene = new Scene(gridPane);
				theStage.setScene(scene);
				theStage.setTitle("Pokemon Selector");
				theStage.show();
			}

		});
		clip2.translateXProperty().bind(item2.translateXProperty().negate());
		item2.setClip(clip2);

		PokemonMenuItem item3 = new PokemonMenuItem(menuData.get(2).getKey());
		item3.setTranslateX(-300);
		item3.setId(menuData.get(2).getKey());
		item3.setOnMouseClicked(new EventHandler<MouseEvent>() {

			public void handle(MouseEvent event) {
				Platform.exit();
			}

		});
		clip3.translateXProperty().bind(item3.translateXProperty().negate());
		item3.setClip(clip3);

		PokemonMenuItem item4 = new PokemonMenuItem(menuData.get(3).getKey());
		item4.setTranslateX(-300);
		item4.setId(menuData.get(3).getKey());
		item4.setOnMouseClicked(new EventHandler<MouseEvent>() {

			public void handle(MouseEvent event) {
				Platform.exit();
			}

		});
		clip4.translateXProperty().bind(item4.translateXProperty().negate());
		item4.setClip(clip4);

		PokemonMenuItem item5 = new PokemonMenuItem(menuData.get(4).getKey());
		item5.setTranslateX(-300);
		item5.setId(menuData.get(4).getKey());
		item5.setOnMouseClicked(new EventHandler<MouseEvent>() {

			public void handle(MouseEvent event) {
				Platform.exit();
			}

		});
		clip5.translateXProperty().bind(item5.translateXProperty().negate());
		item5.setClip(clip5);

		PokemonMenuItem item6 = new PokemonMenuItem(menuData.get(5).getKey());
		item6.setTranslateX(-300);
		item6.setId(menuData.get(5).getKey());
		item6.setOnMouseClicked(new EventHandler<MouseEvent>() {

			public void handle(MouseEvent event) {
				Platform.exit();
			}

		});
		clip6.translateXProperty().bind(item6.translateXProperty().negate());
		item6.setClip(clip6);

		menuBox.getChildren().addAll(item1, item2, item3, item4, item5, item6);

		root.getChildren().add(menuBox);
	}

	// not mvc bc lazy/testing, will change
	public void intro(Stage primaryStage) {
		final AnchorPane root = new AnchorPane();
		Scene scene = new Scene(root, 700, 700);

		Image profImage = new Image(getClass().getResourceAsStream("/res/profoak.png"));
		ImageView profImageView = new ImageView(profImage);
		profImageView.setX(-100);
		profImageView.setY(100);
		root.getChildren().add(profImageView);

		Image pkmnImage = new Image(getClass().getResourceAsStream("/res/arbok.png"));
		final ImageView pkmnImageView = new ImageView(pkmnImage);
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
		final PathTransition pathTransition = new PathTransition();
		pathTransition.setDuration(Duration.millis(500));
		pathTransition.setPath(path);
		pathTransition.setNode(pkmnImageView);
		pathTransition.setOrientation(PathTransition.OrientationType.NONE);
		pathTransition.setCycleCount(1);
		pathTransition.setAutoReverse(false);

		// maybe just don't include
		// Image textboxImage = new
		// Image(getClass().getResourceAsStream("/res/textbox.png"));
		// ImageView textboxImageView = new ImageView(textboxImage);
		// textboxImageView.setLayoutX(185);
		// textboxImageView.setLayoutY(585);
		// root.getChildren().add(textboxImageView);

		final Label textLabel = new Label();
		changeText(textLabel);
		textLabel.setWrapText(true);
		textLabel.setMaxSize(400, 100);
		textLabel.setLayoutX(200);
		textLabel.setLayoutY(600);
		textLabel.setFont(Font.loadFont(getClass().getResourceAsStream("/res/font.ttf"), 12));

		root.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				changeText(textLabel);
				if (introIndex == 4 && !root.getChildren().contains(pkmnImageView)) {
					pkmnTimeline.play();
					root.getChildren().add(pkmnImageView);
					pathTransition.play();
				}
			}
		});
		root.getChildren().add(textLabel);

		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.setTitle("Pokemon");
		primaryStage.show();
	}

	int introIndex = 0;
	String[] introText = { "Welcome to the world of Pokemon!", "My name is professor Oak.",
			"People affectionately refer to me as the pokemon professor",
			"This world is inhabited far and wide by creatures called pokemon" };

	public void changeText(Label label) {

		if (!(introIndex >= introText.length)) {
			label.setText(introText[introIndex]);
			introIndex++;
		}

	}

	public static void main(String[] args) {
		launch(args);
	}
}

package view;

import model.GameEngine;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import controller.Controller;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PathTransition;
import javafx.animation.PauseTransition;
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
import model.Listener;
import model.Model;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
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

public class Main extends Application implements Listener {

	private final Model model = new Model();
	private final GameEngine ge = new GameEngine();
	private final Controller controller = new Controller(ge);

	MediaPlayer mediaPlayer;

	Button newGameButton;
	Button continueButton;
	Button optionsButton;
	Button controlsButton;
	Button creditsButton;
	Button exitButton;

	Stage theStage;

	int[] selectionList = new int[6];
	int globalCounter = 0;

	private final String buttonStyle = "-fx-background-color: linear-gradient(#686868 0%, #232723 25%, #373837 75%, #757575 100%), "
			+ "linear-gradient(#020b02, #3a3a3a), "
			+ "linear-gradient(#9d9e9d 0%, #6b6a6b 20%, #343534 80%, #242424 100%), "
			+ "linear-gradient(#8a8a8a 0%, #6b6a6b 20%, #343534 80%, #262626 100%), "
			+ "linear-gradient(#777777 0%, #606060 50%, #505250 51%, #2a2b2a 100%);"
			+ " -fx-background-insets: 0,1,4,5,6;" + " -fx-background-radius: 9,8,5,4,3; "
			+ " -fx-padding: 15 30 15 30; " + " -fx-font-family: \"PKMN RBYGSC\"; " + " -fx-font-size: 8px;"
			+ " -fx-font-weight: bold;" + " -fx-text-fill: white;"
			+ " -fx-effect: dropshadow( three-pass-box , rgba(255,255,255,0.2) , 1, 0.0 , 0 , 1);"
			+ " -fx-effect: dropshadow( one-pass-box , black , 0, 0.0 , 0 , -1 );";

	private ProgressBar userHealthbar = new ProgressBar();
	private ProgressBar rivalHealthbar = new ProgressBar();

	private Button pkmnButton = new Button("Switch Pkmn");
	private Button forfeitButton = new Button("Forfeit");

	private Button move1Button = new Button("move1");
	private Button move2Button = new Button("move2");
	private Button move3Button = new Button("move3");
	private Button move4Button = new Button("move4");

	private Button firstPkmnButton = new Button("pkmn1");
	private Button secondPkmnButton = new Button("pkmn2");
	private Button thirdPkmnButton = new Button("pkmn3");
	private Button fourthPkmnButton = new Button("pkmn4");
	private Button fifthPkmnButton = new Button("pkmn5");
	private Button sixthPkmnButton = new Button("pkmn6");

	private Image alivePokeball = new Image(getClass().getResourceAsStream("/res/alivepokeball.png"));
	private Image faintedPokeball = new Image(getClass().getResourceAsStream("/res/faintedpokeball.png"));

	private ImageView firstUserPokeballImageView = new ImageView(alivePokeball);
	private ImageView secondUserPokeballImageView = new ImageView(alivePokeball);
	private ImageView thirdUserPokeballImageView = new ImageView(alivePokeball);
	private ImageView fourthUserPokeballImageView = new ImageView(alivePokeball);
	private ImageView fifthUserPokeballImageView = new ImageView(alivePokeball);
	private ImageView sixthUserPokeballImageView = new ImageView(alivePokeball);

	private ImageView firstRivalPokeballImageView = new ImageView(alivePokeball);
	private ImageView secondRivalPokeballImageView = new ImageView(alivePokeball);
	private ImageView thirdRivalPokeballImageView = new ImageView(alivePokeball);
	private ImageView fourthRivalPokeballImageView = new ImageView(alivePokeball);
	private ImageView fifthRivalPokeballImageView = new ImageView(alivePokeball);
	private ImageView sixthRivalPokeballImageView = new ImageView(alivePokeball);

	private Image userPkmn = new Image(getClass().getResourceAsStream("/res/7back.png"));
	private Image rivalPkmn = new Image(getClass().getResourceAsStream("/res/2front.png"));

	ImageView userPkmnImageView = new ImageView(userPkmn);
	ImageView rivalPkmnImageView = new ImageView(rivalPkmn);

	private Label userPkmnNameLabel = new Label("Sceptile");
	private Label rivalPkmnNameLabel = new Label("Blastoise");

	private Label userHpFractionLabel = new Label("100/100");
	private Label rivalHpFractionLabel = new Label("100/100");

	private Label currentStateLabel = new Label("What will Sceptile do?");

	private static final int WIDTH = 1280;
	private static final int HEIGHT = 720;

	@Override
	public void start(Stage primaryStage) {
		try {
			Font.loadFont(getClass().getResourceAsStream("res/font.ttf"), 12);

			theStage = primaryStage;

			Scene scene = new Scene(createContent());
			primaryStage.setTitle("Pokemon Battle Sim Menu");
			primaryStage.setScene(scene);
			primaryStage.show();
			playMusic(0);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private List<Pair<String, Button>> menuData = Arrays.asList(new Pair<String, Button>("New Game", newGameButton),
			new Pair<String, Button>("Game Options", optionsButton),
			new Pair<String, Button>("Controls", controlsButton), new Pair<String, Button>("Credits", creditsButton),
			new Pair<String, Button>("Exit", exitButton));
	// new Pair<String, Button>("Continue", continueButton),

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
		Image image = new Image(getClass().getResourceAsStream("/res/pikachunight.jpg"));
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
		line = new Line(x, y, x, y + 250);
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

	private void playMusic(int track) {
		try {
			String filePath = "file:/" + System.getProperty("user.dir") + "/src/res/pokemonopening.mp3";
			if (track == 1) {
				filePath = "file:/" + System.getProperty("user.dir") + "/src/res/battle.mp3";
			} else if (track == 2){
				filePath = "file:/" + System.getProperty("user.dir") + "/src/res/victory.mp3";
			}
			Media videoFile = new Media(filePath.replace('\\', '/'));
			mediaPlayer = new MediaPlayer(videoFile);
			// mediaPlayer.setAutoPlay(true);
			mediaPlayer.setVolume(0.1);
			mediaPlayer.setOnEndOfMedia(new Runnable() {
				public void run() {
					mediaPlayer.seek(Duration.ZERO);
				}
			});
			mediaPlayer.play();
		} catch (Exception e) {
			e.printStackTrace();
		}

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
				intro(theStage);
			}

		});
		clip.translateXProperty().bind(item1.translateXProperty().negate());
		item1.setClip(clip);

		PokemonMenuItem item2 = new PokemonMenuItem(menuData.get(1).getKey());
		item2.setTranslateX(-300);
		item2.setId(menuData.get(1).getKey());
		item2.setOnMouseClicked(new EventHandler<MouseEvent>() {

			public void handle(MouseEvent event) {

				selectScreen(theStage);
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

		// PokemonMenuItem item6 = new
		// PokemonMenuItem(menuData.get(5).getKey());
		// item6.setTranslateX(-300);
		// item6.setId(menuData.get(5).getKey());
		// item6.setOnMouseClicked(new EventHandler<MouseEvent>() {
		//
		// public void handle(MouseEvent event) {
		// Platform.exit();
		// }
		//
		// });
		// clip6.translateXProperty().bind(item6.translateXProperty().negate());
		// item6.setClip(clip6);

		menuBox.getChildren().addAll(item1, item2, item3, item4, item5);
		// item6);

		root.getChildren().add(menuBox);
	}

	public void selectScreen(Stage theStage) {
		/*
		 * 1 = Venasaur 2 = Blastoise 3 = Charizard 4 = Feraligatr 5 = Meganium
		 * 6 = Typhlosion 7 = Sceptile 8 = Blaziken 9 = Swampert 10 = Empoleon
		 * 11 = Torterra 12 = Infernape
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
		pokemon2.setId(buttonIds[6]);
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
		pokemon3.setId(buttonIds[2]);
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
		pokemon4.setId(buttonIds[7]);
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
		pokemon5.setId(buttonIds[1]);
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
		pokemon6.setId(buttonIds[8]);
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
		pokemon7.setId(buttonIds[4]);
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
		pokemon8.setId(buttonIds[10]);
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
		pokemon10.setId(buttonIds[11]);
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
		pokemon11.setId(buttonIds[3]);
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
		pokemon12.setId(buttonIds[9]);
		pokemon12.setOnMouseClicked(new EventHandler<MouseEvent>() {

			public void handle(MouseEvent event) {
				pokemon12.setEffect(shadow);
				selectionList[globalCounter] = Integer.parseInt(pokemon12.getId());
				globalCounter++;
			}

		});

		Button battle = new Button("BATTLE!");
		battle.setMinSize(200, 100);
		battle.setOnMouseClicked(new EventHandler<MouseEvent>() {

			public void handle(MouseEvent event) {
				if (globalCounter >= 6) {
					ge.playerInit(name, selectionList);
					mediaPlayer.stop();
					playMusic(1);
					battleUi(theStage);
				} else {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error!");
					alert.setHeaderText("Not enough Pokemon to battle with!");
					alert.setContentText("You must select 6 Pokemon!");

					alert.showAndWait();
				}
			}

		});

		Button clear = new Button("CLEAR");
		clear.setMinSize(200, 100);
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

		HBox hbox = new HBox();
		clear.setStyle(buttonStyle);
		battle.setStyle(buttonStyle);
		hbox.getChildren().addAll(clear, battle);
		GridPane.setRowIndex(hbox, 1);
		GridPane.setColumnIndex(hbox, 3);

		gridPane.getChildren().addAll(pokemon1, pokemon2, pokemon3, pokemon4, pokemon5, pokemon6, pokemon7, pokemon8,
				pokemon9, pokemon10, pokemon11, pokemon12, hbox);

		final AnchorPane root = new AnchorPane();
		Image background = new Image(getClass().getResourceAsStream("/res/selectWallpaper.png"));
		ImageView backgroundIv = new ImageView(background);
		backgroundIv.setFitHeight(700);
		backgroundIv.setFitWidth(700);
		root.getChildren().add(backgroundIv);

		root.getChildren().add(gridPane);

		Scene scene = new Scene(root, 700, 700);
		theStage.setScene(scene);
		theStage.setTitle("Pokemon Selector");
		theStage.show();
	}

	// not mvc bc lazy/testing, will change
	public void intro(Stage primaryStage) {
		final AnchorPane root = new AnchorPane();
		Scene scene = new Scene(root, 688, 688);

		// background
		Image background = new Image(getClass().getResourceAsStream("/res/introbackground.png"));
		ImageView backgroundIv = new ImageView(background);
		backgroundIv.setFitHeight(700);
		backgroundIv.setFitWidth(700);
		root.getChildren().add(backgroundIv);

		// prof oak
		Image profImage = new Image(getClass().getResourceAsStream("/res/profoak.png"));
		ImageView profImageView = new ImageView(profImage);
		profImageView.setX(-100);
		profImageView.setY(100);
		root.getChildren().add(profImageView);

		// arbok
		Image pkmnImage = new Image(getClass().getResourceAsStream("/res/arbok.png"));
		final ImageView pkmnImageView = new ImageView(pkmnImage);
		pkmnImageView.setX(250);
		pkmnImageView.setY(200);

		// prof slides in from left
		final Timeline profTimeline = new Timeline();
		profTimeline.setCycleCount(1);
		final KeyValue profKv = new KeyValue(profImageView.xProperty(), 250);
		final KeyFrame profKf = new KeyFrame(Duration.millis(2000), profKv);
		profTimeline.getKeyFrames().add(profKf);
		profTimeline.play();

		// prof moves over for pkmn
		final Timeline pkmnTimeline = new Timeline();
		pkmnTimeline.setCycleCount(1);
		final KeyValue pkmnKv = new KeyValue(profImageView.xProperty(), 350);
		final KeyFrame pkmnKf = new KeyFrame(Duration.millis(500), pkmnKv);
		pkmnTimeline.getKeyFrames().add(pkmnKf);

		// arbok moving out
		Path pkmnPath = new Path();
		pkmnPath.getElements().add(new MoveTo(pkmnImageView.getX(), pkmnImageView.getY()));
		pkmnPath.getElements().add(new QuadCurveTo(200, 200, 250, 350));
		final PathTransition pkmnPathTransition = new PathTransition();
		pkmnPathTransition.setDuration(Duration.millis(500));
		pkmnPathTransition.setPath(pkmnPath);
		pkmnPathTransition.setNode(pkmnImageView);
		pkmnPathTransition.setOrientation(PathTransition.OrientationType.NONE);
		pkmnPathTransition.setCycleCount(1);
		pkmnPathTransition.setAutoReverse(false);

		// pokemon text label
		final Label textLabel = new Label();
		changeIntroText(textLabel);
		textLabel.setWrapText(true);
		textLabel.setMaxSize(400, 100);
		textLabel.setLayoutX(200);
		textLabel.setLayoutY(600);
		textLabel.setFont(Font.loadFont(getClass().getResourceAsStream("/res/font.ttf"), 12));
		root.getChildren().add(textLabel);

		// fading out of arbok and prof
		final FadeTransition pkmnFt = new FadeTransition(Duration.millis(1000), pkmnImageView);
		pkmnFt.setFromValue(1.0);
		pkmnFt.setToValue(0.0);
		pkmnFt.setCycleCount(1);
		final FadeTransition profFt = new FadeTransition(Duration.millis(1000), profImageView);
		profFt.setFromValue(1.0);
		profFt.setToValue(0.0);
		profFt.setCycleCount(1);

		// boy button
		final Button boyButton = new Button("Boy");
		boyButton.setLayoutX(250);
		boyButton.setLayoutY(500);
		boyButton.setPrefWidth(100);
		boyButton.setPrefHeight(50);
		boyButton.setStyle(buttonStyle);

		// boy image
		Image boyImage = new Image(getClass().getResourceAsStream("/res/boy.png"));
		final ImageView boyImageView = new ImageView(boyImage);
		boyImageView.setX(250);
		boyImageView.setY(200);

		// moves boy for name entry
		final Timeline boyTimeline = new Timeline();
		boyTimeline.setCycleCount(1);
		final KeyValue boyKv = new KeyValue(boyImageView.xProperty(), 375);
		final KeyFrame boyKf = new KeyFrame(Duration.millis(500), boyKv);
		boyTimeline.getKeyFrames().add(boyKf);

		// boy fading in
		final FadeTransition boyFt = new FadeTransition(Duration.millis(1000), boyImageView);
		boyFt.setFromValue(0.0);
		boyFt.setToValue(1.0);
		boyFt.setCycleCount(1);

		// boy shrinking
		final ScaleTransition boySt = new ScaleTransition(Duration.millis(500), boyImageView);
		boySt.setFromX(1.0);
		boySt.setFromY(1.0);
		boySt.setToX(0.1);
		boySt.setToY(0.1);
		boySt.setCycleCount(1);

		// girl button
		final Button girlButton = new Button("Girl");
		girlButton.setLayoutX(350);
		girlButton.setLayoutY(500);
		girlButton.setPrefWidth(100);
		girlButton.setPrefHeight(50);
		girlButton.setStyle(buttonStyle);

		// girl image
		Image girlImage = new Image(getClass().getResourceAsStream("/res/girl.png"));
		final ImageView girlImageView = new ImageView(girlImage);
		girlImageView.setX(250);
		girlImageView.setY(200);

		// moves girl for name entry
		final Timeline girlTimeline = new Timeline();
		girlTimeline.setCycleCount(1);
		final KeyValue girlKv = new KeyValue(girlImageView.xProperty(), 375);
		final KeyFrame girlKf = new KeyFrame(Duration.millis(500), girlKv);
		girlTimeline.getKeyFrames().add(girlKf);

		// girl fading in
		final FadeTransition girlFt = new FadeTransition(Duration.millis(1000), girlImageView);
		girlFt.setFromValue(0.0);
		girlFt.setToValue(1.0);
		girlFt.setCycleCount(1);

		// girl shrinking
		final ScaleTransition girlSt = new ScaleTransition(Duration.millis(500), girlImageView);
		girlSt.setFromX(1.0);
		girlSt.setFromY(1.0);
		girlSt.setToX(0.1);
		girlSt.setToY(0.1);
		girlSt.setCycleCount(1);

		// TODO added
		// boyButton.setOnAction(controller);

		// change text to include boy
		boyButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				root.getChildren().remove(boyButton);
				root.getChildren().remove(girlButton);
				boyFt.play();
				root.getChildren().add(boyImageView);
				textLabel.setText(introText[introIndex] + "boy!");
				introIndex++;
			}
		});

		// TODO added
		// girlButton.setOnAction(controller);

		// change text to include girl
		girlButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				root.getChildren().remove(boyButton);
				root.getChildren().remove(girlButton);
				girlFt.play();
				root.getChildren().add(girlImageView);
				textLabel.setText(introText[introIndex] + "girl!");
				introIndex++;
			}
		});

		// done button
		final Button doneButton = new Button("Done");
		doneButton.setLayoutX(200);
		doneButton.setLayoutY(550);
		doneButton.setPrefWidth(170);
		doneButton.setPrefHeight(20);
		doneButton.setStyle(buttonStyle);

		// name text field
		final TextField nameTextField = new TextField();
		nameTextField.setLayoutX(200);
		nameTextField.setLayoutY(500);

		// TODO added
		// doneButton.setOnAction(controller);

		// update name
		doneButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				name = nameTextField.getText();
				root.getChildren().remove(doneButton);
				root.getChildren().remove(nameTextField);
				if (name.equalsIgnoreCase("")) {
					if (root.getChildren().contains(boyImageView)) {
						name = "Ash";
					} else {
						name = "Misty";
					}
				}
				textLabel.setText(introText[introIndex] + name + '!');
				introIndex++;
			}
		});

		// wait ~1 second before removing (to show shrink) then call pokemon
		// selection method
		final PauseTransition pt = new PauseTransition(Duration.millis(750));
		pt.setOnFinished(event -> {
			if (root.getChildren().contains(boyImageView)) {
				root.getChildren().remove(boyImageView);
			}
			if (root.getChildren().contains(girlImageView)) {
				root.getChildren().remove(girlImageView);
			}
			// TODO change to pkmn selection
			selectScreen(primaryStage);
		});

		root.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				// prof moves over, arbok comes out
				if (introIndex == 3) {
					pkmnTimeline.play();
					root.getChildren().add(pkmnImageView);
					pkmnPathTransition.play();
				}
				// arbok and prof fade out, add buttons
				if (introIndex == 6
						&& (!root.getChildren().contains(girlButton) || !root.getChildren().contains(boyButton))) {
					pkmnFt.play();
					profFt.play();
					root.getChildren().add(boyButton);
					root.getChildren().add(girlButton);
					changeIntroText(textLabel);
				}
				// character moves over, add button/textfield
				if (introIndex == 8 && (!root.getChildren().contains(doneButton))) {
					if (root.getChildren().contains(boyImageView)) {
						boyTimeline.play();
					} else {
						girlTimeline.play();
					}
					root.getChildren().add(nameTextField);
					root.getChildren().add(doneButton);
					changeIntroText(textLabel);
				}

				// start pokemon selection
				if (introIndex == introText.length) {
					if (root.getChildren().contains(textLabel)) {
						root.getChildren().remove(textLabel);
					}
					if (root.getChildren().contains(boyImageView)) {
						boySt.play();
						pt.play();
					}
					if (root.getChildren().contains(girlImageView)) {
						girlSt.play();
						pt.play();
					}
				}
				// don't change text if buttons are on the screen - waiting for
				// them to be pressed instead
				if ((!root.getChildren().contains(girlButton) || !root.getChildren().contains(boyButton))
						&& !root.getChildren().contains(doneButton)) {
					changeIntroText(textLabel);
				}
			}
		});

		// root.setOnMouseClicked(controller);

		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.setTitle("Pokemon");
		primaryStage.show();
	}

	private int textBoxIndex = 0;

	public void battleUi(Stage primaryStage) {
		final AnchorPane root = new AnchorPane();

		this.ge.setListener(this);
		Scene scene = new Scene(root, 700, 700);
		Image battleBackground = new Image(getClass().getResourceAsStream("/res/battlebackground.jpg"));
		Image rightBattleBox = new Image(getClass().getResourceAsStream("/res/rightbattlebox.png"));
		Image leftBattleBox = new Image(getClass().getResourceAsStream("/res/leftbattlebox.png"));
		Image battleTextbox = new Image(getClass().getResourceAsStream("/res/battletextbox.png"));

		ImageView battleBackgroundIv = new ImageView(battleBackground);
		battleBackgroundIv.setFitHeight(700);
		battleBackgroundIv.setFitWidth(700);

		ImageView rightBattleBoxIv = new ImageView(rightBattleBox);
		rightBattleBoxIv.setLayoutX(0);
		rightBattleBoxIv.setLayoutY(10);
		rightBattleBoxIv.setFitHeight(115);
		rightBattleBoxIv.setFitWidth(250);

		ImageView leftBattleBoxIv = new ImageView(leftBattleBox);
		leftBattleBoxIv.setLayoutX(470);
		leftBattleBoxIv.setLayoutY(350);
		leftBattleBoxIv.setFitHeight(115);
		leftBattleBoxIv.setFitWidth(250);

		ImageView battleTextboxIv = new ImageView(battleTextbox);
		battleTextboxIv.setLayoutX(0);
		battleTextboxIv.setLayoutY(575);
		battleTextboxIv.setFitHeight(125);
		battleTextboxIv.setFitWidth(400);
		battleTextboxIv.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				updateStateText();
			}
		});

		this.userPkmn = new Image(getClass().getResourceAsStream("/res/" + ge.getP1Pokemon().getID() + "back.png"));
		this.userPkmnImageView = new ImageView(userPkmn);
		this.userPkmnNameLabel = new Label(ge.getP1Pokemon().getName());
		this.userHpFractionLabel = new Label(ge.getP1Pokemon().getHp() + " \\ " + ge.getP1Pokemon().getHp());
		this.currentStateLabel = new Label("What will " + ge.getP1Pokemon().getName() + " do?");
		this.move1Button = new Button(ge.getP1Pokemon().getMoves().get(0).getName());
		this.move2Button = new Button(ge.getP1Pokemon().getMoves().get(1).getName());
		this.move3Button = new Button(ge.getP1Pokemon().getMoves().get(2).getName());
		this.move4Button = new Button(ge.getP1Pokemon().getMoves().get(3).getName());
		System.out.println("/res/" + ge.getAIPokemon().getID() + "front.png");
		this.rivalPkmn = new Image(getClass().getResourceAsStream("/res/" + ge.getAIPokemon().getID() + "front.png"));
		this.rivalPkmnImageView = new ImageView(rivalPkmn);
		this.rivalPkmnNameLabel = new Label(ge.getAIPokemon().getName());
		this.rivalHpFractionLabel = new Label(ge.getAIPokemon().getHp() + " \\ " + ge.getAIPokemon().getHp());

		userPkmnImageView.setFitHeight(300);
		userPkmnImageView.setFitWidth(300);
		userPkmnImageView.setLayoutX(70);
		userPkmnImageView.setLayoutY(300);

		rivalPkmnImageView.setFitHeight(250);
		rivalPkmnImageView.setFitWidth(250);
		rivalPkmnImageView.setLayoutX(400);
		rivalPkmnImageView.setLayoutY(50);

		firstRivalPokeballImageView.setFitHeight(20);
		firstRivalPokeballImageView.setFitWidth(20);
		firstRivalPokeballImageView.setLayoutX(10);
		firstRivalPokeballImageView.setLayoutY(110);

		secondRivalPokeballImageView.setFitHeight(20);
		secondRivalPokeballImageView.setFitWidth(20);
		secondRivalPokeballImageView.setLayoutX(35);
		secondRivalPokeballImageView.setLayoutY(110);

		thirdRivalPokeballImageView.setFitHeight(20);
		thirdRivalPokeballImageView.setFitWidth(20);
		thirdRivalPokeballImageView.setLayoutX(60);
		thirdRivalPokeballImageView.setLayoutY(110);

		fourthRivalPokeballImageView.setFitHeight(20);
		fourthRivalPokeballImageView.setFitWidth(20);
		fourthRivalPokeballImageView.setLayoutX(85);
		fourthRivalPokeballImageView.setLayoutY(110);

		fifthRivalPokeballImageView.setFitHeight(20);
		fifthRivalPokeballImageView.setFitWidth(20);
		fifthRivalPokeballImageView.setLayoutX(110);
		fifthRivalPokeballImageView.setLayoutY(110);

		sixthRivalPokeballImageView.setFitHeight(20);
		sixthRivalPokeballImageView.setFitWidth(20);
		sixthRivalPokeballImageView.setLayoutX(135);
		sixthRivalPokeballImageView.setLayoutY(110);

		firstUserPokeballImageView.setFitHeight(20);
		firstUserPokeballImageView.setFitWidth(20);
		firstUserPokeballImageView.setLayoutX(545);
		firstUserPokeballImageView.setLayoutY(450);

		secondUserPokeballImageView.setFitHeight(20);
		secondUserPokeballImageView.setFitWidth(20);
		secondUserPokeballImageView.setLayoutX(570);
		secondUserPokeballImageView.setLayoutY(450);

		thirdUserPokeballImageView.setFitHeight(20);
		thirdUserPokeballImageView.setFitWidth(20);
		thirdUserPokeballImageView.setLayoutX(595);
		thirdUserPokeballImageView.setLayoutY(450);

		fourthUserPokeballImageView.setFitHeight(20);
		fourthUserPokeballImageView.setFitWidth(20);
		fourthUserPokeballImageView.setLayoutX(620);
		fourthUserPokeballImageView.setLayoutY(450);

		fifthUserPokeballImageView.setFitHeight(20);
		fifthUserPokeballImageView.setFitWidth(20);
		fifthUserPokeballImageView.setLayoutX(645);
		fifthUserPokeballImageView.setLayoutY(450);

		sixthUserPokeballImageView.setFitHeight(20);
		sixthUserPokeballImageView.setFitWidth(20);
		sixthUserPokeballImageView.setLayoutX(670);
		sixthUserPokeballImageView.setLayoutY(450);

		pkmnButton.setLayoutX(400);
		pkmnButton.setLayoutY(650);
		pkmnButton.setPrefWidth(150);
		pkmnButton.setPrefHeight(50);
		pkmnButton.setStyle(buttonStyle);
		pkmnButton.setOnMouseClicked(controller);

		forfeitButton.setLayoutX(550);
		forfeitButton.setLayoutY(650);
		forfeitButton.setPrefWidth(150);
		forfeitButton.setPrefHeight(50);
		forfeitButton.setStyle(buttonStyle);
		forfeitButton.setOnMouseClicked(controller);

		move1Button.setLayoutX(400);
		move1Button.setLayoutY(550);
		move1Button.setPrefWidth(150);
		move1Button.setPrefHeight(50);
		move1Button.setStyle(buttonStyle);
		move1Button.setOnAction(controller);

		move2Button.setLayoutX(550);
		move2Button.setLayoutY(550);
		move2Button.setPrefWidth(150);
		move2Button.setPrefHeight(50);
		move2Button.setStyle(buttonStyle);
		move2Button.setOnAction(controller);

		move3Button.setLayoutX(400);
		move3Button.setLayoutY(600);
		move3Button.setPrefWidth(150);
		move3Button.setPrefHeight(50);
		move3Button.setStyle(buttonStyle);
		move3Button.setOnAction(controller);

		move4Button.setLayoutX(550);
		move4Button.setLayoutY(600);
		move4Button.setPrefWidth(150);
		move4Button.setPrefHeight(50);
		move4Button.setStyle(buttonStyle);
		move4Button.setOnAction(controller);

		firstPkmnButton.setLayoutX(400);
		firstPkmnButton.setLayoutY(550);
		firstPkmnButton.setPrefWidth(150);
		firstPkmnButton.setPrefHeight(50);
		firstPkmnButton.setStyle(buttonStyle);
		firstPkmnButton.setOnAction(controller);

		secondPkmnButton.setLayoutX(550);
		secondPkmnButton.setLayoutY(550);
		secondPkmnButton.setPrefWidth(150);
		secondPkmnButton.setPrefHeight(50);
		secondPkmnButton.setStyle(buttonStyle);
		secondPkmnButton.setOnAction(controller);

		thirdPkmnButton.setLayoutX(400);
		thirdPkmnButton.setLayoutY(600);
		thirdPkmnButton.setPrefWidth(150);
		thirdPkmnButton.setPrefHeight(50);
		thirdPkmnButton.setStyle(buttonStyle);
		thirdPkmnButton.setOnAction(controller);

		fourthPkmnButton.setLayoutX(550);
		fourthPkmnButton.setLayoutY(600);
		fourthPkmnButton.setPrefWidth(150);
		fourthPkmnButton.setPrefHeight(50);
		fourthPkmnButton.setStyle(buttonStyle);
		fourthPkmnButton.setOnAction(controller);

		fifthPkmnButton.setLayoutX(400);
		fifthPkmnButton.setLayoutY(650);
		fifthPkmnButton.setPrefWidth(150);
		fifthPkmnButton.setPrefHeight(50);
		fifthPkmnButton.setStyle(buttonStyle);
		fifthPkmnButton.setOnAction(controller);

		sixthPkmnButton.setLayoutX(550);
		sixthPkmnButton.setLayoutY(650);
		sixthPkmnButton.setPrefWidth(150);
		sixthPkmnButton.setPrefHeight(50);
		sixthPkmnButton.setStyle(buttonStyle);
		sixthPkmnButton.setOnAction(controller);

		rivalHealthbar.setProgress(1);
		rivalHealthbar.setLayoutX(40);
		rivalHealthbar.setLayoutY(60);
		rivalHealthbar.setMinWidth(165);
		if (rivalHealthbar.getProgress() <= 0.3) {
			rivalHealthbar.setStyle("-fx-accent: red;");
		} else if (rivalHealthbar.getProgress() <= 0.7) {
			rivalHealthbar.setStyle("-fx-accent: orange;");
		} else {
			rivalHealthbar.setStyle("-fx-accent: green;");
		}

		userHealthbar.setProgress(1);
		userHealthbar.setLayoutX(530);
		userHealthbar.setLayoutY(400);
		userHealthbar.setMinWidth(165);
		if (userHealthbar.getProgress() <= 0.3) {
			userHealthbar.setStyle("-fx-accent: red;");
		} else if (userHealthbar.getProgress() <= 0.7) {
			userHealthbar.setStyle("-fx-accent: orange;");
		} else {
			userHealthbar.setStyle("-fx-accent: green;");
		}

		rivalPkmnNameLabel.setMaxSize(400, 100);
		rivalPkmnNameLabel.setLayoutX(15);
		rivalPkmnNameLabel.setLayoutY(35);
		rivalPkmnNameLabel.setFont(Font.font("PKMN RBYGSC", 12));

		userPkmnNameLabel.setMaxSize(400, 100);
		userPkmnNameLabel.setLayoutX(515);
		userPkmnNameLabel.setLayoutY(375);
		userPkmnNameLabel.setFont(Font.font("PKMN RBYGSC", 12));

		final Label userHpLabel = new Label("Hp:");
		userHpLabel.setMaxSize(400, 100);
		userHpLabel.setLayoutX(500);
		userHpLabel.setLayoutY(400);
		userHpLabel.setFont(Font.font("PKMN RBYGSC", 12));

		final Label rivalHpLabel = new Label("Hp:");
		rivalHpLabel.setMaxSize(400, 100);
		rivalHpLabel.setLayoutX(10);
		rivalHpLabel.setLayoutY(60);
		rivalHpLabel.setFont(Font.font("PKMN RBYGSC", 12));

		userHpFractionLabel.setMaxSize(400, 100);
		userHpFractionLabel.setLayoutX(530);
		userHpFractionLabel.setLayoutY(425);
		userHpFractionLabel.setFont(Font.font("PKMN RBYGSC", 12));

		rivalHpFractionLabel.setMaxSize(400, 100);
		rivalHpFractionLabel.setLayoutX(45);
		rivalHpFractionLabel.setLayoutY(85);
		rivalHpFractionLabel.setFont(Font.font("PKMN RBYGSC", 12));

		currentStateLabel.setWrapText(true);
		currentStateLabel.setMaxSize(335, 85);
		currentStateLabel.setLayoutX(18);
		currentStateLabel.setLayoutY(595);
		currentStateLabel.setFont(Font.font("PKMN RBYGSC", 12));

		// Add background first
		root.getChildren().add(battleBackgroundIv);

		// left and right description box thing
		root.getChildren().add(leftBattleBoxIv);
		root.getChildren().add(rightBattleBoxIv);

		// battle textbox
		root.getChildren().add(battleTextboxIv);

		// pokemon images
		root.getChildren().add(rivalPkmnImageView);
		root.getChildren().add(userPkmnImageView);

		// Hp bar
		root.getChildren().add(rivalHealthbar);
		root.getChildren().add(userHealthbar);

		// name label
		root.getChildren().add(rivalPkmnNameLabel);
		root.getChildren().add(userPkmnNameLabel);

		// hp label
		root.getChildren().add(rivalHpLabel);
		root.getChildren().add(userHpLabel);

		// hp fraction label
		root.getChildren().add(userHpFractionLabel);
		root.getChildren().add(rivalHpFractionLabel);

		// current state
		root.getChildren().add(currentStateLabel);

		// root.getChildren().add(firstPkmnButton);
		// root.getChildren().add(secondPkmnButton);
		// root.getChildren().add(thirdPkmnButton);
		// root.getChildren().add(fourthPkmnButton);
		// root.getChildren().add(fifthPkmnButton);
		// root.getChildren().add(sixthPkmnButton);

		root.getChildren().add(move1Button);
		root.getChildren().add(move2Button);
		root.getChildren().add(move3Button);
		root.getChildren().add(move4Button);
		root.getChildren().add(pkmnButton);
		root.getChildren().add(forfeitButton);

		root.getChildren().add(firstRivalPokeballImageView);
		root.getChildren().add(secondRivalPokeballImageView);
		root.getChildren().add(thirdRivalPokeballImageView);
		root.getChildren().add(fourthRivalPokeballImageView);
		root.getChildren().add(fifthRivalPokeballImageView);
		root.getChildren().add(sixthRivalPokeballImageView);

		root.getChildren().add(firstUserPokeballImageView);
		root.getChildren().add(secondUserPokeballImageView);
		root.getChildren().add(thirdUserPokeballImageView);
		root.getChildren().add(fourthUserPokeballImageView);
		root.getChildren().add(fifthUserPokeballImageView);
		root.getChildren().add(sixthUserPokeballImageView);

		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.setTitle("Pokemon");
		primaryStage.show();

	}

	@Override
	public void updated() {
		updatePkmnImages();
		updatePkmnNames();
		updateHealthbars();
		updateHpFractions();
		updatePokeballs();
		updateStateText();
		updateMoveButtons();
		updatePkmnButtons();
	}

	private void updatePkmnNames() {
		userPkmnNameLabel.setText(this.ge.getP1Pokemon().getName());
		rivalPkmnNameLabel.setText(this.ge.getAIPokemon().getName());

	}

	private void updatePkmnImages() {
		userPkmnImageView.setImage(
				new Image(getClass().getResourceAsStream("/res/" + this.ge.getP1Pokemon().getID() + "back.png")));
		rivalPkmnImageView.setImage(
				new Image(getClass().getResourceAsStream("/res/" + this.ge.getAIPokemon().getID() + "front.png")));

	}

	private void updatePokeballs() {
		// for (int i = 0; i < model.getUserTeam().size(); ++i){
		// boolean alive = true;
		// if (!model.getUserTeam().get(i).isAlive()){
		// alive = false;
		// }
		// switch (i) {
		// case 0:
		// if (alive) {
		// firstUserPokeballImageView.setImage(alivePokeball);
		// } else {
		// firstUserPokeballImageView.setImage(faintedPokeball);
		// }
		// break;
		// case 1:
		// if (alive) {
		// secondUserPokeballImageView.setImage(alivePokeball);
		// } else {
		// secondUserPokeballImageView.setImage(faintedPokeball);
		// }
		// break;
		// case 2:
		// if (alive) {
		// thirdUserPokeballImageView.setImage(alivePokeball);
		// } else {
		// thirdUserPokeballImageView.setImage(faintedPokeball);
		// }
		// break;
		// case 3:
		// if (alive) {
		// fourthUserPokeballImageView.setImage(alivePokeball);
		// } else {
		// fourthUserPokeballImageView.setImage(faintedPokeball);
		// }
		// break;
		// case 4:
		// if (alive) {
		// fifthUserPokeballImageView.setImage(alivePokeball);
		// } else {
		// fifthUserPokeballImageView.setImage(faintedPokeball);
		// }
		// break;
		// case 5:
		// if (alive) {
		// sixthUserPokeballImageView.setImage(alivePokeball);
		// } else {
		// sixthUserPokeballImageView.setImage(faintedPokeball);
		// }
		// break;
		// }
		// }
		//
		for (int i = 0; i < ge.getAI().getPokeParty().size(); ++i) {
			boolean alive = true;
			if (ge.getAI().getPokeParty().get(i).isFainted()) {
				alive = false;
			}
			switch (i) {
			case 0:
				if (alive) {
					firstRivalPokeballImageView.setImage(alivePokeball);
				} else {
					firstRivalPokeballImageView.setImage(faintedPokeball);
				}
				break;
			case 1:
				if (alive) {
					secondRivalPokeballImageView.setImage(alivePokeball);
				} else {
					secondRivalPokeballImageView.setImage(faintedPokeball);
				}
				break;
			case 2:
				if (alive) {
					thirdRivalPokeballImageView.setImage(alivePokeball);
				} else {
					thirdRivalPokeballImageView.setImage(faintedPokeball);
				}
				break;
			case 3:
				if (alive) {
					fourthRivalPokeballImageView.setImage(alivePokeball);
				} else {
					fourthRivalPokeballImageView.setImage(faintedPokeball);
				}
				break;
			case 4:
				if (alive) {
					fifthRivalPokeballImageView.setImage(alivePokeball);
				} else {
					fifthRivalPokeballImageView.setImage(faintedPokeball);
				}
				break;
			case 5:
				if (alive) {
					sixthRivalPokeballImageView.setImage(alivePokeball);
				} else {
					sixthRivalPokeballImageView.setImage(faintedPokeball);
				}
				break;
			}
		}
	}

	private void updateHealthbars() {

		userHealthbar.setProgress((((this.ge.getP1Pokemon().getHp() <= 0) ? 0.0
				: (float) (this.ge.getP1Pokemon().getHp()) / this.ge.getP1Pokemon().getTotalHP())));
		if (userHealthbar.getProgress() <= 0.3) {
			userHealthbar.setStyle("-fx-accent: red;");
		} else if (userHealthbar.getProgress() <= 0.7) {
			userHealthbar.setStyle("-fx-accent: orange;");
		} else {
			userHealthbar.setStyle("-fx-accent: green;");
		}
		rivalHealthbar.setProgress((((this.ge.getAIPokemon().getHp() <= 0) ? 0.0
				: (float) (this.ge.getAIPokemon().getHp()) / this.ge.getAIPokemon().getTotalHP())));
		if (rivalHealthbar.getProgress() <= 0.3) {
			rivalHealthbar.setStyle("-fx-accent: red;");
		} else if (rivalHealthbar.getProgress() <= 0.7) {
			rivalHealthbar.setStyle("-fx-accent: orange;");
		} else {
			rivalHealthbar.setStyle("-fx-accent: green;");
		}
	}

	private void updateHpFractions() {
		this.userHpFractionLabel.setText(((this.ge.getP1Pokemon().getHp() <= 0) ? "0" : this.ge.getP1Pokemon().getHp())
				+ "/" + this.ge.getP1Pokemon().getTotalHP());
		this.rivalHpFractionLabel.setText(((this.ge.getAIPokemon().getHp() <= 0) ? "0" : this.ge.getAIPokemon().getHp())
				+ "/" + this.ge.getAIPokemon().getTotalHP());
	}

	private void updateStateText() {
	}

	private void updateMoveButtons() {
		this.move1Button.setText("" + this.ge.getP1Pokemon().getMoves().get(0).getName());
		this.move2Button.setText("" + this.ge.getP1Pokemon().getMoves().get(1).getName());
		this.move3Button.setText("" + this.ge.getP1Pokemon().getMoves().get(2).getName());
		this.move4Button.setText("" + this.ge.getP1Pokemon().getMoves().get(3).getName());
		this.pkmnButton.setText("Switch Pkmn");
		this.forfeitButton.setText("Forfeit");

	}

	private void updatePkmnButtons() {
		// probably need to check the size of the team instead (or make them
		// always choose 6 pkmn)
		// firstPkmnButton.setText(model.getCurrentTeam().get(0).getName());
		// secondPkmnButton.setText(model.getCurrentTeam().get(1).getName());
		// thirdPkmnButton.setText(model.getCurrentTeam().get(2).getName());
		// fourthPkmnButton.setText(model.getCurrentTeam().get(3).getName());
		// fifthPkmnButton.setText(model.getCurrentTeam().get(4).getName());
		// sixthPkmnButton.setText(model.getCurrentTeam().get(5).getName());
	}

	public void changeIntroText(Label label) {
		if (!(introIndex >= introText.length)) {
			label.setText(introText[introIndex]);
			introIndex++;
		}
	}

	@Override
	public void attackPhase() {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < ge.outputStrings.size(); i++) {
			stringBuilder.append(this.ge.outputStrings.get(i) + "\n");
			System.out.println(this.ge.outputStrings.get(i));
		}
		System.out.println();

		this.currentStateLabel.setText(stringBuilder.toString());
		this.updateHealthbars();
		this.updateHpFractions();
	}

	@Override
	public void switchPhase() {
		try {
			this.move1Button.setText(this.ge.availablePoke.get(0));
			this.move2Button.setText(this.ge.availablePoke.get(1));
			this.move3Button.setText(this.ge.availablePoke.get(2));
			this.move4Button.setText(this.ge.availablePoke.get(3));
			this.pkmnButton.setText(this.ge.availablePoke.get(4));
			this.forfeitButton.setText("Cancel");
		} catch (Exception e) {

		}
	}

	@Override
	public void gameOver() {
		// TODO Auto-generated method stub
		this.currentStateLabel.setText("Winner is " + this.ge.getWinner() + "!");
		if (this.ge.getWinner().equals(this.ge.getP1().getPlayerName())) {
			rivalPkmnImageView.setImage(null);
			sixthRivalPokeballImageView.setImage(faintedPokeball);
		}
		this.mediaPlayer.stop();
		playMusic(2);
	}

	@Override
	public void faintedPhase() {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateAIImage() {
		rivalPkmnImageView
				.setImage(new Image(getClass().getResourceAsStream("/res/" + ge.getAIPokemon().getID() + "front.png")));
		rivalPkmnNameLabel.setText(ge.getAIPokemon().getName());
		this.updateHealthbars();
		this.updateHpFractions();
		this.updatePokeballs();

	}

	// For intro use
	private int introIndex = 0;
	private final String[] introText = { "Welcome to the world of Pokemon!", "My name is Professor Oak.",
			"People affectionately refer to me as the Pokemon professor!",
			"This world is inhabited far and wide by creatures called Pokemon!",
			"For some people pokemon are pets, others use them for battling!",
			"That is what you will be doing, but first tell me a little about yourself!",
			"Now tell me, are you a boy or are you a girl?", "Oh so you're a ",
			"Lets begin with your name! What is it?", "Right so your name is ",
			"You will now choose your team for battle!" };
	private String name;

	public static void main(String[] args) {
		launch(args);
	}

}

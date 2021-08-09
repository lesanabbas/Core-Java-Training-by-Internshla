package com.lesan.connect4;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Controller implements Initializable {

	private static final int COLUMNS = 7;
	private static final int ROW = 6;
	private static final int CIRCLE_DIAMETER = 80;
	private static final String discColor1 = "#24303E";
	private static final String discColo2 = "#4CAA88";

	private static String PLAYER_ONE = "Player_One";
	private static String PLAYER_TWO = "Player_Two";

	private boolean isPlayerOneTurn = true;

	private Disc[][] insertedDiscArray = new Disc[ROW][COLUMNS];

	@FXML
	private GridPane rootGridPane;

	@FXML
	private Pane insertedDiscsPane;

	@FXML
	private Label playerNameLabel;

	@FXML
	private TextField playerOneTextField, playerTwoTextField;

	@FXML
	public Button setNamesButton;

	private boolean isAllowedToInsert = true;

	public void createPlayGround() {

		setNamesButton.setOnAction(event ->
		{
			PLAYER_ONE = playerOneTextField.getText();
			PLAYER_TWO = playerTwoTextField.getText();
		});

		Shape rectangleWithHoles = createGameStructuralGrid();
		rootGridPane.add(rectangleWithHoles, 0, 1);
		// Adding rectangleWithHoles to rootGridPane at (0,1)
		List<Rectangle> rectangleList = createClickableColumns();

		for (Rectangle rectangle: rectangleList)
		{
			rootGridPane.add(rectangle, 0, 1);
			// Adding rectangles to rectangleLists
		}
	}

	private Shape createGameStructuralGrid() {
		Shape rectangleWithHoles = new Rectangle((COLUMNS+1)*CIRCLE_DIAMETER,(ROW+1)*CIRCLE_DIAMETER);

		for(int row=0; row<ROW; row++) {
			for(int col=0; col<COLUMNS; col++) {
				Circle circle = new Circle();
				circle.setRadius(CIRCLE_DIAMETER/2);
				circle.setCenterX(CIRCLE_DIAMETER/2);
				circle.setCenterY(CIRCLE_DIAMETER/2);
				circle.setSmooth(true);

				circle.setTranslateX(col * (CIRCLE_DIAMETER + 5) + CIRCLE_DIAMETER/4);
				circle.setTranslateY(row * (CIRCLE_DIAMETER + 5) + CIRCLE_DIAMETER/4);
				rectangleWithHoles = Shape.subtract(rectangleWithHoles,circle);
			}
		}

		rectangleWithHoles.setFill(Color.WHITE);
		return rectangleWithHoles;
	}

	private List<Rectangle> createClickableColumns() {

		List<Rectangle> rectangleList = new ArrayList<>();

		for(int col=0; col<COLUMNS; col++){
			Rectangle rectangle = new Rectangle(CIRCLE_DIAMETER,(ROW + 1) * CIRCLE_DIAMETER);
			rectangle.setFill(Color.TRANSPARENT);
			rectangle.setTranslateX(col * (CIRCLE_DIAMETER + 5) + CIRCLE_DIAMETER/4);

			rectangle.setOnMouseEntered(mouseEvent -> rectangle.setFill(Color.valueOf("#eeeeee26")));
			rectangle.setOnMouseExited(mouseEvent -> rectangle.setFill(Color.TRANSPARENT));

			final int column = col;
			rectangle.setOnMouseClicked(mouseEvent -> {
				if(isAllowedToInsert) {
					isAllowedToInsert = false;

					insertDisk(new Disc(isPlayerOneTurn), column);
				}
			});
			rectangleList.add(rectangle);
		}
		return rectangleList;
	}

	private void insertDisk(Disc disc, int column) {

		int row = ROW-1;
		while (row >= 0)
		{
			if (getDiscIfPresent(row, column) == null) {
				break;
			}
			row--;
		}

		if (row < 0)
			return;

		insertedDiscArray[row][column] = disc;
		insertedDiscsPane.getChildren().add(disc);

		disc.setTranslateX(column * (CIRCLE_DIAMETER + 5) + CIRCLE_DIAMETER / 4.0);

		int currentRow = row;
		TranslateTransition translateTransition
				= new TranslateTransition(Duration.seconds(0.5), disc);
		translateTransition.setToY(row * (CIRCLE_DIAMETER + 5) + 60); // ****
		translateTransition.setOnFinished(event ->
		{
			isAllowedToInsert = true;
			if (gameEnded(currentRow, column))
			{
				gameOver();
				return;
			}

			isPlayerOneTurn = !isPlayerOneTurn;

			playerNameLabel.setText(isPlayerOneTurn? PLAYER_ONE : PLAYER_TWO);
		});
		translateTransition.play();
	}

	private boolean gameEnded(int currentRow, int column) {
		List<Point2D> verticalPoints = IntStream.rangeClosed(currentRow-1, currentRow+3)
				.mapToObj(r -> new Point2D(r, column)).collect(Collectors.toList());

		List<Point2D> horizontalPoints = IntStream.rangeClosed(column-3, column+3)
				.mapToObj(c -> new Point2D(currentRow, c)).collect(Collectors.toList());

		Point2D startPoint1 = new Point2D(currentRow-3, column+3);

		List<Point2D> diagonal1Points = IntStream.rangeClosed(0, 6)
				.mapToObj(i -> startPoint1.add(i, -i)).collect(Collectors.toList());

		Point2D startPoint2 = new Point2D(currentRow-3, column+3);

		List<Point2D> diagonal2Points = IntStream.rangeClosed(0, 6)
				.mapToObj(i -> startPoint2.add(i, i)).collect(Collectors.toList());

		boolean isEnded = checkCombinations(verticalPoints) || checkCombinations(horizontalPoints) || checkCombinations(diagonal1Points) || checkCombinations(diagonal2Points);
		return isEnded;
	}

	private boolean checkCombinations(List<Point2D> points) {
		int chain = 0;
		for(Point2D point : points) {

			int rowIndexForArray = (int) point.getX();
			int columnIndexForArray = (int) point.getY();

			Disc disc = getDiscIfPresent(rowIndexForArray, columnIndexForArray);
			if(disc != null && disc.isPlayerOneMove == isPlayerOneTurn) {

				chain++;
				if(chain == 4) {
					return true;
				}
			} else {

				chain = 0;
			}
		}
		return false;
	}

	private Disc getDiscIfPresent(int row, int column) {
		if(row >= ROW || row < 0 || column >= COLUMNS || column < 0) {
			return null;
		}
		return insertedDiscArray[row][column];
	}

	private void gameOver() {
		String winner = isPlayerOneTurn ? PLAYER_ONE : PLAYER_TWO;
		System.out.println("Winner is: "+winner);
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Connect4");
		alert.setHeaderText("The Winner is : "+winner+ " !!");
		alert.setContentText("Want to play again ?");

		ButtonType yesBtn = new ButtonType("Yes");
		ButtonType noBtn = new ButtonType("No, Exit");
		alert.getButtonTypes().setAll(yesBtn,noBtn);
		Platform.runLater( () -> {
			Optional<ButtonType> btnClicked = alert.showAndWait();
			if(btnClicked.isPresent() && btnClicked.get() == yesBtn) {

				resetGame();

			} else {
				Platform.exit();
				System.exit(0);
			}
		});
	}

	public void resetGame() {
		insertedDiscsPane.getChildren().clear();

		for(int row=0; row<insertedDiscArray.length; row++) {

			for(int col=0; col<insertedDiscArray[row].length; col++) {

				insertedDiscArray[row][col] = null;
			}
		}
		isPlayerOneTurn = true;
		playerNameLabel.setText(PLAYER_ONE);

		createPlayGround();
	}

	private static class Disc extends Circle {

		private final boolean isPlayerOneMove;

		public Disc(boolean isPlayerOneMove) {

			this.isPlayerOneMove = isPlayerOneMove;
			setRadius(CIRCLE_DIAMETER/2);
			setCenterX(CIRCLE_DIAMETER/2);
			setCenterX(CIRCLE_DIAMETER/2);
			setFill(isPlayerOneMove? Color.valueOf(discColor1) : Color.valueOf(discColo2));
		}
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {

	}
}

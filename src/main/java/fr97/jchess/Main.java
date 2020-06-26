package fr97.jchess; /**
 * Created by Filip on 11/28/2017.
 */

import fr97.jchess.core.Game;
import fr97.jchess.gui.ChessboardComponent;
import fr97.jchess.gui.GameController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        BorderPane root = new BorderPane();
        HBox root1 = new HBox();
        Game game = new Game(true);
        ChessboardComponent cbc = new ChessboardComponent();
        GameController cbcontroller = new GameController(game, cbc);

        Button btnUndo = new Button("Undo");

        btnUndo.setOnAction( event -> {
            game.undoLastMove();
            cbc.updateTiles(game.boardAsArray());
            if(!game.getCurrentPlayer().isInCheck()){
                cbc.removeChecked();
            }
        });

        root.setTop(new Label("JavaFXChess"));
        root.setCenter(cbc.getComponentNode());

        root1.getChildren().add(new Label("JavaFXChess"));
        root1.getChildren().add(cbc.getComponentNode());
        root1.getChildren().add(btnUndo);

        Scene scene = new Scene(root1, 1000, 800);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("css/style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.setResizable(false);
        primaryStage.show();



    }
}

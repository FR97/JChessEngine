package fr97.jchess;

import fr97.jchess.core.Game;
import fr97.jchess.gui.*;
import fr97.jchess.gui.utils.AlertHelper;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Created by Filip on 11/28/2017.
 */
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        HBox root = new HBox();
        Game game = new Game(true);
        ChessboardComponent chessboard = new ChessboardComponent();
        GameController gameController = new GameController(game, chessboard);

        Button btnUndo = new Button("Undo");

        btnUndo.setOnAction(gameController::undoEventHandler);

        root.getChildren().add(new Label("JavaFXChess"));
        root.getChildren().add(chessboard.getComponentNode());
        root.getChildren().add(btnUndo);

        Scene scene = new Scene(root, 1000, 800);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("css/style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.setResizable(false);
        primaryStage.show();

        primaryStage.setOnCloseRequest(event -> {
            if(!AlertHelper.showConfirmationDialog("Are you sure you want to exit?")){
                event.consume();
            }
        });
    }
}

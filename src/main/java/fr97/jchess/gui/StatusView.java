package fr97.jchess.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.List;

public class StatusView implements FXView<VBox> {

    private static final String LBL_JCHESS_ID = "lbl_jchess";
    private static final String LBL_STATUS_ID = "lbl_status";
    private static final String LBL_MOVES_COUNTER_ID = "lbl_moves_counter";

    private final VBox root;
    private final Label lblGameStatus;
    private final Label lblMovesPlayed;

    public StatusView(){

        root = new VBox();

        root.setAlignment(Pos.CENTER_LEFT);
        root.setSpacing(20);
        root.setPadding(new Insets(10,20,10,0));

        Label lblJChess = new Label("JChess");
        lblJChess.setFont(new Font("Arial Bold", 32));
        lblJChess.setId(LBL_JCHESS_ID);

        StackPane spTitle = new StackPane();

        spTitle.getChildren().add(lblJChess);
        spTitle.setAlignment(Pos.CENTER);

        lblGameStatus = new Label();
        lblGameStatus.setFont(new Font("Arial Bold", 24));
        lblGameStatus.setText("Status: WHITE ON MOVE");
        lblGameStatus.setId(LBL_STATUS_ID);

        lblMovesPlayed = new Label("Total moves: 0");
        lblMovesPlayed.setFont(new Font("Arial Bold", 20));
        lblMovesPlayed.setId(LBL_MOVES_COUNTER_ID);
        List<Node> children = root.getChildren();

        children.add(spTitle);
        children.add(lblGameStatus);
        children.add(lblMovesPlayed);
    }

    public void updateStatus(String status){
        lblGameStatus.setText("Status: " + status.replaceAll("_", " "));
    }

    public void updateMovesPlayed(int count){
        lblMovesPlayed.setText("Total moves: " + count);
    }

    @Override
    public VBox getRoot() {
        return root;
    }
}
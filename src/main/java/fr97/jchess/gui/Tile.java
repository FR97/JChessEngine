package fr97.jchess.gui;

import fr97.jchess.core.piece.PieceColor;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Created by Filip on 11/28/2017.
 */
public class Tile extends Button {

    public final int position;
    private String piece;
    private boolean selected;
    private boolean occupied;
    private boolean checked;
    private boolean possible;
    public Tile(int position){
        this(position, "");
    }

    public Tile(int position, String piece){
        super();
        this.position = position;
        this.selected = false;
        this.checked = false;
        this.possible = false;
        setPrefHeight(80);
        setPrefWidth(80);
        this.getStyleClass().add("tile-style");
        setPiece(piece);
    }

    public void setPiece(String piece){
        this.piece = piece;
        setOccupied();
        setImage();
    }

    public String getPiece(){
        return this.piece;

    }

    public void markAsPossibleMove(boolean possible){
        this.possible = possible;
        if(possible)
            this.getStyleClass().add("tile-possible");
        else
            this.getStyleClass().remove("tile-possible");
    }

    public boolean isPossible(){
        return this.possible;
    }

    public void removePossibleMark(){
        this.getStyleClass().remove("tile-possible");
    }

    private void setOccupied(){
        occupied = piece.length() > 0;
    }

    public PieceColor getPieceColor(){
        if(!occupied){
            return null;
        }else {
            if(piece.startsWith("W"))
                return PieceColor.WHITE;
            else
                return PieceColor.BLACK;
        }
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
        if(checked){
            this.getStyleClass().add("tile-checked");
        }else{
            this.getStyleClass().remove("tile-checked");
        }
    }

    public void setColor(String color)
    {
        this.getStyleClass().add("tile-"+color);
    }

    private void setImage(){
        if(occupied){
            String imgPath ="/img/"+piece+".png";
            this.setGraphic(new ImageView(new Image(getClass().getResourceAsStream(imgPath.toLowerCase()))));
        }else{
            this.setGraphic(null);
        }
    }

    public boolean isOccupied() {
        return occupied;
    }

    public boolean isSelected(){
        return this.selected;
    }

    public void select() {
        this.getStyleClass().add("tile-selected");
        this.selected = true;
    }

    public void deselect(){
        this.getStyleClass().remove("tile-selected");
        this.selected = false;
    }



    public void toggleLastMove(){
        if(this.getStyleClass().contains("tile-last-move")){
            this.getStyleClass().remove("tile-last-move");
        }else{
            this.getStyleClass().add("tile-last-move");
        }
    }

    public void addLastMoveMark(){
        this.getStyleClass().add("tile-last-move");
    }

    public void removeLastMoveMark(){
        if(this.getStyleClass().contains("tile-last-move"))
            this.getStyleClass().remove("tile-last-move");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tile tile = (Tile) o;

        return position ==tile.position;
    }

    @Override
    public String toString() {
        return new StringBuilder().append("Tile{")
            .append("POSITION=").append(position)
            .append(", piece='").append(piece).append('\'')
            .append(", selected=").append(selected)
            .append(", occupied=").append(occupied)
            .append(", checked=").append(checked)
            .append('}').toString();
    }
}



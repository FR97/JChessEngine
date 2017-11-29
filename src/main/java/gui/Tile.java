package gui;

import core_v2.Pieces.PieceColor;
import core_v2.Utils.Position;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Created by Filip on 11/28/2017.
 */
public class Tile extends Button {

    public final Position POSITION;
    private String piece;
    private boolean selected;
    private boolean occupied;
    private boolean checked;
    public Tile(Position position){
        this(position, "");
    }

    public Tile(Position position, String piece){
        super();
        this.POSITION = position;
        this.selected = false;
        this.checked = false;
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
        if(possible)
            this.getStyleClass().add("tile-possible");
        else
            this.getStyleClass().remove("tile-possible");
    }

    public void removePossibleMark(){
        this.getStyleClass().remove("tile-possible");
    }

    private void setOccupied(){
        if(piece.length() > 0)
            occupied = true;
        else
            occupied = false;

    }

    public PieceColor getPieceColor(){
        if(!occupied){
            return null;
        }else {
            if(piece.substring(0,1).equals("W"))
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
            this.setGraphic(new ImageView(new Image(getClass().getResourceAsStream(imgPath))));
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tile tile = (Tile) o;

        return POSITION.equals(tile.POSITION);
    }

    @Override
    public int hashCode() {
        return POSITION.hashCode();
    }

    @Override
    public String toString() {
        return "Tile{" +
                "POSITION=" + POSITION +
                ", piece='" + piece + '\'' +
                ", selected=" + selected +
                ", occupied=" + occupied +
                ", checked=" + checked +
                '}';
    }
}


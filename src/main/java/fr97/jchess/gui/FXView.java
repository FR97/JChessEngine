package fr97.jchess.gui;

import javafx.scene.Parent;

public interface FXView<T extends Parent> {

    T getRoot();

}


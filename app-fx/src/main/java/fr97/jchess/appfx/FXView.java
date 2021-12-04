package fr97.jchess.appfx;

import javafx.scene.Parent;

public interface FXView<T extends Parent> {

    T getRoot();

}


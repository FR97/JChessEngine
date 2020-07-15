package fr97.jchess.gui;

import fr97.jchess.core.GameConfig;
import fr97.jchess.core.ai.AiStrategy;
import fr97.jchess.core.ai.AlphaBeta;
import fr97.jchess.core.ai.Minimax;
import fr97.jchess.core.evaluator.AdvancedEvaluator;
import fr97.jchess.core.evaluator.BasicEvaluator;
import fr97.jchess.core.evaluator.Evaluator;
import javafx.collections.FXCollections;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class ConfigView implements FXView<VBox> {

    private final VBox root;
    private GameConfig config;

    private final ComboBox<Evaluator> cbEvaluators;
    private final ComboBox<AiStrategy> cbAiStrategy;
    private final CheckBox chbIsAi;
    private final Slider sliderDepth;

    public ConfigView(String playerColor) {

        config = GameConfig.createHumanConfig();


        root = new VBox();
        root.setAlignment(Pos.CENTER_LEFT);
        root.setSpacing(20);


        final Label lblPlayer = new Label("Settings for " + playerColor + " player");
        lblPlayer.setFont(new Font("Arial", 24));

        cbEvaluators = new ComboBox<>();
        cbEvaluators.setPrefWidth(240);

        cbEvaluators.setItems(FXCollections.observableArrayList(new BasicEvaluator(), new AdvancedEvaluator()));
        cbEvaluators.setDisable(true);

        cbAiStrategy = new ComboBox<>();
        cbAiStrategy.setPrefWidth(240);

        cbEvaluators.getSelectionModel().selectedItemProperty().addListener(listener -> {
            cbAiStrategy.setItems(FXCollections.observableArrayList(
                new Minimax(cbEvaluators.getSelectionModel().getSelectedItem()),
                new AlphaBeta(cbEvaluators.getSelectionModel().getSelectedItem())));
            cbAiStrategy.getSelectionModel().selectFirst();
        });

        cbAiStrategy.setDisable(true);


        sliderDepth = createSlider();

        sliderDepth.valueProperty().addListener(listener -> {

        });
        sliderDepth.setDisable(true);

        cbAiStrategy.getSelectionModel().selectedItemProperty().addListener(listener -> {

        });

        chbIsAi = new CheckBox("AI enabled?");

        chbIsAi.selectedProperty().addListener(listener -> {
            if (chbIsAi.selectedProperty().get()) {
                cbEvaluators.setDisable(false);
                cbAiStrategy.setDisable(false);
                sliderDepth.setDisable(false);
            } else {
                cbEvaluators.setDisable(true);
                cbAiStrategy.setDisable(true);
                sliderDepth.setDisable(true);
            }
        });

        chbIsAi.selectedProperty().set(false);

        cbEvaluators.getSelectionModel().selectFirst();
        cbAiStrategy.getSelectionModel().selectFirst();

        root.getChildren().addAll(lblPlayer, chbIsAi, cbEvaluators, cbAiStrategy, sliderDepth);

    }

    private Slider createSlider() {
        final Slider slider = new Slider(1, 5, 1);
        slider.setPrefWidth(240);
        slider.setMaxWidth(240);
        slider.setMajorTickUnit(1);
        slider.setMinorTickCount(0);
        slider.setSnapToTicks(true);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setOrientation(Orientation.HORIZONTAL);
        return slider;
    }

    public GameConfig getCurrentConfig() {
        if(chbIsAi.isSelected()){
            return GameConfig.createAiConfig(
                cbAiStrategy.getSelectionModel().getSelectedItem(),
                (int) sliderDepth.getValue());
        } else {
            return GameConfig.createHumanConfig();
        }
    }

    @Override
    public VBox getRoot() {
        return root;
    }
}

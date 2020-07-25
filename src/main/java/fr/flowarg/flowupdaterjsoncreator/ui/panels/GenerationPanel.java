package fr.flowarg.flowupdaterjsoncreator.ui.panels;

import com.jfoenix.controls.JFXButton;
import fr.flowarg.flowupdaterjsoncreator.processors.IProcessor;
import fr.flowarg.flowupdaterjsoncreator.ui.PanelManager;
import javafx.application.Platform;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.concurrent.atomic.AtomicReference;

public class GenerationPanel extends AbstractPanel
{
    private final JsonType type;

    public GenerationPanel(JsonType type)
    {
        super(null);
        this.type = type;
    }

    @Override
    public void init(PanelManager panelManager)
    {
        super.init(panelManager);
        final Label action = new Label(this.type.getLabelText());
        action.setFont(Font.font("Consolas", 48));
        action.setTextFill(Color.WHITE);

        final JFXButton button = new JFXButton("Browse");
        button.setFont(Font.font("Consolas", 38));
        button.setButtonType(JFXButton.ButtonType.RAISED);
        button.setStyle("-fx-background-color: rgb(59,58,58); -fx-text-fill: white;");
        button.setMaxSize(430, 100);
        button.setMinSize(430, 100);
        button.setOnMouseEntered(event -> button.setCursor(Cursor.HAND));
        button.setOnMouseExited(event -> button.setCursor(Cursor.DEFAULT));
        button.setOnMouseClicked(event -> {
            button.setCursor(Cursor.DEFAULT);
            final DirectoryChooser chooser = new DirectoryChooser();
            chooser.setTitle(this.type.getLabelText());
            final File file = chooser.showDialog(this.panelManager.getStage());
            if(file != null)
            {
                this.panelManager.showPanel(Panels.LOADING_PANEL);
                new Thread(() -> {
                    IProcessor processor;
                    switch (this.type)
                    {
                        case MCP:
                            processor = this.panelManager.getJsonCreator().getMcpProcessor();
                            break;
                        case EXT_FILES:
                            processor = this.panelManager.getJsonCreator().getExternalFileProcessor();
                            break;
                        default:
                            processor = this.panelManager.getJsonCreator().getModProcessor();
                            break;
                    }
                    processor.process(file);
                    processor.generate();
                    final AtomicReference<File> jsonFile = new AtomicReference<>(null);
                    Platform.runLater(() -> {
                        final Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Next step");
                        alert.setContentText("Now, you have to select the output JSON file !");
                        alert.showAndWait();
                        final FileChooser jsonFileChooser = new FileChooser();
                        jsonFileChooser.setInitialFileName(this.type.getDefaultFileName());
                        jsonFileChooser.setTitle("Choose output JSON file");
                        jsonFileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JavaScript Object Notation JSON", ".json"));
                        jsonFile.set(jsonFileChooser.showSaveDialog(this.panelManager.getStage()));
                        if(jsonFile.get() != null)
                        {
                            processor.save(jsonFile.get());
                            this.panelManager.showPanel(new EndPanel());
                        }
                        else this.panelManager.showPanel(new GenerationPanel(this.type));
                    });
                }).start();
            }
        });

        this.setCenterH(action);
        this.setCenterH(button);
        this.setCenterV(action);
        this.setCenterV(button);
        this.setCanTakeAllSize(action);
        this.setCanTakeAllSize(button);

        action.setTranslateY(-80);
        button.setTranslateY(60);

        this.layout.getChildren().addAll(action, button);
    }

    @Override
    public String getName()
    {
        return "Generation panel";
    }
}
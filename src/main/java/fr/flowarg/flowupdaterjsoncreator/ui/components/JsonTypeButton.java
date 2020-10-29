package fr.flowarg.flowupdaterjsoncreator.ui.components;

import com.jfoenix.controls.JFXButton;
import fr.flowarg.flowupdaterjsoncreator.FlowUpdaterJsonCreator;
import fr.flowarg.flowupdaterjsoncreator.ui.panels.BrowsePanel;
import fr.flowarg.flowupdaterjsoncreator.ui.panels.JsonType;
import javafx.scene.Cursor;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class JsonTypeButton extends GridPane implements ITakePlace
{
    public JsonTypeButton(JsonType type)
    {
        this.setMinSize(230, 80);
        this.setMaxSize(230, 80);
        final JFXButton button = new JFXButton(type.getButtonName());
        button.setFont(Font.font("Consolas", 24));
        button.setButtonType(JFXButton.ButtonType.RAISED);
        button.setStyle("-fx-background-color: rgb(59,58,58); -fx-text-fill: white;");
        button.setMaxSize(230, 80);
        button.setMinSize(230, 80);
        button.setOnMouseEntered(event -> button.setCursor(Cursor.HAND));
        button.setOnMouseExited(event -> button.setCursor(Cursor.DEFAULT));
        button.setOnMouseClicked(event -> FlowUpdaterJsonCreator.getInstance().getPanelManager().showPanel(new BrowsePanel(type)));
        this.add(button, 0, 0);
    }
}

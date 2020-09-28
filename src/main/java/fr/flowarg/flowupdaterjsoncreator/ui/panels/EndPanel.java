package fr.flowarg.flowupdaterjsoncreator.ui.panels;

import com.jfoenix.controls.JFXButton;
import fr.flowarg.flowupdaterjsoncreator.FlowUpdaterJsonCreator;
import fr.flowarg.flowupdaterjsoncreator.ui.PanelManager;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

class EndPanel extends AbstractPanel
{
    public EndPanel()
    {
        super(null);
    }

    @Override
    public void init(PanelManager panelManager)
    {
        super.init(panelManager);
        final String text = Panels.URL_PANEL.getDefaultUrl().equals("/") ? "Don't forget to add manually\ndownload urls in the json file !" : "Don't forget to check if\n   the json is correct !";
        final Label label = new Label(text);
        final JFXButton button = new JFXButton("Exit");
        button.setButtonType(JFXButton.ButtonType.RAISED);
        button.setStyle("-fx-background-color: rgb(59,58,58); -fx-text-fill: white;");
        button.setMaxSize(400, 90);
        button.setMinSize(400, 90);
        button.setTranslateY(60);
        button.setOnMouseEntered(event -> button.setCursor(Cursor.HAND));
        button.setOnMouseExited(event -> button.setCursor(Cursor.DEFAULT));
        button.setOnMouseClicked(event -> {
            button.setCursor(Cursor.DEFAULT);
            FlowUpdaterJsonCreator.getInstance().shutdown();
        });
        button.setFont(Font.font("Consolas", 38));
        label.setFont(Font.font("Consolas", 32));
        label.setTranslateY(-45);
        label.setTextFill(Color.WHITE);
        this.setCenterH(label);
        this.setCenterV(label);
        this.setCanTakeAllSize(label);
        this.setCenterH(button);
        this.setCenterV(button);
        this.setCanTakeAllSize(button);
        this.layout.getChildren().addAll(label, button);
    }

    @Override
    public String getName()
    {
        return "End panel";
    }
}

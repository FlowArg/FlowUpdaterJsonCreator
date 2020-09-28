package fr.flowarg.flowupdaterjsoncreator.ui.panels;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import fr.flowarg.flowupdaterjsoncreator.ui.PanelManager;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class UrlPanel extends AbstractPanel
{
    private String defaultUrl = "/";

    public UrlPanel()
    {
        super(null);
    }

    @Override
    public void init(PanelManager panelManager)
    {
        super.init(panelManager);

        final Label enterUrl = new Label("Enter the default URL");
        enterUrl.setFont(Font.font("Consolas", 48));
        enterUrl.setTextFill(Color.WHITE);

        final JFXTextArea urlArea = new JFXTextArea();
        urlArea.setMinSize(400, 90);
        urlArea.setMaxSize(400, 90);
        urlArea.setFocusColor(Color.WHITE);
        urlArea.setUnFocusColor(Color.BLUEVIOLET);
        urlArea.setFocusTraversable(false);
        urlArea.setStyle("-fx-text-fill: white;");
        urlArea.setFont(Font.font("Consolas", 24));

        final JFXButton skip = new JFXButton("Skip this step ?");
        skip.setUnderline(true);
        skip.setStyle("-fx-text-fill: white; -fx-background-color: rgba(0, 0, 0, 0);");
        skip.setButtonType(JFXButton.ButtonType.RAISED);
        skip.setOnMouseEntered(event -> skip.setCursor(Cursor.HAND));
        skip.setOnMouseExited(event -> skip.setCursor(Cursor.DEFAULT));
        skip.setOnMouseClicked(event -> {
            skip.setCursor(Cursor.DEFAULT);
            panelManager.showPanel(Panels.CHOOSE_JSON_TYPE_PANEL);
        });
        skip.setFont(Font.font("Consolas", 22));
        skip.setFocusTraversable(false);

        final JFXButton enter = new JFXButton("Done");
        enter.setFont(Font.font("Consolas", 34));
        enter.setButtonType(JFXButton.ButtonType.RAISED);
        enter.setStyle("-fx-text-fill: white; -fx-background-color: rgb(59, 58, 58);");
        enter.setOnMouseEntered(event -> enter.setCursor(Cursor.HAND));
        enter.setOnMouseExited(event -> enter.setCursor(Cursor.DEFAULT));
        enter.setOnMouseClicked(event -> {
            if(urlArea.getText().contains("\n") || urlArea.getText().contains("\t"))
            {
                enterUrl.setText("Invalid URL !");
                enterUrl.setTextFill(Color.RED);
            }
            else
            {
                skip.setCursor(Cursor.DEFAULT);
                this.defaultUrl = urlArea.getText();
                panelManager.showPanel(Panels.CHOOSE_JSON_TYPE_PANEL);
            }
        });
        enter.setFocusTraversable(false);

        this.setCenterH(enterUrl);
        this.setCenterV(enterUrl);
        this.setCenterV(urlArea);
        this.setCenterH(urlArea);
        this.setCenterH(skip);
        this.setCenterV(skip);
        this.setCenterH(enter);
        this.setCenterV(enter);
        this.setCanTakeAllSize(enterUrl);
        this.setCanTakeAllSize(urlArea);
        this.setCanTakeAllSize(skip);
        this.setCanTakeAllSize(enter);

        enterUrl.setTranslateY(-60);
        urlArea.setTranslateY(20);
        skip.setTranslateY(250);
        enter.setTranslateY(120);

        this.getLayout().getChildren().addAll(enterUrl, urlArea, skip, enter);
    }

    @Override
    public String getName()
    {
        return "UrlPanel";
    }

    public String getDefaultUrl()
    {
        return this.defaultUrl.endsWith("/") ? this.defaultUrl : this.defaultUrl + '/';
    }
}

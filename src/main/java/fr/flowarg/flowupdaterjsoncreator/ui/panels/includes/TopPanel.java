package fr.flowarg.flowupdaterjsoncreator.ui.panels.includes;

import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import fr.flowarg.flowupdaterjsoncreator.Main;
import fr.flowarg.flowupdaterjsoncreator.ui.PanelManager;
import fr.flowarg.flowupdaterjsoncreator.ui.panels.AbstractPanel;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class TopPanel extends AbstractPanel
{
    private static double xOffset = 0;
    private static double yOffset = 0;

    public TopPanel()
    {
        super(null);
    }

    @Override
    public void init(PanelManager panelManager)
    {
        super.init(panelManager);

        this.layout.setStyle("-fx-background-color: rgb(31,35,37);");

        final GridPane topBarButton = new GridPane();
        final Label title = new Label();

        this.layout.getChildren().add(topBarButton);
        this.layout.getChildren().add(title);
        title.setFont(Font.font("Consolas", FontWeight.THIN, FontPosture.REGULAR, 19.0f));
        title.setStyle("-fx-text-fill: white;");
        title.setText("FlowUpdater - JsonEditor");

        this.setCenterH(title);
        topBarButton.setMinWidth(100);
        topBarButton.setMaxWidth(100);

        this.setCanTakeAllSize(topBarButton);
        this.setRight(topBarButton);

        final MaterialDesignIconView close = new MaterialDesignIconView(MaterialDesignIcon.WINDOW_CLOSE);
        final MaterialDesignIconView resize = new MaterialDesignIconView(MaterialDesignIcon.WINDOW_MAXIMIZE);
        final MaterialDesignIconView minimize = new MaterialDesignIconView(MaterialDesignIcon.WINDOW_MINIMIZE);
        final Image iconImage = new Image(Main.class.getResourceAsStream("/assets/icon.png"));
        final ImageView icon = new ImageView(iconImage);

        GridPane.setVgrow(close, Priority.ALWAYS);
        GridPane.setVgrow(resize, Priority.ALWAYS);
        GridPane.setVgrow(minimize, Priority.ALWAYS);
        GridPane.setVgrow(icon, Priority.ALWAYS);

        icon.setFitHeight(27);
        icon.setFitWidth(27);

        this.setLeft(icon);
        icon.setTranslateX(7);
        icon.setTranslateY(1);

        this.layout.getChildren().add(icon);

        close.setFill(Color.WHITE);
        close.setOpacity(0.70d);
        close.setSize("30px");
        close.setOnMouseEntered(event -> close.setOpacity(1.0d));
        close.setOnMouseExited(event -> close.setOpacity(0.70d));
        close.setOnMouseClicked(event -> panelManager.getJsonCreator().shutdown());
        close.setTranslateX(65);

        resize.setFill(Color.WHITE);
        resize.setOpacity(0.70d);
        resize.setSize("28px");
        resize.setOnMouseEntered(event -> resize.setOpacity(1.0d));
        resize.setOnMouseExited(event -> resize.setOpacity(0.70d));
        resize.setOnMouseClicked(event -> this.panelManager.getStage().setMaximized(!this.panelManager.getStage().isMaximized()));
        resize.setTranslateX(38);

        minimize.setFill(Color.WHITE);
        minimize.setOpacity(0.70d);
        minimize.setSize("30px");
        minimize.setOnMouseEntered(event -> minimize.setOpacity(1.0d));
        minimize.setOnMouseExited(event -> minimize.setOpacity(0.70d));
        minimize.setOnMouseClicked(event -> this.panelManager.getStage().setIconified(true));
        minimize.setTranslateX(7);

        this.layout.setOnMousePressed(event -> {
            if(!this.panelManager.getStage().isMaximized())
            {
                xOffset = panelManager.getStage().getX() - event.getScreenX();
                yOffset = panelManager.getStage().getY() - event.getScreenY();
            }
        });

        this.layout.setOnMouseClicked(event -> {
            if(!this.panelManager.getStage().isMaximized())
                panelManager.getStage().setOpacity(1d);
        });

        this.layout.setOnMouseReleased(event -> {
            if(!this.panelManager.getStage().isMaximized())
                panelManager.getStage().setOpacity(1d); });

        this.layout.setOnMouseDragged(event -> {
            if(!this.panelManager.getStage().isMaximized())
            {
                panelManager.getStage().setOpacity(0.75d);
                panelManager.getStage().setX(event.getScreenX() + xOffset);
                panelManager.getStage().setY(event.getScreenY() + yOffset);
            }
        });

        topBarButton.getChildren().addAll(close, resize, minimize);
    }

    @Override
    public String getName()
    {
        return "";
    }
}

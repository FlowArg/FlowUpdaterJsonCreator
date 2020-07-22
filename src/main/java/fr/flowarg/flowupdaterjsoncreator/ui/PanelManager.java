package fr.flowarg.flowupdaterjsoncreator.ui;

import fr.flowarg.flowupdaterjsoncreator.FlowUpdaterJsonCreator;
import fr.flowarg.flowupdaterjsoncreator.Main;
import fr.flowarg.flowupdaterjsoncreator.ui.panels.IPanel;
import fr.flowarg.flowupdaterjsoncreator.ui.panels.includes.TopPanel;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PanelManager
{
    private final FlowUpdaterJsonCreator jsonCreator;
    private final Stage stage;
    private final IPanel topPanel;
    private final GridPane centerPanel;

    public PanelManager(FlowUpdaterJsonCreator jsonCreator, Stage stage)
    {
        this.jsonCreator = jsonCreator;
        this.stage = stage;
        this.topPanel = new TopPanel();
        this.centerPanel = new GridPane();
    }

    public void init()
    {
        this.stage.getIcons().add(new Image(Main.class.getResourceAsStream("/assets/icon.png")));
        this.stage.setTitle("FlowUpdater - JsonEditor");
        this.stage.setMinWidth(1280);
        this.stage.setMinHeight(720);
        this.stage.setWidth(1280);
        this.stage.setHeight(720);
        this.stage.initStyle(StageStyle.UNDECORATED);
        this.stage.show();
        this.stage.centerOnScreen();

        final GridPane layout = new GridPane();
        this.setBackground(layout);
        this.stage.setScene(new Scene(layout));

        final RowConstraints topPanelRules = new RowConstraints();
        topPanelRules.setValignment(VPos.TOP);
        topPanelRules.setMaxHeight(27);
        topPanelRules.setMinHeight(27);

        layout.getRowConstraints().addAll(topPanelRules, new RowConstraints());
        layout.add(this.topPanel.getLayout(), 0, 0);

        this.topPanel.init(this);
        layout.add(this.centerPanel, 0, 1);
        GridPane.setHgrow(this.centerPanel, Priority.ALWAYS);
        GridPane.setVgrow(this.centerPanel, Priority.ALWAYS);
    }

    public void showPanel(IPanel panel)
    {
        this.jsonCreator.getLogger().info("Opening : " + panel.getName());
        this.centerPanel.getChildren().clear();
        this.centerPanel.getChildren().add(panel.getLayout());
        panel.init(this);
        panel.onShow();
    }

    public void setBackground(GridPane layout)
    {
        layout.setStyle("-fx-background-color: rgb(28,27,27);" + "-fx-backgound-repeat: skretch;" + "-fx-backgound-position: center;" + "-fx-background-size: cover;");
    }

    public Stage getStage()
    {
        return this.stage;
    }

    public FlowUpdaterJsonCreator getJsonCreator()
    {
        return this.jsonCreator;
    }

    public IPanel getTopPanel()
    {
        return this.topPanel;
    }
}

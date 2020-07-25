package fr.flowarg.flowupdaterjsoncreator.ui;

import fr.flowarg.flowupdaterjsoncreator.FlowUpdaterJsonCreator;
import fr.flowarg.flowupdaterjsoncreator.ui.panels.Panels;
import javafx.application.Application;
import javafx.stage.Stage;

public class FxApplication extends Application
{
    @Override
    public void start(Stage stage)
    {
        final FlowUpdaterJsonCreator jsonCreator = FlowUpdaterJsonCreator.getInstance();
        jsonCreator.setPanelManager(stage);
        jsonCreator.getPanelManager().init();
        jsonCreator.getPanelManager().showPanel(Panels.CHOOSE_JSON_TYPE_PANEL);
    }
}

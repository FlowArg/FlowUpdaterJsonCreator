package fr.flowarg.flowupdaterjsoncreator.ui.panels;

import fr.flowarg.flowupdaterjsoncreator.ui.PanelManager;
import fr.flowarg.flowupdaterjsoncreator.ui.components.ITakePlace;
import javafx.scene.layout.GridPane;

public interface IPanel extends ITakePlace
{
    void init(PanelManager panelManager);
    GridPane getLayout();
    void onShow();
    String getName();
}

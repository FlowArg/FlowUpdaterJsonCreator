package fr.flowarg.flowupdaterjsoncreator.ui.panels;

import com.jfoenix.controls.JFXSpinner;
import fr.flowarg.flowupdaterjsoncreator.ui.PanelManager;

class LoadingPanel extends AbstractPanel
{
    public LoadingPanel()
    {
        super(null);
    }

    @Override
    public void init(PanelManager panelManager)
    {
        super.init(panelManager);
        final JFXSpinner spinner = new JFXSpinner();
        spinner.setRadius(200);
        this.setCenterH(spinner);
        this.setCenterV(spinner);
        this.setCanTakeAllSize(spinner);
        this.layout.getChildren().add(spinner);
    }

    @Override
    public String getName()
    {
        return "Loading panel";
    }
}

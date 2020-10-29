package fr.flowarg.flowupdaterjsoncreator.ui.panels;

import fr.flowarg.flowupdaterjsoncreator.ui.PanelManager;
import fr.flowarg.flowupdaterjsoncreator.ui.components.JsonTypeButton;
import javafx.scene.layout.GridPane;

class ChooseJsonTypePanel extends AbstractPanel
{
    @Override
    public void init(PanelManager panelManager)
    {
        super.init(panelManager);
        final GridPane mods = new JsonTypeButton(JsonType.MODS);
        final GridPane externalFiles = new JsonTypeButton(JsonType.EXT_FILES);
        final GridPane mcp = new JsonTypeButton(JsonType.MCP);

        mods.setTranslateY(20);
        mcp.setTranslateY(-20);

        this.setTop(mods);
        this.setCenterV(externalFiles);
        this.setBottom(mcp);

        this.setCenterH(mods);
        this.setCenterH(externalFiles);
        this.setCenterH(mcp);

        this.setCanTakeAllSize(mods);
        this.setCanTakeAllSize(externalFiles);
        this.setCanTakeAllSize(mcp);

        this.layout.getChildren().addAll(mods, externalFiles, mcp);
    }

    @Override
    public String getName()
    {
        return "Choose JsonType panel";
    }
}

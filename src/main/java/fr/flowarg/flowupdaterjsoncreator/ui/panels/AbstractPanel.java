package fr.flowarg.flowupdaterjsoncreator.ui.panels;

import fr.flowarg.flowlogger.ILogger;
import fr.flowarg.flowupdaterjsoncreator.FlowUpdaterJsonCreator;
import fr.flowarg.flowupdaterjsoncreator.ui.PanelManager;
import fr.flowarg.flowupdaterjsoncreator.ui.components.IMovable;
import javafx.animation.FadeTransition;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.util.Duration;

public abstract class AbstractPanel implements IPanel, IMovable
{
    protected GridPane layout = new GridPane();
    protected PanelManager panelManager;
    protected final ILogger logger;

    public AbstractPanel()
    {
        this.logger = FlowUpdaterJsonCreator.getInstance().getLogger();
    }

    @Override
    public void init(PanelManager panelManager)
    {
        this.panelManager = panelManager;
        GridPane.setHgrow(this.layout, Priority.ALWAYS);
        GridPane.setVgrow(this.layout, Priority.ALWAYS);
    }

    @Override
    public GridPane getLayout()
    {
        return this.layout;
    }

    @Override
    public void onShow()
    {
        final FadeTransition transition = new FadeTransition(Duration.seconds(1), this.layout);
        transition.setFromValue(0);
        transition.setToValue(1);
        transition.setAutoReverse(true);
        transition.play();
    }

    @Override
    public abstract String getName();

    @Override
    public void setLeft(Node node)
    {
        GridPane.setHalignment(node, HPos.LEFT);
    }

    @Override
    public void setRight(Node node)
    {
        GridPane.setHalignment(node, HPos.RIGHT);
    }

    @Override
    public void setTop(Node node)
    {
        GridPane.setValignment(node, VPos.TOP);
    }

    @Override
    public void setBottom(Node node)
    {
        GridPane.setValignment(node, VPos.BOTTOM);
    }

    @Override
    public void setBaseLine(Node node)
    {
        GridPane.setValignment(node, VPos.BASELINE);
    }

    @Override
    public void setCenterH(Node node)
    {
        GridPane.setHalignment(node, HPos.CENTER);
    }

    @Override
    public void setCenterV(Node node)
    {
        GridPane.setValignment(node, VPos.CENTER);
    }
}

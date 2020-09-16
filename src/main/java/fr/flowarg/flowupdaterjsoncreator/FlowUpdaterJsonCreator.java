package fr.flowarg.flowupdaterjsoncreator;

import fr.flowarg.flowlogger.Logger;
import fr.flowarg.flowupdaterjsoncreator.processors.ExternalFileProcessor;
import fr.flowarg.flowupdaterjsoncreator.processors.IProcessor;
import fr.flowarg.flowupdaterjsoncreator.processors.MCPProcessor;
import fr.flowarg.flowupdaterjsoncreator.processors.ModProcessor;
import fr.flowarg.flowupdaterjsoncreator.ui.FxApplication;
import fr.flowarg.flowupdaterjsoncreator.ui.PanelManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class FlowUpdaterJsonCreator
{
    private static FlowUpdaterJsonCreator instance;
    private final Logger logger;
    private final IProcessor modProcessor;
    private final IProcessor mcpProcessor;
    private final IProcessor externalFileProcessor;
    private PanelManager panelManager;

    FlowUpdaterJsonCreator()
    {
        instance = this;
        this.logger = new Logger("[JsonCreator]", null);
        this.modProcessor = new ModProcessor();
        this.mcpProcessor = new MCPProcessor();
        this.externalFileProcessor = new ExternalFileProcessor();
    }

    public void start()
    {
        this.logger.info("Starting json creator...");
        try
        {
            Class.forName("javafx.application.Application");
        } catch (ClassNotFoundException e)
        {
            this.logger.err("You must have JavaFX !");
            this.shutdown();
        }
        Application.launch(FxApplication.class);
    }

    public void shutdown()
    {
        this.logger.info("Shutting down...");
        System.exit(0);
    }

    public Logger getLogger()
    {
        return this.logger;
    }

    public PanelManager getPanelManager()
    {
        return this.panelManager;
    }

    public void setPanelManager(Stage stage)
    {
        this.panelManager = new PanelManager(this, stage);
    }

    public IProcessor getModProcessor()
    {
        return this.modProcessor;
    }

    public IProcessor getMcpProcessor()
    {
        return this.mcpProcessor;
    }

    public IProcessor getExternalFileProcessor()
    {
        return this.externalFileProcessor;
    }

    public static FlowUpdaterJsonCreator getInstance()
    {
        return instance;
    }
}

package fr.flowarg.flowupdaterjsoncreator.processors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import fr.flowarg.flowio.FileUtils;
import fr.flowarg.flowupdaterjsoncreator.FlowUpdaterJsonCreator;
import fr.flowarg.flowupdaterjsoncreator.json.MCP;
import fr.flowarg.flowupdaterjsoncreator.ui.panels.Panels;

import java.io.File;
import java.io.IOException;

public class MCPProcessor implements IProcessor
{
    private MCP mcp;
    private String finalJson;

    @Override
    public void process(File dir, Object... args) throws Exception
    {
        final File client = new File(dir, "client.jar");
        final boolean clientExist = client.exists();
        final File server = new File(dir, "server.jar");
        final boolean serverExist = server.exists();
        this.mcp = new MCP(clientExist ? Panels.URL_PANEL.getDefaultUrl() + client.getName() : "", clientExist ? FileUtils.getSHA1(client) : "", clientExist ? (int)FileUtils.getFileSizeBytes(client) : -1,
                           serverExist ? Panels.URL_PANEL.getDefaultUrl() + server.getName() : "", serverExist ? FileUtils.getSHA1(server) : "", serverExist ? (int)FileUtils.getFileSizeBytes(server) : -1);
    }

    @Override
    public void generate(Object... args)
    {
        final JsonObject object = new JsonObject();
        object.addProperty("clientURL", this.mcp.getClientDownloadURL());
        object.addProperty("clientSha1", this.mcp.getClientSha1());
        object.addProperty("clientSize", this.mcp.getClientSize());
        object.addProperty("serverURL", this.mcp.getServerDownloadURL());
        object.addProperty("serverSha1", this.mcp.getServerSha1());
        object.addProperty("serverSize", this.mcp.getServerSize());
        final Gson gson = new GsonBuilder().setPrettyPrinting().create();
        this.finalJson = gson.toJson(object);
    }

    @Override
    public void save(File file, Object... args)
    {
        try
        {
            FileUtils.saveFile(file, this.finalJson);
        } catch (IOException e)
        {
            FlowUpdaterJsonCreator.getInstance().getLogger().printStackTrace(e);
        }
    }
}

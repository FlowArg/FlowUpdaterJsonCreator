package fr.flowarg.flowupdaterjsoncreator.processors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import fr.flowarg.flowio.FileUtils;
import fr.flowarg.flowupdaterjsoncreator.FlowUpdaterJsonCreator;
import fr.flowarg.flowupdaterjsoncreator.json.Mod;
import fr.flowarg.flowupdaterjsoncreator.ui.panels.Panels;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ModProcessor implements IProcessor
{
    private final List<Mod> mods = new ArrayList<>();
    private String finalJson;

    @Override
    public void process(File dir, Object... args) throws Exception
    {
        this.mods.clear();
        if(dir.listFiles() != null)
            for (File mod : dir.listFiles())
                if(!mod.isDirectory())
                    mods.add(new Mod(mod.getName(), Panels.URL_PANEL.getDefaultUrl() + "mods/" + mod.getName(), FileUtils.getSHA1(mod), FileUtils.getFileSizeBytes(mod)));
    }

    @Override
    public void generate(Object... args)
    {
        final JsonObject object = new JsonObject();
        final JsonArray modArray = new JsonArray(this.mods.size());
        final Gson gson = new GsonBuilder().setPrettyPrinting().create();
        this.mods.forEach(mod -> {
            final JsonObject modObject = new JsonObject();
            modObject.addProperty("name", mod.getName());
            modObject.addProperty("downloadURL", mod.getDownloadURL());
            modObject.addProperty("sha1", mod.getSha1());
            modObject.addProperty("size", mod.getSize());
            modArray.add(modObject);
        });
        object.add("mods", modArray);

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

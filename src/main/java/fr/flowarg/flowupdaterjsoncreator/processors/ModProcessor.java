package fr.flowarg.flowupdaterjsoncreator.processors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import fr.flowarg.flowio.FileUtils;
import fr.flowarg.flowupdaterjsoncreator.json.Mod;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ModProcessor implements IProcessor
{
    private final List<Mod> mods = new ArrayList<>();
    private String finalJson;

    @Override
    public void process(File dir, Object... args)
    {
        this.mods.clear();
        if(dir.listFiles() != null)
            for (File mod : dir.listFiles())
                if(!mod.isDirectory())
                {
                    mods.add(new Mod(mod.getName(), "", FileUtils.getSHA1(mod), (int)FileUtils.getFileSizeBytes(mod)));
                }
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
            final BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(this.finalJson);
            writer.flush();
            writer.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}

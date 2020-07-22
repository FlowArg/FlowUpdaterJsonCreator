package fr.flowarg.flowupdaterjsoncreator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import fr.flowarg.flowio.FileUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Processor
{
    private final List<File> modsFile = new ArrayList<>();
    private final List<Mod> mods = new ArrayList<>();
    private String finalJson;

    public void listFiles(File dir)
    {
        this.modsFile.clear();
        if(dir.listFiles() != null)
            for (File mod : dir.listFiles())
                if(!mod.isDirectory())
                    modsFile.add(mod);
    }

    public void processMods()
    {
        this.mods.clear();
        this.modsFile.forEach(file -> mods.add(new Mod(file.getName(), "", FileUtils.getSHA1(file), (int)FileUtils.getFileSizeBytes(file))));
    }

    public void generateJson()
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

    public void saveJson(File jsonFile)
    {
        try
        {
            final BufferedWriter writer = new BufferedWriter(new FileWriter(jsonFile));
            writer.write(this.finalJson);
            writer.flush();
            writer.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}

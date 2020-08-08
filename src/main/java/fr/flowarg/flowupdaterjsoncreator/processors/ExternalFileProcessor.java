package fr.flowarg.flowupdaterjsoncreator.processors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import fr.flowarg.flowio.FileUtils;
import fr.flowarg.flowupdaterjsoncreator.json.ExternalFile;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExternalFileProcessor implements IProcessor
{
    private final List<ExternalFile> externalFiles = new ArrayList<>();
    private String finalJson;

    @Override
    public void process(File dir, Object... args)
    {
        this.externalFiles.clear();
        if(dir.listFiles() != null)
            for (File mod : dir.listFiles())
                if(!mod.isDirectory())
                    this.externalFiles.add(new ExternalFile(mod.getName(), "", FileUtils.getSHA1(mod), FileUtils.getFileSizeBytes(mod)));
                else
                {
                    for (File sub : this.getSubFiles(mod))
                        this.externalFiles.add(new ExternalFile(sub.getAbsolutePath().replace(dir.getAbsolutePath(), ""), "", FileUtils.getSHA1(sub), FileUtils.getFileSizeBytes(sub)));
                }
    }

    private List<File> getSubFiles(File fi)
    {
        final List<File> files = new ArrayList<>();

        for(File file : fi.listFiles())
        {
            if(file.isDirectory())
                files.addAll(this.getSubFiles(file));
            else files.add(file);
        }

        return files;
    }

    @Override
    public void generate(Object... args)
    {
        final JsonObject object = new JsonObject();
        final JsonArray extFilesArray = new JsonArray(this.externalFiles.size());
        final Gson gson = new GsonBuilder().setPrettyPrinting().create();
        this.externalFiles.forEach(extFile -> {
            final JsonObject extFileObject = new JsonObject();
            extFileObject.addProperty("path", extFile.getPath());
            extFileObject.addProperty("downloadURL", extFile.getDownloadURL());
            extFileObject.addProperty("sha1", extFile.getSha1());
            extFileObject.addProperty("size", extFile.getSize());
            extFilesArray.add(extFileObject);
        });
        object.add("extfiles", extFilesArray);

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

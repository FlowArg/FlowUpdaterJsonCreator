package fr.flowarg.flowupdaterjsoncreator.json;

public class Mod
{
    private final String name;
    private final String downloadURL;
    private final String sha1;
    private final long size;

    public Mod(String name, String downloadURL, String sha1, long size)
    {
        this.name = name;
        this.downloadURL = downloadURL;
        this.sha1 = sha1;
        this.size = size;
    }

    public String getName()
    {
        return this.name;
    }

    public String getDownloadURL()
    {
        return this.downloadURL;
    }

    public String getSha1()
    {
        return this.sha1;
    }

    public long getSize()
    {
        return this.size;
    }
}

package fr.flowarg.flowupdaterjsoncreator.json;

public class ExternalFile
{
    private final String path;
    private final String downloadURL;
    private final String sha1;
    private final long size;

    public ExternalFile(String path, String downloadURL, String sha1, long size)
    {
        this.path = path;
        this.downloadURL = downloadURL;
        this.sha1 = sha1;
        this.size = size;
    }

    public String getPath()
    {
        return this.path;
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

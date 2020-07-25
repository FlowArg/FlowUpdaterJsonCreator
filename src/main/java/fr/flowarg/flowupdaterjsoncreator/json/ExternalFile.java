package fr.flowarg.flowupdaterjsoncreator.json;

public class ExternalFile
{
    private final String path;
    private final String downloadURL;
    private final String sha1;

    public ExternalFile(String path, String downloadURL, String sha1)
    {
        this.path = path;
        this.downloadURL = downloadURL;
        this.sha1 = sha1;
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
}

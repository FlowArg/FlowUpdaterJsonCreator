package fr.flowarg.flowupdaterjsoncreator.json;

public class MCP
{
    private final String clientDownloadURL;
    private final String clientSha1;
    private final int clientSize;

    private final String serverDownloadURL;
    private final String serverSha1;
    private final int serverSize;

    public MCP(String clientDownloadURL, String clientSha1, int clientSize, String serverDownloadURL, String serverSha1, int serverSize)
    {
        this.clientDownloadURL = clientDownloadURL;
        this.clientSha1 = clientSha1;
        this.clientSize = clientSize;
        this.serverDownloadURL = serverDownloadURL;
        this.serverSha1 = serverSha1;
        this.serverSize = serverSize;
    }

    public String getClientDownloadURL()
    {
        return this.clientDownloadURL;
    }

    public String getClientSha1()
    {
        return this.clientSha1;
    }

    public int getClientSize()
    {
        return this.clientSize;
    }

    public String getServerDownloadURL()
    {
        return this.serverDownloadURL;
    }

    public String getServerSha1()
    {
        return this.serverSha1;
    }

    public int getServerSize()
    {
        return this.serverSize;
    }
}

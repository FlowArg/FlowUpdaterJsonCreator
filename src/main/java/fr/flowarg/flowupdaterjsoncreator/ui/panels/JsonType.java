package fr.flowarg.flowupdaterjsoncreator.ui.panels;

public enum JsonType
{
    MODS("Select mods directory", "mods.json", "Mods"),
    EXT_FILES("Select external files directory", "externalfiles.json", "External Files"),
    MCP("Select mcp directory", "mcp.json", "MCP");

    private final String labelText;
    private final String defaultFileName;
    private final String buttonName;

    JsonType(String labelText, String defaultFileName, String buttonName)
    {
        this.labelText = labelText;
        this.defaultFileName = defaultFileName;
        this.buttonName = buttonName;
    }

    public String getLabelText()
    {
        return this.labelText;
    }

    public String getDefaultFileName()
    {
        return this.defaultFileName;
    }

    public String getButtonName()
    {
        return this.buttonName;
    }
}

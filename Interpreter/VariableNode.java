
public class VariableNode extends Node
{
    String name;
    public VariableNode(String name)
    {
        this.name = name;
    }
    public String getName()
    {
        return name;
    }
    private void setName(String n)
    {
        name = n;
    }
    public String toString()
    {
        return "VariableNode(" + name + ")";
    }
}

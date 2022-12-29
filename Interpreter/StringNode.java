
public class StringNode extends Node
{
   private String str;
    public StringNode()
    {
        str = "";
    }
    public StringNode(String str)
    {
        this.str = str;
    }
    private void setString(String myString)
    {
        str = myString;
    }
    public String getString()
    {
        return str;
    }
    @Override
    public String toString()
    {
        return "StringNode(" + str + ")";
    }
}

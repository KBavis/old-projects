import java.util.*;
public class StatementsNode extends Node 
{
    List<StatementNode> nodes;
    public StatementsNode(List<StatementNode> nodes)
    {
        this.nodes = nodes;
    }
    public List<StatementNode> getList()
    {
        return nodes;
    }
    private void setNodes(List<StatementNode>  n)
    {
        nodes = n;
    }
    public String toString()
    {
     String values = "";
        for(int i = 0; i < nodes.size(); i++)
        {
            values += nodes.get(i).toString() + " End of line\n";
        }
        return values;
    }
    
}

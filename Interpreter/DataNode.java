import java.util.*;
public class DataNode extends StatementNode
{
    List<Node> nodes;
    public DataNode(List<Node> nodes)
    {
        this.nodes = nodes;
    }
    public List<Node> getList()
    {
        return nodes;
    }
    private void setNodes(List<Node>  n)
    {
        nodes = n;
    }
    public String toString()
    {
     String values = "";
        for(int i = 0; i < nodes.size(); i++)
        {
            values += nodes.get(i).toString() + " ";
        }
        return "DataNode [ " + values + "]";
    }
}

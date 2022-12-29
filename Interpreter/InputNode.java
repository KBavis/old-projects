import java.util.*;
public class InputNode extends StatementNode
{
    List<Node> nodes;
    public InputNode(List<Node> nodes)
    {
        this.nodes = nodes;
    }
    public List<Node> getList()
    {
        return nodes;
    }
    private void setNodes(List<Node> theNodes)
    {
        nodes = theNodes;
    }
    public String toString()
    {
     String values = "";
     String first = "";
        for(int i = 0; i < nodes.size(); i++)
        {
            if(i == 0)
            {
                first += nodes.get(i).toString() + " ";
            }
           else
           {
            values += nodes.get(i).toString() + " ";
           }
        }
        return "InputNode "+ first + "[ " + values + "]";
    }
   
}

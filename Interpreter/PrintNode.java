import java.util.*;
public class PrintNode extends StatementNode
{
    List <Node> nodes;
    public PrintNode(List<Node> nodes)
    {
        this.nodes = nodes;
    }
    public List<Node> getNodes()
    {
        return nodes;
    }
    private void setNodes(List<Node> theNodes)
    {
        nodes = theNodes;
    }
    @Override
    public String toString()
    {
        String values = "";
        for(int i = 0; i < nodes.size(); i++)
        {
            values += nodes.get(i).toString() + " ";
        }
        return "PrintNode(" + values + ")";
    }
}

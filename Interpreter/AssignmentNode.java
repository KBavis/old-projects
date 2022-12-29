
public class AssignmentNode extends StatementNode
{
    Node name;
    Node value;
    public AssignmentNode(Node name, Node value)
    {
        this.name = name;
        this.value = value;
    }
    public Node getName()
    {
        return name;
    }
    public Node getValue()
    {
        return value;
    }
    private void setName(Node n)
    {
        name = n;
    }
    private void setValue(Node v)
    {
        value = v;
    }
    @Override
    public String toString()
    {
        return "AssignmentNode(" + name + "," + value + ")";
    }
    
}

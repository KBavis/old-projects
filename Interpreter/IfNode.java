
public class IfNode extends StatementNode
{
    Node first;
    BooleanOperationNode op;
    Node second;
    Node label;
    public IfNode(Node first, BooleanOperationNode op, Node second)
    {
        this.first = first;
        this.op = op;
        this.second = second;
    }
    public IfNode(Node first, BooleanOperationNode op, Node second, Node label)
    {
        this.first = first;
        this.op = op;
        this.second = second;
        this.label = label;
    }
    public Node getFirst()
    {
        return first;
    }
     public BooleanOperationNode getOperation()
    {
        return op;
    }
     public Node getSecond()
    {
        return second;
    }
    public Node getLabel()
    {
        return label;
    }
    private void setFirst(Node f)
    {
        first = f;
    }
    private void setBooleanOp(BooleanOperationNode operation)
    {
        op = operation;
    }
    private void setSecond(Node s)
    {
        second = s;
    }
    private void setLabel(Node l)
    {
        label = l;
    }
    public String toString()
    {
       return "IfNode(" + first + ", " + op + ", " + second + " THEN " + label + ")";
    }
}

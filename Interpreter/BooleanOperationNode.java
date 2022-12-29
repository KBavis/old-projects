
public class BooleanOperationNode
{
    String operation;
    public BooleanOperationNode(String operation)
    {
        this.operation = operation;
    }
    public String getOperation()
    {
        return operation;
    }
    private void setOperation(String op)
    {
        operation = op;
    }
    public String toString()
    {
        return "BooleanOperationNode(" + operation + ")";
    }
}

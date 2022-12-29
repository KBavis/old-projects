

public class MathOpNode extends Node
{
    Node left;
    Node right;
    OperationType type;
    public enum OperationType{
        add, subtract, times, divide
    }
    public MathOpNode()
    {
        left = null;
        right = null;
        type = null;;
    }
    public MathOpNode(OperationType type, Node left, Node right)
    {
        this.type = type;
        this.left = left;
        this.right = right;
    }
    public void setOperation(OperationType myType)
    {
        type = myType;
    }
    public void setRight(Node r)
    {
        right = r;
    }
    public void setLeft(Node l)
    {
        left = l;
    }
    public OperationType getOperation()
    {
        return type;
    }
    public Node getLeftNode()
    {
        return left;
    }
    public Node getRightNode()
    {
        return right;
    }
    @Override
    public String toString()
    {
      return "MathOpNode(" + type + "," + left + "," + right +")";
    }
   
}

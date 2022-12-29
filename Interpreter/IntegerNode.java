

public class IntegerNode extends Node
{
    private int number;
    public IntegerNode()
    {
        number = 0;
    }
    public IntegerNode(int n)
    {
        number = n;
    }
    public int getNumber()
    {
        return number;
    }
    private void setNumber(int n)
    {
        number = n;
    }
    @Override
    public String toString()
    {
        return "IntegerNode(" + number + ")" ;
    }
}

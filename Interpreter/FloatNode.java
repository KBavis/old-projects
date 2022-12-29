public class FloatNode extends Node
{
    private float number;
    public FloatNode()
    {
        number = 0;
    }
    public FloatNode(float number)
    {
        this.number = number;
    }
    public float getFloat()
    {
        return number;
    }
    private void setFloat(float n)
    {
        number = n;
    }
    @Override
    public String toString()
    {
        return "FloatNode(" + number + ")";
    }
}

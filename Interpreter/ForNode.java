

public class ForNode extends StatementNode
{
    Node variable;
    Node start;
    Node end;
    Node step;
    StatementNode nextStatement;
    public ForNode(Node variable, Node start, Node end, Node step)
    {
        this.variable = variable;
        this.start = start;
        this.end = end;
        this.step = step;
    }
    public Node getVariable()
    {
        return variable;
    }
    public Node getStart()
    {
        return start;
    }
    public Node getEnd()
    {
        return end;
    }
    public Node getStep()
    {
        return step;
    }
    private void setVariable(Node v)
    {
        variable = v;
    }
    private void setStart(Node s)
    {
        start = s;
    }
    private void setEnd(Node e)
    {
        end = e;
    }
    private void setStep(Node s)
    {
        step = s;
    }
    @Override
    public String toString()
    {
        if(step == null)
        {
            step = new IntegerNode(1);
            return "ForNode(" + variable + ", " + "Start Value: " + start + ", " + "End Value: " + end + ", " + "Step: " + step + ")";
        }
        else 
        {
            return "ForNode(" + variable + ", " + "Start Value: " + start + ", " + "End Value: " + end + ", " + "Step: " + step + ")";
        }
    }
}

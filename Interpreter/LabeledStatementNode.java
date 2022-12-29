
public class LabeledStatementNode extends StatementNode 
{
    String label;
    StatementNode statement;
    public LabeledStatementNode(String label, StatementNode statement)
    {
        this.label = label;
        this.statement = statement;
    }
    public String getLabel()
    {
        return label;
    }
    public StatementNode getStatement()
    {
        return statement;
    }
    private void setLabel(String l)
    {
        label = l;
    }
    private void setStatement(StatementNode s)
    {
        statement = s;
    }
    public String toString()
    {
        return "LabeledStatementNode [" + label + " " + statement + " ]";
    }
}

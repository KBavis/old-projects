

public class NextNode extends StatementNode
{
   Node variable;
   StatementNode nextFor;
   public NextNode(Node variable)
   {
       this.variable = variable;
   }
   public Node getVariable()
   {
       return variable;
   }
   public void setVariable(Node v)
   {
       variable = v;
   }
   public String toString()
   {
       return "NextNode(" + variable + ")";
   }
}

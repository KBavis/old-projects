
public class GosubNode extends StatementNode
{
   Node variable;
   public GosubNode(Node variable)
   {
       this.variable = variable;
   }
   public Node getVariable()
   {
       return variable;
   }
   private void setVariable(Node v)
   {
       variable = v;
   }
   public String toString()
   {
       return "GoSubNode [" + variable + "]";
   }
   
   
}


import java.util.*;
public class FunctionNode extends StatementNode
{
    List<Node> parameterList;
    
    Node string;
    Node firstValue;
    Node secondValue;
    String functionName;
    public FunctionNode(String functionName)
    {
        this.functionName = functionName;
    }
    public FunctionNode(String functionName,Node string, Node firstValue)
    {
        this.functionName = functionName;
        this.string = string;
        this.firstValue = firstValue;
    }
    public FunctionNode(String functionName,Node string, Node firstValue, Node secondValue)
    {
        this.functionName = functionName;
        this.string = string;
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }
    public FunctionNode(String functionName, Node firstValue)
    {
        this.functionName = functionName;
        this.firstValue = firstValue;
    }
    public String getFunctionName()
    {
        return functionName;
    }
    public Node getString()
    {
        return string;
    }
    public Node getFirstValue()
    {
        return firstValue;
    }
    public Node getSecondValue()
    {
        return secondValue;
    }
    private void setFunctionName(String function)
    {
        functionName = function;
    }
    private void setString(Node s)
    {
        string = s;
    }
    private void setFirstValue(Node first)
    {
        firstValue = first;
    }
    private void setSecondValue(Node second)
    {
        secondValue = second;
    }
    public String toString()
    {
        if(firstValue == null && string == null && secondValue == null)
        {
            return "FunctionNode(" + functionName + "())";
        }
        else if(string != null && firstValue != null && secondValue == null)
        {
            return "FunctionNode(" + functionName + "(" + string + ", " + firstValue + "))";
        }
        else if(string != null && firstValue != null && secondValue != null)
        {
            return "FunctionNode(" + functionName + "(" + string + ", " + firstValue + ", " + secondValue + "))";
        }
        else if(firstValue != null && string == null && secondValue == null)
        {
            return "FunctionNode(" + functionName + "(" + firstValue + "))";
        }
        else 
        {
            return "FunctionNode(" + functionName + "(" + string + "))";
        }
    }
    
   
}

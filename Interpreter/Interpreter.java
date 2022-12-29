
import java.util.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.*;
public class Interpreter 
{
    Node nodes;
    HashMap<String, Integer > int_hashmap = new HashMap<String, Integer>();
    HashMap<String, Float> float_hashmap = new HashMap<String, Float>();
    HashMap<String, String> string_hashmap = new HashMap<String, String>();
    HashMap<String, Node> node_hashmap = new HashMap<String, Node>();
    List<Node> dataValues = new ArrayList<>();
    boolean done = false;
    StatementNode currentNode = null;
    Scanner console = new Scanner(System.in);
    int leftInt;
    int rightInt;
    int valueInt;
    float leftFloat;
    float rightFloat;
    float valueFloat;
     MathOpNode LeftTemp = null;
     MathOpNode RightTemp = null;
      MathOpNode tempMathOp = null;
    public Interpreter(Node nodes)
    {
        this.nodes = nodes;
    }
    public void interpret()
    {
        //Initalizes our AST
        initalize();
        StatementsNode AST = (StatementsNode)(nodes);
        List<StatementNode> ourList = AST.getList();
        currentNode = ourList.get(0);
        //Loop to go through statements until we have no more statements
      try
      {
        while(done != true)
        {
            if(currentNode instanceof ReadNode)
            {
                interpretReadNode();
                currentNode = currentNode.next;
                if(currentNode == null)
                {
                   done = true;
                }
            }
            if(currentNode instanceof InputNode)
            {
                interpretInputNode();
                currentNode = currentNode.next;
                if(currentNode == null)
                {
                   done = true;
                }
            }
            if(currentNode instanceof PrintNode)
            {
                interpretPrintNode();
                currentNode = currentNode.next;
                if(currentNode == null)
                {
                   done = true;
                }
            }
            if(currentNode instanceof AssignmentNode)
            {
                interpretAssignmentNode();
                currentNode = currentNode.next;
                if(currentNode == null)
                {
                   done = true;
                }
            }
            
        }
      }
      catch(Exception e)
      {
          e.printStackTrace();
      }
        
        
    }
    //Method to interpret assignmentNode
    public void interpretAssignmentNode() throws Exception
    {
        AssignmentNode assignmentNode = (AssignmentNode)(currentNode);
        Node name = assignmentNode.getName();
        VariableNode varName = (VariableNode)(name);
        String nameVariable = varName.getName();
        Node myNode = assignmentNode.getValue();
        //For a String Variable
        if(nameVariable.contains("$"))
        {
            //If the variable already exists, replace with new value
           if(string_hashmap.containsKey(nameVariable))
           {
               //Variable getting assigned to just a String
             if(myNode instanceof StringNode)
             {
                 string_hashmap.remove(nameVariable);
                 StringNode stringNode = (StringNode)(myNode);
                 String myString = stringNode.getString();
                 string_hashmap.put(nameVariable, myString);
             }
             //Variable gettign assign to a function
             else if(myNode instanceof FunctionNode)
             {
                 FunctionNode functionNode = (FunctionNode)(myNode);
                 if(functionNode.getFunctionName().equals("LEFT$"))
                 {
                     string_hashmap.remove(nameVariable);
                     String left = interpretLeft(myNode);
                     string_hashmap.put(nameVariable,left);
                 }
                 else if(functionNode.getFunctionName().equals("RIGHT$"))
                 {
                     string_hashmap.remove(nameVariable);
                     String right = interpretRight(myNode);
                     string_hashmap.put(nameVariable,right);
                 }
                 else if(functionNode.getFunctionName().equals("MID$"))
                 {
                     string_hashmap.remove(nameVariable);
                     String mid = interpretMiddle(myNode);
                     string_hashmap.put(nameVariable,mid);
                 }
                 else if(functionNode.getFunctionName().equals("NUM$"))
                 {
                     string_hashmap.remove(nameVariable);
                     String num = interpretNum(myNode);
                     string_hashmap.put(nameVariable,num);
                 }
                 else 
                 {
                     throw new Exception("Illegal String Assignment");
                 }
             }
             //Assigning variable to another Variable
             else if(myNode instanceof VariableNode)
                 {
                    VariableNode varNode = (VariableNode)(myNode);
                    String varNodeName = varNode.getName();
                    //Ensures that its a String Variable
                    if(varNodeName.indexOf('$') != -1)
                    {
                        //Ensures variable exists
                       if(string_hashmap.containsKey(varNodeName))
                       {
                         string_hashmap.remove(nameVariable);
                         String value = string_hashmap.get(varNodeName);
                         string_hashmap.put(nameVariable,value);
                       }
                       else
                       {
                           throw new Exception("Illegal String Assignment");
                       }
                    }
                    else
                    {
                        throw new Exception("Illegal String Assignment");
                    }
                 }
             else
             {
                 throw new Exception("Illegal String Assignment");
             }
           }
           //If our variable doesn't already exist, we create it
           else
           {
               //Same implementation as above, just no logner replacing variable
              if(myNode instanceof StringNode)
              {
               StringNode stringNode = (StringNode)(myNode);
               String myString = stringNode.getString();
               string_hashmap.put(nameVariable,myString);
              }
              else if(myNode instanceof FunctionNode)
              {
                 FunctionNode functionNode = (FunctionNode)(myNode);
                 if(functionNode.getFunctionName().equals("LEFT$"))
                 {
                     String left = interpretLeft(myNode);
                     string_hashmap.put(nameVariable,left);
                 }
                 else if(functionNode.getFunctionName().equals("RIGHT$"))
                 {
                     String right = interpretRight(myNode);
                     string_hashmap.put(nameVariable,right);
                 }
                 else if(functionNode.getFunctionName().equals("MID$"))
                 {
                     String mid = interpretMiddle(myNode);
                     string_hashmap.put(nameVariable,mid);
                 }
                 else if(functionNode.getFunctionName().equals("NUM$"))
                 {
                     String num = interpretNum(myNode);
                     string_hashmap.put(nameVariable,num);
                 }
                 else 
                 {
                     throw new Exception("Illegal String Assignment");
                 }
              }
              else if(myNode instanceof VariableNode)
                 {
                    VariableNode varNode = (VariableNode)(myNode);
                    String varNodeName = varNode.getName();
                    if(varNodeName.indexOf('$') != -1)
                    {
                       if(string_hashmap.containsKey(varNodeName))
                       {
                         String value = string_hashmap.get(varNodeName);
                         string_hashmap.put(nameVariable,value);
                       }
                       else
                       {
                           throw new Exception("Illegal String Assignment");
                       }
                    }
                    else
                    {
                        throw new Exception("Illegal String Assignment");
                    }
                 }
              else
              {
               throw new Exception("Illegal String Assignment");
              }
           }
        }
        //If our variable takes in a float
        else if(nameVariable.contains("%"))
        {
            //If our variable exists already, update value
           if(float_hashmap.containsKey(nameVariable))
           {
               //if we are assigning variable to a function
              if(myNode instanceof FunctionNode)
              {
                  FunctionNode functionNode = (FunctionNode)(myNode);
                  if(functionNode.getFunctionName().equals("VAL%"))
                  {
                     float_hashmap.remove(nameVariable);
                     float val = interpretValf(myNode);
                     float_hashmap.put(nameVariable, val);
                  }
                  else
                  {
                     throw new Exception("Illegal Float Assignment");
                  }
              }
              //Evaluating the expression with our EvaluateFloatMathOp function
              else if(myNode instanceof FloatNode || myNode instanceof MathOpNode)
              {
                float myFloatValue = EvaluateFloatMathOp(myNode);
                float_hashmap.put(nameVariable,myFloatValue);
              }
              //Assinging to an already existing variable
              else if(myNode instanceof VariableNode)
              {
                    VariableNode varNode = (VariableNode)(myNode);
                    String varNodeName = varNode.getName();
                    //Ensures types match
                    if(varNodeName.indexOf('%') != -1)
                    {
                       if(float_hashmap.containsKey(varNodeName))
                       {
                         float_hashmap.remove(nameVariable);
                         float value = float_hashmap.get(varNodeName);
                         float_hashmap.put(nameVariable,value);
                       }
                       else
                       {
                           throw new Exception("Illegal Float Assignment");
                       }
                    }
                    else
                    {
                        throw new Exception("Illegal Float Assignment");
                    }
               }
              else
              {
                  throw new Exception("Illegal Float Assignment");
              }
           }
           else
           {
                //Same implementation as above, except we are creating a new variable
                if(myNode instanceof FunctionNode)
                {
                    FunctionNode functionNode = (FunctionNode)(myNode);
                    if(functionNode.getFunctionName().equals("VAL%"))
                    {
                        float val = interpretValf(myNode);
                        float_hashmap.put(nameVariable, val);
                    }
                    else
                    {
                        throw new Exception("Illegal Float Assignment");
                    }
                }
               else if(myNode instanceof FloatNode || myNode instanceof MathOpNode)
               {
                  float myFloatValue = EvaluateFloatMathOp(myNode);
                  float_hashmap.put(nameVariable,myFloatValue);
               }
               else if(myNode instanceof VariableNode)
                 {
                    VariableNode varNode = (VariableNode)(myNode);
                    String varNodeName = varNode.getName();
                    if(varNodeName.indexOf('%') != -1)
                    {
                       if(float_hashmap.containsKey(varNodeName))
                       {
                         float value = float_hashmap.get(varNodeName);
                         float_hashmap.put(nameVariable,value);
                       }
                       else
                       {
                           throw new Exception("Illegal Float Assignment");
                       }
                    }
                    else
                    {
                        throw new Exception("Illegal Float Assignment");
                    }
                 }
               else
               {
                   throw new Exception("Illegal Float Assignment");
               }
            }
        }
        //Assinging a variable to an integer
        else
        {
            //If our variable already exists
            if(int_hashmap.containsKey(nameVariable))
            {
                //Assigning variable to a fucntion
                if(myNode instanceof FunctionNode)
                {
                    FunctionNode functionNode = (FunctionNode)(myNode);
                    if(functionNode.getFunctionName().equals("RANDOM"))
                    {
                        int_hashmap.remove(nameVariable);
                        int random = interpretRandom();
                        int_hashmap.put(nameVariable, random);
                    }
                    else if(functionNode.getFunctionName().equals("VAL"))
                    {
                        int_hashmap.remove(nameVariable);
                        int val = interpretVali(myNode);
                        int_hashmap.put(nameVariable, val);
                    }
                    else
                    {
                        throw new Exception("Illegal Integer Assignment");
                    }
                 }
                 //Evaluating expression being set to our variable
                 else if(myNode instanceof MathOpNode || myNode instanceof IntegerNode)
                 {
                   int_hashmap.remove(nameVariable);
                   int myIntegerValue = EvaluateIntMathOp(myNode);
                   int_hashmap.put(nameVariable,myIntegerValue);
                 }
                 //Setting our variable to an already existing variable
                 else if(myNode instanceof VariableNode)
                 {
                    VariableNode varNode = (VariableNode)(myNode);
                    String varNodeName = varNode.getName();
                    //Ensures types match
                    if(varNodeName.indexOf('$') == -1 && varNodeName.indexOf('%') == -1)
                    {
                       if(int_hashmap.containsKey(varNodeName))
                       {
                         int_hashmap.remove(nameVariable);
                         int value = int_hashmap.get(varNodeName);
                         int_hashmap.put(nameVariable,value);
                       }
                       else
                       {
                           throw new Exception("Illegal Integer Assignment");
                       }
                    }
                    else
                    {
                        throw new Exception("Illegal Integer Assignment");
                    }
                 }
                 else
                 {
                     throw new Exception("Illegal Integer Assignmnet");
                 }
            }
            //Same implementation as above, except we are creating the variable
            else
            {
                if(myNode instanceof FunctionNode)
                {
                    FunctionNode functionNode = (FunctionNode)(myNode);
                    if(functionNode.getFunctionName().equals("RANDOM"))
                    {
                        int random = interpretRandom();
                        int_hashmap.put(nameVariable, random);
                    }
                    else if(functionNode.getFunctionName().equals("VAL"))
                    {
                        int val = interpretVali(myNode);
                        int_hashmap.put(nameVariable, val);
                    }
                    else
                    {
                        throw new Exception("Illegal Integer Assignment");
                    }
                 }
                 else if(myNode instanceof MathOpNode || myNode instanceof IntegerNode)
                 {
                   int myIntegerValue = EvaluateIntMathOp(myNode);
                   int_hashmap.put(nameVariable,myIntegerValue);
                 }
                else if(myNode instanceof VariableNode)
                {
                    VariableNode varNode = (VariableNode)(myNode);
                    String varNodeName = varNode.getName();
                    if(varNodeName.indexOf('$') == -1 && varNodeName.indexOf('%') == -1)
                    {
                       if(int_hashmap.containsKey(varNodeName))
                       {
                        int value = int_hashmap.get(varNodeName);
                        int_hashmap.put(nameVariable,value);
                       }
                       else
                       {
                           throw new Exception("Illegal Integer Assignment");
                       }
                    }
                    else
                    {
                        throw new Exception("Illegal Integer Assignment");
                    }
                }
                else
                {
                     throw new Exception("Illegal Integer Assignment");
                }
            }
        }
    }
    //Evaluate expression being assigned to int variable
    public int EvaluateIntMathOp(Node expression) throws Exception
    {
        //Returns value in integer node
        if(expression instanceof IntegerNode)
        {
            IntegerNode integerNode = (IntegerNode)(expression);
            valueInt = integerNode.getNumber();
            return valueInt;
        }
        //Returns value associated with variable node
        else if(expression instanceof VariableNode)
        {
            VariableNode variableNode = (VariableNode)(expression);
            String variableName = variableNode.getName();
            if(variableName.contains("%") || variableName.contains("$"))
            {
                throw new Exception("Illegal Integer Assignment");
            }
            else
            {
                if(int_hashmap.containsKey(variableName))
                {
                    valueInt = int_hashmap.get(variableName);
                    return valueInt;
                }
                else
                {
                    throw new Exception("Cannot find a value associated with that variable");
                }
            }
        }
        //Deals with a MathOpNode
        else if(expression instanceof MathOpNode)
        {
            MathOpNode mathOp = (MathOpNode)(expression);
            Node leftSide = mathOp.getLeftNode();
            Node rightSide = mathOp.getRightNode();
            MathOpNode.OperationType sign = mathOp.getOperation();
            if(leftSide instanceof MathOpNode)
            {
                LeftTemp = new MathOpNode(sign,leftSide,rightSide);
                EvaluateIntMathOp(leftSide);
            }
            //Evaluates left side
            else if(LeftTemp != null)
            {
                leftInt = EvaluateIntMathOp(leftSide);
                rightInt = EvaluateIntMathOp(rightSide);
                if(sign == MathOpNode.OperationType.add)
                {
                    valueInt = leftInt + rightInt;
                    IntegerNode intNode = new IntegerNode(valueInt);
                    tempMathOp = new MathOpNode(LeftTemp.getOperation(),intNode,LeftTemp.getRightNode());
                    LeftTemp = null;
                    EvaluateIntMathOp(tempMathOp);
                }
                else if(sign == MathOpNode.OperationType.subtract)
                {
                    valueInt = leftInt - rightInt;
                    IntegerNode intNode = new IntegerNode(valueInt);
                    tempMathOp = new MathOpNode(LeftTemp.getOperation(),intNode,LeftTemp.getRightNode());
                    LeftTemp = null;
                    EvaluateIntMathOp(tempMathOp);
                }
                else if(sign == MathOpNode.OperationType.times)
                {
                    valueInt = leftInt * rightInt;
                    IntegerNode intNode = new IntegerNode(valueInt);
                    tempMathOp = new MathOpNode(LeftTemp.getOperation(),intNode,LeftTemp.getRightNode());
                    LeftTemp = null;
                    EvaluateIntMathOp(tempMathOp);
                }
                else
                {
                    valueInt = leftInt / rightInt;
                    IntegerNode intNode = new IntegerNode(valueInt);
                    tempMathOp = new MathOpNode(LeftTemp.getOperation(),intNode,LeftTemp.getRightNode());
                    LeftTemp = null;
                    EvaluateIntMathOp(tempMathOp);
                }
            }
            else if(rightSide instanceof MathOpNode)
            {
                RightTemp = new MathOpNode(sign,leftSide,rightSide);
                EvaluateIntMathOp(rightSide);
            }
            //Evaluates right side
            else if(RightTemp != null)
            {
                leftInt = EvaluateIntMathOp(leftSide);
                rightInt = EvaluateIntMathOp(rightSide);
                if(sign == MathOpNode.OperationType.add)
                {
                    valueInt = leftInt + rightInt;
                    IntegerNode intNode = new IntegerNode(valueInt);
                    tempMathOp = new MathOpNode(RightTemp.getOperation(),RightTemp.getLeftNode(),intNode);
                    RightTemp = null;
                    EvaluateIntMathOp(tempMathOp);
                }
                else if(sign == MathOpNode.OperationType.subtract)
                {
                    valueInt = leftInt - rightInt;
                    IntegerNode intNode = new IntegerNode(valueInt);
                    tempMathOp = new MathOpNode(RightTemp.getOperation(),RightTemp.getLeftNode(),intNode);
                    RightTemp = null;
                    EvaluateIntMathOp(tempMathOp);
                }
                 else if(sign == MathOpNode.OperationType.times)
                {
                    valueInt = leftInt * rightInt;
                    IntegerNode intNode = new IntegerNode(valueInt);
                    tempMathOp = new MathOpNode(RightTemp.getOperation(),RightTemp.getLeftNode(),intNode);
                    RightTemp = null;
                    EvaluateIntMathOp(tempMathOp);
                }
                 else if(sign == MathOpNode.OperationType.divide)
                {
                    valueInt = leftInt / rightInt;
                    IntegerNode intNode = new IntegerNode(valueInt);
                    tempMathOp = new MathOpNode(RightTemp.getOperation(),RightTemp.getLeftNode(),intNode);
                    RightTemp = null;
                    EvaluateIntMathOp(tempMathOp);
                }
            }
            else
            {
                //Gets our final value of our MathOpNode
                leftInt = EvaluateIntMathOp(leftSide);
                rightInt = EvaluateIntMathOp(rightSide);
                if(sign == MathOpNode.OperationType.add)
                {
                    valueInt = leftInt + rightInt;
                }
                else if(sign == MathOpNode.OperationType.subtract)
                {
                    valueInt = leftInt - rightInt;
                }
                else if(sign == MathOpNode.OperationType.times)
                {
                    valueInt = leftInt * rightInt;
                }
                else
                {
                    valueInt = leftInt / rightInt;
                }
            }
        }
        return valueInt;
    }
    
    //Evaluating Float Value
    public float EvaluateFloatMathOp(Node expression) throws Exception
    {
        //Same implementation as above, just for float
        if(expression instanceof FloatNode)
        {
            FloatNode floatNode = (FloatNode)(expression);
            valueFloat = floatNode.getFloat();
            return valueFloat;
        }
        else if(expression instanceof VariableNode)
        {
            VariableNode variableNode = (VariableNode)(expression);
            String variableName = variableNode.getName();
            if(variableName.contains("%"))
            {
                if(float_hashmap.containsKey(variableName))
                {
                    valueFloat = float_hashmap.get(variableName);
                    return valueFloat;
                }
                else 
                {
                    throw new Exception("Cannot find a value associated with that variable");
                }
            }
            else
            {
                throw new Exception("Illegal Float Assignment");
            }
        }
        else if(expression instanceof MathOpNode)
        {
            MathOpNode mathOp = (MathOpNode)(expression);
            Node leftSide = mathOp.getLeftNode();
            Node rightSide = mathOp.getRightNode();
            MathOpNode.OperationType sign = mathOp.getOperation();
            if(leftSide instanceof MathOpNode)
            {
                LeftTemp = new MathOpNode(sign,leftSide,rightSide);
                EvaluateFloatMathOp(leftSide);
            }
            else if(LeftTemp != null)
            {
                leftFloat = EvaluateFloatMathOp(leftSide);
                rightFloat = EvaluateFloatMathOp(rightSide);
                if(sign == MathOpNode.OperationType.add)
                {
                    valueFloat = leftFloat + rightFloat;
                    FloatNode floatNode = new FloatNode(valueFloat);
                    tempMathOp = new MathOpNode(LeftTemp.getOperation(),floatNode,LeftTemp.getRightNode());
                    LeftTemp = null;
                    EvaluateFloatMathOp(tempMathOp);
                }
                else if(sign == MathOpNode.OperationType.subtract)
                {
                    valueFloat = leftFloat - rightFloat;
                    FloatNode floatNode = new FloatNode(valueFloat);
                    tempMathOp = new MathOpNode(LeftTemp.getOperation(),floatNode,LeftTemp.getRightNode());
                    LeftTemp = null;
                    EvaluateFloatMathOp(tempMathOp);
                }
                else if(sign == MathOpNode.OperationType.times)
                {
                    valueFloat = leftFloat * rightFloat;
                    FloatNode floatNode = new FloatNode(valueFloat);
                    tempMathOp = new MathOpNode(LeftTemp.getOperation(),floatNode,LeftTemp.getRightNode());
                    LeftTemp = null;
                    EvaluateFloatMathOp(tempMathOp);
                }
                else
                {
                    valueFloat = leftFloat/rightFloat;
                    FloatNode floatNode = new FloatNode(valueFloat);
                    tempMathOp = new MathOpNode(LeftTemp.getOperation(),floatNode,LeftTemp.getRightNode());
                    LeftTemp = null;
                    EvaluateFloatMathOp(tempMathOp);
                }
            }
            else if(rightSide instanceof MathOpNode)
            {
                RightTemp = new MathOpNode(sign,leftSide,rightSide);
                EvaluateFloatMathOp(rightSide);
            }
            else if(RightTemp != null)
            {
                leftFloat = EvaluateFloatMathOp(leftSide);
                rightFloat = EvaluateFloatMathOp(rightSide);
                if(sign == MathOpNode.OperationType.add)
                {
                    valueFloat = leftFloat + rightFloat;
                    FloatNode floatNode = new FloatNode(valueFloat);
                    tempMathOp = new MathOpNode(RightTemp.getOperation(),RightTemp.getLeftNode(),floatNode);
                    RightTemp = null;
                    EvaluateFloatMathOp(tempMathOp);
                }
                else if(sign == MathOpNode.OperationType.subtract)
                {
                    valueFloat = leftFloat - rightFloat;
                    FloatNode floatNode = new FloatNode(valueFloat);
                    tempMathOp = new MathOpNode(RightTemp.getOperation(),RightTemp.getLeftNode(),floatNode);
                    RightTemp = null;
                    EvaluateFloatMathOp(tempMathOp);
                }
                 else if(sign == MathOpNode.OperationType.times)
                {
                    valueFloat = leftFloat * rightFloat;
                    FloatNode floatNode = new FloatNode(valueFloat);
                    tempMathOp = new MathOpNode(RightTemp.getOperation(),RightTemp.getLeftNode(),floatNode);
                    RightTemp = null;
                    EvaluateFloatMathOp(tempMathOp);
                }
                 else if(sign == MathOpNode.OperationType.divide)
                {
                    valueFloat = leftFloat/rightFloat;
                    FloatNode floatNode = new FloatNode(valueFloat);
                    tempMathOp = new MathOpNode(RightTemp.getOperation(),RightTemp.getLeftNode(),floatNode);
                    RightTemp = null;
                    EvaluateFloatMathOp(tempMathOp);
                }
            }
            else
            {
                leftFloat = EvaluateFloatMathOp(leftSide);
                rightFloat = EvaluateFloatMathOp(rightSide);
                if(sign == MathOpNode.OperationType.add)
                {
                    valueFloat = leftFloat + rightFloat;
                }
                else if(sign == MathOpNode.OperationType.subtract)
                {
                    valueFloat = leftFloat - rightFloat;
                }
                else if(sign == MathOpNode.OperationType.times)
                {
                    valueFloat = leftFloat * rightFloat;
                }
                else
                {
                    valueFloat = leftFloat / rightFloat;
                }
            }
        }
        return valueFloat;
    }
    
    //Interpreting Random Function
    public int interpretRandom()
    {
        int randomNumber = (int)Math.floor(Math.random() * (100)+1);
        return randomNumber; 
    }
    //Interpreting Left$ Function
    public String interpretLeft(Node node)
    {
        FunctionNode functionNode = (FunctionNode)(node);
        Node myString = functionNode.getString();
        StringNode thisString = (StringNode)(myString);
        String stringVariable = thisString.getString();
        Node myInteger = functionNode.getFirstValue();
        IntegerNode thisInteger = (IntegerNode)(myInteger);
        int integerVariable = thisInteger.getNumber();
        String substringString = stringVariable.substring(0,integerVariable);
        return substringString;
    }
    //Interpreting Right$ Function
    public String interpretRight(Node node)
    {
        FunctionNode functionNode = (FunctionNode)(node);
        Node myString = functionNode.getString();
        StringNode thisString = (StringNode)(myString);
        String stringVariable = thisString.getString();
        Node myInteger = functionNode.getFirstValue();
        IntegerNode thisInteger = (IntegerNode)(myInteger);
        int integerVariable = thisInteger.getNumber();
        String substringString = stringVariable.substring(integerVariable);
        return substringString;
    }
    //Interpreting Mid$ Function
    public String interpretMiddle(Node node)
    {
        FunctionNode functionNode = (FunctionNode)(node);
        Node myString = functionNode.getString();
        StringNode thisString = (StringNode)(myString);
        String stringVariable = thisString.getString();
        Node myInteger = functionNode.getFirstValue();
        IntegerNode thisInteger = (IntegerNode)(myInteger);
        int integerVariable = thisInteger.getNumber();
        Node mySecondInteger = functionNode.getSecondValue();
        IntegerNode thisSecondInteger = (IntegerNode)(mySecondInteger);
        int secondIntegerVariable = thisSecondInteger.getNumber();
        String substringString = stringVariable.substring(integerVariable,integerVariable + secondIntegerVariable);
        return substringString;
    }
    //Interpreting Num$ Function
    public String interpretNum(Node node)
    {
        FunctionNode functionNode = (FunctionNode)(node);
        Node myValue = functionNode.getFirstValue();
        if(myValue instanceof IntegerNode)
        {
            IntegerNode integerNode = (IntegerNode)(myValue);
            int intNumber = integerNode.getNumber();
            String number = String.valueOf(intNumber);
            return number;
        }
        else
        {
            FloatNode floatNode = (FloatNode)(myValue);
            float floatNumber = floatNode.getFloat();
            String number = String.valueOf(floatNumber);
            return number;
        }
    }
    //Interpreting Val Function
    public int interpretVali(Node node)
    {
        //System.out.println("Our Node: " + node.toString());
        FunctionNode functionNode = (FunctionNode)(node);
        Node myString = functionNode.getFirstValue();
        StringNode stringNode = (StringNode)(myString);
        String myStringVariable = stringNode.getString();
        int stringValue = Integer.parseInt(myStringVariable);
        return stringValue;
    }
    //Interpreting Val% Function
    public float interpretValf(Node node)
    {
        FunctionNode functionNode = (FunctionNode)(node);
        Node myString = functionNode.getFirstValue();
        StringNode stringNode = (StringNode)(myString);
        String myStringVariable = stringNode.getString();
        float stringValue = Float.parseFloat(myStringVariable);
        return stringValue;
    }
    //Interpreting Print Node
    public void interpretPrintNode() throws Exception
    {
        PrintNode printNode = (PrintNode)(currentNode);
        List<Node> printList = printNode.getNodes();
        for(int i = 0; i < printList.size(); i++)
        {
            //Printing off value assocaited with our variable
            if(printList.get(i) instanceof VariableNode)
            {
                VariableNode var = (VariableNode)(printList.get(i));
                //For Float Variable
                if(var.getName().contains("%"))
                {
                       if(float_hashmap.get(var.getName()) != null)
                       {
                         System.out.print(float_hashmap.get(var.getName()) + " ");
                       }
                       else
                       {
                           throw new Exception("That variable does not exist");
                       }
                }
                //For String Variable
                else if(var.getName().contains("$"))
                {
                        if(string_hashmap.get(var.getName()) != null)
                        {
                          System.out.print(string_hashmap.get(var.getName()) + " ");
                        }
                        else
                        {
                            throw new Exception("That variable does not exist");
                        }
                }
                //For Integer Variable
                else
                {
                      if(int_hashmap.get(var.getName()) != null)
                      {
                        System.out.print(int_hashmap.get(var.getName()) + " ");
                      }
                      else
                      {
                          throw new Exception("That variable does not exist");
                      }
                }
            }
            //Printing off just a string
            else if(printList.get(i) instanceof StringNode)
            {
                StringNode stringNode = (StringNode)(printList.get(i));
                System.out.print(stringNode.getString() + " ");
            }
            //Printing value corresponding with our functions
            else if(printList.get(i) instanceof FunctionNode)
            {
                FunctionNode functionNode = (FunctionNode)(printList.get(i));
                if(functionNode.getFunctionName().equals("RANDOM"))
                {
                      System.out.print(interpretRandom() + " ");
                }
                else if(functionNode.getFunctionName().equals("LEFT$"))
                {
                      System.out.print(interpretLeft(printList.get(i)) + " ");
                }
                else if(functionNode.getFunctionName().equals("RIGHT$"))
                {
                       System.out.print(interpretRight(printList.get(i)) + " ");
                }
                else if(functionNode.getFunctionName().equals("MID$"))
                {
                      System.out.print(interpretMiddle(printList.get(i)) + " ");
                }
                else if(functionNode.getFunctionName().equals("NUM$"))
                {
                       System.out.print(interpretNum(printList.get(i)) + " ");
                }
                else if(functionNode.getFunctionName().equals("VAL"))
                {
                       System.out.print(interpretVali(printList.get(i)) + " ");
                }
                else if(functionNode.getFunctionName().equals("VAL%"))
                {
                      System.out.print(interpretValf(printList.get(i)) + " ");
                }
            }
            
            //System.out.println(printList.get(i));
        }
        System.out.println();
        
    }
    //Interpreting Input Node
    public void interpretInputNode() throws Exception
    {
        InputNode inputNode = (InputNode)(currentNode);
        List<Node> inputList = inputNode.getList();
        //Printing the prompt
        if(inputList.get(0) instanceof StringNode == true)
        {
            StringNode stringNode = (StringNode)(inputList.get(0));
            System.out.println(stringNode.getString());
        }
        else if(inputList.get(0) instanceof VariableNode == true)
        {
            VariableNode varNode = (VariableNode)(inputList.get(0));
            System.out.println(varNode.getName());
        }
        //Taking in input from the user and storing it in its corresponding variable
        for(int i = 1; i < inputList.size(); i++)
        {
            VariableNode variableNode = (VariableNode)(inputList.get(i));
            //Looks for String
            if(variableNode.getName().contains("$"))
            {
                String myString = console.next();
               if(string_hashmap.containsKey(variableNode.getName()))
               {
                 string_hashmap.remove(variableNode.getName());
                 string_hashmap.put(variableNode.getName(), myString);
               }
               else
               {
                string_hashmap.put(variableNode.getName(), myString);
               }
            }
            //Looks for Float
            else if(variableNode.getName().contains("%"))
            {
                Float myFloat = console.nextFloat();
               if(float_hashmap.containsKey(variableNode.getName()))
               {
                 float_hashmap.remove(variableNode.getName());
                 float_hashmap.put(variableNode.getName(), myFloat);
               }
               else
               {
                float_hashmap.put(variableNode.getName(), myFloat);
               }
            }
            //Looks for Int
            else
            {
                Integer myInteger = console.nextInt();
                if(int_hashmap.containsKey(variableNode.getName()))
                {
                    int_hashmap.remove(variableNode.getName());
                    int_hashmap.put(variableNode.getName(), myInteger);
                }
                else
                {
                  int_hashmap.put(variableNode.getName(), myInteger);
                }
            }
        }
    }
    //Interpreting Read Node
    public void interpretReadNode() throws Exception
    {
        ReadNode readNode = (ReadNode)(currentNode);
        List<Node> readList = readNode.getList();
        Node currentData = null;
        for(int i = 0; i < readList.size(); i++)
        {
            Node current = readList.get(i);
            VariableNode currentVariable = (VariableNode)(current);
            //Ensures there is still data to be read
            if(dataValues.size() > 0)
            {
              currentData = dataValues.get(0);
            }
            else
            {
                throw new Exception("Your trying to access data that isn't there.");
            }
            //Exceptions for type-mismatch
           if(currentData instanceof StringNode && currentVariable.getName().indexOf('$') == -1)
           {
               throw new Exception("Illegal String Assignment");
           }
           if(currentData instanceof FloatNode && currentVariable.getName().indexOf('%') == -1)
           {
               throw new Exception("Illegal Float Assignment");
           }
           if(currentData instanceof IntegerNode && currentVariable.getName().indexOf('$') != -1)
           {
               throw new Exception("Illegal Integer Assignment");
           }
           if(currentData instanceof IntegerNode && currentVariable.getName().indexOf('%') != -1)
           {
               throw new Exception("Illegal Integer Assignment");
           }
            //Linking our data with our variables
            if(currentData instanceof StringNode)
            {
                StringNode stringNode = (StringNode)(currentData);
                if(string_hashmap.containsKey(currentVariable.getName()))
                {
                  string_hashmap.remove(currentVariable.getName());
                  string_hashmap.put(currentVariable.getName(), stringNode.getString());
                  dataValues.remove(0);
                }
                else
                {
                    string_hashmap.put(currentVariable.getName(),stringNode.getString());
                    dataValues.remove(0);
                }
           }
           if(currentData instanceof IntegerNode)
           {
               IntegerNode integerNode = (IntegerNode)(currentData);
               if(int_hashmap.containsKey(currentVariable.getName()))
               {
                   int_hashmap.remove(currentVariable.getName());
                   int_hashmap.put(currentVariable.getName(), integerNode.getNumber());
                   dataValues.remove(0);
               }
               else
               {
                   int_hashmap.put(currentVariable.getName(), integerNode.getNumber());
                   dataValues.remove(0);
               }
           }
           if(currentData instanceof FloatNode)
           {
               FloatNode floatNode = (FloatNode)(currentData);
               if(float_hashmap.containsKey(currentVariable.getName()))
               {
                   float_hashmap.remove(currentVariable.getName());
                   float_hashmap.put(currentVariable.getName(), floatNode.getFloat());
                   dataValues.remove(0);
               }
               else
               {
                   float_hashmap.put(currentVariable.getName(), floatNode.getFloat());
                   dataValues.remove(0);
               }
           }
        }
    }
    //Method to run initalize our walks
    public void initalize()
    {
        labeledStatementWalk();
        nextAndForWalk();
        dataWalk();
        statementsNextWalk();
    }
    //Method to remove LabeledStatementNode and Add to Hashmap
    public void labeledStatementWalk()
    {
        StatementsNode AST = (StatementsNode)(nodes);
        List<StatementNode> ourList = AST.getList();
        StatementNode temp = null;
       for(int i = 0; i < ourList.size(); i++)
       {
          temp = ourList.get(i);
          if(temp instanceof LabeledStatementNode == true)
          {
               LabeledStatementNode label = (LabeledStatementNode)(temp);  
               String myLabel = label.getLabel();
               StatementNode myStatement = label.getStatement();
               node_hashmap.put(myLabel, myStatement);
               ourList.set(i, myStatement);
          }    
       }
    }
    //Method to link NextNode with a ForNode and link ForNode with statement following NextNode
    public void nextAndForWalk()
    {
        StatementsNode AST = (StatementsNode)(nodes);
        List<StatementNode> ourList = AST.getList();
        StatementNode temp = null;
        ForNode forNodeOne = null;
        NextNode nextNodeOne = null;
        ForNode forNodeTwo = null;
        NextNode nextNodeTwo = null; 
        for(int i = 0; i < ourList.size(); i++)
        {
            temp = ourList.get(i);
           if(temp instanceof ForNode == true)
           {
              if(ourList.get(i+1) instanceof ForNode == true)
              {
                 forNodeOne = (ForNode)(temp);
              }
              else
              {
                forNodeTwo = (ForNode)(temp);
              }
            }
           if(temp instanceof NextNode == true)
           {
              if(forNodeOne != null && nextNodeOne == null)
              {
                 nextNodeOne = (NextNode)(temp);
                 nextNodeOne.nextFor = forNodeOne;
                 forNodeOne.nextStatement = ourList.get(i + 1);
              } 
              else 
              {
                  nextNodeTwo = (NextNode)(temp);
                  nextNodeTwo.nextFor = forNodeTwo;
                  forNodeTwo.nextStatement = ourList.get(i + 1);
              }
             }
           }   
    }
    //Method to remove DataNode and add data to List
    public void dataWalk()
     {
         StatementsNode AST = (StatementsNode)(nodes);
        List<StatementNode> ourList = AST.getList();
        StatementNode temp = null;
        DataNode dataNode = null;
        for(int i = 0; i < ourList.size(); i++)
        {
            temp = ourList.get(i);
            if(temp instanceof DataNode)
            {
                dataNode = (DataNode)(temp);
                dataValues.addAll(dataNode.getList());
                ourList.remove(i);
                
            }
        }
    }
    //Method to set each StatementNode to the next StatementNode in our AST
    public void statementsNextWalk()
    {
        StatementsNode AST = (StatementsNode)(nodes);
        List<StatementNode> ourList = AST.getList();
        StatementNode temp = null;
        for(int i =0; i < ourList.size(); i++)
        {
            temp = ourList.get(i);
            if(i + 1 != ourList.size())
            {
              temp.next = ourList.get(i + 1);
            }
        }
        temp.next = null;
    }
    
}

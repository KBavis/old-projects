import java.util.*;
import java.lang.Throwable;
import java.lang.Exception;
public class Parserr
{
   private List<Token> tokens;
   MathOpNode op;
   MathOpNode.OperationType sign;
   Node soFar;
   Node ATS;
   public Parserr(List<Token> tokens)
   {
       this.tokens = tokens;
   }
   //Parsing Tokens & Printing
   public Node parse()
   {
       while(tokens.size() != 0)
       {
           ATS = statements();
           //if(ATS != null)
           //{
            //System.out.println("Parser: " + ATS.toString());
          //}
      
      }
      return ATS;
   }
   //Checks for a specific token type and returns token if there
   private Token matchAndReturn(Token.Type type)
   {
       //System.out.println("Entering matchAndReturn with: " + tokens.get(0));
       if(tokens.isEmpty() != true)
       {
       if(tokens.get(0).getType() != type)
       {
           return null;
       }
       else
       {
           Token returnValue = tokens.get(0);
           tokens.remove(0);
           return returnValue;
       }
    }
    return null;
   }
   public Node expression()
   {
       //Gets 1st term
     try
     {
       Node soFar = term();
       if(soFar == null)
       {
           FunctionNode function = FunctionStatement();
           if(function != null)
           {
               if(getRightOfExpression(function) == function)
               {
                return function;
               }
           }
           return null;
       }
      
       //Call to execute right side of expression
       try
       {
       return getRightOfExpression(soFar);
       }
       catch(Exception e)
       {
           e.printStackTrace();
       }
    }
    catch(Exception e)
    {
        e.printStackTrace();
    }
       return null;
   }
   public Node getRightOfExpression(Node left) throws Exception
   {
       //Gets either plus or minus
       Token operation = getExpressionOperation();
       if(operation == null)
       {
           return left;
       }
       //Gets 2nd term
       Node secondTerm = term();
       if(secondTerm == null)
       {
           throw new Exception("Error");
       }
       if(operation.getType() == Token.Type.PLUS)
       {
           sign = MathOpNode.OperationType.add;
       }
       else if(operation.getType() == Token.Type.MINUS)
       {
       sign = MathOpNode.OperationType.subtract;
      }
      //Stores new node in op & calls this method again with this node
       op = new MathOpNode(sign,left,secondTerm);
       return getRightOfExpression(op);
   }
   //Gets Plus or Minus
   public Token getExpressionOperation()
   {
       Token token = matchAndReturn(Token.Type.PLUS);
       if(token == null)
       {
           token = matchAndReturn(Token.Type.MINUS);
       }
       return token;
   }
   //Gets Times or Divide
   public Token getTermOperation()
   {
        Token token = matchAndReturn(Token.Type.TIMES);
       if(token == null)
       {
           token = matchAndReturn(Token.Type.DIVIDE);
       }
       return token;
   }
   public Node term()
   {
       //Gets left factor
       try
       {
        soFar = factor();
       }
       catch(Exception e)
       {
         e.printStackTrace();
       }
       if(soFar == null)
       {
           return null;
       }
       //else if(soFar instanceof VariableNode == true)
       //{
           //return soFar;
       //}
       //Calls to get right side of term
       try
       {
       return getRightOfTerm(soFar);
       }
       catch(Exception e)
       {
           e.printStackTrace();
       }
       return null;
   }
   public Node getRightOfTerm(Node left) throws Exception
   {
       //Gets the times or divide
       Token operation = getTermOperation();
       if( operation == null)
       {
           return left;
       }
       //Gets second factor 
       Node secondFactor = factor();
       if(secondFactor == null)
       {
          throw new Exception("Error"); 
       }
       if(operation.getType() == Token.Type.TIMES)
       {
           sign = MathOpNode.OperationType.times;
       }
       else if(operation.getType() == Token.Type.DIVIDE)
       {
       sign = MathOpNode.OperationType.divide;
    }
    //Stores new node in op and calls method again with this node
       op = new MathOpNode(sign,left,secondFactor);
       return getRightOfTerm(op);
   }
   private Node factor() throws Exception
   {
       //System.out.println("Entering factor with: " + tokens.get(0));
       Token num = matchAndReturn(Token.Type.NUMBER);
       if(num != null)
       {
           //Checks if its a float or int and returns 
           if(num.getString().contains("."))
           {
               return new FloatNode(Float.parseFloat(num.getString()));
           }
           else
           {
               return new IntegerNode(Integer.parseInt(num.getString()));
           }
       }
       //Checks for Lparen and then calls expression... Ensures there is a Rparen
       if(matchAndReturn(Token.Type.LPAREN) != null)
       {
           Node expression = expression();
           if(expression == null)
           {
               throw new Exception("Error");
           }
           if(matchAndReturn(Token.Type.RPAREN) != null)
           {
               return expression;
           }
           throw new Exception("Error");
       }
       //Creates variable node if there is an identifier
       Token value = matchAndReturn(Token.Type.IDENTIFIER);
       if(value != null)
       {
           VariableNode variable = new VariableNode(value.getString());
           return variable;
       }
       return null;
   }
   public Node statements()
   {
      try
      {
         // System.out.println("IN STATEMENTS: " + statement());
        return statement();
      }
      catch(Exception e)
      {
          e.printStackTrace();
      }
      return null;
   }
   public StatementsNode statement() throws Exception
   {
    List<StatementNode> allStatements = new ArrayList<>();
    //Try - Catch to handle exceptions
    while(tokens.size() != 0)
    {
    try
    {
        //Calls LabelStatement and checks if there is a label statement
        
      LabeledStatementNode labelNode = LabelStatement();
     if(labelNode != null)
     {
           allStatements.add(labelNode); 
           labelNode = null;
     }
     
    //Calls assignment to see if there is an assignment present
    
    AssignmentNode assignmentNode = assignment();
    if(assignmentNode != null)
    {
       // System.out.println("Our Assign Node in Statement: " + assignmentNode.toString());
        allStatements.add(assignmentNode);
       
    }
    //Calls DataStatement and checks if there is a data statement
    DataNode dataNode = DataStatement();
    if(dataNode != null)
    {
        allStatements.add(dataNode);
        
    }
    //Calls InputStatement and checks if there is an input statement
    InputNode inputNode = InputStatement();
    if(inputNode != null)
    {
        allStatements.add(inputNode);
        
    }
     //Calls ReadStatement and checks if there is a read statement
    ReadNode readNode = ReadStatement();
    if(readNode != null)
    {
        allStatements.add(readNode);
        
    }
    //Calls GoSubStatement and checks if there is a go sub statement
    GosubNode goSub = GoSubStatement();
    if(goSub != null)
    {
        allStatements.add(goSub);
        
    }
    //Calls ReturnStatement and checks if there is a return statement
    
    ReturnNode returnNode = ReturnStatement();
    if(returnNode != null)
    {
        allStatements.add(returnNode);
        returnNode = null;
    }
    
    //Calls ForStatement and checks if there is a for statement
    ForNode forNode = ForStatement();
    if(forNode != null)
    {
        allStatements.add(forNode);
        
    }
    //Calls NextStatement and checks if there is a next statement
    NextNode nextNode = NextStatement();
    if(nextNode != null)
    {
        allStatements.add(nextNode);
        
    }
    //Calls IfStatement and checks if there is an if statement
    IfNode ifNode = IfStatement();
    if(ifNode != null)
    {
        allStatements.add(ifNode);
        
    }
      //Calls PrintStatement and checks if there is a print statement
     PrintNode printNode = PrintStatement();
     if(printNode != null)
     {
       allStatements.add(printNode);
       
    }

   }
   catch(Exception e)
   {
       e.printStackTrace();
   }
 }
   return new StatementsNode(allStatements);
}
   //Function to handle Token Type READ
   public ReadNode ReadStatement() throws Exception
   {
       List<Node> readStatements = new ArrayList<>();
       if(matchAndReturn(Token.Type.READ) != null)
       {
           readStatements = ReadList();
          if(readStatements != null)
           {
           ReadNode read = new ReadNode(readStatements);
           return read;
          }
          if(readStatements == null)
          {
              throw new Exception("Illegal Read Statement.");
          }
       }
       return null;
   }
   //Helper Method for ReadStatement
   public List<Node> ReadList()
   {
       List<Node> list = new ArrayList<>();
       Node variable;
       int iterations = 0;
       try
       {
        do
        {
            variable = factor();
            if(variable != null && variable instanceof VariableNode == true)
            {
                list.add(variable);
                iterations++;
            }
            //Ensures there is an input and we that we get the expected input
            if(variable != null && variable instanceof VariableNode == false)
            {
                return null;
            }
            if(variable == null && iterations == 0)
            {
                return null;
            }
            //No more commas, list is over
            if(matchAndReturn(Token.Type.COMMA) == null)
            {
                break;
            }
        }while(variable != null);
       }
       catch(Exception e)
       {
           e.printStackTrace();
       }
       return list;
   }

   //Method for TokenType Print
   public PrintNode PrintStatement() throws Exception
   {
       List<Node> expressions = new ArrayList<>();
       //System.out.println("In PrintStatement");
       if(matchAndReturn(Token.Type.PRINT) != null)
       {
            expressions = PrintList();
            //System.out.println("We are in Print Statement");
           if(expressions != null)
            {
            PrintNode print = new PrintNode(expressions);
            //System.out.println("Our PrintNode in PrintStatement: " + print.toString());
            //System.out.println("PrintNode: "+ print.toString());
            return print;
           }
           else if(expressions == null)
             {
               throw new Exception("Illegal Print Statement");
             }
        }
        //System.out.println("About to return null");
       return null;
   }
   //Helper Method to PrintStatement
   public List<Node> PrintList()
   {
       List<Node> expr = new ArrayList<>();
       try
       {
       Node expression;
       Node string;
       Node factor;
       int iterations = 0;
       do
       {
           //System.out.println("We are in PrintList");
           expression = expression();
           string = makeStringNode();
           //Checks if its an expression or string
           if(expression == null && string != null)
           {
               tokens.remove(0);
               expr.add(string);
               iterations++;
           }
           if(expression != null && string == null)
           {
            expr.add(expression);
            iterations++;
           }
           //Ensures there is an input and we that we get the expected input
           if(expression == null && string == null)
           {
               return null;
            }
           //If no comma, print list is over
           if(matchAndReturn(Token.Type.COMMA) == null)
           {
               break;
           }
       }while(expression != null || string != null);
      }
      catch(Exception e)
      {
          e.printStackTrace();
      }
       return expr;
    }
    //Function to handle TokenType DATA
    public DataNode DataStatement() throws Exception
    {
        List<Node> data = new ArrayList<>();
        if(matchAndReturn(Token.Type.DATA) != null)
        {
            data = DataList();
           if(data != null)
           {
            DataNode dataNode = new DataNode(data);
            return dataNode;
           }  
           if(data == null)
           {
               throw new Exception("Illegal Data Statement.");
           }
        }
        return null;
    }
    //Helper method for DataStatement that returns a List
    public List<Node> DataList()
    {
        List<Node> dataExpressions = new ArrayList<>();
        Node factor;
        Node string;
        int iterations = 0;
       try
       {
        do
        {
            factor = factor();
            string = makeStringNode();
            if(factor != null && factor instanceof IntegerNode == true || factor instanceof FloatNode == true)
            {
                dataExpressions.add(factor);
                iterations++;
            }
            if(string != null)
            {
                dataExpressions.add(string);
                tokens.remove(0);
            }
            //Ensures there is an input and we that we get the expected input
            if(factor != null && factor instanceof VariableNode == true || factor instanceof MathOpNode == true)
            {
                return null;
            }
            if(factor == null && string == null && iterations == 0)
            {
                return null;
            }
            //If no more commas, break from loop
            if(matchAndReturn(Token.Type.COMMA) == null)
            {
                break;
            }
        }while(factor != null || string != null);
       }
       catch(Exception e)
       {
           e.printStackTrace();
       }
       return dataExpressions;
    }
   //Method to create Assignment Node
   public AssignmentNode assignment() throws Exception
   {
     try
     {
           Node variable = factor();
           if(variable instanceof VariableNode == true)
           {
               //System.out.println("Your Assignment Variable: " + variable.toString());
              if(matchAndReturn(Token.Type.EQUALS) != null)
              {
                  Node assignmentExpression = expression();
                  StringNode string = makeStringNode();
                  //System.out.println("Your Assignment Expression: " + assignmentExpression.toString());
                  if(assignmentExpression != null)
                  {
                      AssignmentNode assign = new AssignmentNode(variable, assignmentExpression);
                      //System.out.println("Our AssignmentNode in Assignment: " + assign.toString());
                      return assign;
                  }
                  else if(string != null)
                  {
                      tokens.remove(0);
                      AssignmentNode assign = new AssignmentNode(variable, string);
                      //System.out.println("Our AssignmentNode in Assignment: " + assign.toString());
                      return assign;
                  }
                  //Ensures there is an input and we that we get the expected input
                  else 
                  {
                      throw new Exception("Illegal Assignment Statement");
                  }
              }
            }
     }
     catch(Exception e)
      {
         e.printStackTrace();
      }
      return null;
   }
   //Method to create String Node
   public StringNode makeStringNode()
   {
       if(tokens.size() != 0)
       {
       if(tokens.get(0).getType() == Token.Type.STRING)
       {
           StringNode myStringNode = new StringNode(tokens.get(0).toString());
           //System.out.println(myStringNode.toString());
           return myStringNode;
       }
    }
       return null;
   }
   //Method to create Input Statement
   public InputNode InputStatement() throws Exception
   {
       List<Node> inputStatements = new ArrayList<>();
       if(matchAndReturn(Token.Type.INPUT) != null)
       {
           inputStatements = InputList();
          if(inputStatements != null)
           {
           InputNode input = new InputNode(inputStatements);
           return input;
          }
          if(inputStatements == null)
          {
              throw new Exception("Illegal Input Statement");
          }
       }
       return null;
   }
   //Helper method to InputStatement
   public List<Node> InputList()
   {
       List<Node> inputExpressions = new ArrayList<>();
        Node variable;
        Node string;
        int iterations = 0;
       try
       {
        do
        {
            variable = factor();
            string = makeStringNode();
            if(variable != null && variable instanceof VariableNode == true)
            {
                inputExpressions.add(variable);
                iterations++;
            }
            //Ensures that String is the first parameter if one is entered
            if(string != null && iterations == 0)
            {
                inputExpressions.add(string);
                tokens.remove(0);
                iterations++;
            }
            else if(string != null && iterations != 0)
            {
                return null;
            }
            //Ensures there is input and that it is the expected input
            if(string == null && variable == null && iterations == 0)
            {
                return null;
            }
            if(variable != null && variable instanceof VariableNode == false)
            {
                return null;
            }
            //No more comma, then break from loop
            if(matchAndReturn(Token.Type.COMMA) == null)
            {
                break;
            }
        }while(variable != null || string != null);
       }
       catch(Exception e)
       {
           e.printStackTrace();
       }
       return inputExpressions;
       
   }
   //Method to handle Label Statements
   
   public LabeledStatementNode LabelStatement() throws Exception
   {
       Token label = matchAndReturn(Token.Type.LABEL);
       if(label != null)
       {
           String myLabel = label.getString();
           StatementNode statement = getStatementNode();
           if(statement != null)
           {
               LabeledStatementNode labelNode = new LabeledStatementNode(myLabel, statement);
               return labelNode;
           }
           if(statement == null)
           {
               throw new Exception("Illegal Label Statement");
           }
       }
       return null;
   }
   //Helper method for Label Statement
   public StatementNode getStatementNode()
   {
       try
    {
     PrintNode printNode = PrintStatement();
     if(printNode != null)
     {
        return printNode;
    }
   
    //Calls assignment to see if there is an assignment present
    AssignmentNode assignmentNode = assignment();
    if(assignmentNode != null)
    {
         return assignmentNode;
    }
    //Calls DataStatement and checks if there is a data statement
    DataNode dataNode = DataStatement();
    if(dataNode != null)
    {
        return dataNode;
    }
    //Calls InputStatement and checks if there is an input statement
    InputNode inputNode = InputStatement();
    if(inputNode != null)
    {
        return inputNode;
    }
     //Calls ReadStatement and checks if there is a read statement
    ReadNode readNode = ReadStatement();
    if(readNode != null)
    {
        return readNode;
    }
   }
   catch(Exception e)
   {
       e.printStackTrace();
   }
   return null;
       
   }
   
   //Method for a GOSUB statement
   public GosubNode GoSubStatement() throws Exception
   {
       if(matchAndReturn(Token.Type.GOSUB) != null)
       {
           Node variable = getVariable();
           if(variable != null)
           {
               GosubNode goSub = new GosubNode(variable);
               return goSub;
           }
           if(variable == null)
           {
               throw new Exception("Illegal GoSub Statement");
           }
       }
       return null;
   }
   //Helper method for GoSubStatement that gets our variable
   public Node getVariable()
   {
    try
    {
       Node variable = factor();
       if(variable != null && variable instanceof VariableNode == true)
       {
           return variable;
       }
       //Makes sure it isn't a list of variables
       if(matchAndReturn(Token.Type.COMMA) != null)
       {
           return null;
       }
    }
    catch(Exception e)
    {
        e.printStackTrace();
    }
    return null;   
   }
   
   public ReturnNode ReturnStatement() throws Exception
   {
       Token myReturn = matchAndReturn(Token.Type.RETURN);
       Node factorNode = factor();
       Node expressionNode = expression();
       //Ensures its alone on the line
     if(factorNode != null || expressionNode != null && myReturn != null)
      {
          //throw new Exception("Illegal Return Statement");
      } 
     if(myReturn != null)
     {
       ReturnNode returnNode = new ReturnNode();
       return returnNode;
     }
       return null;
   }
   
   //Method for a For Statement
   public ForNode ForStatement() throws Exception
   {
     try
     {
       if(matchAndReturn(Token.Type.FOR) != null)
       {
           Node variable = factor();
           if(variable != null && variable instanceof VariableNode == true)
           {
               if(matchAndReturn(Token.Type.EQUALS) != null)
               {
                   //Gets start value
                   Node start = factor();
                   if(start != null && start instanceof IntegerNode == true)
                   {
                       if(matchAndReturn(Token.Type.TO) != null)
                       {
                           //Gets end value
                           Node end = factor();
                           if(end != null && end instanceof IntegerNode == true)
                           {
                               //Checks if they specify step
                               if(matchAndReturn(Token.Type.STEP) != null)
                               {
                                     Node step = factor();
                                    if(step != null && step instanceof IntegerNode == true)
                                    {
                                       ForNode forNode = new ForNode(variable,start,end,step);
                                       return forNode;
                                    }
                                    else
                                    {
                                        throw new Exception("Illegal For Statement");
                                    }
                               }
                              else
                              {
                               ForNode forNode = new ForNode(variable, start, end, null);
                               return forNode;
                              }
                           } 
                           else
                           {
                              throw new Exception("Illegal For Statement");
                           }
                        }
                        else
                        {
                             throw new Exception("Illegal For Statement");
                        }
                   }
                    else
                     {
                        throw new Exception("Illegal For Statement");
                     }
               }
               else
               {
                  throw new Exception("Illegal For Statement");
               }
           }
           else
           {
               throw new Exception("Illegal For Statement");
           }
       }
     }
     catch(Exception e)
     {
         e.printStackTrace();
     }
     return null;
   }
   //Method for a Next Statement
   public NextNode NextStatement() throws Exception
   {
      try
      {
       if(matchAndReturn(Token.Type.NEXT) != null)
       {
           Node variable = factor();
           //Ensures its a variable 
           if(variable != null && variable instanceof VariableNode == true)
           {
               NextNode next = new NextNode(variable);
               return next;
           }
           else
           {
               throw new Exception("Illegal Next Statement");
           }
       }
      }
      catch(Exception e)
      {
          e.printStackTrace();
      }
      return null;
   }
   //Method for IF Statement
   public IfNode IfStatement() throws Exception
   {
       if(matchAndReturn(Token.Type.IF) != null)
       {
           //Ensures following IF is an expression, booleanOperation, expression
           Node first = expression();
           if(first != null)
           {
               BooleanOperationNode operation = getBoolOperation();
              if(operation != null)
              {
                   Node second = expression();
                   if(second != null)
                   {
                       if(matchAndReturn(Token.Type.THEN) != null)
                       {
                           Node label = expression();
                           if(label != null && label instanceof VariableNode == true)
                           {
                               IfNode ifStatement = new IfNode(first, operation, second, label);
                               return ifStatement;
                           }
                       }
                   }
              }
           }
           throw new Exception("Illegal If Statement");
       }
       return null;
   }
   //Method to get our BooleanOperation
   public BooleanOperationNode getBoolOperation()
   {
       Token greater = matchAndReturn(Token.Type.GREATER);
       Token less = matchAndReturn(Token.Type.LESS);
       Token lessOrequal = matchAndReturn(Token.Type.LESSorEQUAL);
       Token greaterOrequal = matchAndReturn(Token.Type.GREATERorEQUAL);
       Token notEqual = matchAndReturn(Token.Type.NOTEQUALS);
       Token equal = matchAndReturn(Token.Type.EQUALS); 
       //Generates BooleanOperationNode
       if(lessOrequal != null)
       {
           BooleanOperationNode operation = new BooleanOperationNode(lessOrequal.getString());
           return operation;
       }
       if(greaterOrequal != null)
       {
           BooleanOperationNode operation = new BooleanOperationNode(greaterOrequal.getString());
           return operation;
       }
       if(notEqual != null)
       {
           BooleanOperationNode operation = new BooleanOperationNode(notEqual.getString());
           return operation;
       }
       if(equal != null)
       {
           BooleanOperationNode operation = new BooleanOperationNode(equal.getString());
           return operation;
       }
        if(greater != null)
       {
           BooleanOperationNode operation = new BooleanOperationNode(greater.getString());
           return operation;
       }
       if(less != null)
       {
           BooleanOperationNode operation = new BooleanOperationNode(less.getString());
           return operation;
       }
       return null;
   }
   //Method to check for invocation of known functions
   
   public FunctionNode FunctionStatement() throws Exception
   {
       List<Node> parameterList = new ArrayList<>();
       try
       {
       Token myRandom = matchAndReturn(Token.Type.RANDOM);
       Token myLeft = matchAndReturn(Token.Type.LEFT);
       Token myRight = matchAndReturn(Token.Type.RIGHT);
       Token myMid = matchAndReturn(Token.Type.MID);
       Token myNum = matchAndReturn(Token.Type.NUM);
       Token myValI = matchAndReturn(Token.Type.VALi);
       Token myValF = matchAndReturn(Token.Type.VALf);
       //Ensures no parameters for RANDOM()
       if(myRandom != null)
       {
           String functionName = myRandom.getString();
           if(matchAndReturn(Token.Type.LPAREN) == null)
           {
              throw new Exception("Illegal Function Statement");
           }
           if(matchAndReturn(Token.Type.RPAREN) == null)
           {
               throw new Exception("Illgal Function Statement");
           }
           FunctionNode function = new FunctionNode(functionName);
           return function;
       }
       //Ensures correct parameters for LEFT$
       else if(myLeft != null)
       {
         if(matchAndReturn(Token.Type.LPAREN) != null)
          {
               Node string = makeStringNode();
               if(string != null)
               {
                  tokens.remove(0);
                  if(matchAndReturn(Token.Type.COMMA) != null)
                  {
                   Node factor = factor();
                   if(factor != null && factor instanceof IntegerNode == true)
                   {
                     if(matchAndReturn(Token.Type.RPAREN) != null)
                     {
                       String functionName = myLeft.getString();
                       FunctionNode function = new FunctionNode(functionName,string,factor);
                       return function;
                     }
                   }
                  }
               }
          }
          throw new Exception("Illegal Function Statement");
       }
       //Ensures correct parameters for RIGHT$
       else if(myRight != null)
       {
           if(matchAndReturn(Token.Type.LPAREN) != null)
          {
               Node string = makeStringNode();
               if(string != null)
               {
                  tokens.remove(0);
                  if(matchAndReturn(Token.Type.COMMA) != null)
                  {
                   Node factor = factor();
                   if(factor != null && factor instanceof IntegerNode == true)
                   {
                     if(matchAndReturn(Token.Type.RPAREN) != null)
                     {
                       String functionName = myRight.getString();
                       FunctionNode function = new FunctionNode(functionName,string,factor);
                       return function;
                     }
                   }
                  }
               }
          }
          throw new Exception("Illegal Function Statement");
       }
       //Ensures correct parameters for MID$
       else if(myMid != null)
       {
           if(matchAndReturn(Token.Type.LPAREN) != null)
          {
               Node string = makeStringNode();
               if(string != null)
               {
                  tokens.remove(0);
                  if(matchAndReturn(Token.Type.COMMA) != null)
                  {
                   Node factor = factor();
                   if(factor != null && factor instanceof IntegerNode == true)
                   {
                     if(matchAndReturn(Token.Type.COMMA) != null)
                     {
                       Node secondFactor = factor();
                       if(secondFactor != null && secondFactor instanceof IntegerNode == true)
                        {
                           if(matchAndReturn(Token.Type.RPAREN) != null)
                           {
                             String functionName = myMid.getString();
                             FunctionNode function = new FunctionNode(functionName,string,factor,secondFactor);
                             return function;
                           }
                        }
                      }
                   }
                  }
               }
          }
          throw new Exception("Illegal Function Statement");
       }
       //Ensures correct parameter for NUM$
       else if(myNum != null)
       {
           if(matchAndReturn(Token.Type.LPAREN) != null)
           {
               Node factor = factor();
              if(factor != null && factor instanceof IntegerNode == true)
              {
                 if(matchAndReturn(Token.Type.RPAREN) != null)
                 {
                   String functionName = myNum.getString();
                   FunctionNode function = new FunctionNode(functionName,factor);
                   return function;
                 }
              }
              else if(factor != null && factor instanceof FloatNode == true)
              {
                 if(matchAndReturn(Token.Type.RPAREN) != null)
                 {
                   String functionName = myNum.getString();
                   FunctionNode function = new FunctionNode(functionName,factor);
                   return function;
                 }
              }
           }
           throw new Exception("Illegal Function Statement");
       }
       //Ensures correct parameter for VAL
       else if(myValI != null)
       {
          if(matchAndReturn(Token.Type.LPAREN) != null)
          {
               Node string = makeStringNode();
               if(string != null)
               {
                   tokens.remove(0);
                   if(matchAndReturn(Token.Type.RPAREN) != null)
                   {
                       String functionName = myValI.getString();
                       FunctionNode function = new FunctionNode(functionName,string);
                       return function;
                   }
               }
          }
          throw new Exception("Illegal Function Statement");
       }
       //Ensures correct parameter for VAL%
       else if(myValF != null)
       {
          if(matchAndReturn(Token.Type.LPAREN) != null)
          {
               Node string = makeStringNode();
               if(string != null)
               {
                   tokens.remove(0);
                   if(matchAndReturn(Token.Type.RPAREN) != null)
                   {
                       String functionName = myValF.getString();
                       FunctionNode function = new FunctionNode(functionName,string);
                       return function;
                   }
               }
          }
          throw new Exception("Illegal Function Statement");
       }
      }
      catch(Exception e)
      {
          e.printStackTrace();
      }
       return null;
   }
  
}

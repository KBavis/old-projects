import java.lang.Object;
import java.lang.Throwable;
import java.lang.Exception;
import java.lang.IllegalArgumentException;
import java.util.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.*;
public class Lexer 
{
    //Returns Operand (Including if its a negative, includes a decimal, or is more than one number long)
    private static String getOperand(String operand, int index)
    {
        int i = index;
        for(; i < operand.length();){
            
            if(Character.isDigit(operand.charAt(i)) || operand.charAt(i) == '.' || operand.charAt(i) == '-' )
            {
                i++;
            }
            else
            {
                return operand.substring(index,i);
            }
        }
        return operand.substring(index,i);
    }
    //Returns String --> Could be Identifier, Known Word, or Label
    private static String getString(String word, int index)
    {
        int i = index;
        for(; i< word.length();){
             if(Character.isLetter(word.charAt(i)) || Character.isWhitespace(word.charAt(i)) || Character.isDigit(word.charAt(i)) || word.charAt(i) == '.' || word.charAt(i) == '$' || word.charAt(i) == '%' || word.charAt(i)==',') 
            {
                i++;
            }
            else
            {
                return word.substring(index, i);
            }
        }    
        return word.substring(index,i);
    }
    private static String getPhrase(String word, int index)
    {
        int i = index;
        for(; i< word.length();){
            if(Character.isLetter(word.charAt(i)) || word.charAt(i) == ':' || word.charAt(i) == '$' || word.charAt(i) == '%') 
            {
                i++;
            }
            else
            {
                return word.substring(index, i);
            }
        }
        return word.substring(index,i);
        
    }
    public static List<Token> lex(String input)
    {
        //Data Strucutre for Tokens
        List<Token> results = new ArrayList<Token>();
        //Data Structure for Known Words
        HashMap<String, Token.Type> hashmap = new HashMap<String, Token.Type>();
        hashmap.put("PRINT", Token.Type.PRINT);
        hashmap.put("READ", Token.Type.READ);
        hashmap.put("DATA", Token.Type.DATA);
        hashmap.put("INPUT", Token.Type.INPUT);
        hashmap.put("GOSUB", Token.Type.GOSUB);
        hashmap.put("RETURN", Token.Type.RETURN);
        hashmap.put("FOR", Token.Type.FOR);
        hashmap.put("NEXT", Token.Type.NEXT);
        hashmap.put("STEP", Token.Type.STEP);
        hashmap.put("TO", Token.Type.TO);
        hashmap.put("IF", Token.Type.IF);
        hashmap.put("RANDOM", Token.Type.RANDOM);
        hashmap.put("LEFT$", Token.Type.LEFT);
        hashmap.put("RIGHT$", Token.Type.RIGHT);
        hashmap.put("MID$", Token.Type.MID);
        hashmap.put("NUM$", Token.Type.NUM);
        hashmap.put("VAL", Token.Type.VALi);
        hashmap.put("VAL%", Token.Type.VALf);
        hashmap.put("THEN", Token.Type.THEN);
        try
        {
        for(int i = 0; i < input.length();)
        {
            char current = input.charAt(i);
           //State Machine for each Operator that creates Tokens
            switch(current)
            {
                case '+':
                    results.add(new Token(Token.Type.PLUS, String.valueOf(current)));
                    i++;
                    break;
                case '-':
                //If statement to check if it is a negative number or a minus sign
                if(input.length() > 1)
                {
                if(Character.isDigit(input.charAt(i+1))){
                    String operand = getOperand(input, i);
                        i += operand.length();
                        results.add(new Token(Token.Type.NUMBER, operand));
                    }
                    else
                    {
                    results.add(new Token(Token.Type.MINUS, String.valueOf(current)));
                    i++;
                }
            }
            else
            {
                results.add(new Token(Token.Type.MINUS, String.valueOf(current)));
                i++;
            }
                    break;
                case '*':
                    results.add(new Token(Token.Type.TIMES, String.valueOf(current)));
                    i++;
                    break;
                case '/':
                    results.add(new Token(Token.Type.DIVIDE, String.valueOf(current)));
                    i++;
                    break;
                case '=':
                if(input.length() > 1)
                {
                if(input.charAt(i-1) == '<' || input.charAt(i-1) == '>')
                {
                    i++;
                }
              }
              
                results.add(new Token(Token.Type.EQUALS, String.valueOf(current)));
                i++;
                break;
                case '<':
                //Ensures input is more than one letter
             if(input.length() > 1)
                {
                    //Checks if its a less than or equal to sign
                 if(input.charAt(i+1) == '=')
                 {
                     i+=2;
                     String temp = "<=";
                     results.add(new Token(Token.Type.LESSorEQUAL, temp));
                     break;
                  }
                    //Checks if its a not equals sign
                 else if(input.charAt(i+1) == '>')
                 {
                     i+=2;
                     String temp = "<>";
                     results.add(new Token(Token.Type.NOTEQUALS, temp));
                     break;
                    }
                 else 
                 {
                  results.add(new Token(Token.Type.LESS, String.valueOf(current)));
                   i++;
                   break;
                 }
              }
          else
          {
            results.add(new Token(Token.Type.LESS, String.valueOf(current)));
            i++;
            break;
         }
                
                case '>':
                //Ensures input is more than one letter
                if(input.length() > 1)
                {
                    //Checks if its a greater than or equal to sign
                  if(input.charAt(i+1) == '='){
                      i+=2;
                      String temp = ">=";
                      results.add(new Token(Token.Type.GREATERorEQUAL, temp));
                    }
                    else
                    {
                        results.add(new Token(Token.Type.GREATER, String.valueOf(current)));
                        i++;
                    }
                }
                else
                {
                    results.add(new Token(Token.Type.GREATER, String.valueOf(current)));
                    i++;
                }
                 break;
                 case '(':
                    results.add(new Token(Token.Type.LPAREN, String.valueOf(current)));
                    i++;
                   break;
                  case ')':
                    results.add(new Token(Token.Type.RPAREN, String.valueOf(current)));
                    i++;
                    break;
                   case '"':
                   i++;
                    String word = getString(input , i);
                    i+= word.length();
                    i++;
                    results.add(new Token(Token.Type.STRING, word));
                     break;
                   case ',':
                     results.add(new Token(Token.Type.COMMA, String.valueOf(current)));
                     i++;
                     break;
                                   //Default Case 
                default: 
                
                    if(Character.isWhitespace(current)){
                        i++;
                    }
                    else if(Character.isLetter(current))
                    {
                        String phrase = getPhrase(input , i);
                        //Checks if String is a "Known Word"
                        int counterDollar = 0;
                        int counterPercent = 0;
                        //Ensures there isn't both a $ and % in a phrase
                        for(int j = 0; j < phrase.length(); j++)
                        {
                            if(phrase.charAt(j) == '$')
                            {
                                counterDollar++;
                            }
                            else if(phrase.charAt(j) == '%')
                            {
                                counterPercent++;
                            }
                            
                        }
                        if(counterDollar >= 1 && counterPercent >= 1)
                        {
                            throw new IllegalArgumentException();
    
                        }
                        
                    if(hashmap.containsKey(phrase))
                        {
                           if(phrase.toUpperCase().equals("PRINT"))
                            {
                                results.add(new Token(Token.Type.PRINT, phrase));
                            }
                            else if(phrase.toUpperCase().equals("READ"))
                            {
                                results.add(new Token(Token.Type.READ, phrase));
                            }
                            else if(phrase.toUpperCase().equals("DATA"))
                            {
                                results.add(new Token(Token.Type.DATA, phrase));
                            }
                            else if(phrase.toUpperCase().equals("INPUT"))
                            {
                                results.add(new Token(Token.Type.INPUT, phrase));
                            }
                            else if(phrase.toUpperCase().equals("GOSUB"))
                            {
                                results.add(new Token(Token.Type.GOSUB, phrase));
                            }
                            else if(phrase.toUpperCase().equals("RETURN"))
                            {
                                results.add(new Token(Token.Type.RETURN, phrase));
                            }
                            else if(phrase.toUpperCase().equals("FOR"))
                            {
                                results.add(new Token(Token.Type.FOR, phrase));
                            }
                            else if(phrase.toUpperCase().equals("NEXT"))
                            {
                                results.add(new Token(Token.Type.NEXT, phrase));
                            }
                            else if(phrase.toUpperCase().equals("STEP"))
                            {
                                results.add(new Token(Token.Type.STEP, phrase));
                            }
                            else if(phrase.toUpperCase().equals("TO"))
                            {
                                results.add(new Token(Token.Type.TO, phrase));
                            }
                            else if(phrase.toUpperCase().equals("IF"))
                            {
                                results.add(new Token(Token.Type.IF, phrase));
                            }
                            else if(phrase.toUpperCase().equals("RANDOM"))
                            {
                                results.add(new Token(Token.Type.RANDOM, phrase));
                            }
                            else if(phrase.toUpperCase().equals("LEFT$"))
                            {
                                results.add(new Token(Token.Type.LEFT, phrase));
                            }
                            else if(phrase.toUpperCase().equals("RIGHT$"))
                            {
                                results.add(new Token(Token.Type.RIGHT, phrase));
                            }
                            else if(phrase.toUpperCase().equals("MID$"))
                            {
                                results.add(new Token(Token.Type.MID, phrase));
                            }
                            else if(phrase.toUpperCase().equals("NUM$"))
                            {
                                results.add(new Token(Token.Type.NUM, phrase));
                            }
                            else if(phrase.toUpperCase().equals("VAL"))
                            {
                                results.add(new Token(Token.Type.VALi, phrase));
                            }
                            else if(phrase.toUpperCase().equals("VAL%"))
                            {
                                results.add(new Token(Token.Type.VALf, phrase));
                            }
                            else if(phrase.toUpperCase().equals("THEN"))
                            {
                                results.add(new Token(Token.Type.THEN, phrase));
                            }
                        
                            i+= phrase.length();    
                            break;
                         }
                        //Checks if String is a Label
                        if(phrase.charAt(phrase.length()-1) == ':')
                        {
                            results.add(new Token(Token.Type.LABEL, phrase));
                            i+= phrase.length();
                            break;
                        }
                        //Checks if its an Identifier
                        else{
                        results.add(new Token(Token.Type.IDENTIFIER, phrase));
                        i+= phrase.length();
                        break;
                    }
                    }
                    //Checks if its an operand
                    else if(Character.isDigit(current)){
                        String operand = getOperand(input, i);
                        i += operand.length();
                        results.add(new Token(Token.Type.NUMBER, operand));
                    }
                    else
                    {
                        throw new IllegalArgumentException();
                    }
                    break;
                } 
                }
            }catch(IllegalArgumentException e)
            {
                e.printStackTrace();
                results.add(new Token(Token.Type.ERROR, e.getMessage()));
            }
            return results;   
            }
    }



import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.lang.Object;
import java.lang.Throwable;
import java.lang.Exception;
import java.lang.IllegalArgumentException;
import java.io.File;
import java.util.*;

public class Basic
{
    //Reads text file into a list of strings
    public static List<String> readFileInList(String fileName)
    {
        List<String> lines = Collections.emptyList();
        //Try-Catch to catch any exceptions thrown
        try
        {
            lines = Files.readAllLines(Paths.get(fileName));
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        return lines;
    }
   public static void main(String[] args)
   {
       //Ensures only one arguement
        Node AST = null;
       if(args.length > 1){
           throw new IllegalArgumentException("Error");
        }
    
        
        //Takes in file from its direct path 
           List l = readFileInList(args[0]);
           Lexer lxr = new Lexer();
           List<Token> tknlst = new ArrayList<Token>();
           Iterator<String> itr = l.iterator();
           List<String> strings = new ArrayList<String>();
        
    
           
           
           //Iterates through the list until empty
           while(itr.hasNext())
           {
               strings.add(itr.next());
            }
              
               
           //Goes through list and parses each line and adds to a single list of tokens
                for(int i = 0; i < strings.size(); i++)
            {
               tknlst.addAll(lxr.lex(strings.get(i)));
            }
            
           /* 
           //Prints Tokens
            for(int i = 0; i < tknlst.size(); i ++)
            {
               System.out.print(tknlst.get(i) + " ");
            }
           System.out.print("EndOfLine");
           System.out.println();
          */
            Parserr p = new Parserr(tknlst);
            
           try
            {
              AST = p.parse();
            }
          catch(Exception e)
        {
           e.printStackTrace();
        }
           
          Interpreter interpret = new Interpreter(AST);
          interpret.interpret();
          
           
            
    }
}

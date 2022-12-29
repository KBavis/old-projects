import java.util.*;

public class Token
{
    private final Type t;
    private final String c;
    //Types of tokens
    public enum Type{
        PLUS, MINUS, TIMES, DIVIDE, NUMBER, EQUALS, LESS, LESSorEQUAL, GREATER, GREATERorEQUAL, 
        NOTEQUALS, LPAREN, RPAREN, STRING, IDENTIFIER, LABEL, PRINT, COMMA, ERROR, READ, DATA, INPUT, GOSUB,
        RETURN, FOR, NEXT, STEP, TO, IF, RANDOM, LEFT, RIGHT, MID, NUM, VALi, VALf, THEN
    }
    //Constructor
    public Token(Type t, String c)
    {
        this.t = t;
        this.c = c;  
    }
    //Accessor methods
    public Type getType()
    {
        return t;
    }
    public String getString()
    {
        return c;
    }
    @Override
    //ToString that ensures proper format of token
    public String toString()
    {
        /*
        if(t == Type.NUMBER || t == Type.STRING || t == Type.IDENTIFIER || t == Type.LABEL)
        {
        return this.t + "("  + this.c + ")" ;
    }
    else if(t == Type.ERROR)
    {
        return this.c + " ";
    }
        else
        {
            return this.t + " ";
        }
        */
       return this.c;
    }
    
}
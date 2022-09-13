import java.util.BitSet;
public class LongWord {
   BitSet bitSet;
   //Contructors
    public LongWord(){
        BitSet bitSet = new BitSet();
        this.bitSet = bitSet;
        this.bitSet.clear();
    }  
    public LongWord(BitSet bitSet){
        this.bitSet = bitSet;
        this.bitSet.clear();
    }
    //Get bit i as a boolean
    public boolean getBit(int i){
        return this.bitSet.get(i);
    }
    //Set bit i to true
    public void setBit(int i){
        this.bitSet.set(i);
    }
    //Resets bit to false
    public void clearBit(int i){
        this.bitSet.clear(i);
    }
    //Flips bit i
    public void toggleBit(int i){
        this.bitSet.flip(i);
    }
    //Method to convert binary to hexadecimal
    public String binaryToHex(String bin){
        String hex = "";
        if(bin.equals("0000")){
            hex = "0";
        }
        else if(bin.equals("0001")){
            hex = "1";
        }
        else if(bin.equals("0010")){
            hex = "2";
        }
        else if(bin.equals("0011")){
            hex = "3";
        }
        else if(bin.equals("0100")){
            hex = "4";
        }
        else if(bin.equals("0101")){
            hex = "5";
        }
        else if(bin.equals("0110")){
            hex = "6";
        }
        else if(bin.equals("0111")){
            hex = "7";
        }
        else if(bin.equals("1000")){
            hex = "8";
        }
        else if(bin.equals("1001")){
            hex = "9";
        }
        else if(bin.equals("1010")){
            hex = "a";
        }
        else if(bin.equals("1011")){
            hex = "b";
        }
        else if(bin.equals("1100")){
            hex = "c";
        }
        else if(bin.equals("1101")){
            hex = "d";
        }
        else if(bin.equals("1110")){
            hex = "e";
        }
        else if(bin.equals("1111")){
            hex = "f";
        }
        return hex;
    }
    //Method to convert binary to decimal (returns int)
    public int binToDecimal(String b){
       int n = 0;
       int total = 0;
        for(int i = 31; i >= 0; i--){
           if(b.charAt(i) == '1'){
               total += Math.pow(2,n);
           }
           n++;
        }
        return total;
    }
    //Sets the value of the bits of this long-word
   public void set(int num){
       String binaryConversion = "";
       //If the num is positive
       if(num > 0){
          while(num > 0){
             int val = num % 2;
             binaryConversion += val;
             num/= 2;
          }
          //Calculating the amount of zeros to be inserted
          int amount = 32 - binaryConversion.length();
          String zero = "";
          for(int i = 0; i < amount; i ++){
             zero += "0";
          }
          String returnVal = binaryConversion + zero;
          this.bitSet.clear();
          for(int i = 0; i < 32; i++){
             if(returnVal.charAt(i) == '1'){
                this.bitSet.set(i);
             }
          }
       }
       //If it is negative
       else if(num < 0){
           //Makes it positive
           num *= -1;
           while(num > 0){
             int val = num % 2;
             binaryConversion += val;
             num/= 2;
          }
          //Calculating the amount of zeros to be inserted
          int amount = 32 - binaryConversion.length();
          String zero = "";
          for(int i = 0; i < amount; i ++){
             zero += "0";
          }
          String returnVal = binaryConversion + zero;
          //Clears the bitset
          this.bitSet.clear();
          for(int i = 0; i < 32; i++){
              if(returnVal.charAt(i) == '1'){
                this.bitSet.set(i);
              }
          }
          //Flips the bits of the bitset
          for(int i = 0; i < 32; i++){
              this.bitSet.flip(i);
          }
          int i = 0;
          //Deals with where to add our 1
          while(true){
              if(this.bitSet.get(i) == false){
                  this.bitSet.set(i);
                  break;
              }
              else{
                  this.bitSet.flip(i);
                  i++;
              }
          }
       }
    }
    //Method to convert binary to decimal (returns long)
    public long longBinToDecimal(String b){
       int n = 0;
       long total = 0;
        for(int i = b.length() - 1; i >= 0; i--){
           if(b.charAt(i) == '1'){
               total += Math.pow(2,n);
           }
           n++;
        }
        return total;
    }
    //Returns the value of the long-word as an int
    public int getSigned(){
        //If its negative
        if(this.bitSet.get(31) == true){
           //Use two's complement
           String val = "";
           for(int i = 31; i >= 0; i--){
              //Flip the bits 
              if(this.bitSet.get(i) == true){
                 val += "0";
              }
              else{
                 val += "1";
              }
           }
           //Negate integer & add one
           return -1 * (1 + binToDecimal(val));
           
        }
        //If its postive
        else{
           String val = "";
           for(int i = 31; i >= 0; i--){
              if(this.bitSet.get(i) == true){
                 val += "1";
              }
              else{
                 val += "0";
              }
           }
            return binToDecimal(val);
        }
    }
    //Method to copy the values of the bits from another long-word into this one 
    public void copy(LongWord other){
        this.bitSet.clear();
        for(int i = 0; i < 32; i++){
            if(other.getBit(i) == true){
                this.setBit(i);
            }
        }
    }
    //Method to return the value of the long-word as a long
    public long getUnsigned(){
       String val = "";
       for(int i = 31; i >= 0; i--){
              if(this.bitSet.get(i) == true){
                 val += "1";
              }
              else{
                 val += "0";
              }
           }
         return longBinToDecimal(val);
    }
    //Logical shift left that creates new LongWord
    public LongWord shiftLeftLogical(int amount){
        int a = this.getSigned();
        int b = a << amount;
        LongWord newLong = new LongWord();
        newLong.set(b);
        return newLong;
    }
    //Logical shift right that creates new LongWord
    public LongWord shiftRightLogical(int amount){
        int a = this.getSigned();
        int b = a >>> amount;
        LongWord newLong = new LongWord();
        newLong.set(b);
        return newLong;
    }
    //Arithmitic shift right that creates new LongWord
    public LongWord shiftRightArithmitic(int amount){
        int a = this.getSigned();
        int b = a >> amount;
        LongWord newLong = new LongWord();
        newLong.set(b);
        return newLong;
    }
    //negates the LongWord creating another one
    public LongWord not(){
        LongWord newLongWord = new LongWord();
        for(int i = 0; i < 32; i++){
            if(this.getBit(i) == false){
                newLongWord.setBit(i);
            }
        }
        return newLongWord;
    }
    //Ands two LongWords together and returns a third
    public LongWord and(LongWord other){
        LongWord newLongWord = new LongWord();
        for(int i = 0; i < 32; i++){
            if(this.getBit(i) == true && other.getBit(i) == true){
                newLongWord.setBit(i);
            }
        }
        return newLongWord;
    }
    //Ors two LongWords together and returns a third
    public LongWord or(LongWord other){
        LongWord newLongWord = new LongWord();
        for(int i = 0; i < 32; i++){
            if(this.getBit(i) == true || other.getBit(i) == true){
                newLongWord.setBit(i);
            }
        }
        return newLongWord;
    }
    //XOr two LongWords together and returns a third
    public LongWord xor(LongWord other){
        LongWord newLongWord = new LongWord();
        for(int i = 0; i < 32; i++){
            if(this.getBit(i) == true && other.getBit(i) == false){
                newLongWord.setBit(i);
            }
            else if(this.getBit(i) == false && other.getBit(i) == true){
                newLongWord.setBit(i);
            }
        }
        return newLongWord;
    }
    //Returns true if all bits are 0's in this LongWord
    public boolean isZero(){
        for(int i = 0 ; i < 32; i++){
            if(this.getBit(i) == true){
                return false;
            }
        }
        return true;
    }
    //toString() to print out our LongWord
    @Override
    public String toString(){
        String myBitSet = "";
        String hex = "0x";
        String conversion = "";

        int counter = 0;
        for(int i = 31; i >= 0; i--){
            if(counter % 4 == 0){
                //Converts the 4 bits to hexadecimal
                hex += binaryToHex(conversion);
                conversion = "";
                myBitSet += " ";
            }
            if(this.bitSet.get(i) == true){
                myBitSet += "1";
                conversion += "1";
            }
            else{
                myBitSet += "0";
                conversion += "0";
            }
            counter++;
        }
        hex += binaryToHex(conversion);
        //Returns our bitset and the hexadecimal conversion
        return myBitSet + '\t' + hex; 
}
}

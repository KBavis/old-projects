public class ALU implements operations {
   boolean ZF;
   boolean NF;
   boolean CF;
   boolean OF;


   //Accessors for our flags
   public boolean getZF(){
      return ZF;
   }
   public boolean getNF(){
      return NF;
   }
   public boolean getCF(){
      return CF;
   }
   public boolean getOF(){
      return OF;
   }
   //Mutator that performs ALU operation 
   public LongWord operate(int code, LongWord op1, LongWord op2){
      LongWord result = new LongWord();
      //AND
      if(code == 0){
         result = op1.and(op2);
         if(result.getBit(31) == true){
            NF = true;
         }
         if(result.isZero() == true){
            ZF = true;
         }
         return result;
      }
      //OR
      else if(code == 1){
         result = op1.or(op2);
         if(result.getBit(31) == true){
            NF = true;
         }
         if(result.isZero() == true){
            ZF = true;
         }
      }
      //XOR
      else if(code == 2){
         result = op1.xor(op2);
         if(result.getBit(31) == true){
            NF = true;
         }
         if(result.isZero() == true){
            ZF = true;
         }
      }
      //ADD
      else if(code == 3){
         result = rippleCarryAdd(op1,op2,false);
          if(result.getBit(31) == true){
            NF = true;
         }
         if(result.isZero() == true){
            ZF = true;
         }
      }
      //SUB 
      else if(code == 4){
         result = rippleCarryAdd(op1, op2.not(), true);
         if(result.getBit(31) == true){
            NF = true;
         }
         if(result.isZero() == true){
            ZF = true;
         }
      }
      //SLL
      else if(code == 5){
         result = op1.shiftLeftLogical(op2.getSigned());
         if(op1.getBit(31) == true){
            
         }
         if(result.getBit(31) == true){
            NF = true;
         }
         if(result.isZero() == true){
            ZF = true;
         }
      }
      return result;
   }

   //Method for addition and subtraction 
   private LongWord rippleCarryAdd(LongWord a, LongWord b, boolean cin){
      LongWord res = new LongWord();
      if(cin == true){
         boolean carry = true;
         for(int i = 0; i < 32; i++){
               if(a.getBit(i) ^ b.getBit(i) == true){
                  if(carry == false){
                     res.setBit(i);
                  }
               }
               else if(a.getBit(i) == true && b.getBit(i) == true){
                  if(carry == true){
                     res.setBit(i);
                  }
                  else{
                     carry = true;
                  }
               }
               else if(a.getBit(i) == false && b.getBit(i) == false){
                  if(carry == true){
                     res.setBit(i);
                     carry = false;
                  }
               }
            }
      }
      else{
            boolean carry = false;
            for(int i = 0; i < 32; i++){
               if(a.getBit(i) ^ b.getBit(i) == true){
                  if(carry == false){
                     res.setBit(i);
                  }
               }
               else if(a.getBit(i) == true && b.getBit(i) == true){
                  if(carry == true){
                     res.setBit(i);
                  }
                  else{
                     carry = true;
                  }
               }
               else if(a.getBit(i) == false && b.getBit(i) == false){
                  if(carry == true){
                     res.setBit(i);
                     carry = false;
                  }
               }
            }
            if(carry == true){
               CF = true;
               OF = true;
            }
         }
      return res;
   }
}

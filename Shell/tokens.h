#include "data.h"
// Gaurd
#ifndef TOKENS_H
#define TOKENS_H
//Method that reads in our command
void read_input(char* commands);
//Method to remove characters (i.e the character ' )
char* removechr(char* str, char c);
//Method that tokenizes our input
void tokenize_input(char* input,char* tokens[]);
#endif
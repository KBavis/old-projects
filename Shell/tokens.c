#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <fcntl.h>
#include "tokens.h"

//Method that reads in users input
void read_input(char* commands){
    int counter = 0;
    for(;;){
        int c = fgetc(stdin);
        commands[counter] = (char)c;
        counter++;
        if(c == '\n'){
            break;
        }
    }
    
}

//Method to remove char
char* removechr(char* str, char c){
    int len = strlen(str);
    for(int i = 0; i < len; i++){
        if(str[i] == c){
            for(int j = i; j < len; j++){
                str[j] = str[j + 1];
            }
            len--;
            i--;
        }
    }
    len = strlen(str);
    str[len] = '\0';
    return str;
}

// Method that tokenizes our input.

void tokenize_input(char* input,char* tokens[]){
    int counter = 0;

    // Splits up input word by word
    char* command = strtok(input, " \n");

    if(strcmp(command,"exit") == 0){
        exit(1);
    }

    while(command != NULL){
        tokens[counter++] = strdup(command);
        command = strtok(NULL, " \n");
    }
    tokens[counter] = NULL;  

    int i = 0;
    //Removing each instance of ' in our tokens
    //So we can run commands such as egrep 'g'
    while(tokens[i] != NULL){
        if(strchr(tokens[i],'\'')){
            tokens[i] = removechr(tokens[i],'\'');
        }
        i++;
    }
}

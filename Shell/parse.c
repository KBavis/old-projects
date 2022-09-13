#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "data.h"
#include "parse.h"

//Code to generate our command table
struct CommandTable* generate_commands(char* tokens[]){
    int i = 0;
    // Number of Commands
    int numCommands = 0;
    // Numbers of Arguments Per Command
    int num_command_args[10];
    // Command Table Data Struture
    struct CommandTable* table = malloc(sizeof(table));
    table->num_commands = 0;
    int commandNumber = 0;

    //Loop To Parse Commands
    do{
        struct Command* command = malloc(sizeof(command));
        int numArgs = 0;
        command->num_args = 0;
        command->cmd = tokens[i];
        for(;;){
            i++;

            // End Of Tokens or New Command
            if(tokens[i] == NULL || strcmp(tokens[i],"|") == 0){
                num_command_args[commandNumber] = numArgs;
                commandNumber++;

                // Parsing Our Executable Member To Be Exec Later In eecute.c
                command->executable[0] = command->cmd;
                for(int i = 0; i < numArgs; i++){
                    command->executable[i + 1] = command->args[i];
                }
                command->executable[numArgs + 1] = NULL;
                break;
            }
            
            //in file
            if(strcmp(tokens[i],"<") == 0){
                i++;
                table->in_file = tokens[i];
            }
            // out file
            else if(strcmp(tokens[i],">") == 0){
                i++;
                command->out_file = tokens[i];
            }
            //command runs in background
            else if(strcmp(tokens[i],"&") == 0){
                table->run_background = true;
            }
            // args
            else{
                command->args[numArgs] = tokens[i];
                numArgs++;
            }
        }

        //Adds command to table of commands
        table->command[numCommands] = command;
        numCommands++;

        if(tokens[i] == NULL){
            // Parsing Our Executable Member To Be Exec Later
            command->executable[0] = command->cmd;
            for(int i = 0; i < numArgs; i++){
                command->executable[i + 1] = command->args[i];
            }
                command->executable[numArgs + 1] = NULL;
            break;
        }
        else{
            i++;
        }
    }while(tokens[i] != NULL);
    
    table->num_commands = numCommands;
    // initalizing the number of arguments each command has in our Command Table 
    for(int j = 0; j < table->num_commands; j++){
        table->command[j]->num_args = num_command_args[j];
    }

    return table;
    
}

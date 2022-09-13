#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <fcntl.h>
#include "data.h"
#include "execute.h"

//Execution of our command(s)
void execute_command(struct CommandTable* table){
    int status = 0;
    // Saves I/O
    int temp_in = dup(STDIN_FILENO);
    int temp_out = dup(STDOUT_FILENO);
    
    int fd_in;
    int fd_out;


    // Sets Input
    // If Input File Present, Set Input To File
    if(table->in_file){
        fd_in = open(table->in_file,O_RDONLY);
    }
    else{
        fd_in = dup(temp_in);
    }


    int pid;
    //For Loop To Execute Commands
    for(int i = 0; i < table->num_commands; i++){
        //Redirect Input
        dup2(fd_in, STDIN_FILENO);
        close(fd_in);

        //If We Are At our Last Command, Set This To Out File If There Is One Present
        if(i == table->num_commands - 1){

            if(table->command[i]->out_file){
               fd_out = open(table->command[i]->out_file, O_CREAT|O_RDWR|O_APPEND, S_IRUSR| S_IWUSR); 
               if(fd_out == -1){
                   perror("open");
               }
            }
            else{
                fd_out = dup(temp_out);
            }
        }
        // Pipe
        else{
            int p[2];
            if(pipe(p) < 0){
                perror("Error piping\n");
                exit(EXIT_FAILURE);
            }

            //If there is an outfile present
            if(table->command[i]->out_file){
                fd_out = open(table->command[i]->out_file, O_CREAT|O_RDWR|O_APPEND, S_IRUSR| S_IWUSR); 
            }
            else{
                fd_out = p[1];
            }
            fd_in = p[0];
        }


        // Redirect Output
        dup2(fd_out,STDOUT_FILENO);
        close(fd_out);

        //Forking
        pid = fork();
        if(pid < 0){
            perror("Error while forking\n");
            exit(EXIT_FAILURE);
        }
        // Child Process
        else if(pid == 0){
            execvp(table->command[i]->executable[0],table->command[i]->executable);
            perror("Cant exec\n");
            exit(EXIT_FAILURE);
        }
    }

    // Restore our default fds
    dup2(temp_in,STDIN_FILENO);
    dup2(temp_out,STDOUT_FILENO);
    close(temp_in);
    close(temp_out);
    
    //Wait for our commands to execute
    if(table->run_background == false){
        waitpid(pid, &status, WUNTRACED | WCONTINUED);
    }
}

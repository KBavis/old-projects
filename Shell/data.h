#include <stdbool.h>
// Gaurd
#ifndef DATA_H
#define DATA_H
// Data structure to deal with multiple commands (I/O redirection and Pipes)
struct CommandTable{
    struct Command* command[100];
    char* in_file;
    bool run_background;
    int num_commands;
};

// Data sturucutre to deal with a command;
struct Command{
    char* cmd;
    char* args[15];
    char* executable[25];
    int num_args;
    char* out_file;
};
#endif
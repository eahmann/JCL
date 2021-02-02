# JCL
Java Command Line


Built with gradle

build/libs should already contain compiled JAR file 
otherwise build a new JAR by running 'gradle build' command in terminal

to run:
`java -jar <PATH TO JAR FILE>`

Sample output for v1
```
❯ java -jar build/libs/Programming\ Assignment\ 1-1.0-SNAPSHOT.jar
/home/eric/IdeaProjects/377/Programming Assignment 1
Welcome to Java Command Line v1
To begin enter command at prompt. Type HELP to see commands

Current working directory /home/eric/IdeaProjects/377/Programming Assignment 1
Enter Command:
>> HELP
Available commands: 
LIST CHDIR RUN REMOVE RENAME HELP QUIT 

Current working directory /home/eric/IdeaProjects/377/Programming Assignment 1
Enter Command:
>> list
LIST COMMAND EXECUTED
total 32
-rw-r--r-- 1 eric eric    0 Feb  1 13:48 Code.java
drwxr-xr-x 8 eric eric 4096 Feb  1 16:15 build
-rw-r--r-- 1 eric eric  250 Jan 25 10:13 build.gradle
drwxr-xr-x 3 eric eric 4096 Jan 25 10:07 gradle
-rwxr-xr-x 1 eric eric 5766 Jan 25 10:07 gradlew
-rw-r--r-- 1 eric eric 2763 Jan 25 10:07 gradlew.bat
-rw-r--r-- 1 eric eric   47 Jan 25 10:07 settings.gradle
drwxr-xr-x 4 eric eric 4096 Jan 25 10:07 src

Current working directory /home/eric/IdeaProjects/377/Programming Assignment 1
Enter Command:
>> CHDIR /home/eric/IdeaProjects/377/
CHDIR COMMAND EXECUTED
New Working Directory: /home/eric/IdeaProjects/377

Current working directory /home/eric/IdeaProjects/377/
Enter Command:
>> list
LIST COMMAND EXECUTED
total 4
drwxr-xr-x 7 eric eric 4096 Feb  1 16:15 Programming Assignment 1

Current working directory /home/eric/IdeaProjects/377/
Enter Command:
>> QUIT

Thank you for using Java Command Line v1
```

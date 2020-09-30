# Duke User Guide

## Table of Contents
* [Introduction](#introduction)
* [Getting Started](#getting-started)
* [Usage](#usage)
* [Features](#features)
* [Summary](#command-summary)

## Introduction
Duke is a Command Line Interface (CLI) based application that helps you manage your tasks.

## Getting Started
1. Ensure you have installed Java 11.
2. Download the latest IP.jar from GitHub
3. Open your command prompt and navigate to the folder where IP.jar was downloaded to.
4. Type in `java -jar ip.jar` and enter.

## Usage
> `Commands` are used to describe the format of a command,and words in square brackets e.g. `[arguments]` indicate parameters that need to be provided by you.

### Command Description `Command`

Describes command and its outcome.


Format: `command  [arguments]`

Example of usage: `example`

## Features 
* [Adding a Todo](#add-a-todo-task-todo)
* [Adding a Deadline](#add-a-deadline-task-deadline)
* [Adding an Event](#add-an-event-task-event)
* [Listing tasks](#list-all-tasks-list)
* [Marking a task as done](#mark-a-task-as-done-done)
* [Deleting a task](#delete-a-task-delete)
* [Finding tasks](#find-tasks-find)
* [Exiting the program](#exit-duke-program-bye)

>* The tasks list is saved to an external .txt file whenever the tasks list is modified (i.e. tasks are added, marked as done or deleted)
### Add a Todo task `todo` 
Adds a Todo task to the tasks list. Tasks are marked as incomplete by default when added.

Format: `todo [task_description]`

Example of usage: `todo CS2113T increment level-7092`

### Add a Deadline task `deadline` 
Adds a Deadline task to the tasks list. Tasks are marked as incomplete by default when added. 

Format: `deadline [task_description] /by [date]`

Example of usage: `deadline lecture quiz /by next Wednesday`

### Add an Event task `event` 
Adds an Event task to the tasks list. Tasks are marked as incomplete by default when added. 

Format: `event [task_description] /at [date]`

Example of usage: `event phase 3 zoom party /at 29th February`

### List all tasks `list` 
Shows a list of all tasks currently stored in Duke. 

Format: `list`

Example of usage: `list` displays the list of tasks.

### Mark a task as done `done` 
Marks a task on the list as done. 
>* The `[task_index]` provided corresponds to its place in the list as shown by using `list`
>* The `[task_index]` provided should not exceed the tasks list index range.

Format: `done [task_index]`

Example of usage: `done 5` marks the 5th task in the list as done if there are at least 5 tasks in the list

### Delete a task `delete` 
Deletes a task from the list. 
>* The `[task_index]` provided corresponds to its place in the list as shown by using `list`
>* The `[task_index]` provided should not exceed the tasks list index range.

Format: `delete [task_index]`

Example of usage: `delete 2` deletes the 2nd task from the list if there are at least 2 tasks in the list

### Find tasks `find` 
Finds all tasks in the list containing a given keyword or keyphrase and shows them in a list. 

Format: `find [keyword]`

Example of usage: `find lab sessions` shows a list of tasks containing '`lab sessions`'

### Exit Duke program `bye` 
Quits the Duke program. 

Format: `bye`

Example of usage: `bye` saves the current tasks list to an external .txt file and ends the program 

## Command Summary
|Feature|Command format|
|:---|:---|
|Add a Todo task| `todo [task_description]`|
|Add a Deadline task| `deadline [task_description] /by [date]`|
|Add an Event task| `event [task_description] /at [date]`|
|Listing all tasks| `list`|
|Marking a task as done| `done [task_index]`|
|Deleting a task| `delete [task_index]`|
|Finding tasks| `find [keyword(s)]`|
|Exit program| `bye`|




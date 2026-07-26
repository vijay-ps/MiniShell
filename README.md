# 🖥️ MiniShell

A lightweight terminal emulator built in **Java** that replicates essential shell functionality. The project provides an interactive command-line interface capable of executing system commands, navigating directories, maintaining command history, and handling built-in shell commands.

This project was built to gain a deeper understanding of operating system concepts, process execution, file system navigation, and Java's `ProcessBuilder` API without relying on any external frameworks.

---

## ✨ Features

- Interactive shell prompt
- Execute external system commands using `ProcessBuilder`
- Change working directory (`cd`)
- Command history (`history`)
- Built-in help command (`help`)
- Clear terminal (`clear` / `cls`)
- ANSI colored output
- Relative and absolute path navigation
- Graceful error handling
- Custom working directory management independent of the JVM

---

## 🛠️ Tech Stack

- Java
- ProcessBuilder API
- Java File API
- Scanner
- BufferedReader
- ANSI Escape Sequences
- Object-Oriented Programming

---

## 📂 Project Structure

```text
MiniShell/
│
├── MiniShell.java
├── AnsiColors.java
├── README.md
```

---

## 🚀 Supported Commands

| Command                  | Description                          |
| ------------------------ | ------------------------------------ |
| `cd <directory>`         | Change the current working directory |
| `history`                | Display previously executed commands |
| `help`                   | Display all available commands       |
| `clear` / `cls`          | Clear the terminal screen            |
| `exit`                   | Exit the shell                       |
| Any valid system command | Executed using Java ProcessBuilder   |

---

## ⚙️ How It Works

The shell continuously waits for user input inside a REPL (Read-Evaluate-Print Loop).

1. Read the user command.
2. Check whether it is a built-in command.
3. Execute built-in commands internally.
4. Otherwise, execute external commands using `ProcessBuilder`.
5. Display the output.
6. Repeat until `exit` is entered.

---

## 🧠 Concepts Learned

While building this project, I explored:

- Java ProcessBuilder
- Process Management
- File System Navigation
- Relative vs Absolute Paths
- Canonical Paths
- Working Directory Management
- ANSI Escape Sequences
- Command Parsing
- Java File API
- Error Handling
- REPL (Read-Evaluate-Print Loop)

---

## ⚠️ Challenges Faced

- Maintaining a custom working directory independent of the JVM
- Handling relative and absolute directory navigation
- Executing operating system commands from Java
- Implementing built-in commands without invoking the operating system
- Managing command history
- Displaying colored terminal output using ANSI escape sequences

---

## 📖 Example Usage

```bash
John@MiniShell:C:\Projects> cd src

C:\Projects\src

John@MiniShell:C:\Projects\src> history

pwd
cd src
history

John@MiniShell:C:\Projects\src> help

1. cd <directory>
2. clear
3. history
4. help
5. exit
```

---

## 💡 Future Improvements

- [ ] Built-in `pwd` command
- [ ] Built-in `ls` implementation
- [ ] Built-in `echo`
- [ ] Input Redirection (`<`)
- [ ] Output Redirection (`>`, `>>`)
- [ ] Command Pipes (`|`)
- [ ] Environment Variable Support
- [ ] Command Auto-completion
- [ ] Multi-threaded Command Execution
- [ ] Command Aliases
- [ ] Replace `Scanner` with `BufferedReader`
- [ ] Refactor into multiple classes for better architecture
- [ ] Cross-platform support (Windows, Linux, macOS)

---

## 🎯 Why This Project?

Most Java applications abstract away operating system interactions. This project focuses on understanding what happens underneath by implementing the core behavior of a shell using Java's standard libraries.

It serves as a practical introduction to:

- Operating Systems
- Process Execution
- Terminal Applications
- File System APIs
- System Programming with Java

---

### README for Game 2048 Project 🎮🎲

Welcome to the **Game 2048 Project**! This project is a Java-based implementation of the classic **2048 puzzle game**, where the goal is to combine tiles of the same value to reach the **2048 tile**. Below, you'll find details about the game, how to play it, and how to set it up locally. 🌟

---

## ✨ Features
- 🧩 Fully functional **2048 game implementation**.
- 🕹️ Option to play as a **Human Player**.
- 🤖 Framework in place for a **Computer Player AI** (to be implemented in the future).
- ✅ **Unit tests** to verify the correctness of the game logic and functionality.
- 💻 Simple and intuitive **GUI** for user interaction.

---

## 🛠️ Project Structure
Here's how the project is organized:
```
GAME_2048/
├── bin/                      # Compiled Java class files
├── src/
│   ├── ttfe/
│   │   ├── publictests/      # Public unit tests
│   │   ├── tests/            # Additional test cases
│   │   │   ├── SimpleTests.java
│   │   │   ├── ComputerPlayer.java
│   │   │   ├── GameImplementation.java
│   │   │   ├── GUI.java
│   │   │   ├── HumanPlayer.java
│   │   │   ├── MoveDirection.java
│   │   │   ├── PlayerInterface.java
│   │   │   ├── SimulatorInterface.java
│   │   │   ├── TTFE.java      # Main entry point
│   │   │   ├── TTFEFactory.java
│   │   │   └── UserInterface.java
│   │   └── ...
├── .classpath               # Eclipse project configuration
├── .project                 # Eclipse project configuration
```

### Key Files
- **`TTFE.java`**: Main entry point for the game. Run this file to start the game.
- **`GameImplementation.java`**: Contains the core implementation of the game logic.
- **`tests/`**: Includes unit tests to ensure game functionality.

---

## 🚀 How to Run the Game Locally
1. **Clone the Repository** 🖥️  
   Clone this project to your local machine using:
   ```bash
   git clone <repository-url>
   ```
   
2. **Open in IDE** 🛠️  
   Import the project into your favorite IDE (e.g., **Eclipse**, **IntelliJ IDEA**, or **VS Code**).

3. **Compile the Code** 🖇️  
   Ensure you have Java Development Kit (JDK) installed (version 8 or later). Compile the project by building it in your IDE or using the terminal:
   ```bash
   javac -d bin src/ttfe/*.java
   ```

4. **Run the Game** ▶️  
   Execute the `TTFE.java` file.  
   In VS Code or other supported IDEs, you can right-click `TTFE.java` and select **Run Java**.

5. **Choose Your Options** 🖱️  
   A prompt will appear asking for:
   - The **width** of the board.
   - The **height** of the board.
   - Whether you want to play as a **Human Player** or allow the (future) **Computer Player**.

   Once you enter the details, the game will begin! 🎉

---

## 🎮 How to Play the Game
- Use your **arrow keys** to slide the tiles up, down, left, or right.
- Tiles of the same value will **merge** when they collide.
- Try to reach the **2048 tile** to win! 🌟
- The game ends when there are no more valid moves.

---

## 🛡️ Unit Testing
The project includes unit tests located in the `tests/` directory:
- **SimpleTests.java**: Basic tests for game logic.
- **GameImplementation.java**: Ensures the core mechanics are functioning as expected.

To run the tests, execute them via your IDE's test runner or a build tool like Maven or Gradle.

---

## 🚧 Future Plans
- **AI Player** 🤖: Implementing a smart Computer Player using AI algorithms.
- **Enhanced GUI** 🎨: Improving the user interface for better gameplay experience.

---

## 🏗️ Contributing
Contributions are welcome! Feel free to fork the repository and submit pull requests. 🙌

---

Happy playing! 🎉 If you have any questions or suggestions, feel free to reach out. 😊

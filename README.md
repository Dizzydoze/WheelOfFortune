# WheelOfFortune

<p align="center">
    <img src="https://github.com/Dizzydoze/WheelOfFortune/assets/106438058/cda87041-6d7d-44f4-ba26-575f767ee865">
</p>

- **Description**: A game of guessing a hidden phrase. Players get multiple chances to guess only one character at a time for reviewing the whole hidden phrase. Players win by successfully guess the entire hidden phrase. Players lose by guessing wrong characters and losing all their chances.
- **Repo**: https://github.com/Dizzydoze/WheelOfFortune
- **Development process**:
    - **Process**: The whole project has been refactored with inheritance and interfaces. The idea of design is basically divided the function into 3 parts. First, the abstract class of Game, the MasterMindGame and WheelOfFortuneGame both inherit from it and create their own game logic. Second, the class of Player, AI and interactive players will try to get their own guess phrase which is processed by the process logics in each game. Final part is Record, it simple help tracing the record of each player and do some calculation to get the TOP K players or average scores.
    - **Bugs**: Inheritance from abstract classes and Implementation from interfaces requires to implement all the abstract functions, or there will be compile errors.
    - **Concepts**: When it comes to OOP, it’s mostly about designing rather than just coding. Try to draw out a diagram for the relationship among all the classes and interfaces, and how the functions being called. Only start to code after we get full understanding of the logic in the whole project.

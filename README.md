# Nonogram_project
Nonogram project for Java and Git course

This project is a simple implementation of a 5x5 Nonogram puzzle game. The release version 1.0 features an incomplete version of the final project in which the progress grid is not formatted properly and the solution checking is not functional. Apart from that, there are several possible future improvements saved for further releases:

- Implementing different grid sizes for different difficulty levels.
- Implementing a maximum number of tries before failing the level.
- Make multiple nonogram options for each difficulty level.
- Add javadoc.

The program structure is as follows:
  - Classes: One main class for the general code and a class for the nonogram.
  - A matrix is used for the nonogram data structure.
  - Two arrays are used for the hints corresponding to each row and column.
  - Final and static types are used for variable and methods corresponding to inmutable data like the solution and the hints.
  - An interactive menu is used to start the game, stop it and to see the final solution. Further functionality like selecting difficulty level should be shown here.
  - Control flow: Basic loops and if-else blocks are used. In general, nested for loops are used for traversing the matrices and while loops are used to control whether the       game is still ongoing. A switch case is used for the interactive menu.

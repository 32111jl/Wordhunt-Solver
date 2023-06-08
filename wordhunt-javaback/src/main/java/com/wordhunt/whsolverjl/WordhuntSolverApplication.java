package com.wordhunt.whsolverjl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;
import java.util.List;

/**
 * Main class that starts Spring Boot app.
 * `main` method is the entry point of the app.
 * Initializes Spring Boot app, sets up web server to listen for requests.
 */
@SpringBootApplication
public class WordhuntSolverApplication {

	public static void main (String[] args) {
    // Scanner kb = new Scanner(System.in);
    // System.out.println("Enter the 16 letters of the board, from left to right: ");
    // String str = kb.nextLine();
    // String str = "adukwetobpltuwar";

    SpringApplication.run(WordhuntSolverApplication.class, args);
  }

  public void processBoard(String boardString) {
    char[][] board = convertBoard(boardString);

    long startTime = System.currentTimeMillis(); //currentTimeMillis returns start time

    WordHuntSolver solver = new WordHuntSolver(board);
    solver.recursiveSolver();

    long endTime = System.currentTimeMillis();
    long elapsedTime = endTime - startTime;
    
    List<String> sortedList = solver.sortFoundWords();
    for (String word : sortedList) {
      System.out.println(word);
    }

    System.out.println("Finished in " + elapsedTime + " ms!");
  }

  public static char[][] convertBoard(String input) {
    if (input.length() != 16) {
      throw new IllegalArgumentException("Input must be 16 characters long!");
    }

    char[][] board = new char[4][4];

    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board.length; j++) {
        board[i][j] = input.charAt(i * 4 + j); // i*4 + j calculates the index of the flat-1D array
      }
    }
    return board;
  }
}
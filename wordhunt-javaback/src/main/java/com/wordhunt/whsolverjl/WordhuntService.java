package com.wordhunt.whsolverjl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WordhuntService {

  public List<String> processBoard(String boardString) {
    char[][] board = convertBoard(boardString);

    long startTime = System.currentTimeMillis();

    WordHuntSolver solver = new WordHuntSolver(board);
    solver.recursiveSolver();

    long endTime = System.currentTimeMillis();
    long elapsedTime = endTime - startTime;

    List<String> sortedList = solver.sortFoundWords();
    // for (String word : sortedList) {
    //     System.out.println(word);
    // }

    System.out.println("Finished in " + elapsedTime + " ms!");

    return sortedList;
  }

  public static char[][] convertBoard(String input) {
    if (input.length() != 16) {
      throw new IllegalArgumentException("Input must be 16 characters long!");
    }

    char[][] board = new char[4][4];

    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board.length; j++) {
        board[i][j] = input.charAt(i * 4 + j);
      }
    }
    return board;
  }
}
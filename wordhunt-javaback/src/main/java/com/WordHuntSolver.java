package com.wordhunt.whsolverjl;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
// import java.util.Scanner;
// import java.util.Set;
// import java.util.HashSet;
// import java.util.List;
// import java.util.ArrayList;
// import java.util.Comparator;
import java.util.*;

public class WordHuntSolver {
  private char[][] board; // initial 4x4 board
  private Set<String> words; // set of words from words.txt
  private Set<String> foundWords; // set of valid words from board

  public WordHuntSolver(char[][] board) {
    this.board = board;
    this.words = new HashSet<>();
    this.foundWords = new HashSet<>();

    try {
      List<String> lines = Files.readAllLines(Paths.get("./src/main/resources/words.txt"));
      for (String line : lines) {
        words.add(line);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // public static char[][] convertBoard(String input) {
  //   if (input.length() != 16) {
  //     throw new IllegalArgumentException("Input must be 16 characters long!");
  //   }

  //   char[][] board = new char[4][4];

  //   for (int i = 0; i < board.length; i++) {
  //     for (int j = 0; j < board.length; j++) {
  //       board[i][j] = input.charAt(i * 4 + j); // i*4 + j calculates the index of the flat-1D array
  //     }
  //   }
  //   return board;
  // }

  public void recursiveSolver() {
    boolean[][] visited = new boolean[4][4];
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board.length; j++) {
        recursiveSolver(i, j, "", visited);
      }
    }
    // System.out.println("done");
  }

  private void recursiveSolver(int i, int j, String formedWord, boolean[][] visited) {
    if (i < 0 || i >= 4 || j < 0 || j >= 4) {
      return;
    }
    if (visited[i][j]) {
      return;
    }

    formedWord += board[i][j]; // add current letter to the temporary formed word
    visited[i][j] = true; // mark current letter as visited

    // if (!hasPrefix(formedWord)) { // if formedWord isn't a valid prefix, any other resulting words won't work either
    //   visited[i][j] = false; // unmark current letter
    //   return;
    // }

    if (words.contains(formedWord)) {
      foundWords.add(formedWord);
    }

    int[] rowDirections = {1, 0, -1, 0, 1, -1, 1, -1}; // (1, 0) = move one step down, (0, -1) = move one step left
    int[] colDirections = {0, 1, 0, -1, -1, 1, 1, -1};

    for (int k = 0; k < 8; k++) {
      int newRow = i + rowDirections[k];
      int newCol = j + colDirections[k];
      if (newRow >= 0 && newRow < 4 && newCol >= 0 && newCol < 4 && !visited[newRow][newCol]) {
        // check if newRow/newCol are within bounds and not visited
        recursiveSolver(newRow, newCol, formedWord, visited);
      }
    }
    visited[i][j] = false; // for backtracking purposes
  }

  // private boolean hasPrefix(String prefix) {
  //   for (String word : words) {
  //     if (word.startsWith(prefix)) {
  //       return true;
  //     }
  //   }
  //   return false;
  // }

  public List<String> sortFoundWords() { // sort by length and alphabetically
    List<String> sorted = new ArrayList<>(foundWords);

    Comparator<String> comparator = new Comparator<String>() {
      @Override
      public int compare(String word1, String word2) {
        int lengthCompare = word2.length() - word1.length(); // longer words first
        if (lengthCompare != 0) {
          return lengthCompare;
        }
  
        return word1.compareToIgnoreCase(word2);
      }
    };
    sorted.sort(comparator);
    return sorted;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (char[] row : board) {
      for (char c : row) {
        sb.append(c).append(" ");
      }
      sb.append("\n");
    }
    return sb.toString();
  }
}
package com.example.tictactoe;

public class Game {
    private String[] board;
    private String currentPlayer;
    private String winner;
    private boolean isGameOver;

    public Game() {
        reset();
    }

    public void reset() {
        board = new String[]{"", "", "", "", "", "", "", "", ""};
        currentPlayer = "X";
        winner = null;
        isGameOver = false;
    }

    public void makeMove(int index) {
        // Prevent move if game is over or cell is already taken
        if (isGameOver || index < 0 || index > 8 || !board[index].isEmpty()) {
            return;
        }

        board[index] = currentPlayer;
        checkWinner();

        // Switch player if game is not over
        if (!isGameOver) {
            currentPlayer = currentPlayer.equals("X") ? "O" : "X";
        }
    }

    private void checkWinner() {
        int[][] winConditions = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // Rows
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // Columns
            {0, 4, 8}, {2, 4, 6}             // Diagonals
        };

        for (int[] condition : winConditions) {
            if (!board[condition[0]].isEmpty() &&
                board[condition[0]].equals(board[condition[1]]) &&
                board[condition[0]].equals(board[condition[2]])) {
                winner = board[condition[0]];
                isGameOver = true;
                return;
            }
        }

        // Check for Draw
        boolean isFull = true;
        for (String cell : board) {
            if (cell.isEmpty()) {
                isFull = false;
                break;
            }
        }

        if (isFull) {
            isGameOver = true;
            winner = "Draw";
        }
    }

    // Getters are required for Spring Boot to convert this to JSON
    public String[] getBoard() { return board; }
    public String getCurrentPlayer() { return currentPlayer; }
    public String getWinner() { return winner; }
    public boolean isGameOver() { return isGameOver; }
}
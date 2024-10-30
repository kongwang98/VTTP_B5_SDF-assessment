package vttp.batch5.sdf.task02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    private char[][] board;

    public Main(String filePath) {
        board = new char[3][3];
        readBoardFromFile(filePath);
    }

    private void readBoardFromFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            for (int i = 0; i < 3; i++) {
                String line = br.readLine();
                board[i] = line.toCharArray();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printBoard() {
        System.out.println("Current Board:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void evaluateOpenPositions() {
        System.out.println("Utility of open positions:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '.') { 
                    board[i][j] = 'x'; 
                    int utility = evaluate(); 
                    board[i][j] = '.'; // Reset position
                    System.out.printf("Position (%d, %d): Utility = %d%n", i, j, utility);
                }
            }
        }
    }

    private int evaluate() {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                if (board[i][0] == 'x') return 1; 
                if (board[i][0] == 'o') return -1; 
            }
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                if (board[0][i] == 'x') return 1; 
                if (board[0][i] == 'o') return -1; 
            }
        }
       //check diagonal
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            if (board[0][0] == 'x') return 1; 
            if (board[0][0] == 'o') return -1; 
        }
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            if (board[0][2] == 'x') return 1; 
            if (board[0][2] == 'o') return -1; 
        }

        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '.') {
                    return 0; 
                }
            }
        }

        return 0; 
    }

    public static void main(String[] args) {
        if (args.length != 1) {
			System.err.println("Error: Please provide board.txt file");
			return;
		}

		String fileName = args[0];
		File file = new File(fileName);
		if (!file.exists()){
			System.out.println("File does not exist");
		}
        Main game = new Main(fileName);
        game.printBoard(); 
        game.evaluateOpenPositions(); 
    }
}




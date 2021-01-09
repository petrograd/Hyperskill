package tictactoe;

import java.util.Scanner;

public class tooComplicatedOnStage2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        System.out.println(str.replaceAll("a", "b"));

//        TicTacToe game1 = new TicTacToe();
//        game1.getBattleField();


    }
}

class TicTacToe {
    int[][] battleField;
    int battleFieldSize = 3;
    TicTacToe() {
        battleField = new int[battleFieldSize][battleFieldSize];
    }
    public void getBattleField() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter cells: ");
        String firstInputLine = scanner.nextLine().toUpperCase();
        loadMatrix(firstInputLine);
        printBattleField();
    }

    private void printBattleField() {

        //print matrix
        System.out.println("---------");
        for (int k = 0; k < 3; k++) {
            System.out.print("| ");
            for (int l = 0; l < 3; l++) {
                char ch = ' ';
                if (battleField[k][l] == 1) {
                    ch = 'X';
                } else if (battleField[k][l] == 10) {
                    ch = 'O';
                }
                System.out.print(ch + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");

    }

    private void loadMatrix(String initialLine) {

        int i = -1;
        int row, col;

        for (int j = 0; j < initialLine.length(); j++) {
            char ch = initialLine.charAt(j);
            if (ch == '_' || ch == 'X' || ch == 'O') {
                i++;
                row = i / 3;
                col = i % 3;
                int in;
                if (ch == 'X') {
                    battleField[row][col] = 1;
                } else if (ch == 'O') {
                    battleField[row][col] = 10;
                }
            }

        }


    }


}

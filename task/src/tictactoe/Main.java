package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TicTacToe3 game1 = new TicTacToe3();
        game1.printBattleField();
      //  game1.getBattleField();
        game1.play();

    }
}

class TicTacToe3 {
    int[][] battleField;
    int battleFieldSize = 3;
    private final static Scanner scanner = new Scanner(System.in);
    TicTacToe3() {
        battleField = new int[battleFieldSize][battleFieldSize];
        //getBattleField();
    }
    public void getBattleField() {

        System.out.println("Enter cells: ");
        String firstInputLine = scanner.nextLine().toUpperCase();

//        String row1= firstInputLine.substring(0,3).replace("", " ").trim();
//        String row2 = firstInputLine.substring(3, 6).replace("", " ").trim();
//        String row3 = firstInputLine.substring(6, 9).replace("", " ").trim();
//
//        System.out.println("---------");
//        System.out.println("| "  +row1 + " |");
//        System.out.println("| "  +row2 + " |");
//        System.out.println("| "  +row3+  " |");
//        System.out.println("---------");
        loadbattleField(firstInputLine);
        printBattleField();
    }

    public void printBattleField() {

        //print battleField
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

    private void loadbattleField(String initialLine) {
        int row, col;
        for (int j = 0; j < initialLine.length(); j++) {
            char ch = initialLine.charAt(j);
            if (ch == '_' || ch == 'X' || ch == 'O') {
                row = j / 3;
                col = j % 3;
                if (ch == 'X') {
                    battleField[row][col] = 1;
                } else if (ch == 'O') {
                    battleField[row][col] = 10;
                }
            }
        }
    }
    private String analizeGameState() {
        int x = getNumberOfTurns(1);
        int y = getNumberOfTurns(10);
        if ((Math.abs(x - y) > 1) || (hasTreeInARow(10) && hasTreeInARow(1))) {
            return "Impossible";
        }
        if (hasTreeInARow(10)) {
            return "O wins";
        }
        if (hasTreeInARow(1)) {
            return "X wins";
        }
        if (hasNoEmptyCells()) {
            return "Draw";
        }
        return "Game not finished";
    }

    private int getNumberOfKeyCells(int key) {
        int cnt = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (battleField[i][j] == key) {
                    cnt++;
                }
            }
        }
        return cnt;

    }
    private int getNumberOfTurns(int key) {
        return getNumberOfKeyCells(key);
    }

    private boolean hasNoEmptyCells() {
        if (getNumberOfKeyCells(0) > 0) {
            return false;
        }
        return true;
    }

    private boolean hasTreeInARow(int key) {

        // check vertical
        boolean isVertical = false;
        for (int i = 0; i < 3; i++) {
            if (battleField[i][0] == key &&  battleField[i][1] == key && battleField[i][2] == key) {
                isVertical = true;
                break;
            }
        }
        // check horizontal
        boolean isHorizontal = false;
        for (int i = 0; i < 3; i++) {
            if (battleField[0][i] == key && battleField[1][i] == key && battleField[2][i] == key) {
                isHorizontal = true;
                break;
            }
        }
        boolean mainDiagonal = true;
        for (int i = 0; i < 3; i++) {
            mainDiagonal = mainDiagonal && battleField[i][i] == key;
        }
        boolean secondDiagonal = true;
        for (int i = 0; i < 3; i++) {
            secondDiagonal = secondDiagonal && battleField[i][2 - i] == key;

        }
        return isVertical || isHorizontal || mainDiagonal || secondDiagonal;
    }

    public void printState() {
        System.out.println(analizeGameState());
    }

    public void askCoordinates() {
        while (!getCoordinates()){
            
        }
    }

    private boolean getCoordinates() {
        System.out.print("Enter the coordinates: ");
        String str = scanner.nextLine();
        if (str.matches("\\d\\s\\d")) {
            String[] s = str.split(" ");
            int a = Integer.valueOf(s[0]); // row
            int b = Integer.valueOf(s[1]); // column
            if (a < 1 || a > 3 || b < 1 || b > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
                return false;
            }

            int r = a - 1;
            int c = b - 1;
//            System.out.println(r + " " + c);
            if (battleField[r][c] > 0) {
                System.out.println("This cell is occupied! Choose another one!");
                return false;
            } else {
                int countZero = count(10);
                int countX = count(1);
                if (countX > countZero) {
                    battleField[r][c] = 10;
                }
                else {
                    battleField[r][c] = 1;
                }

            }
            //System.out.println(a + " " + b);
            return true;
        } else {
            System.out.println("You should enter numbers!");
        }
        return false;

    }
    private int count(int i) {
        int cnt = 0;
        for (int j = 0; j < 3; j++) {
            for (int k = 0; k < 3; k++) {
                if (battleField[j][k] == i)
                    cnt++;
            }

        }
        return cnt;
    }

    public void play() {
        while (!hasNoEmptyCells()){
            askCoordinates();
            printBattleField();
            if (!"Game not finished".equals(analizeGameState()))
                break;
        }
        printState();
    }
}






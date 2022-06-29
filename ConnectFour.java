import java.util.Scanner;

public class ConnectFour {

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in); // declare an object of the Scanner class

        //first start by asking the user for what they wish the height and length of the board to be
        System.out.print("What would you like the height of the board to be? ");
        int height = userInput.nextInt();
        System.out.print("What would you like the length of the board to be? ");
        int length = userInput.nextInt();

        char[][] gameBoard = new char[height][length]; // new char[board row][board column]
        initializeBoard(gameBoard); // method call to initializeBoard()
        System.out.print("\nPlayer 1: x \n" + "Player 2: o\n"); // tell the players what their tokens are

        boolean gameOver = false; // keeps track of whether game is over or not
        int rowFilled; // keeps track of the row that a token is placed in
        boolean winnerFound = false;
        int numOfTurns = (height) * (length); // find the number of spots to be filled in the game board
        int turnsCompleted = 0; // keeps track of the spots filled

        while (gameOver == false) {
            if (gameOver == false) {
                System.out.print("Player 1: Which column would you like to choose? "); // player one places their token
                int playerOneInput = userInput.nextInt();
                rowFilled = insertChip(gameBoard, playerOneInput, 'x'); // method call to insertChip() and outputs row filled
                turnsCompleted++;
                winnerFound = checkIfWinner(gameBoard, playerOneInput, rowFilled, 'x'); // method call to checkIfWinner()
                if (winnerFound) { // if checkIfWinner() outputs true (meaning there was four in a row), set gameOver to true
                    System.out.println("Player 1 won the game!\n");
                    gameOver = true;
                }
            }
            if (gameOver == false) {
                System.out.print("Player 2: Which column would you like to choose? "); // player one places their token
                int playerTwoInput = userInput.nextInt();
                rowFilled = insertChip(gameBoard, playerTwoInput, 'o'); // method call to insertChip() and outputs row filled
                turnsCompleted++;
                winnerFound = checkIfWinner(gameBoard, playerTwoInput, rowFilled, 'o'); // method call to checkIfWinner()
                if (winnerFound) { // if checkIfWinner() outputs true (meaning there was four in a row), set gameOver to true
                    System.out.println("Player 2 won the game!\n");
                    gameOver = true;
                }
            }
            // check to see if there is a tie
            if (numOfTurns <= turnsCompleted) {
                for (int i = 0; i < gameBoard.length; i++) {
                    for (int j = 0; j < gameBoard[0].length; j++) {
                        if (gameBoard[i][j] == 'x' || gameBoard[i][j] == 'o') { // if all spots is filled with 'x' or 'o' and no winner is found, there is a tie
                            System.out.println("Draw. Nobody wins.\n");
                            gameOver = true;
                            break;
                        }
                        break;
                    }
                    break;
                }
                break;
            }
        }
    }

    public static void printBoard(char[][] array) { // printBoard() prints the board.
        for (int i = array.length - 1; i >= 0; i--) { // loops through all the rows
            for (int j = 0; j < array[0].length; j++) { // loops through all the columns
                System.out.print(array[i][j] + " "); // prints each element of the 2D array
            }
            System.out.println("");
        }
        System.out.println("");
    }

    public static void initializeBoard(char[][] array) { // initializeBoard() sets each spot in the array to “-”.
        for (int i = 0; i < array.length; i++) { // loops through all the rows
            for (int j = 0; j < array[0].length; j++) { // loops through all the columns
                array[i][j] = '-';
                System.out.print(array[i][j] + " "); // prints '-' as each element of the 2D array
            }
            System.out.println("");
        }
    }

    /*
    insertChip() places the token in the column that the user has chosen and will find the next available spot in that column if there are already
    tokens there. The row that the token is placed in is returned.
     */

    public static int insertChip(char[][] array, int col, char chipType) {
        int rowPlaced = 0;
        int row = 0; // keeps track of the row being evaluated
        for (int i = 1; i < array.length; i++) { // loop through each row
            if (array[row][col] == '-') { // places the chipType if '-' is present
                array[row][col] = chipType;
                break;
            }
            if (array[row][col] == 'x' || array[row][col] == 'o') {
                if (array[row + i][col] == '-') {
                    array[row + i][col] = chipType; // places the chipType of '-' is present in the spot above the 'x' or 'o'
                    rowPlaced += i;
                    row++;
                    break;
                }
                else {
                    continue;
                }
            }
        }
        printBoard(array); // method call to printBoard()
        return rowPlaced;
    }

    /*
    After a token is added, checkIfWinner() checks whether the token in this location, of the specified chip type, creates four in a row. Will
    return true if someone won, and false otherwise
     */

    public static boolean checkIfWinner(char[][] gameBoard, int col, int row, char chipType) {
        boolean createsFour = false;
        int counter = 0;

        while (createsFour == false) { // looks left to right (horizontal) for four 'x' or 'o' in a row
            for (int j = 0; j < gameBoard[0].length; j++) { // loops through from 0 to column length
                if (gameBoard[row][j] == chipType) {
                    counter++; // increments counter if the spot is filled with a token
                }
                else {
                    counter = 0;
                }
                if (counter == 4) { // if there is four in a row, sets createsFour to true
                    createsFour = true;
                    counter = 0;
                    break;
                }
            }
            counter = 0;
            break;
        }

        while (createsFour == false) { // looks up and down (vertical) for four 'x' or 'o' in a row
            for (int i = gameBoard.length - 1; i >= 0; i--) { // loops through from 0 to row length
                if (gameBoard[i][col] == chipType) {
                    counter++; // increments counter if the spot is filled with a token
                }
                else {
                    counter = 0;
                }
                if (counter == 4) { // if there is four in a row, sets createsFour to true
                    createsFour = true;
                    counter = 0;
                    break;
                }
            }
            counter = 0;
            break;
        }

        return createsFour;
    }
}


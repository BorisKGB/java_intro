package ru.java.study.lesson2.hw;

public class Task4 {
    /**
     * <p>(*) Отвалидировать доску судоку<br>
     * Требования:<br>
     * Объявлена доска 9 x 9 необходимо отвалидировать в соответствии с правилами<br>
     * 1. Каждая строка должна содержать цифру 1-9 без повторения<br>
     * 2. Каждая колонка должна содержать цифру 1-9 без повторения<br>
     * 3. Каждый под блок из 9 элементов 3 x 3 должна содержать цифру 1-9 без повторения<br>
     * Ограничения<br>
     * • board.length == 9<br>
     * • board[i].length == 9<br>
     * • board[i][j] значение число или '.'</p>
     * <p>Доска предзадана</p>
     *
     * <p><br>От себя добавил не валидные вариации досок</p>
     */
    public static void main(String[] args) {
        char[][] board = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
        char[][] boardInvalidLine = {
                {'5', '3', '.', '.', '7', '.', '5', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
        char[][] boardInvalidBlock = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '3', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
        System.out.println("Validate: " + isValidSudoku(board));
        System.out.println("Validate2: " + isValidSudoku(boardInvalidLine));
        System.out.println("Validate3: " + isValidSudoku(boardInvalidBlock));
    }

    /**
     * @param board sudoku board to validate
     * @return validation result(boolean)
     */
    private static boolean isValidSudoku(char[][] board) {
        boolean boardValid = true;
        int i = 0;
        while (boardValid && i < board.length) {
            boardValid = boardValid && validateLine(board, i, true);
            boardValid = boardValid && validateLine(board, i, false);
            if (i % 3 == 0){
                boardValid = boardValid && validateBlocksStartsOnLine(board, i);
            }
            i++;
        }
        return boardValid;
    }

    /**
     * @param board where to validate blocks
     * @param lineCoordinate Line on which starts blocks to validate
     * @return validation result(boolean)
     */
    private static boolean validateBlocksStartsOnLine(char[][] board, int lineCoordinate) {
        boolean boardValid = true;
        int j = 0;
        while (boardValid && j < 3) {
            boardValid = validateBlock(board, lineCoordinate, j*3);
            j++;
        }
        return boardValid;
    }

    /**
     * @param board where to validate block
     * @param startX block start X coordinate
     * @param startY block start Y coordinate
     * @return validation result(boolean)
     */
    private static boolean validateBlock(char[][] board, int startX, int startY) {
        int[] charCounter = new int[board.length];
        boolean blockUnique = true;
        int i = 0;
        while (blockUnique && i < 3) {
            int j = 0;
            while (blockUnique && j < 3) {
                char field = board[startX+i][startY+j];
                if (field != '.') {
                    //todo: may be there is some better way to check unique values
                    int charVal = Integer.parseInt(String.valueOf(field))-1;
                    charCounter[charVal]++;
                    if (charCounter[charVal] > 1) blockUnique = false;
                }
                j++;
            }
            i++;
        }
        return blockUnique;
    }

    /**
     * Validate horizontal or vertical line of board
     * @param lineCoordinate point where line starts on board, must be on one of the edges
     * @param horizontal true to check lines, false to check columns
     * @return boolean. True if line is valid
     */
    private static boolean validateLine(char[][] board, int lineCoordinate, boolean horizontal) {
        int[] charCounter = new int[board.length];
        boolean lineUnique = true;
        int i = 0;
        while (lineUnique && i < board.length) {
            //todo: may be there is some better way to check unique values
            char field = (horizontal) ? board[lineCoordinate][i] : board[i][lineCoordinate];
            if (field != '.') {
                int charVal = Integer.parseInt(String.valueOf(field))-1;
                charCounter[charVal]++;
                if (charCounter[charVal] > 1) lineUnique = false;
            }
            i++;
        }
        return lineUnique;
    }

}

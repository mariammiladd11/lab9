/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication12;

/**
 *
 * @author CYBER-TECH
 */
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParallelMode27 extends SudukoChecker {
     public ParallelMode27(SudokuBoard board) {
        super(board);
    }
  @Override
    public VerificationResult validate() {
        VerificationResult result = new VerificationResult();
        ExecutorService executor = Executors.newFixedThreadPool(27);

        // 9 row checkers
        for (int r = 0; r < 9; r++) {
            int rowIndex = r;
            executor.execute(() -> {
                int[] row = board.getRow(rowIndex);
                var duplicates = DuplicateFinder.findDuplicates(row, rowIndex, 0);
                result.addRowDuplicates(rowIndex, duplicates);
            });
        }

        // 9 column checkers
        for (int c = 0; c < 9; c++) {
            int colIndex = c;
            executor.execute(() -> {
                int[] col = board.getCol(colIndex);
                var duplicates = DuplicateFinder.findDuplicates(col, 0, colIndex);
                result.addColDuplicates(colIndex, duplicates);
            });
        }

        // 9 box checkers
        for (int b = 0; b < 9; b++) {
            int boxIndex = b;
            executor.execute(() -> {
                int startRow = (boxIndex / 3) * 3;
                int startCol = (boxIndex % 3) * 3;
                int[] box = board.getBox(boxIndex);
                var duplicates = DuplicateFinder.findDuplicates(box, startRow, startCol);
                result.addBoxDuplicates(boxIndex, duplicates);
            });
        }

        executor.shutdown();
        while (!executor.isTerminated()) {}

        result.setValid(result.hasNoDuplicates());
        return result;
    }  
}


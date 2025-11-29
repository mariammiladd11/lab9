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

public class ParallelMode27 {
    public static DuplicateReport run(SudokuBoard board) {

        DuplicateReport report = new DuplicateReport();
        ExecutorService executor = Executors.newFixedThreadPool(27);

        // 9 row checkers
        for (int r = 0; r < 9; r++) {
            executor.execute(new RowChecker(board, r, report));
        }

        // 9 column checkers
        for (int c = 0; c < 9; c++) {
            executor.execute(new ColumnChecker(board, c, report));
        }

        // 9 box checkers
        for (int b = 0; b < 9; b++) {
            executor.execute(new BoxChecker(board, b, report));
        }

        executor.shutdown();
        while (!executor.isTerminated()) {}

        return report;
    }
}


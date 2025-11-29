/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication12;

/**
 *
 * @author CYBER-TECH
 */
import java.util.HashSet;
import java.util.Set;

public class RowChecker implements Runnable {
    private final SudokuBoard board;
    private final int row;
    private final DuplicateReport report;

    public RowChecker(SudokuBoard board, int row, DuplicateReport report) {
        this.board = board;
        this.row = row;
        this.report = report;
    }

    @Override
    public void run() {
        Set<Integer> seen = new HashSet<>();
        for (int col = 0; col < 9; col++) {
            int value = board.getValue(row, col);
            if (seen.contains(value)) {
                report.addIssue("Duplicate value " + value +
                        " found in ROW " + row + " at column " + col);
            }
            seen.add(value);
        }
    }
}


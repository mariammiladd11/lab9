/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication12;

/**
 *
 * @author MALAK
 */
public class CheckerFactory {
   
    
    public static SudukoChecker createChecker(String mode, SudokuBoard board) {
    switch (mode.toLowerCase()) {
        case "sequential":
            return new SequentialChecker(board);
        case "3-thread":
            return new ParallelMode3(board);
        case "27-thread":
            return new ParallelMode27(board);
        default:
            throw new IllegalArgumentException("Unknown mode: " + mode);
    }
}

}

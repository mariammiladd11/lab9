/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication12;

/**
 *
 * @author Linae
 */
public abstract class SudukoChecker {
    protected final SudokuBoard board;

    public SudukoChecker(SudokuBoard board) {
        this.board = board;
    }
    public abstract VerificationResult validate();
    
}

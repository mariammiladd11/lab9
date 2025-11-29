/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication12;

/**
 *
 * @author MALAK
 */
public class SudokuVerifier {
    public static void main(String[] args) {

        if (args.length < 2) {
            System.out.println("Usage: java -jar SudokuChecker.jar <mode> <file.txt>");
            System.out.println("Modes: sequential | 3-thread | 27-thread");
            return;
        }

        String mode = args[0];
        String filePath = args[1];

        try {
            
            SudokuBoard board = SudokuLoader.loadFromFile(filePath);

            
            SudukoChecker checker = CheckerFactory.createChecker(mode);

            
            VerificationResult result = checker.validate(board);

            
            System.out.print(OutputFormatter.format(result));

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
}

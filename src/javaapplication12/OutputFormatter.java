package javaapplication12;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author MALAK
 */
public class OutputFormatter {
    public static String format(VerificationResult result) {

        if (result.isValid()) {
            return "VALID\n";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("INVALID\n");

        if (!result.getRowDuplicates().isEmpty()) {
            sb.append("Row duplicates: ")
                    .append(result.getRowDuplicates())
                    .append("\n");
        }

        if (!result.getColDuplicates().isEmpty()) {
            sb.append("Column duplicates: ")
                    .append(result.getColDuplicates())
                    .append("\n");
        }

        if (!result.getBoxDuplicates().isEmpty()) {
            sb.append("Box duplicates: ")
                    .append(result.getBoxDuplicates())
                    .append("\n");
        }

        return sb.toString();
    }
}

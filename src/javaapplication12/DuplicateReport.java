/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication12;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author CYBER-TECH
 */

public class DuplicateReport {
    private final List<String> issues = Collections.synchronizedList(new ArrayList<>());

    public void addIssue(String issue) {
        issues.add(issue);
    }

    public List<String> getIssues() {
        return issues;
    }

    public boolean hasIssues() {
        return !issues.isEmpty();
    }
}

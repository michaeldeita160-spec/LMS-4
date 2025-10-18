package com.lms.dao;

import com.lms.model.Grade;
import com.lms.demo.DemoDataProvider;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Grade Data Access Object - Demo Mode Only (No Database)
 */
public class GradeDAO {

    private List<Grade> grades;

    public GradeDAO() {
        this.grades = DemoDataProvider.getGrades();
    }

    /**
     * Get all grades for a user
     */
    public List<Grade> getGradesByUser(int userId) {
        List<Grade> userGrades = new ArrayList<>();
        for (Grade grade : grades) {
            if (grade.getUserId() == userId) {
                userGrades.add(grade);
            }
        }
        return userGrades;
    }

    /**
     * Get grades for a specific course
     */
    public List<Grade> getGradesByCourse(int userId, int courseId) {
        List<Grade> courseGrades = new ArrayList<>();
        for (Grade grade : grades) {
            if (grade.getUserId() == userId && grade.getCourseId() == courseId) {
                courseGrades.add(grade);
            }
        }
        return courseGrades;
    }

    /**
     * Add or update grade
     */
    public boolean saveGrade(Grade grade) {
        try {
            // Check if grade already exists
            for (int i = 0; i < grades.size(); i++) {
                Grade existing = grades.get(i);
                if (existing.getUserId() == grade.getUserId() &&
                    existing.getCourseId() == grade.getCourseId() &&
                    existing.getAssessmentType().equals(grade.getAssessmentType()) &&
                    existing.getAssessmentId() == grade.getAssessmentId()) {
                    // Update existing grade
                    grades.set(i, grade);
                    System.out.println("✓ Grade updated successfully!");
                    return true;
                }
            }
            
            // Add new grade
            int newId = grades.size() + 1;
            grade.setGradeId(newId);
            grade.setGradedAt(new Timestamp(System.currentTimeMillis()));
            grades.add(grade);
            System.out.println("✓ Grade added successfully!");
            return true;
        } catch (Exception e) {
            System.err.println("Grade save error - " + e.getMessage());
            return false;
        }
    }

    /**
     * Get grade by ID
     */
    public Grade getGradeById(int gradeId) {
        for (Grade grade : grades) {
            if (grade.getGradeId() == gradeId) {
                return grade;
            }
        }
        return null;
    }

    /**
     * Calculate average grade for a course
     */
    public double getAverageGrade(int userId, int courseId) {
        List<Grade> courseGrades = getGradesByCourse(userId, courseId);
        
        if (courseGrades.isEmpty()) {
            return 0.0;
        }
        
        double total = 0.0;
        for (Grade grade : courseGrades) {
            total += grade.getScore();
        }
        
        return total / courseGrades.size();
    }

    /**
     * Get letter grade from numeric score
     */
    public String getLetterGrade(double score) {
        if (score >= 90) return "A";
        if (score >= 80) return "B";
        if (score >= 70) return "C";
        if (score >= 60) return "D";
        return "F";
    }

    /**
     * Get overall GPA for user
     */
    public double calculateGPA(int userId) {
        List<Grade> userGrades = getGradesByUser(userId);
        
        if (userGrades.isEmpty()) {
            return 0.0;
        }
        
        double totalPoints = 0.0;
        int count = 0;
        
        for (Grade grade : userGrades) {
            totalPoints += convertToGPA(grade.getScore());
            count++;
        }
        
        return count > 0 ? totalPoints / count : 0.0;
    }

    private double convertToGPA(double score) {
        if (score >= 90) return 4.0;
        if (score >= 80) return 3.0;
        if (score >= 70) return 2.0;
        if (score >= 60) return 1.0;
        return 0.0;
    }
}


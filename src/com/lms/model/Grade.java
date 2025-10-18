package com.lms.model;

import java.sql.Timestamp;

/**
 * Grade Model Class
 */
public class Grade {
    private int gradeId;
    private int userId;
    private int courseId;
    private String courseName;
    private double totalScore;
    private String letterGrade;
    private String remarks;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    // Additional fields for quiz/assignment grading
    private String assessmentType;  // "Quiz" or "Assignment"
    private int assessmentId;
    private double score;
    private Timestamp gradedAt;
    
    // Constructors
    public Grade() {}
    
    public Grade(int gradeId, int userId, int courseId, double totalScore, String letterGrade) {
        this.gradeId = gradeId;
        this.userId = userId;
        this.courseId = courseId;
        this.totalScore = totalScore;
        this.letterGrade = letterGrade;
    }
    
    // Getters and Setters
    public int getGradeId() {
        return gradeId;
    }
    
    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }
    
    public int getUserId() {
        return userId;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public int getCourseId() {
        return courseId;
    }
    
    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
    
    public String getCourseName() {
        return courseName;
    }
    
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    
    public double getTotalScore() {
        return totalScore;
    }
    
    public void setTotalScore(double totalScore) {
        this.totalScore = totalScore;
    }
    
    public String getLetterGrade() {
        return letterGrade;
    }
    
    public void setLetterGrade(String letterGrade) {
        this.letterGrade = letterGrade;
    }
    
    public String getRemarks() {
        return remarks;
    }
    
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    
    public Timestamp getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
    
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getAssessmentType() {
        return assessmentType;
    }

    public void setAssessmentType(String assessmentType) {
        this.assessmentType = assessmentType;
    }

    public int getAssessmentId() {
        return assessmentId;
    }

    public void setAssessmentId(int assessmentId) {
        this.assessmentId = assessmentId;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Timestamp getGradedAt() {
        return gradedAt;
    }

    public void setGradedAt(Timestamp gradedAt) {
        this.gradedAt = gradedAt;
    }
}


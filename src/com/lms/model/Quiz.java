package com.lms.model;

import java.sql.Timestamp;

/**
 * Quiz Model Class
 */
public class Quiz {
    private int quizId;
    private int courseId;
    private int lessonId;
    private String quizTitle;
    private String description;
    private int totalMarks;
    private int passingMarks;
    private int durationMinutes;
    private Timestamp createdAt;
    
    // Constructors
    public Quiz() {}
    
    public Quiz(int quizId, String quizTitle, int totalMarks, int durationMinutes) {
        this.quizId = quizId;
        this.quizTitle = quizTitle;
        this.totalMarks = totalMarks;
        this.durationMinutes = durationMinutes;
    }
    
    // Getters and Setters
    public int getQuizId() {
        return quizId;
    }
    
    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }
    
    public int getCourseId() {
        return courseId;
    }
    
    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
    
    public int getLessonId() {
        return lessonId;
    }
    
    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }
    
    public String getQuizTitle() {
        return quizTitle;
    }
    
    public void setQuizTitle(String quizTitle) {
        this.quizTitle = quizTitle;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public int getTotalMarks() {
        return totalMarks;
    }
    
    public void setTotalMarks(int totalMarks) {
        this.totalMarks = totalMarks;
    }
    
    public int getPassingMarks() {
        return passingMarks;
    }
    
    public void setPassingMarks(int passingMarks) {
        this.passingMarks = passingMarks;
    }
    
    public int getDurationMinutes() {
        return durationMinutes;
    }
    
    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }
    
    public Timestamp getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
    
    @Override
    public String toString() {
        return quizTitle;
    }
}


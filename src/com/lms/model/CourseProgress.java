package com.lms.model;

/**
 * Course Progress Model Class
 * Used for displaying user progress in dashboard
 */
public class CourseProgress {
    private int courseId;
    private String courseName;
    private String courseCode;
    private int totalLessons;
    private int completedLessons;
    private double progressPercentage;
    private double averageScore;
    private String status; // active, completed, dropped
    
    // Constructors
    public CourseProgress() {}
    
    public CourseProgress(int courseId, String courseName, int totalLessons, 
                         int completedLessons, double averageScore) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.totalLessons = totalLessons;
        this.completedLessons = completedLessons;
        this.averageScore = averageScore;
        this.progressPercentage = totalLessons > 0 ? 
            (completedLessons * 100.0 / totalLessons) : 0;
    }
    
    // Getters and Setters
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
    
    public String getCourseCode() {
        return courseCode;
    }
    
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
    
    public int getTotalLessons() {
        return totalLessons;
    }
    
    public void setTotalLessons(int totalLessons) {
        this.totalLessons = totalLessons;
    }
    
    public int getCompletedLessons() {
        return completedLessons;
    }
    
    public void setCompletedLessons(int completedLessons) {
        this.completedLessons = completedLessons;
    }
    
    public double getProgressPercentage() {
        return progressPercentage;
    }
    
    public void setProgressPercentage(double progressPercentage) {
        this.progressPercentage = progressPercentage;
    }
    
    public double getAverageScore() {
        return averageScore;
    }
    
    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
}


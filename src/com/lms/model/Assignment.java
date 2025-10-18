package com.lms.model;

import java.sql.Timestamp;

/**
 * Assignment Model Class
 */
public class Assignment {
    private int assignmentId;
    private int courseId;
    private int lessonId;
    private String assignmentTitle;
    private String description;
    private int totalMarks;
    private Timestamp dueDate;
    private Timestamp createdAt;
    
    // Constructors
    public Assignment() {}
    
    public Assignment(int assignmentId, String assignmentTitle, String description, 
                     int totalMarks, Timestamp dueDate) {
        this.assignmentId = assignmentId;
        this.assignmentTitle = assignmentTitle;
        this.description = description;
        this.totalMarks = totalMarks;
        this.dueDate = dueDate;
    }
    
    // Getters and Setters
    public int getAssignmentId() {
        return assignmentId;
    }
    
    public void setAssignmentId(int assignmentId) {
        this.assignmentId = assignmentId;
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
    
    public String getAssignmentTitle() {
        return assignmentTitle;
    }
    
    public void setAssignmentTitle(String assignmentTitle) {
        this.assignmentTitle = assignmentTitle;
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
    
    public Timestamp getDueDate() {
        return dueDate;
    }
    
    public void setDueDate(Timestamp dueDate) {
        this.dueDate = dueDate;
    }
    
    public Timestamp getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
    
    @Override
    public String toString() {
        return assignmentTitle;
    }
}


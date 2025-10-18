package com.lms.model;

import java.sql.Timestamp;

/**
 * Lesson Model Class
 */
public class Lesson {
    private int lessonId;
    private int courseId;
    private String lessonTitle;
    private String lessonContent;
    private int lessonOrder;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private boolean completed; // For tracking user progress
    
    // Constructors
    public Lesson() {}
    
    public Lesson(int lessonId, String lessonTitle, String lessonContent) {
        this.lessonId = lessonId;
        this.lessonTitle = lessonTitle;
        this.lessonContent = lessonContent;
    }
    
    // Getters and Setters
    public int getLessonId() {
        return lessonId;
    }
    
    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }
    
    public int getCourseId() {
        return courseId;
    }
    
    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
    
    public String getLessonTitle() {
        return lessonTitle;
    }
    
    public void setLessonTitle(String lessonTitle) {
        this.lessonTitle = lessonTitle;
    }
    
    public String getLessonContent() {
        return lessonContent;
    }
    
    public void setLessonContent(String lessonContent) {
        this.lessonContent = lessonContent;
    }
    
    public int getLessonOrder() {
        return lessonOrder;
    }
    
    public void setLessonOrder(int lessonOrder) {
        this.lessonOrder = lessonOrder;
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
    
    public boolean isCompleted() {
        return completed;
    }
    
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
    
    @Override
    public String toString() {
        return lessonTitle;
    }
}


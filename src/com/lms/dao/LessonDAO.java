package com.lms.dao;

import com.lms.model.Lesson;
import com.lms.demo.DemoDataProvider;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Lesson Data Access Object - Demo Mode Only (No Database)
 */
public class LessonDAO {

    private List<Lesson> lessons;
    private Map<String, Set<Integer>> lessonProgress;

    public LessonDAO() {
        this.lessons = DemoDataProvider.getLessons();
        this.lessonProgress = DemoDataProvider.getLessonProgress();
    }

    /**
     * Get lessons by course ID
     */
    public List<Lesson> getLessonsByCourse(int courseId) {
        List<Lesson> courseLessons = new ArrayList<>();
        for (Lesson lesson : lessons) {
            if (lesson.getCourseId() == courseId) {
                courseLessons.add(lesson);
            }
        }
        return courseLessons;
    }

    /**
     * Get lessons by course with user progress (overloaded method)
     */
    public List<Lesson> getLessonsByCourse(int courseId, int userId) {
        // For demo mode, just return all lessons for the course
        // Progress is tracked separately
        return getLessonsByCourse(courseId);
    }

    /**
     * Get lesson by ID
     */
    public Lesson getLessonById(int lessonId) {
        for (Lesson lesson : lessons) {
            if (lesson.getLessonId() == lessonId) {
                return lesson;
            }
        }
        return null;
    }

    /**
     * Mark lesson as completed
     */
    public boolean markLessonCompleted(int userId, int lessonId) {
        try {
            String key = userId + "_" + lessonId;
            Set<Integer> completed = lessonProgress.get(key);
            if (completed == null) {
                completed = new HashSet<>();
                lessonProgress.put(key, completed);
            }
            System.out.println("✓ Lesson marked as completed!");
            return true;
        } catch (Exception e) {
            System.err.println("Error marking lesson complete - " + e.getMessage());
            return false;
        }
    }

    /**
     * Check if lesson is completed
     */
    public boolean isLessonCompleted(int userId, int lessonId) {
        String key = userId + "_" + lessonId;
        return lessonProgress.containsKey(key);
    }

    /**
     * Get completed lessons count for a course
     */
    public int getCompletedLessonsCount(int userId, int courseId) {
        int count = 0;
        for (Lesson lesson : lessons) {
            if (lesson.getCourseId() == courseId && 
                isLessonCompleted(userId, lesson.getLessonId())) {
                count++;
            }
        }
        return count;
    }

    /**
     * Get total lessons count for a course
     */
    public int getTotalLessonsCount(int courseId) {
        int count = 0;
        for (Lesson lesson : lessons) {
            if (lesson.getCourseId() == courseId) {
                count++;
            }
        }
        return count;
    }

    /**
     * Get next lesson in course
     */
    public Lesson getNextLesson(int courseId, int currentLessonOrder) {
        for (Lesson lesson : lessons) {
            if (lesson.getCourseId() == courseId && 
                lesson.getLessonOrder() == currentLessonOrder + 1) {
                return lesson;
            }
        }
        return null;
    }

    /**
     * Get previous lesson in course
     */
    public Lesson getPreviousLesson(int courseId, int currentLessonOrder) {
        for (Lesson lesson : lessons) {
            if (lesson.getCourseId() == courseId && 
                lesson.getLessonOrder() == currentLessonOrder - 1) {
                return lesson;
            }
        }
        return null;
    }
}


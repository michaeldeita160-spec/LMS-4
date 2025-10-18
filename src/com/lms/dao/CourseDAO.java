package com.lms.dao;

import com.lms.model.Course;
import com.lms.model.CourseProgress;
import com.lms.demo.DemoDataProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Course Data Access Object - Demo Mode Only (No Database)
 */
public class CourseDAO {

    private List<Course> courses;
    private List<com.lms.model.Lesson> lessons;
    private Map<Integer, List<Integer>> enrollments;
    private Map<String, Set<Integer>> lessonProgress;
    private Map<String, Integer> quizAttempts;

    public CourseDAO() {
        this.courses = DemoDataProvider.getCourses();
        this.lessons = DemoDataProvider.getLessons();
        this.enrollments = DemoDataProvider.getEnrollments();
        this.lessonProgress = DemoDataProvider.getLessonProgress();
        this.quizAttempts = DemoDataProvider.getQuizAttempts();
    }

    /**
     * Get all courses
     */
    public List<Course> getAllCourses() {
        return new ArrayList<>(courses);
    }

    /**
     * Get enrolled courses for a user
     */
    public List<Course> getEnrolledCourses(int userId) {
        List<Course> enrolledCourses = new ArrayList<>();
        List<Integer> enrolledCourseIds = enrollments.get(userId);
        
        if (enrolledCourseIds != null) {
            for (Integer courseId : enrolledCourseIds) {
                Course course = getCourseById(courseId);
                if (course != null) {
                    enrolledCourses.add(course);
                }
            }
        }
        
        return enrolledCourses;
    }

    /**
     * Get course by ID
     */
    public Course getCourseById(int courseId) {
        for (Course course : courses) {
            if (course.getCourseId() == courseId) {
                return course;
            }
        }
        return null;
    }

    /**
     * Enroll user in course
     */
    public boolean enrollInCourse(int userId, int courseId) {
        try {
            List<Integer> userEnrollments = enrollments.get(userId);
            if (userEnrollments == null) {
                userEnrollments = new ArrayList<>();
                enrollments.put(userId, userEnrollments);
            }
            
            if (!userEnrollments.contains(courseId)) {
                userEnrollments.add(courseId);
                System.out.println("✓ Enrolled in course successfully!");
                return true;
            }
            
            System.out.println("Already enrolled in this course.");
            return false;
        } catch (Exception e) {
            System.err.println("Enrollment error - " + e.getMessage());
            return false;
        }
    }

    /**
     * Check if user is enrolled in course
     */
    public boolean isEnrolled(int userId, int courseId) {
        List<Integer> userEnrollments = enrollments.get(userId);
        return userEnrollments != null && userEnrollments.contains(courseId);
    }

    /**
     * Get course progress for user
     */
    public List<CourseProgress> getCourseProgress(int userId) {
        List<CourseProgress> progressList = new ArrayList<>();
        List<Integer> enrolledCourseIds = enrollments.get(userId);
        
        if (enrolledCourseIds != null) {
            for (Integer courseId : enrolledCourseIds) {
                Course course = getCourseById(courseId);
                if (course != null) {
                    CourseProgress progress = new CourseProgress();
                    progress.setCourseId(courseId);
                    progress.setCourseName(course.getCourseName());
                    
                    // Calculate progress
                    int totalLessons = countLessonsInCourse(courseId);
                    int completedLessons = countCompletedLessons(userId, courseId);
                    int progressPercentage = totalLessons > 0 ? 
                        (completedLessons * 100) / totalLessons : 0;
                    
                    progress.setProgressPercentage(progressPercentage);
                    
                    // Calculate average score
                    double avgScore = calculateAverageScore(userId, courseId);
                    progress.setAverageScore(avgScore);
                    
                    progressList.add(progress);
                }
            }
        }
        
        return progressList;
    }

    private int countLessonsInCourse(int courseId) {
        int count = 0;
        for (com.lms.model.Lesson lesson : lessons) {
            if (lesson.getCourseId() == courseId) {
                count++;
            }
        }
        return count;
    }

    private int countCompletedLessons(int userId, int courseId) {
        int count = 0;
        for (com.lms.model.Lesson lesson : lessons) {
            if (lesson.getCourseId() == courseId) {
                String key = userId + "_" + lesson.getLessonId();
                if (lessonProgress.containsKey(key)) {
                    count++;
                }
            }
        }
        return count;
    }

    private double calculateAverageScore(int userId, int courseId) {
        List<com.lms.model.Quiz> courseQuizzes = new ArrayList<>();
        for (com.lms.model.Quiz quiz : DemoDataProvider.getQuizzes()) {
            if (quiz.getCourseId() == courseId) {
                courseQuizzes.add(quiz);
            }
        }
        
        if (courseQuizzes.isEmpty()) {
            return 0.0;
        }
        
        int totalScore = 0;
        int quizCount = 0;
        
        for (com.lms.model.Quiz quiz : courseQuizzes) {
            String key = userId + "_" + quiz.getQuizId();
            Integer score = quizAttempts.get(key);
            if (score != null) {
                totalScore += score;
                quizCount++;
            }
        }
        
        return quizCount > 0 ? (double) totalScore / quizCount : 0.0;
    }

    /**
     * Search courses by keyword
     */
    public List<Course> searchCourses(String keyword) {
        List<Course> results = new ArrayList<>();
        String lowerKeyword = keyword.toLowerCase();
        
        for (Course course : courses) {
            if (course.getCourseName().toLowerCase().contains(lowerKeyword) ||
                course.getDescription().toLowerCase().contains(lowerKeyword)) {
                results.add(course);
            }
        }
        
        return results;
    }

    /**
     * Check if user is enrolled in a course
     */
    public boolean isUserEnrolled(int userId, int courseId) {
        List<Integer> enrolledCourseIds = enrollments.get(userId);
        return enrolledCourseIds != null && enrolledCourseIds.contains(courseId);
    }

    /**
     * Enroll user in a course
     */
    public boolean enrollUserInCourse(int userId, int courseId) {
        try {
            List<Integer> enrolledCourseIds = enrollments.get(userId);
            if (enrolledCourseIds == null) {
                enrolledCourseIds = new ArrayList<>();
                enrollments.put(userId, enrolledCourseIds);
            }

            if (!enrolledCourseIds.contains(courseId)) {
                enrolledCourseIds.add(courseId);
                System.out.println("User " + userId + " enrolled in course " + courseId);
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}


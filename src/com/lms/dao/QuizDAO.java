package com.lms.dao;

import com.lms.model.Quiz;
import com.lms.model.QuizQuestion;
import com.lms.demo.DemoDataProvider;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Quiz Data Access Object - Demo Mode Only (No Database)
 */
public class QuizDAO {

    private List<Quiz> quizzes;
    private List<QuizQuestion> quizQuestions;
    private Map<String, Integer> quizAttempts;
    private Map<String, List<QuizAttemptRecord>> attemptHistory;

    public QuizDAO() {
        this.quizzes = DemoDataProvider.getQuizzes();
        this.quizQuestions = DemoDataProvider.getQuizQuestions();
        this.quizAttempts = DemoDataProvider.getQuizAttempts();
        this.attemptHistory = new HashMap<>();
    }

    /**
     * Get quizzes by course ID
     */
    public List<Quiz> getQuizzesByCourse(int courseId) {
        List<Quiz> courseQuizzes = new ArrayList<>();
        for (Quiz quiz : quizzes) {
            if (quiz.getCourseId() == courseId) {
                courseQuizzes.add(quiz);
            }
        }
        return courseQuizzes;
    }

    /**
     * Get quiz by ID
     */
    public Quiz getQuizById(int quizId) {
        for (Quiz quiz : quizzes) {
            if (quiz.getQuizId() == quizId) {
                return quiz;
            }
        }
        return null;
    }

    /**
     * Get questions for a quiz
     */
    public List<QuizQuestion> getQuizQuestions(int quizId) {
        List<QuizQuestion> questions = new ArrayList<>();
        for (QuizQuestion question : quizQuestions) {
            if (question.getQuizId() == quizId) {
                questions.add(question);
            }
        }
        return questions;
    }

    /**
     * Submit quiz attempt
     */
    public boolean submitQuizAttempt(int quizId, int userId, double score, int totalQuestions) {
        try {
            String key = userId + "_" + quizId;
            int scoreInt = (int) Math.round(score);
            quizAttempts.put(key, scoreInt);

            // Store in attempt history
            List<QuizAttemptRecord> history = attemptHistory.get(key);
            if (history == null) {
                history = new ArrayList<>();
                attemptHistory.put(key, history);
            }

            Quiz quiz = getQuizById(quizId);
            boolean passed = quiz != null && scoreInt >= quiz.getPassingMarks();

            QuizAttemptRecord record = new QuizAttemptRecord(userId, quizId, scoreInt, totalQuestions, passed);
            history.add(record);

            System.out.println("✓ Quiz submitted successfully! Score: " + scoreInt + "/" + totalQuestions);
            return true;
        } catch (Exception e) {
            System.err.println("Quiz submission error - " + e.getMessage());
            return false;
        }
    }

    /**
     * Get quiz score for user
     */
    public Integer getQuizScore(int userId, int quizId) {
        String key = userId + "_" + quizId;
        return quizAttempts.get(key);
    }

    /**
     * Get quiz attempt history
     */
    public List<QuizAttemptRecord> getQuizAttemptHistory(int userId, int quizId) {
        String key = userId + "_" + quizId;
        List<QuizAttemptRecord> history = attemptHistory.get(key);
        return history != null ? new ArrayList<>(history) : new ArrayList<>();
    }

    /**
     * Check if user has attempted quiz
     */
    public boolean hasAttemptedQuiz(int userId, int quizId) {
        String key = userId + "_" + quizId;
        return quizAttempts.containsKey(key);
    }

    /**
     * Get all quizzes for a user (with scores)
     */
    public List<Quiz> getQuizzesWithScores(int userId, int courseId) {
        List<Quiz> courseQuizzes = getQuizzesByCourse(courseId);
        // In demo mode, just return the quizzes
        // Scores can be retrieved separately using getQuizScore()
        return courseQuizzes;
    }

    // Inner class for quiz attempt records
    public static class QuizAttemptRecord {
        public int userId;
        public int quizId;
        public int score;
        public int totalMarks;
        public int totalQuestions;
        public boolean passed;
        public Timestamp attemptDate;

        public QuizAttemptRecord(int userId, int quizId, int score, int totalMarks, boolean passed) {
            this.userId = userId;
            this.quizId = quizId;
            this.score = score;
            this.totalMarks = totalMarks;
            this.totalQuestions = totalMarks; // Assuming 1 mark per question
            this.passed = passed;
            this.attemptDate = new Timestamp(System.currentTimeMillis());
        }

        public int getScore() { return score; }
        public int getTotalQuestions() { return totalQuestions; }
        public Timestamp getAttemptDate() { return attemptDate; }
    }

    /**
     * Get quiz attempts for a user and course
     */
    public List<Object[]> getQuizAttempts(int userId, int courseId) {
        List<Object[]> attempts = new ArrayList<>();

        // Iterate through all attempt history entries
        for (Map.Entry<String, List<QuizAttemptRecord>> entry : attemptHistory.entrySet()) {
            for (QuizAttemptRecord record : entry.getValue()) {
                if (record.userId == userId) {
                    Quiz quiz = getQuizById(record.quizId);
                    if (quiz != null && quiz.getCourseId() == courseId) {
                        Object[] attempt = new Object[5];
                        attempt[0] = quiz.getQuizTitle();
                        attempt[1] = record.score;
                        attempt[2] = record.totalMarks;
                        attempt[3] = record.passed ? "Passed" : "Failed";
                        attempt[4] = record.attemptDate;
                        attempts.add(attempt);
                    }
                }
            }
        }

        return attempts;
    }
}


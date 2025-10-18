package com.lms.demo;

import com.lms.model.*;
import java.sql.Timestamp;
import java.util.*;

/**
 * Demo Data Provider - Provides sample in-memory data for demo mode
 * No database required!
 */
public class DemoDataProvider {
    
    private static List<User> users = new ArrayList<>();
    private static List<Course> courses = new ArrayList<>();
    private static List<Lesson> lessons = new ArrayList<>();
    private static List<Quiz> quizzes = new ArrayList<>();
    private static List<QuizQuestion> quizQuestions = new ArrayList<>();
    private static List<Grade> grades = new ArrayList<>();
    private static Map<Integer, List<Integer>> enrollments = new HashMap<>(); // userId -> courseIds
    private static Map<String, Set<Integer>> lessonProgress = new HashMap<>(); // "userId_lessonId" -> completed
    private static Map<String, Integer> quizAttempts = new HashMap<>(); // "userId_quizId" -> score
    
    static {
        initializeDemoData();
    }
    
    private static void initializeDemoData() {
        // Create demo users
        createDemoUsers();
        
        // Create demo courses
        createDemoCourses();
        
        // Create demo lessons
        createDemoLessons();
        
        // Create demo quizzes
        createDemoQuizzes();
        
        // Create demo quiz questions
        createDemoQuizQuestions();
        
        // Create demo enrollments
        createDemoEnrollments();
        
        // Create demo progress
        createDemoProgress();
        
        // Create demo grades
        createDemoGrades();
    }
    
    private static void createDemoUsers() {
        // Demo student - Only student account for demo mode
        User student = new User();
        student.setUserId(1);
        student.setUsername("demo_student");
        student.setPassword("XohImNooBHFR0OVvjcYpJ3NgPQ1qq73WKhHvch0VQtg="); // SHA-256 hash of "password"
        student.setEmail("student@demo.com");
        student.setFullName("Demo Student");
        student.setRole("student");
        student.setBio("I'm a demo student exploring the LMS!");
        student.setPhone("+1234567890");
        student.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        users.add(student);
    }
    
    private static void createDemoCourses() {
        Course c1 = new Course();
        c1.setCourseId(1);
        c1.setCourseName("Web Development Fundamentals");
        c1.setCourseCode("WEB101");
        c1.setDescription("Learn HTML, CSS, and JavaScript from scratch. Build modern, responsive websites.");
        c1.setTeacherId(2);
        c1.setTeacherName("Prof. Demo Teacher");
        c1.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        courses.add(c1);
        
        Course c2 = new Course();
        c2.setCourseId(2);
        c2.setCourseName("Database Management Systems");
        c2.setCourseCode("DB201");
        c2.setDescription("Master SQL, database design, and data management concepts.");
        c2.setTeacherId(2);
        c2.setTeacherName("Prof. Demo Teacher");
        c2.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        courses.add(c2);
        
        Course c3 = new Course();
        c3.setCourseId(3);
        c3.setCourseName("Python Programming");
        c3.setCourseCode("PY101");
        c3.setDescription("Learn Python programming from basics to advanced topics including OOP.");
        c3.setTeacherId(2);
        c3.setTeacherName("Prof. Demo Teacher");
        c3.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        courses.add(c3);
        
        Course c4 = new Course();
        c4.setCourseId(4);
        c4.setCourseName("Digital Marketing Essentials");
        c4.setCourseCode("MKT101");
        c4.setDescription("Learn SEO, social media marketing, and digital advertising strategies.");
        c4.setTeacherId(2);
        c4.setTeacherName("Prof. Demo Teacher");
        c4.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        courses.add(c4);
        
        Course c5 = new Course();
        c5.setCourseId(5);
        c5.setCourseName("Data Structures & Algorithms");
        c5.setCourseCode("CS301");
        c5.setDescription("Master fundamental data structures and algorithmic problem-solving.");
        c5.setTeacherId(2);
        c5.setTeacherName("Prof. Demo Teacher");
        c5.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        courses.add(c5);
    }
    
    private static void createDemoLessons() {
        // Web Development lessons
        lessons.add(createLesson(1, 1, "Introduction to HTML", "Learn the basics of HTML structure, tags, and elements.", 1));
        lessons.add(createLesson(2, 1, "CSS Styling Basics", "Master CSS selectors, properties, and styling techniques.", 2));
        lessons.add(createLesson(3, 1, "JavaScript Fundamentals", "Introduction to JavaScript variables, functions, and DOM manipulation.", 3));
        lessons.add(createLesson(4, 1, "Responsive Web Design", "Learn to create mobile-friendly websites using media queries.", 4));
        
        // Database lessons
        lessons.add(createLesson(5, 2, "Introduction to Databases", "Understanding database concepts and relational models.", 1));
        lessons.add(createLesson(6, 2, "SQL SELECT Statements", "Learn to query data using SELECT, WHERE, and ORDER BY.", 2));
        lessons.add(createLesson(7, 2, "Database Design", "Master normalization and entity-relationship diagrams.", 3));
        lessons.add(createLesson(8, 2, "Advanced SQL Joins", "Learn INNER, OUTER, and CROSS joins.", 4));
        
        // Python lessons
        lessons.add(createLesson(9, 3, "Python Basics", "Variables, data types, and basic operations in Python.", 1));
        lessons.add(createLesson(10, 3, "Control Flow", "If statements, loops, and conditional logic.", 2));
        lessons.add(createLesson(11, 3, "Functions and Modules", "Creating reusable code with functions and modules.", 3));
        lessons.add(createLesson(12, 3, "Object-Oriented Programming", "Classes, objects, inheritance, and polymorphism.", 4));
        
        // Digital Marketing lessons
        lessons.add(createLesson(13, 4, "Digital Marketing Overview", "Introduction to digital marketing channels and strategies.", 1));
        lessons.add(createLesson(14, 4, "SEO Fundamentals", "Search engine optimization techniques and best practices.", 2));
        lessons.add(createLesson(15, 4, "Social Media Marketing", "Leveraging social platforms for business growth.", 3));
        
        // Data Structures lessons
        lessons.add(createLesson(16, 5, "Arrays and Lists", "Understanding linear data structures.", 1));
        lessons.add(createLesson(17, 5, "Stacks and Queues", "LIFO and FIFO data structures.", 2));
        lessons.add(createLesson(18, 5, "Trees and Graphs", "Hierarchical and network data structures.", 3));
    }
    
    private static Lesson createLesson(int id, int courseId, String title, String content, int order) {
        Lesson lesson = new Lesson();
        lesson.setLessonId(id);
        lesson.setCourseId(courseId);
        lesson.setLessonTitle(title);
        lesson.setLessonContent(content);
        lesson.setLessonOrder(order);
        lesson.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        return lesson;
    }
    
    private static void createDemoQuizzes() {
        quizzes.add(createQuiz(1, 1, 1, "HTML Basics Quiz", "Test your HTML knowledge", 20, 12, 15));
        quizzes.add(createQuiz(2, 1, 2, "CSS Fundamentals Quiz", "Assess your CSS skills", 20, 12, 15));
        quizzes.add(createQuiz(3, 1, 3, "JavaScript Basics Quiz", "Evaluate your JavaScript knowledge", 25, 15, 20));
        quizzes.add(createQuiz(4, 2, 6, "SQL SELECT Quiz", "Test your SQL query skills", 30, 18, 25));
        quizzes.add(createQuiz(5, 3, 9, "Python Basics Quiz", "Test your Python fundamentals", 20, 12, 15));
    }
    
    private static Quiz createQuiz(int id, int courseId, int lessonId, String title, String desc, int total, int passing, int duration) {
        Quiz quiz = new Quiz();
        quiz.setQuizId(id);
        quiz.setCourseId(courseId);
        quiz.setLessonId(lessonId);
        quiz.setQuizTitle(title);
        quiz.setDescription(desc);
        quiz.setTotalMarks(total);
        quiz.setPassingMarks(passing);
        quiz.setDurationMinutes(duration);
        quiz.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        return quiz;
    }
    
    private static void createDemoQuizQuestions() {
        // HTML Quiz questions
        quizQuestions.add(createQuestion(1, 1, "What does HTML stand for?", 
            "Hyper Text Markup Language", "High Tech Modern Language", 
            "Home Tool Markup Language", "Hyperlinks and Text Markup Language", "A", 5));
        quizQuestions.add(createQuestion(2, 1, "Which HTML tag is used for the largest heading?", 
            "<heading>", "<h6>", "<h1>", "<head>", "C", 5));
        quizQuestions.add(createQuestion(3, 1, "What is the correct HTML element for inserting a line break?", 
            "<break>", "<lb>", "<br>", "<newline>", "C", 5));
        quizQuestions.add(createQuestion(4, 1, "Which attribute is used to provide alternative text for an image?", 
            "title", "alt", "src", "longdesc", "B", 5));
        
        // CSS Quiz questions
        quizQuestions.add(createQuestion(5, 2, "What does CSS stand for?", 
            "Creative Style Sheets", "Cascading Style Sheets", 
            "Computer Style Sheets", "Colorful Style Sheets", "B", 5));
        quizQuestions.add(createQuestion(6, 2, "Which property is used to change the background color?", 
            "color", "bgcolor", "background-color", "bg-color", "C", 5));
        
        // JavaScript Quiz questions
        quizQuestions.add(createQuestion(7, 3, "Which keyword is used to declare a variable in JavaScript?", 
            "var", "variable", "v", "declare", "A", 5));
        quizQuestions.add(createQuestion(8, 3, "What is the correct syntax for a for loop?", 
            "for (i = 0; i < 5)", "for i = 1 to 5", 
            "for (i = 0; i < 5; i++)", "for (i < 5; i++)", "C", 5));
        
        // SQL Quiz questions
        quizQuestions.add(createQuestion(9, 4, "Which SQL statement is used to extract data from a database?", 
            "GET", "EXTRACT", "SELECT", "OPEN", "C", 10));
        quizQuestions.add(createQuestion(10, 4, "Which SQL keyword is used to sort the result-set?", 
            "SORT", "ORDER BY", "SORT BY", "ORDER", "B", 10));
        
        // Python Quiz questions
        quizQuestions.add(createQuestion(11, 5, "Which of the following is the correct extension of Python files?", 
            ".python", ".pl", ".py", ".p", "C", 5));
        quizQuestions.add(createQuestion(12, 5, "What is the output of print(2 ** 3)?", 
            "5", "6", "8", "9", "C", 5));
    }
    
    private static QuizQuestion createQuestion(int id, int quizId, String question, 
            String optA, String optB, String optC, String optD, String correct, int marks) {
        QuizQuestion q = new QuizQuestion();
        q.setQuestionId(id);
        q.setQuizId(quizId);
        q.setQuestionText(question);
        q.setOptionA(optA);
        q.setOptionB(optB);
        q.setOptionC(optC);
        q.setOptionD(optD);
        q.setCorrectAnswer(correct);
        q.setMarks(marks);
        return q;
    }
    
    private static void createDemoEnrollments() {
        // Enroll demo student in all courses
        List<Integer> studentCourses = Arrays.asList(1, 2, 3, 4, 5);
        enrollments.put(1, studentCourses);
    }
    
    private static void createDemoProgress() {
        // Mark some lessons as completed for demo student
        lessonProgress.put("1_1", new HashSet<>()); // Student 1, Lesson 1
        lessonProgress.put("1_2", new HashSet<>());
        lessonProgress.put("1_5", new HashSet<>());
        lessonProgress.put("1_9", new HashSet<>());
        
        // Add some quiz scores
        quizAttempts.put("1_1", 18); // Student 1, Quiz 1, Score 18/20
        quizAttempts.put("1_2", 15); // Student 1, Quiz 2, Score 15/20
        quizAttempts.put("1_5", 16); // Student 1, Quiz 5, Score 16/20
    }
    
    private static void createDemoGrades() {
        Grade g1 = new Grade();
        g1.setGradeId(1);
        g1.setUserId(1);
        g1.setCourseId(1);
        g1.setCourseName("Web Development Fundamentals");
        g1.setTotalScore(85.5);
        g1.setLetterGrade("B");
        g1.setRemarks("Good progress!");
        grades.add(g1);
    }
    
    // Getter methods
    public static List<User> getUsers() { return new ArrayList<>(users); }
    public static List<Course> getCourses() { return new ArrayList<>(courses); }
    public static List<Lesson> getLessons() { return new ArrayList<>(lessons); }
    public static List<Quiz> getQuizzes() { return new ArrayList<>(quizzes); }
    public static List<QuizQuestion> getQuizQuestions() { return new ArrayList<>(quizQuestions); }
    public static List<Grade> getGrades() { return new ArrayList<>(grades); }
    public static Map<Integer, List<Integer>> getEnrollments() { return new HashMap<>(enrollments); }
    public static Map<String, Set<Integer>> getLessonProgress() { return new HashMap<>(lessonProgress); }
    public static Map<String, Integer> getQuizAttempts() { return new HashMap<>(quizAttempts); }
}


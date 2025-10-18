package com.lms.demo;

/**
 * Demo Mode Manager - Always Enabled (No Database Mode)
 * This version is demo-only, no database required!
 */
public class DemoModeManager {
    
    // Always in demo mode for LMS-2
    private static final boolean DEMO_MODE = true;
    
    static {
        System.out.println("========================================");
        System.out.println("   LMS-2 DEMO MODE");
        System.out.println("   No database required!");
        System.out.println("   All data is in-memory only");
        System.out.println("========================================");
    }
    
    /**
     * Check if demo mode is enabled (always true)
     */
    public static boolean isDemoMode() {
        return DEMO_MODE;
    }
    
    /**
     * Get demo credentials info
     */
    public static String getDemoCredentials() {
        return "Demo Login Credentials:\n\n" +
               "Student Account:\n" +
               "  Username: demo_student\n" +
               "  Password: password\n\n" +
               "Note: This is a demo-only version.\n" +
               "Data will not be saved permanently.";
    }
    
    /**
     * Get welcome message
     */
    public static String getWelcomeMessage() {
        return "Welcome to LMS-2 Demo!\n\n" +
               "This is a standalone demo version with:\n" +
               "✓ No database setup required\n" +
               "✓ Pre-loaded sample data\n" +
               "✓ Full LMS features\n" +
               "✓ NetBeans .form files for easy GUI editing\n\n" +
               "Login with: demo_student / password";
    }
}


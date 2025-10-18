package com.lms.ui.components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Custom PasswordField with modern styling
 */
public class CustomPasswordField extends JPasswordField {
    private String placeholder;
    
    public CustomPasswordField() {
        super();
        initStyle();
    }
    
    public CustomPasswordField(int columns) {
        super(columns);
        initStyle();
    }
    
    public CustomPasswordField(String placeholder) {
        super();
        this.placeholder = placeholder;
        initStyle();
    }
    
    private void initStyle() {
        setFont(new Font("Segoe UI", Font.PLAIN, 14));
        setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            new EmptyBorder(8, 12, 8, 12)
        ));
        setBackground(Color.WHITE);
        setForeground(new Color(50, 50, 50));
    }
    
    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if (placeholder != null && getPassword().length == 0 && !isFocusOwner()) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(new Color(150, 150, 150));
            g2.setFont(getFont());
            
            FontMetrics fm = g2.getFontMetrics();
            int x = getInsets().left;
            int y = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();
            
            g2.drawString(placeholder, x, y);
            g2.dispose();
        }
    }
}


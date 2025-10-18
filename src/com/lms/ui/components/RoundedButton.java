package com.lms.ui.components;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

/**
 * Custom Rounded Button with modern styling
 */
public class RoundedButton extends JButton {
    private Color backgroundColor;
    private Color hoverColor;
    private Color pressedColor;
    private int cornerRadius = 15;
    
    public RoundedButton(String text) {
        super(text);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setFont(new Font("Segoe UI", Font.BOLD, 14));
        setForeground(Color.WHITE);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Default colors
        backgroundColor = new Color(41, 128, 185); // Blue
        hoverColor = new Color(52, 152, 219);
        pressedColor = new Color(31, 97, 141);
        
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setBackground(hoverColor);
            }
            
            public void mouseExited(java.awt.event.MouseEvent evt) {
                setBackground(backgroundColor);
            }
            
            public void mousePressed(java.awt.event.MouseEvent evt) {
                setBackground(pressedColor);
            }
            
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                setBackground(hoverColor);
            }
        });
    }
    
    public void setBackgroundColor(Color color) {
        this.backgroundColor = color;
        this.hoverColor = color.brighter();
        this.pressedColor = color.darker();
        setBackground(color);
    }
    
    public void setCornerRadius(int radius) {
        this.cornerRadius = radius;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Draw background
        if (getModel().isPressed()) {
            g2.setColor(pressedColor);
        } else if (getModel().isRollover()) {
            g2.setColor(hoverColor);
        } else {
            g2.setColor(backgroundColor);
        }
        
        g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius));
        
        g2.dispose();
        super.paintComponent(g);
    }
}


package com.lms.ui.components;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

/**
 * Custom Progress Bar with modern styling
 */
public class ProgressBar extends JProgressBar {
    private Color barColor;
    private int cornerRadius = 10;
    
    public ProgressBar() {
        super();
        initStyle();
    }
    
    public ProgressBar(int min, int max) {
        super(min, max);
        initStyle();
    }
    
    private void initStyle() {
        setStringPainted(true);
        setBorderPainted(false);
        setOpaque(false);
        setFont(new Font("Segoe UI", Font.BOLD, 12));
        setForeground(Color.WHITE);
        barColor = new Color(46, 204, 113); // Green
    }
    
    public void setBarColor(Color color) {
        this.barColor = color;
        repaint();
    }
    
    public void setCornerRadius(int radius) {
        this.cornerRadius = radius;
        repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        int width = getWidth();
        int height = getHeight();
        
        // Draw background
        g2.setColor(new Color(230, 230, 230));
        g2.fill(new RoundRectangle2D.Float(0, 0, width, height, cornerRadius, cornerRadius));
        
        // Draw progress
        int progressWidth = (int) ((width * getValue()) / (double) getMaximum());
        g2.setColor(barColor);
        g2.fill(new RoundRectangle2D.Float(0, 0, progressWidth, height, cornerRadius, cornerRadius));
        
        // Draw text
        if (isStringPainted()) {
            String text = getString();
            FontMetrics fm = g2.getFontMetrics();
            int textWidth = fm.stringWidth(text);
            int textHeight = fm.getHeight();
            
            g2.setColor(getForeground());
            g2.drawString(text, (width - textWidth) / 2, (height + textHeight / 2) / 2);
        }
        
        g2.dispose();
    }
}


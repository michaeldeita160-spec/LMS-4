package com.lms.ui.components;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

/**
 * Custom Rounded Panel with modern styling
 */
public class RoundedPanel extends JPanel {
    private Color backgroundColor;
    private int cornerRadius = 15;
    private boolean hasShadow = false;
    
    public RoundedPanel() {
        super();
        setOpaque(false);
        backgroundColor = Color.WHITE;
    }
    
    public RoundedPanel(LayoutManager layout) {
        super(layout);
        setOpaque(false);
        backgroundColor = Color.WHITE;
    }
    
    public void setBackgroundColor(Color color) {
        this.backgroundColor = color;
        repaint();
    }
    
    public void setCornerRadius(int radius) {
        this.cornerRadius = radius;
        repaint();
    }
    
    public void setHasShadow(boolean hasShadow) {
        this.hasShadow = hasShadow;
        repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Draw shadow if enabled
        if (hasShadow) {
            g2.setColor(new Color(0, 0, 0, 30));
            g2.fill(new RoundRectangle2D.Float(2, 2, getWidth() - 4, getHeight() - 4, cornerRadius, cornerRadius));
        }
        
        // Draw background
        g2.setColor(backgroundColor);
        g2.fill(new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius));
        
        // Draw border
        g2.setColor(new Color(200, 200, 200));
        g2.draw(new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius));
        
        g2.dispose();
        super.paintComponent(g);
    }
}


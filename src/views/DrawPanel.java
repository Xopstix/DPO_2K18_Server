package views;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * Created by ManuSahun & Jose-chan on 16/4/18.
 */

public class DrawPanel extends JPanel {
    private int numberYDivisions;
    private int numbDays;
    private int padding = 25;
    private int labelPadding = 25;
    private int pointWidth = 4;

    public DrawPanel (){

    }
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2 = (Graphics2D)g;
        g.setColor(Color.WHITE);
        g.fillRect(0,0,800,800);

        for (int i = 0; i < numberYDivisions + 1; i++) {
            int x0 = padding + labelPadding;
            int x1 = pointWidth + padding + labelPadding;
            int y0 = getHeight() - ((i * (getHeight() - padding * 2 - labelPadding)) / numberYDivisions + padding + labelPadding);
            int y1 = y0;

            g2.setColor(Color.LIGHT_GRAY);
            g2.drawLine(padding + labelPadding + 1 + pointWidth, y0, getWidth() - padding, y1);
            g2.setColor(Color.BLACK);
            String yLabel = ((int) ((0 + (10 - 0) * ((i * 1.0) / numberYDivisions)) * 100)) / 100.0 + "";
            FontMetrics metrics = g2.getFontMetrics();
            int labelWidth = metrics.stringWidth(yLabel);
            g2.drawString(yLabel, x0 - labelWidth - 5, y0 + (metrics.getHeight() / 2) - 3);
            g2.drawLine(x0, y0, x1, y1);
        }

        for (int i = 0; i < numbDays; i++) {
            int x0 = i * (getWidth() - padding * 2 - labelPadding) / (numbDays - 1) + padding + labelPadding;
            int x1 = x0;
            int y0 = getHeight() - padding - labelPadding;
            int y1 = y0 - pointWidth;

            g2.setColor(Color.LIGHT_GRAY);
            g2.drawLine(x0, getHeight() - padding - labelPadding - 1 - pointWidth, x1, padding);
            g2.setColor(Color.BLACK);
            String xLabel = i + "";
            FontMetrics metrics = g2.getFontMetrics();
            int labelWidth = metrics.stringWidth(xLabel);
            g2.drawString(xLabel, x0 - labelWidth / 2, y0 + metrics.getHeight() + 3);
            g2.drawLine(x0, y0, x1, y1);
        }
    }
    public void setXY(int x, int y){
        this.numberYDivisions = y;
        this.numbDays = x;
    }

}

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
import java.util.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import static java.lang.Math.abs;

/**
 * Created by ManuSahun  on 16/4/18.
 */

public class DrawPanel extends JPanel {
    private int numberYDivisions;
    private int numbDays;
    private int padding = 25;
    private int labelPadding = 25;
    private int pointWidth = 4;
    private int proyectos[] = {0,4,1,0,3};
    private int year;
    private int month;
    private int day;

    public DrawPanel (){

    }
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2 = (Graphics2D)g;
        g.setColor(Color.WHITE);
        g.fillRect(0,0,8000,8000);

        // y axis
        for (int i = 0; i < numberYDivisions + 1; i++) {
            int x0 = padding + labelPadding;
            int x1 = pointWidth + padding + labelPadding;
            int y0 = getHeight() - ((i * (getHeight() - padding * 2 - labelPadding)) / numberYDivisions + padding + labelPadding);
            int y1 = y0;

            g2.setColor(Color.LIGHT_GRAY);
            g2.drawLine(padding + labelPadding + 1 + pointWidth, y0, getWidth() - padding, y1);
            g2.setColor(Color.BLACK);
            //String yLabel = ((int) ((10 * ((i * 1.0) / numberYDivisions)) * 100)) / 100 + "";
            String yLabel = ((i) + "");
            FontMetrics metrics = g2.getFontMetrics();
            int labelWidth = metrics.stringWidth(yLabel);
            g2.drawString(yLabel, x0 - labelWidth - 5, y0 + (metrics.getHeight() / 2) - 3);
            g2.drawLine(x0, y0, x1, y1);
        }

        // x axis
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

        for (int i = 0; i<(proyectos.length-1);i++){
            int x0 = padding + labelPadding;
            int y0 = getHeight() - padding - labelPadding;
            int incrX = abs(x0-((getWidth() - padding * 2 - labelPadding) / (numbDays - 1) + padding + labelPadding));
            int incrY = abs(y0 - (getHeight() - ((1 * (getHeight() - padding * 2 - labelPadding)) / numberYDivisions + padding + labelPadding)));

            System.out.println("x0:  " + x0);
            System.out.println("y0:  " + y0);
            System.out.println("incrX:  " + incrX);
            System.out.println("incrY:  " + incrY);

            g2.setColor(Color.BLUE);
            g2.drawLine(x0 + incrX*i,y0 - (incrY*proyectos[i]),x0 + incrX*(i+1),y0 - incrY*proyectos[i+1]);

        }

        /*System.out.println("x0:  " + x0);
        System.out.println("y0:  " + y0);
        System.out.println("incrX:  " + incrX);
        System.out.println("incrY:  " + incrY);*/


    }
    public void setXY(int x, int y){
        this.numberYDivisions = y;
        this.numbDays = x;
    }
    public void setNumProjects(int[] proyectos){
        this.proyectos = proyectos;
    }

    public void getDate (){
        java.util.Date fecha = new Date();

        day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        month = (Calendar.getInstance().get(Calendar.MONTH)) + 1;
        year = Calendar.getInstance().get(Calendar.YEAR);
    }

}

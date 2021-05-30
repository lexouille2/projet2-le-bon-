/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.jacob.projet.projet2.terrain;


import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

/**
 *
 * @author PC
 */
public class Terrain {
    
    private double xmin;
    private double xmax;
    private double ymin;
    private double ymax;
    private ArrayList<TriangleTerrain> aTT;
    
    public Terrain(double xmin, double xmax, double ymin, double ymax, ArrayList<TriangleTerrain> aTT){
        this.xmin = xmin;
        this.xmax = xmax;
        this.ymax = ymax;
        this.ymin = ymin;
        this.aTT = new ArrayList<TriangleTerrain>();
    }

    public double getXmin() {
        return xmin;
    }

    public double getXmax() {
        return xmax;
    }

    public double getYmin() {
        return ymin;
    }

    public double getYmax() {
        return ymax;
    }

       
}

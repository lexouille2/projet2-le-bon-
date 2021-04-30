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
    private GroupeTT grpTT;//ensemble de triangle terrain
    
    public Terrain(double xmin, double xmax, double ymin, double ymax, GroupeTT grpTT){
        this.xmin = xmin;
        this.xmax = xmax;
        this.ymax = ymax;
        this.ymin = ymin;
        this.grpTT = grpTT;
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

    public GroupeTT getGrpTT() {
        return grpTT;
    }



    private Paint getCouleur() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
       
}

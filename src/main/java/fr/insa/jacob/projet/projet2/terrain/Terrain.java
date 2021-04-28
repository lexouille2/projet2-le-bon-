/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.jacob.projet.projet2.terrain;


import java.util.ArrayList;

/**
 *
 * @author PC
 */
public class Terrain {
    
    private double xmin;
    private double xmax;
    private double ymin;
    private double ymax;
    private ArrayList<TriangleTerrain> listTT;//ensemble de triangle terrain
    
    public Terrain(double xmin, double xmax, double ymin, double ymax, ArrayList<TriangleTerrain> listTT){
        this.xmin = xmin;
        this.xmax = xmax;
        this.ymax = ymax;
        this.ymin = ymin;
        this.listTT = new ArrayList<TriangleTerrain>();
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

    public ArrayList<TriangleTerrain> getListTT() {
        return listTT;
    }

    
}

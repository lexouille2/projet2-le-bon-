/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.jacob.projet.projet2.terrain;

import fr.insa.jacob.projet.projet2.noeud.Point;

/**
 *
 * @author PC
 */
public class TriangleTerrain{
    
    private int idTT;
    private Point p1;
    private Point p2;
    private Point p3;

    public TriangleTerrain(int idTT, Point p1, Point p2, Point p3) {
        this.idTT = idTT;
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }
    
    public Point getP1(){
        return this.p1;
    }
    
    public Point getP2(){
        return this.p2;
    }
    
    public Point getP3(){
        return this.p3;
    }
    
    public int getIdTT(){
        return this.idTT;
    }
    
    
}

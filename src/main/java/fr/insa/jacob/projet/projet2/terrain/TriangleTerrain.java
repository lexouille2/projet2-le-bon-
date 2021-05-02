/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.jacob.projet.projet2.terrain;

import fr.insa.jacob.projet.projet2.noeud.Point;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author PC
 */
public class TriangleTerrain{
    
    private int idTT;
    private Point p1;
    private Point p2;
    private Point p3;
    private GroupeTT grpTT;

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

    public GroupeTT getGroupeTT(){
        return grpTT;
    }

    public void setGroupeTT(GroupeTT grpTT) {
        this.grpTT = grpTT;
    }
    
    
    public void dessine(GraphicsContext context){
        //context.setStroke(this.getCouleur());
        context.strokeLine(this.p1.getPx(), this.p1.getPy(), this.p2.getPx(), this.p2.getPy());
        context.strokeLine(this.p2.getPx(), this.p2.getPy(), this.p3.getPx(), this.p3.getPy());
        context.strokeLine(this.p3.getPx(), this.p3.getPy(), this.p1.getPx(), this.p1.getPy());
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.jacob.projet.projet2.terrain;

import fr.insa.jacob.projet.projet2.noeud.Point;
import fr.insa.jacob.projet.projet2.treillis.FigureSimple;
import fr.insa.jacob.projet.projet2.treillis.Identificateur;
import fr.insa.jacob.projet.projet2.treillis.Treillis;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author PC
 */
public class TriangleTerrain extends FigureSimple{
    
    private int idTT;
    private Point p1;
    private Point p2;
    private Point p3;

    public TriangleTerrain(int idTT, Point p1, Point p2, Point p3, Color couleur) {
        super(Color.GREEN);
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

    public void setP1(Point p1) {
        this.p1 = p1;
    }

    public void setP2(Point p2) {
        this.p2 = p2;
    }

    public void setP3(Point p3) {
        this.p3 = p3;
    }
    
    
    public void dessine(GraphicsContext context){
        context.setStroke(this.getCouleur());
        context.strokeLine(this.p1.getPx(), this.p1.getPy(), this.p2.getPx(), this.p2.getPy());
        context.strokeLine(this.p2.getPx(), this.p2.getPy(), this.p3.getPx(), this.p3.getPy());
        context.strokeLine(this.p3.getPx(), this.p3.getPy(), this.p1.getPx(), this.p1.getPy());
    }

    @Override
    public double distance(Point p) {
        double dx1 = this.p1.getPx() - p.getPx();
        double dy1 = this.p1.getPy() - p.getPy();
        double d1 = Math.sqrt(dx1 * dx1 + dy1 * dy1);
        
        double dx2 = this.p2.getPx() - p.getPx();
        double dy2 = this.p2.getPy() - p.getPy();
        double d2 = Math.sqrt(dx2 * dx2 + dy2 * dy2);
        
        double dx3 = this.p3.getPx() - p.getPx();
        double dy3 = this.p3.getPy() - p.getPy();
        double d3 = Math.sqrt(dx3 * dx3 + dy3 * dy3);
        
        double d = 0;
        if (d1 < d2 && d1 < d3){
            d = d1;
        } else if (d2 < d1 && d2 < d3){
            d = d2;
        } else if (d3 < d1 && d3 < d2){
            d = d3;
        }
        return d;
    }

    @Override
    public void dessineSelection(GraphicsContext context) {
       context.setStroke(Treillis.COULEUR_SELECTION);
       context.strokeLine(this.p1.getPx(), this.p1.getPy(), this.p2.getPx(), this.p2.getPy());
       context.strokeLine(this.p2.getPx(), this.p2.getPy(), this.p3.getPx(), this.p3.getPy());
       context.strokeLine(this.p3.getPx(), this.p3.getPy(), this.p1.getPx(), this.p1.getPy());
    }

    @Override
    public void save(Writer w, Identificateur<Treillis> num) throws IOException {
        if(! num.objExist(this)){
            int id = num.creeID(this);
            this.p1.save(w, num);
            this.p2.save(w, num);
            this.p3.save(w, num);
            /*            this.type.save(w, num);*/
            w.append("Triangle Terrain" + ";" + id + ";" + num.getID(this.p1) + ";" + num.getID(this.p2) + ";" + num.getID(this.p3) + ";" + FigureSimple.saveColor(this.getCouleur()) + "\n");
        }
    }
    
}

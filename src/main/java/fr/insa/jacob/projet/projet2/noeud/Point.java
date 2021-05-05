/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.jacob.projet.projet2.noeud;

import fr.insa.jacob.projet.projet2.terrain.GroupeTT;
import fr.insa.jacob.projet.projet2.terrain.Terrain;
import fr.insa.jacob.projet.projet2.treillis.FigureSimple;
import fr.insa.jacob.projet.projet2.treillis.Treillis;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;





/**
 *
 * @author PC
 */
public class Point extends FigureSimple{
    
    public static double RAYON_IN_DRAW = 5;
    
    private double px;
    private double py;

    public Point(double px, double py, Color couleur) {
        super(couleur);
        this.px = px;
        this.py = py;
    }

    public Point(double px, double py) {
        this(px, py, Color.BLACK);
    }

    public Point() {
        this(0, 0);
    }
    
    public double getPx() {
        return px;
    }

    public double getPy(){
        return this.py;
    }
    
    public void setPx (double px){
        this.px = px;
    }
    
    public void setPy (double py){
        this.py = py;
    }

    public String toString() {
        return "Point{" + "px=" + px + ", py=" + py + '}';
    }
    
    
    public double distance(Point p2) {
        double dx = this.px - p2.px;
        double dy = this.py - p2.py;
        return Math.sqrt(dx * dx + dy * dy);
    }
    
    public void dessine(GraphicsContext context){
        context.setFill(this.getCouleur());
        context.fillOval(this.px-RAYON_IN_DRAW, this.py-RAYON_IN_DRAW, 2*RAYON_IN_DRAW, 2*RAYON_IN_DRAW);
    }

    @Override
    public void dessineSelection(GraphicsContext context) {
       context.setFill(FigureSimple.COULEUR_SELECTION);
       context.fillOval(this.px-RAYON_IN_DRAW, this.py-RAYON_IN_DRAW, 2*RAYON_IN_DRAW, 2*RAYON_IN_DRAW);
    }


}

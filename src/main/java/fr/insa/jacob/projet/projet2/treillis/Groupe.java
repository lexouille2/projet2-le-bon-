/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.jacob.projet.projet2.treillis;

import fr.insa.jacob.projet.projet2.barre.Barre;
import fr.insa.jacob.projet.projet2.barre.TypeBarre;
import fr.insa.jacob.projet.projet2.noeud.Noeud;
import fr.insa.jacob.projet.projet2.noeud.Point;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author PC
 */
public class Groupe extends FigureSimple{
    
    
    private List<FigureSimple> grpFS;
    
    public Groupe(Color couleur){
        super(couleur);
        this.grpFS = new ArrayList<FigureSimple>();
    }
    
    
    public void add(FigureSimple fS){
        if (fS.getGrpFS() != this){
            if (fS.getGrpFS() != null){
                throw new Error ("Figure deja dans un autre groupe");
            }
            this.grpFS.add(fS);
            fS.setGrpFS(this);
        }
    }
    
    public void dessine(GraphicsContext context){
        for (FigureSimple fS : this.grpFS){
            fS.dessine(context);
        }
    }
    
    public FigureSimple plusProche(Noeud n, double distMax) {
        if (this.grpFS.isEmpty()) {//groupe vide ou objets trop loin
            return null;
        } else {
            FigureSimple fmin = this.grpFS.get(0);
            Point p = new Point(n.getAbsNoeud(),n.getOrdNoeud());
            double min = fmin.distance(p);
            for (int i = 1; i < this.grpFS.size(); i++) {
                FigureSimple fcur = this.grpFS.get(i);
                double cur = fcur.distance(p);
                if (cur < min) {
                    min = cur;
                    fmin = fcur;
                }
            }
            if (min <= distMax) {
                return fmin;
            } else {
                return null;
            }
        }
    }
        
    /*    public static Groupe groupeTest(){
    Point p1 = new Point (10,10);
    Point p2 = new Point (100,10);
    Point p3 = new Point (10,100);
    Noeud n4 = new Noeud (1,p1);
    Noeud n5 = new Noeud (2,p2);
    Noeud n6 = new Noeud (3,p3);
    TypeBarre tip = new TypeBarre(1,1,1,1,1,1);
    Barre b1 = new Barre(8, n4, n5, tip);
    Barre b2 = new Barre(8, n4, n6, tip);
    Groupe grpfS = new Groupe();
    grpfS.add(b1);
    grpfS.add(b2);
    grpfS.add(n6);
    return grpfS;
    }*/
    
    /*    public static void main(String[] args) {
    System.out.println(groupeTest());
    }*/

    @Override
    public double distance(Point p) {
        if (this.grpFS.isEmpty()) {
            return new Point(0, 0).distance(p);
        } else {
            double dist = this.grpFS.get(0).distance(p);
            for (int i = 1; i < this.grpFS.size(); i++) {
                double cur = this.grpFS.get(i).distance(p);
                if (cur < dist) {
                    dist = cur;
                }
            }
            return dist;
        }
    }

    @Override
    public void dessineSelection(GraphicsContext context) {
        for (FigureSimple f : this.grpFS) {
            f.dessineSelection(context);
        }
    }

}

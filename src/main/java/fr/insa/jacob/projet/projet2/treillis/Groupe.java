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

/**
 *
 * @author PC
 */
public class Groupe extends Treillis{
    
    private List<FigureSimple> grpFS;
    
    public Groupe(){
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
    
    public static Groupe groupeTest(){
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
    }
    
    public static void main(String[] args) {
        System.out.println(groupeTest());
    }
}

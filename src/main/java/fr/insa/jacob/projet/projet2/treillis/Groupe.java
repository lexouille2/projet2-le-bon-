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
public class Groupe extends Treillis{
    
    private List<Treillis> grpT;
    
    public List<Treillis> getGrpT() {
        return grpT;
    }
    
    public Groupe(){
        this.grpT = new ArrayList<Treillis>();
    }
    
    
    public void add(Treillis t){
        if (t.getGrp() != this){
            if (t.getGrp() != null){
                throw new Error ("Figure deja dans un autre groupe");
            }
            this.grpT.add(t);
            t.setGrp(this);
        }
    }
    
    public void dessine(GraphicsContext context){
        for (Treillis t : this.grpT){
            t.dessine(context);
        }
    }
    
    public Treillis plusProche(Point p, double distMax) {
        if (this.grpT.isEmpty()) {//groupe vide ou objets trop loin
            return null;
        } else {
            Treillis fmin = this.grpT.get(0);
            //Point p = new Point(n.getAbsNoeud(),n.getOrdNoeud());
            double min = fmin.distance(p);
            for (int i = 1; i < this.grpT.size(); i++) {
                Treillis fcur = this.grpT.get(i);
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
    
    /*    public static void main(String[] args) {
    System.out.println(groupeTest());
    }*/

    @Override
    public double distance(Point p) {
        if (this.grpT.isEmpty()) {
            return new Point(0, 0).distance(p);
        } else {
            double dist = this.grpT.get(0).distance(p);
            for (int i = 1; i < this.grpT.size(); i++) {
                double cur = this.grpT.get(i).distance(p);
                if (cur < dist) {
                    dist = cur;
                }
            }
            return dist;
        }
    }

    @Override
    public void dessineSelection(GraphicsContext context) {
        for (Treillis t : this.grpT) {
            t.dessineSelection(context);
        }
    }
    

    public void changeCouleur(Color value) {
        for (Treillis t : this.grpT) {
            t.changeCouleur(value);
        }
    }

    public Groupe sousGroupe(List<Treillis> lt) {
        // verifie que les figures font actuellement partie du groupe
        // et les enleve du groupe
        for (int i = 0; i < lt.size(); i++) {
            Treillis t = lt.get(i);
            if (t.getGrp() != this) {
                throw new Error(t + " n'appartient pas au groupe " + this);
            }
            this.grpT.remove(t);
            t.setGrp(null);
        }
        Groupe sg = new Groupe();
        for (int i = 0; i < lt.size(); i++) {
            sg.add(lt.get(i));
        }
        this.add(sg);
        return sg;
    }
    
    
    @Override
    public void save(Writer w, Identificateur<Treillis> num) throws IOException{
        if (!num.objExist(this)) {
            int id = num.creeID(this);
            for (Treillis t : this.grpT) {
                t.save(w, num);
            }
            w.append("Groupe;" + id);
            for (Treillis t : this.grpT) {
                w.append(";" + num.getID(t));
            }
            w.append("\n");
        }
    } 
    
    

}

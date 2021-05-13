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
import java.io.File;
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
    
    public int size() {
        return this.grpT.size();
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
    
    
    public void remove(Treillis t) {
        if (t.getGrp() != this) {
            throw new Error("la figure n'est pas dans le groupe");
        }
        this.grpT.remove(t);
        t.setGrp(null);
    }
        
    public static Groupe groupeTest(){
    Point p1 = new Point (10,10);
    Point p2 = new Point (100,10);
    Point p3 = new Point (10,100);
    Noeud n4 = new Noeud (1,p1);
    Noeud n5 = new Noeud (2,p2);
    Noeud n6 = new Noeud (3,p3);
    TypeBarre tip = new TypeBarre(1,1,1,1,1,1);
    Barre b1 = new Barre(8, n4, n5);
    Barre b2 = new Barre(8, n4, n6);
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

    @Override
    public String toString() {
        String res = "Groupe {\n";
        for (int i = 0; i < this.grpT.size(); i++) {
            res = res + indente(this.grpT.get(i).toString(), "  ") + "\n";
        }
        return res + "}";
    }

    public static String indente(String toIndente, String prefix) {
        return prefix + toIndente.replaceAll("\n", "\n" + prefix);
    }
    
    public static void exempleProblemeSauvegarde() {
        Point p11 = new Point(1, 1);
        Point p12 = new Point(2, 2);
        Point p13 = new Point(2, 2);
        Point p14 = new Point(3, 3);
        Noeud n11 = new Noeud(12,p11);
        Noeud n12 = new Noeud(13, p12);
        Noeud n13 = new Noeud(14, p13);
        Noeud n14 = new Noeud(15, p14);
        TypeBarre type = new TypeBarre(1,2,3,4,5,6);
        Barre s11 = new Barre(3, n11, n12);
        Barre s12 = new Barre(5, n13, n14);
        Groupe gr1 = new Groupe();
        gr1.add(s11);
        gr1.add(s12);
        Point p21 = new Point(1, 1);
        Point p22 = new Point(2, 2);
        Point p24 = new Point(3, 3);
        Noeud n21 = new Noeud(9,p21);
        Noeud n22 = new Noeud(10, p22);
        Noeud n24 = new Noeud(11, p24);
        Barre s21 = new Barre(0,n21, n22);
        Barre s22 = new Barre(3, n22,n24);
        Groupe gr2 = new Groupe();
        gr2.add(s21);
        gr2.add(s22);
        gr2.add(gr1);
        System.out.println("Groupe 1 : " + gr1);
        System.out.println("Groupe 2 : " + gr2);
        try {
            gr1.sauvegarde(new File("groupe1.txt"));
            gr2.sauvegarde(new File("groupe2.txt"));
        } catch (IOException ex) {
            throw new Error("probleme : " + ex.getMessage());
        }
    }
    
    public static void testLecture() {
        try {
            Treillis lu = Treillis.lecture(new File("groupe2.txt"));
            System.out.println("fig lue : " + lu);
            } catch (IOException ex) {
            throw new Error(ex);
        }
    }

    public static void main(String[] args) {
        exempleProblemeSauvegarde();
        //testLecture();
    }

    @Override
    public void suppr(List<Treillis> t) {
        t = null;
    }

}

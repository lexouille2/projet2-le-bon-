/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.jacob.projet.projet2.treillis;

import fr.insa.jacob.projet.projet2.barre.Barre;
import fr.insa.jacob.projet.projet2.barre.BarreAcier;
import fr.insa.jacob.projet.projet2.barre.BarreBois;
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
    
    public void removeAll(List<Treillis> lt) {
        for (Treillis t : lt) {
            this.remove(t);
        }
    }

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
    
    public static void testLecture() {
        try {
            Treillis lu = Treillis.lecture(new File("groupe2.txt"));
            System.out.println("fig lue : " + lu);
            } catch (IOException ex) {
            throw new Error(ex);
        }
    }

}

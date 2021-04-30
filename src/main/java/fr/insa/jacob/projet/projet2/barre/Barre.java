/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.jacob.projet.projet2.barre;

import fr.insa.jacob.projet.projet2.noeud.Noeud;
import fr.insa.jacob.projet.projet2.terrain.GroupeTT;
import fr.insa.jacob.projet.projet2.terrain.Terrain;

/**
 *
 * @author PC
 */
public class Barre extends Terrain{
    
    private int idBarre;
    private Noeud n1, n2;
    private TypeBarre type;

    public Barre(int idBarre, Noeud n1, Noeud n2, TypeBarre type, double xmin, double xmax, double ymin, double ymax, GroupeTT grpTT) {
        super(xmin, xmax, ymin, ymax, grpTT);
        this.idBarre = idBarre;
        this.n1 = n1;
        this.n2 = n2;
        this.type = type;
    }

    public int getIdBarre() {
        return idBarre;
    }

    public Noeud getN1() {
        return n1;
    }

    public Noeud getN2() {
        return n2;
    }

    public TypeBarre getType() {
        return type;
    }
    
    public double longueur() {
        Barre barre;
        return this.n1.distance(this.n2);
    }

    @Override
    public String toString() {
        return "Barre{" + "idBarre=" + idBarre + ", n1=" + n1 + ", n2=" + n2 + ", type=" + type + '}';
    }
    
    public void dessine(GraphicsContext context){
        context.setStroke(this.getCouleur());
        context.strokeLine(this.n1.getPx(), this.n1.getPy(), this.n2.getPx(), this.n2.getPy());
    }
}
    


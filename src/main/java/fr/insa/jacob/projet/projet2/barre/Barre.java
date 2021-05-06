/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.jacob.projet.projet2.barre;

import fr.insa.jacob.projet.projet2.noeud.Noeud;
import fr.insa.jacob.projet.projet2.noeud.NoeudAppui;
import fr.insa.jacob.projet.projet2.noeud.NoeudSimple;
import fr.insa.jacob.projet.projet2.noeud.Point;
import fr.insa.jacob.projet.projet2.terrain.GroupeTT;
import fr.insa.jacob.projet.projet2.terrain.Terrain;
import fr.insa.jacob.projet.projet2.terrain.TriangleTerrain;
import fr.insa.jacob.projet.projet2.treillis.FigureSimple;
import fr.insa.jacob.projet.projet2.treillis.Identificateur;
import fr.insa.jacob.projet.projet2.treillis.Treillis;
import java.io.IOException;
import java.io.Writer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author PC
 */
public class Barre extends FigureSimple{
    
    private int idBarre;
    private Noeud n1, n2; 
    private TypeBarre type;

    public Barre(int idBarre, Noeud n1, Noeud n2, TypeBarre type, Color couleur) {
        super(couleur);
        this.idBarre = idBarre;
        this.n1 = n1;
        this.n2 = n2;
        this.type = type;
    }

    public Barre(int idBarre, Noeud n1, Noeud n2, TypeBarre type) {
        this(idBarre,n1,n2,type,Color.BLACK);
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
    
    public double longueurBarre() {
        double dx = this.n1.getAbsNoeud() - this.n2.getAbsNoeud();
        double dy = this.n1.getOrdNoeud() - this.n2.getOrdNoeud();
        return Math.sqrt(dx * dx + dy * dy);
    }
        
    @Override
    public String toString() {
        return "Barre{" + "idBarre=" + idBarre + ", n1=" + n1 + ", n2=" + n2 + ", type=" + type + '}';
    }
    
    /*public void dessine(GraphicsContext context){
    //context.setStroke(this.getCouleur());
    context.strokeLine(this.n1.getPx(), this.n1.getPy(), this.n2.getPx(), this.n2.getPy());
    }*/
    
    /*    public static void test(){
    Point p1, p2, p3;
    p1 = new Point(1, 0);
    p2 = new Point(0, 1);
    p3 = new Point(1,1);
    TriangleTerrain TTT = new TriangleTerrain(9, p1, p2, p3);
    
    /*Noeud neuu;     // --> test pour savoir si on peut passer de noeud a noeud simple ou appui
    neuu = new NoeudSimple(3, p1);  // !!! on peut créer un noeud et ENSUITE dire si c'est un noeud simple ou appui
    neuu = new NoeudAppui(6, p2, TTT, 1);*/
    
    /*    Noeud n1, n2;
    n1 = new Noeud(3, p1);
    n2 = new Noeud(4, p2);
    
    TypeBarre tip = new TypeBarre(1,1,1,1,1,1);
    Barre bar = new Barre(5, n1, n2, tip);*/
    
    //bar.longueur(); // pour la longueur de la barre on s'en fout de savoir quels types de noeuds on a, il nous faut juste 2 noeuds quelconques.
    /*    System.out.println(bar.longueurBarre());
    }*/
    
    public void dessine(GraphicsContext context){
        context.setStroke(this.getCouleur());
        context.strokeLine(this.n1.getAbsNoeud(), this.n1.getOrdNoeud(), this.n2.getAbsNoeud(), this.n2.getOrdNoeud());
    }
    
    /*    public static void main(String[] args) {
    test();
    }*/

    @Override
    public double distance(Point p) {
        double x1 = this.n1.getAbsNoeud();
        double y1 = this.n1.getOrdNoeud();
        double x2 = this.n2.getAbsNoeud();
        double y2 = this.n2.getOrdNoeud();
        double x3 = p.getPx();
        double y3 = p.getPy();
        double up = ((x3 - x1) * (x2 - x1) + (y3 - y1) * (y2 - y1))
                / (Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        if (up < 0) {
            return this.n1.distance(p);
        } else if (up > 1) {
            return this.n2.distance(p);
        } else {
            Point p4 = new Point(x1 + up * (x2 - x1),
                    y1 + up * (y2 - y1));
            return p4.distance(p);
        }
    }

    @Override
    public void dessineSelection(GraphicsContext context) {
        context.setStroke(Treillis.COULEUR_SELECTION);
        context.strokeLine(this.n1.getAbsNoeud(), this.n1.getOrdNoeud(), this.n2.getAbsNoeud(), this.n2.getOrdNoeud());
    }
    
    @Override
    public void save(Writer w, Identificateur<Treillis> num) throws IOException{
        if(! num.objExist(this)){
            int id = num.creeID(this);
            this.n1.save(w, num);
            this.n2.save(w, num);
            w.append("Barre;"+id+";"+num.getID(this.n1)+";" +num.getID(this.n2)+";"+this.type+";"+FigureSimple.saveColor(this.getCouleur())+"\n");
        }
    }
}
    


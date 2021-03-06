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
import static fr.insa.jacob.projet.projet2.noeud.Point.RAYON_IN_DRAW;
import fr.insa.jacob.projet.projet2.terrain.Terrain;
import fr.insa.jacob.projet.projet2.terrain.TriangleTerrain;
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
public class Barre extends FigureSimple{
    
    private int idBarre;
    private Noeud n1, n2; 
    private TypeBarre type;

    public Barre(int idBarre, Noeud n1, Noeud n2, TypeBarre type, Color couleur) {
        super(type.getCol());
        this.idBarre = idBarre;
        this.n1 = n1;
        this.n2 = n2;
        this.type = type;
    }

    public Barre(int idBarre, Noeud n1, Noeud n2, TypeBarre type) {
        this(idBarre,n1,n2,type,type.getCol());
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
    
    public double longueurBarre() {
        double dx = this.n1.getAbsNoeud() - this.n2.getAbsNoeud();
        double dy = this.n1.getOrdNoeud() - this.n2.getOrdNoeud();
        return Math.sqrt(dx * dx + dy * dy);
    }
        
    @Override
    public String toString() {
        return "Barre{" + "idBarre=" + idBarre + ", n1=" + n1 + ", n2=" + n2 + /*", type=" + type + */'}';
    }
    
    public void dessine(GraphicsContext context){
        context.setStroke(this.getCouleur());
        context.strokeLine(this.n1.getAbsNoeud(), this.n1.getOrdNoeud(), this.n2.getAbsNoeud(), this.n2.getOrdNoeud());
        context.fillOval(this.n1.getAbsNoeud()-RAYON_IN_DRAW, this.n1.getOrdNoeud()-RAYON_IN_DRAW, 2*RAYON_IN_DRAW, 2*RAYON_IN_DRAW);
        context.fillOval(this.n2.getAbsNoeud()-RAYON_IN_DRAW, this.n2.getOrdNoeud()-RAYON_IN_DRAW, 2*RAYON_IN_DRAW, 2*RAYON_IN_DRAW);
    }

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
    
    public void save(Writer w, Identificateur<Treillis> num) throws IOException{
        if(! num.objExist(this)){
            int id = num.creeID(this);
            this.n1.save(w, num);
            this.n2.save(w, num);
            /*            this.type.save(w, num);*/
            w.append("Barre;"+id+";"+num.getID(this.n1)+";" +num.getID(this.n2)+";"+/*num.getID(this.type)+";"+*/FigureSimple.saveColor(this.getCouleur())+"\n");
        }
    }

}
    


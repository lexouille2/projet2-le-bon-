/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.jacob.projet.projet2.noeud;
    
import static fr.insa.jacob.projet.projet2.noeud.Point.RAYON_IN_DRAW;
import fr.insa.jacob.projet.projet2.terrain.GroupeTT;
import fr.insa.jacob.projet.projet2.terrain.Terrain;
import fr.insa.jacob.projet.projet2.treillis.FigureSimple;
import javafx.scene.canvas.GraphicsContext;

    /**
 *
 * @author PC
 */
public class Noeud extends FigureSimple{ 
    
    private int idNoeud;
    protected Point ne;

    public Noeud(int idNoeud, Point ne) {
        this.idNoeud = idNoeud;
        this.ne = ne;
    }
    
    public double getAbsNoeud(){
        return this.ne.getPx();
    }
    
    public double getOrdNoeud(){
        return this.ne.getPy();
    }
    
    public void dessine(GraphicsContext context){
        //context.setFill(this.getCouleur());
        context.fillOval(this.ne.getPx()-RAYON_IN_DRAW, this.ne.getPy()-RAYON_IN_DRAW, 2*RAYON_IN_DRAW, 2*RAYON_IN_DRAW);
    }

}


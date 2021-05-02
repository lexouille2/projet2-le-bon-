/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.jacob.projet.projet2.terrain;

import fr.insa.jacob.projet.projet2.noeud.Point;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author PC
 */
public class GroupeTT{
    
    private List<TriangleTerrain> groupe;

    public GroupeTT() {
        this.groupe = new ArrayList<TriangleTerrain>();
    }
    
    public void add(TriangleTerrain TT){
        if (TT.getGroupeTT() != this){
            if (TT.getGroupeTT() != null){
                throw new Error ("figure deja dans un autre groupe");
            }
            this.groupe.add(TT);
            TT.setGroupeTT(this);
        }
    }
    
    public void dessine(GraphicsContext context){
        for (TriangleTerrain TT : this.groupe){
            TT.dessine(context);
        }
    }
    
    public static GroupeTT groupeTest(){
        Point p1 = new Point (10,10);
        Point p2 = new Point (100,10);
        Point p3 = new Point (10,100);
        Point p4 = new Point (100,100);
        Point p5 = new Point (10,100);
        Point p6 = new Point (100,10);
        TriangleTerrain TT1 = new TriangleTerrain(8, p1, p2, p3);
        TriangleTerrain TT2 = new TriangleTerrain(9, p4, p5, p6);
        GroupeTT grpTT = new GroupeTT();
        grpTT.add(TT1);
        grpTT.add(TT2);
        return grpTT;
    }
    
    public static void main(String[] args) {
        System.out.println(groupeTest());
    }
}

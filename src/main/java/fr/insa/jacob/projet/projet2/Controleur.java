/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.jacob.projet.projet2;

import fr.insa.jacob.projet.projet2.barre.Barre;
import fr.insa.jacob.projet.projet2.barre.TypeBarre;
import fr.insa.jacob.projet.projet2.noeud.Noeud;
import fr.insa.jacob.projet.projet2.noeud.Point;
import fr.insa.jacob.projet.projet2.terrain.GroupeTT;
import fr.insa.jacob.projet.projet2.treillis.FigureSimple;
import fr.insa.jacob.projet.projet2.treillis.Groupe;
import fr.insa.jacob.projet.projet2.treillis.Treillis;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 *
 * @author PC
 */
public class Controleur {
    
    private MainPane vue;
    
    private int etat;
    
    private double[] pos1 = new double[2];//on retient px et py du premier clic
    
    private Point pointPos1;
    
    private List<FigureSimple> selection;
    
    public Controleur(MainPane vue){
        this.vue = vue;
        this.selection = new ArrayList<>();
    }
    
    public void changeEtat(int nouvelEtat) {
        if (nouvelEtat == 20) {
            this.vue.getRbSelect().setSelected(true);
            this.selection.clear();
            this.vue.redrawAll();
        } else if (nouvelEtat == 30) {
            // creation de points
            this.vue.getRbNoeud().setSelected(true);
            this.selection.clear();
            this.vue.getbGrouper().setDisable(true);
            this.vue.redrawAll();
        } else if (nouvelEtat == 40) {
            // creation de segments étape 1
            this.vue.getRbBarre().setSelected(true);
            this.selection.clear();
            this.vue.getbGrouper().setDisable(true);
            this.vue.redrawAll();
        } else if (nouvelEtat == 41) {
            // creation de segments étape 2
        }
        this.etat = nouvelEtat;
    }

    void clicDansZoneDessin(MouseEvent t) {
        if(this.etat == 20){ //on dessine juste un point
            Noeud pclic = new Noeud(0,new Point(t.getX(),t.getY()));
            //Color col = Color.color(Math.random(), Math.random(), Math.random());
            //pas de limite de distance entre le clic et l'objet sélectionné
            FigureSimple proche = this.vue.getModel().plusProche(pclic,Double.MAX_VALUE);
            //il faut prévoir le cas où le groupe est vide, donc pas de plus proche
            if (proche != null) {
                if (t.isShiftDown()) {
                    this.selection.add(proche);//on ajoute le plus proche à la sélection courante
                } else if (t.isControlDown()) {
                    if (this.selection.contains(proche)) {
                        this.selection.remove(proche);//s'il était déjà dedans, on l'enlève
                    } else {
                        this.selection.add(proche);//sinon je le mets
                    }
                } else {
                    this.selection.clear();
                    this.selection.add(proche);//la sélection devient juste l'élément sélectionné
                }
                this.activeBoutonsSuivantSelection();
                this.vue.redrawAll();
            }
        } else if (this.etat == 30){ //pos1 retient la position du premier clic
            double px = t.getX();
            double py = t.getY();
            Color col = this.vue.getCpCouleur().getValue();
            Groupe model = this.vue.getModel();
            model.add(new Point(px, py));
            this.vue.redrawAll();
        } else if (this.etat == 40) {
            this.pos1[0] = t.getX();
            this.pos1[1] = t.getY();
            this.changeEtat(41);
        }else if (this.etat == 41){
            double px2 = t.getX();
            double py2 = t.getY();
            Point pointPos2 = new Point (px2,py2);
            Color col = this.vue.getCpCouleur().getValue();
            TypeBarre tip = new TypeBarre (1,2,3,4,5,6);
            this.vue.getModel().add(
            new Barre(1, new Noeud(1,pointPos1),new Noeud(2,pointPos2),tip));
            this.vue.redrawAll(); //on redessine le model qui a été modifié
            this.changeEtat(40);
        }
    }
    
    
    void boutonSelect (ActionEvent t){
        this.changeEtat(20);
    }
    
    void boutonNoeud (ActionEvent t){
        this.changeEtat(30);
    }
    
    void boutonBarre (ActionEvent t){
        this.changeEtat(40);
    }
    
    private void activeBoutonsSuivantSelection(){
        if(this.selection.size() < 2){
            this.vue.getbGrouper().setDisable(true);
        }else{
            this.vue.getbGrouper().setDisable(false);
        }
    }
    
    public List<FigureSimple> getSelection() {
        return selection;
    }
}

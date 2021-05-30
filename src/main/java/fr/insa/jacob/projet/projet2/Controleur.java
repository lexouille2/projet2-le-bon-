/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.jacob.projet.projet2;

import fr.insa.jacob.projet.projet2.barre.Barre;
import fr.insa.jacob.projet.projet2.barre.BarreAcier;
import fr.insa.jacob.projet.projet2.barre.BarreBois;
import fr.insa.jacob.projet.projet2.barre.TypeBarre;
import fr.insa.jacob.projet.projet2.noeud.Noeud;
import fr.insa.jacob.projet.projet2.noeud.NoeudAppui;
import fr.insa.jacob.projet.projet2.noeud.Point;
import fr.insa.jacob.projet.projet2.terrain.TriangleTerrain;
import fr.insa.jacob.projet.projet2.treillis.Groupe;
import fr.insa.jacob.projet.projet2.treillis.Treillis;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author PC
 */
public class Controleur {
    
    private MainPane vue;
    
    private int etat;
    
    private double[] pos1 = new double[4];
    
    private Point debutSeg;
    private Point finSeg;
    
    private Noeud debutBarre;
    
    private ArrayList<Noeud> ANoeud = new ArrayList<Noeud>();
    
    private List<Treillis> selection;
    
    public Controleur(MainPane vue){
        this.vue = vue;
        this.selection = new ArrayList<>();
    }
    
    public void changeEtat(int nouvelEtat) {
        if (nouvelEtat == 20) { // bouton select
            this.vue.getRbSelect().setSelected(true);
            this.selection.clear();
            this.vue.redrawAll();
        } else if(nouvelEtat == 30) {
            this.vue.getRbTT().setSelected(true);
            this.selection.clear();
            this.vue.getbGrouper().setDisable(true);
            this.vue.getbSupprimer().setDisable(true);
            this.vue.redrawAll();
        } else if (nouvelEtat == 40) { // bouton noeud
            this.vue.getRbNoeud().setSelected(true);
            this.selection.clear();
            this.vue.getbGrouper().setDisable(true);
            this.vue.getbSupprimer().setDisable(true);
            this.vue.redrawAll();
        } else if (nouvelEtat == 50) { // bouton noeud
            this.vue.getRbNoeudAD().setSelected(true);
            this.selection.clear();
            this.vue.getbGrouper().setDisable(true);
            this.vue.getbSupprimer().setDisable(true);
            this.vue.redrawAll();
        } else if (nouvelEtat == 55) { // bouton noeud
            this.vue.getRbNoeudAS().setSelected(true);
            this.selection.clear();
            this.vue.getbGrouper().setDisable(true);
            this.vue.getbSupprimer().setDisable(true);
            this.vue.redrawAll();
        } else if (nouvelEtat == 60) { // bouton barre
            // creation de segments étape 1
            this.vue.getRbBarre1().setSelected(true);
            this.selection.clear();
            this.vue.getbGrouper().setDisable(true);
            this.vue.getbSupprimer().setDisable(true);
            this.vue.redrawAll();
        } else if (nouvelEtat == 61) { // 2e point du segment
            // creation de segments étape 2
        }else if (nouvelEtat == 70) { // bouton barre
            // creation de segments étape 1
            this.vue.getRbBarre2().setSelected(true);
            this.selection.clear();
            this.vue.getbGrouper().setDisable(true);
            this.vue.getbSupprimer().setDisable(true);
            this.vue.redrawAll();
        } else if (nouvelEtat == 71) { // 2e point du segment
            // creation de segments étape 2
        }
        this.etat = nouvelEtat;
    }

    void clicDansZoneDessin(MouseEvent t) {
        if(this.etat == 20){ 
            Point pclic = new Point(t.getX(),t.getY());
            Treillis proche = this.vue.getModel().plusProche(pclic,Double.MAX_VALUE);
            if (proche != null) {
                if (t.isShiftDown()) {
                    this.selection.add(proche);
                } else if (t.isControlDown()) {
                    if (this.selection.contains(proche)) {
                        this.selection.remove(proche);
                    } else {
                        this.selection.add(proche);
                    }
                } else {
                    this.selection.clear();
                    this.selection.add(proche);
                }
                this.activeBoutonsSuivantSelection();
                this.vue.redrawAll();
            }
        } else if(this.etat == 30){
            this.pos1[0] = t.getX();
            this.pos1[1] = t.getY();
            this.changeEtat(31);       
        } else if (this.etat == 31){   
            this.pos1[2] = t.getX();
            this.pos1[3] = t.getY();
            this.changeEtat(32);
        } else if (this.etat == 32) {
            double px3 = t.getX();
            double py3 = t.getY();
            Color col = this.vue.getCpCouleur().getValue();
            this.vue.getModel().add(new TriangleTerrain(3, new Point(this.pos1[0], this.pos1[1]), new Point(this.pos1[2], this.pos1[3]), new Point(px3, py3), col));
            this.vue.redrawAll();
            this.changeEtat(30);
        } else if (this.etat == 40){ 
            double px = t.getX();
            double py = t.getY();
            Color col = Color.BLACK;
            Groupe model = this.vue.getModel();
            model.add(new Noeud(0, new Point(px, py), col));
            this.ANoeud.add(new Noeud(0, new Point(px, py), col));
            this.vue.redrawAll();
        } else if (this.etat == 50){
            debutSeg = new Point(t.getX(), t.getY());
            this.changeEtat(51);
        }else if(this.etat == 51){
            finSeg = new Point(t.getX(), t.getY());
            this.changeEtat(52);
        }else if(this.etat == 52){
            Point clic3 = new Point(t.getX(), t.getY());
            double distDebAlpha = distanceAlpha(debutSeg, clic3);
            double distFinAlpha = distanceAlpha(finSeg, clic3);
            double distTot = distDebAlpha + distFinAlpha;
            double ALPHA = distDebAlpha / distTot;
            Point pouain = calcPos(debutSeg, finSeg, clic3, 1-ALPHA);
            this.vue.getModel().add(new NoeudAppui(1, debutSeg, finSeg, 6, pouain, Color.BLUE));
            this.ANoeud.add(new NoeudAppui(1, debutSeg, finSeg, 6, pouain));
            this.vue.redrawAll();
            this.changeEtat(50);
            } else if (this.etat == 55){
            debutSeg = new Point(t.getX(), t.getY());
            this.changeEtat(56);
        }else if(this.etat == 56){
            finSeg = new Point(t.getX(), t.getY());
            this.changeEtat(57);
        }else if(this.etat == 57){
            Point clic3 = new Point(t.getX(), t.getY());
            double distDebAlpha = distanceAlpha(debutSeg, clic3);
            double distFinAlpha = distanceAlpha(finSeg, clic3);
            double distTot = distDebAlpha + distFinAlpha;
            double ALPHA = distDebAlpha / distTot;
            Point pouain = calcPos(debutSeg, finSeg, clic3, 1-ALPHA);
            this.vue.getModel().add(new NoeudAppui(1, debutSeg, finSeg, 6, pouain, Color.BLUE));
            this.ANoeud.add(new NoeudAppui(1, debutSeg, finSeg, 6, pouain));
            this.vue.redrawAll();
            this.changeEtat(55);
        } else if (this.etat == 60) {
            double px2 = t.getX();
            double py2 = t.getY();
            int z = 0;
            int compteur = 0;
            while (z < this.ANoeud.size()){
                if ( (px2 > (this.ANoeud.get(z).getAbsNoeud() - 5)) && (px2 < (this.ANoeud.get(z).getAbsNoeud() + 5)) && (py2 > (this.ANoeud.get(z).getOrdNoeud() - 5)) && (py2 < (this.ANoeud.get(z).getOrdNoeud() + 5)) ){
                    compteur = 1;
                    debutBarre = this.ANoeud.get(z);
                    this.changeEtat(61);
                    z = this.ANoeud.size() + 1;
                }
                z = z+1;
            }
            if(compteur == 0) {
                this.pos1[0] = t.getX();
                this.pos1[1] = t.getY();
                debutBarre = new Noeud(1, new Point(this.pos1[0], this.pos1[1]));
                Noeud n = new Noeud(1, new Point(this.pos1[0], this.pos1[1]));
                this.ANoeud.add(n);
                this.vue.getModel().add(n);
                this.changeEtat(61);
            }
            
        }else if (this.etat == 61){
            double px2 = t.getX();
            double py2 = t.getY();
            Color col = this.vue.getCpCouleur().getValue();
            int z = 0;
            int compteur = 0;
            while (z < this.ANoeud.size()){
                if ( (px2 > (this.ANoeud.get(z).getAbsNoeud() - 5)) && (px2 < (this.ANoeud.get(z).getAbsNoeud() + 5)) && (py2 > (this.ANoeud.get(z).getOrdNoeud() - 5)) && (py2 < (this.ANoeud.get(z).getOrdNoeud() + 5)) ){
                    compteur = 1;
                    Noeud deb = debutBarre;
                    Noeud fin = this.ANoeud.get(z);
                    this.vue.getModel().add(deb);
                    this.vue.getModel().add(fin);
                    TypeBarre tip = new BarreAcier();
                    this.vue.getModel().add(new Barre(1, deb, fin, tip));
                    this.vue.redrawAll();
                    this.changeEtat(60);
                    z = this.ANoeud.size() + 1;
                }
                z = z+1;
            }
            if(compteur == 0){
                Noeud deb = debutBarre;
                Noeud fin = new Noeud(2,new Point(px2,py2), col);
                this.vue.getModel().add(deb);
                this.vue.getModel().add(fin);
                TypeBarre tip = new BarreAcier();
                this.vue.getModel().add(new Barre(1, deb, fin, tip));
                Noeud n = new Noeud(2,new Point(px2,py2), col);
                this.ANoeud.add(n);
                this.vue.getModel().add(n);
                this.vue.redrawAll();
                this.changeEtat(60);
            }
              
        }else if (this.etat == 70) {
            double px2 = t.getX();
            double py2 = t.getY();
            int z = 0;
            int compteur = 0;
            while (z < this.ANoeud.size()){
                if ( (px2 > (this.ANoeud.get(z).getAbsNoeud() - 5)) && (px2 < (this.ANoeud.get(z).getAbsNoeud() + 5)) && (py2 > (this.ANoeud.get(z).getOrdNoeud() - 5)) && (py2 < (this.ANoeud.get(z).getOrdNoeud() + 5)) ){
                    compteur = 1;
                    debutBarre = this.ANoeud.get(z);
                    this.changeEtat(71);
                    z = this.ANoeud.size() + 1;
                }
                z = z+1;
            }
            if(compteur == 0) {
                this.pos1[0] = t.getX();
                this.pos1[1] = t.getY();
                debutBarre = new Noeud(1, new Point(this.pos1[0], this.pos1[1]));
                this.ANoeud.add(new Noeud(1, new Point(this.pos1[0], this.pos1[1])));
                this.changeEtat(71);
            }
        }else if (this.etat == 71){
            double px2 = t.getX();
            double py2 = t.getY();
            Color col = this.vue.getCpCouleur().getValue();
            int z = 0;
            int compteur = 0;
            while (z < this.ANoeud.size()){
                if ( (px2 > (this.ANoeud.get(z).getAbsNoeud() - 5)) && (px2 < (this.ANoeud.get(z).getAbsNoeud() + 5)) && (py2 > (this.ANoeud.get(z).getOrdNoeud() - 5)) && (py2 < (this.ANoeud.get(z).getOrdNoeud() + 5)) ){
                    compteur = 1;
                    Noeud deb = debutBarre;
                    Noeud fin = this.ANoeud.get(z);
                    this.vue.getModel().add(deb);
                    this.vue.getModel().add(fin);
                    TypeBarre teep = new BarreBois();
                    this.vue.getModel().add(new Barre(1, deb, fin, teep));
                    this.vue.redrawAll();
                    this.changeEtat(70);
                    z = this.ANoeud.size() + 1;
                }
                z = z+1;
            }
            if(compteur == 0){
                Noeud deb = debutBarre;
                Noeud fin = new Noeud(2,new Point(px2,py2), col);
                this.vue.getModel().add(deb);
                this.vue.getModel().add(fin);
                TypeBarre teep = new BarreBois();
                this.vue.getModel().add(new Barre(1, deb, fin, teep));
                this.ANoeud.add(new Noeud(2,new Point(px2,py2), col));
                this.vue.redrawAll();
                this.changeEtat(70);
            }
        }
    }
    
    
    void boutonSelect (ActionEvent t){
        this.changeEtat(20);
    }
    
    void boutonTT (ActionEvent t){
        this.changeEtat(30);
    }
    
    void boutonNoeud (ActionEvent t){
        this.changeEtat(40);
    }
    
    void boutonNoeudAD (ActionEvent t){
        this.changeEtat(50);
    }
    
    void boutonNoeudAS (ActionEvent t){
        this.changeEtat(55);
    }
    
    void boutonBarre1 (ActionEvent t){
        this.changeEtat(60);
    }
    
    void boutonBarre2 (ActionEvent t){
        this.changeEtat(70);
    }
    
    private void activeBoutonsSuivantSelection(){
        if((this.selection.size() < 2) && (this.selection.size() <1)){
            this.vue.getbGrouper().setDisable(true);
            this.vue.getbSupprimer().setDisable(true);            
        }else if(this.selection.size() == 1 ){
            this.vue.getbGrouper().setDisable(true);
            this.vue.getbSupprimer().setDisable(false);
        }else{
            this.vue.getbGrouper().setDisable(false); 
            this.vue.getbSupprimer().setDisable(false);
        }
    }
    
    public List<Treillis> getSelection() {
        return selection;
    }
    
    void boutonGrouper(ActionEvent t) {
        if (this.etat == 20 && this.selection.size() > 1) {
            Groupe ssGroupe = this.vue.getModel().sousGroupe(selection);
            this.selection.clear();
            this.selection.add(ssGroupe);
            this.vue.redrawAll();
        }
    }
    
    void boutonSupprimer(ActionEvent t){
        if (this.etat == 20 && this.selection.size() > 0) {
            this.vue.getModel().removeAll(this.selection);
            this.selection.clear();
            this.activeBoutonsSuivantSelection();
            this.vue.redrawAll();
        }
    }
            
    void changeColor(Color value) {
        if (this.etat == 20 && this.selection.size() > 0) {
            for (Treillis t : this.selection) {
                t.changeCouleur(value);
            }
            this.vue.redrawAll();
        }
    }
    
    void realSave(File f) {
        try {
            this.vue.getModel().sauvegarde(f);
            this.vue.setCurFile(f);
            this.vue.getInStage().setTitle(f.getName());
        } catch (IOException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Problème durant la sauvegarde");
            alert.setContentText(ex.getLocalizedMessage());

            alert.showAndWait();
        } finally {
            this.changeEtat(20);
        }
    }

    void menuSave(ActionEvent t) {
        if (this.vue.getCurFile() == null) {
            this.menuSaveAs(t);
        } else {
            this.realSave(this.vue.getCurFile());
        }
    }

    void menuSaveAs(ActionEvent t) {
        FileChooser chooser = new FileChooser();
        File f = chooser.showSaveDialog(this.vue.getInStage());
        if (f != null) {
            this.realSave(f);
        }
    }

    void menuOpen(ActionEvent t) {
        FileChooser chooser = new FileChooser();
        File f = chooser.showOpenDialog(this.vue.getInStage());
        if (f != null) {
            try {
                Treillis lu = Treillis.lecture(f);
                Groupe glu = (Groupe) lu;
                Stage nouveau = new Stage();
                nouveau.setTitle(f.getName());
                Scene sc = new Scene(new MainPane(nouveau, f, glu), 800, 600);
                nouveau.setScene(sc);
                nouveau.show();
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Problème durant la sauvegarde");
                alert.setContentText(ex.getLocalizedMessage());

                alert.showAndWait();
            } finally {
                this.changeEtat(20);
            }
        }
    }

    void menuNouveau(ActionEvent t) {
        Stage nouveau = new Stage();
        nouveau.setTitle("Nouveau");
        Scene sc = new Scene(new MainPane(nouveau), 800, 600);
        nouveau.setScene(sc);
        nouveau.show();
    }

    void menuApropos(ActionEvent t) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("A propos");
        alert.setHeaderText(null);
        alert.setContentText("Ce logiciel est vraiment génial !\n");
        alert.showAndWait();
    }
    
    public double distanceAlpha(Point p1, Point p2) {
        double dx = p1.getPx() - p2.getPx();
        double dy = p1.getPy() - p2.getPy();
        return Math.sqrt(dx * dx + dy * dy);
    }
    
    public Point calcPos(Point debutSeg, Point finSeg, Point ne, double alpha){
        double absPos = ne.getPx();
        double ordPos = ne.getPy();
        double absDebut = debutSeg.getPx();
        double ordDebut = debutSeg.getPy();
        double absFin = finSeg.getPx();
        double ordFin = finSeg.getPy();
        absPos = (1 - alpha) * absFin + absDebut * alpha;
        ordPos = (1 - alpha) * ordFin + alpha * ordDebut;
        Point point = new Point(absPos, ordPos);
        ne = point;
        return ne;
    }
    
    
}

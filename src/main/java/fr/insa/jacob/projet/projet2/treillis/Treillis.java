/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.jacob.projet.projet2.treillis;

import fr.insa.jacob.projet.projet2.barre.Barre;
import fr.insa.jacob.projet.projet2.noeud.Noeud;
import fr.insa.jacob.projet.projet2.noeud.Point;
import fr.insa.jacob.projet.projet2.terrain.Terrain;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 *
 * @author PC
 */
public abstract class Treillis {

    public static Color COULEUR_SELECTION = Color.RED;
    
    private Groupe grp;
    
    public Groupe getGrp() {
        return grp;
    }

    void setGrp(Groupe grp) {
        this.grp = grp;
    }
    
    public abstract void dessine(GraphicsContext context);  
    
    public abstract double distance(Point p);
    
    public abstract void dessineSelection(GraphicsContext context);
    
    public abstract void changeCouleur(Color value);
    
    public abstract void save(Writer w, Identificateur<Treillis> num) throws IOException;
    
 
    public void sauvegarde(File fout) throws IOException {
        Identificateur<Treillis> num = new Identificateur<Treillis>();
        try (BufferedWriter bout = new BufferedWriter(new FileWriter(fout))) {
            this.save(bout, num);
        }
    }

    public static Treillis lecture(File fin) throws IOException {
        Identificateur<Treillis> num = new Identificateur<Treillis>();
        Treillis derniere = null;//on crée la variable qui donnera la dernière figure qu'on a lu
        try (BufferedReader bin = new BufferedReader(new FileReader(fin))) {
            String line;
            while ((line = bin.readLine()) != null && line.length() != 0) {
                String[] bouts = line.split(";");
                if (bouts[0].equals("Point")) {
                    int id = Integer.parseInt(bouts[1]);
                    double px = Double.parseDouble(bouts[2]);
                    double py = Double.parseDouble(bouts[3]);
                    Color col = FigureSimple.parseColor(bouts[4], bouts[5], bouts[6]);
                    Point np = new Point(px, py, col);
                    num.associe(id, np);//id associé au point
                    derniere = np;
                }else if (bouts[0].equals("Noeud")) {
                    int id = Integer.parseInt(bouts[1]);
                   // int idP = Integer.parseInt(bouts[2]);
                    double px = Double.parseDouble(bouts[2]);
                    double py = Double.parseDouble(bouts[3]);
                    Color col = FigureSimple.parseColor(bouts[4], bouts[5], bouts[6]);
                    Point point = new Point(px,py);
                    Noeud nn = new Noeud(id,point,col);
                    num.associe(id, nn);//id associé au noeud
                    derniere = nn;
                } else if (bouts[0].equals("Barre")) {
                    int id = Integer.parseInt(bouts[1]);
                    int idN1 = Integer.parseInt(bouts[2]);
                    int idN2 = Integer.parseInt(bouts[3]);
                    Color col = FigureSimple.parseColor(bouts[4], bouts[5], bouts[6]);
                    Noeud n1 = (Noeud) num.getObj(idN1);
                    Noeud n2 = (Noeud) num.getObj(idN2);
                    Barre nb = new Barre(id, n1, n2, col);//on crée la barre
                    num.associe(id, nb);//id associé à la barre
                    derniere = nb;
                } else if (bouts[0].equals("Groupe")) {//bouts est la position dans le fichier texte
                    int id = Integer.parseInt(bouts[1]);
                    Groupe ng = new Groupe();
                    num.associe(id, ng);//id associé au groupe
                    for (int i = 2; i < bouts.length; i++) {
                        int idSous = Integer.parseInt(bouts[i]);
                        Treillis tre = num.getObj(idSous);
                        ng.add(tre);
                    }
                    derniere = ng;
                }
            }

        }
        return derniere;
    }
}


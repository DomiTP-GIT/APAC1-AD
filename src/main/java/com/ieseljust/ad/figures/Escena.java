package com.ieseljust.ad.figures;


import javafx.scene.canvas.GraphicsContext;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class Escena implements Serializable {
  //Les figures de l'escena s'emmagatzemen en una llista
  List<Figura> LlistaFigures;
  // L'escena tindrà unes dimensions.
  // Aquesta valor ens determinaran el marc per dibuixar posteriorment.
  private int tamX;
  private int tamY;

  // Aquest objecte ens servirà per validar els colors
  // en format hexadecimal (Ex: #000000)


  Escena() {
    // Constructor. Pr defecte creem un tamany de 800x600;
    this.tamX = 800;
    this.tamY = 600;

    // Inicialitzem la llista de figures
    LlistaFigures = new ArrayList<Figura>();

  }

  Escena(int x, int y) {

    // Constructor (sobrecarregat), quan se'ns indica
    // un tamany per al marc.

    this.tamX = x;
    this.tamY = y;

    // Inicialitzem la llista de figures
    LlistaFigures = new ArrayList<Figura>();
  }

  public void dimensions(int x, int y) {
    // Estableix les dimensions del marc
    this.tamX = x;
    this.tamY = y;
  }

  // Getters del tamany
  int getX() {
    return this.tamX;
  }

  int getY() {
    return this.tamY;
  }

  // Retorna si l'escena és buida
  public Boolean esBuida() {
    return this.LlistaFigures.isEmpty();
  }


  public void add(Figura figura) {
        /*  
        Aquest mètode afig un objecte de tipus Figura
        a l'escena. Com que figura és una classe abstract,
        aquestes figures podran ser de diversos tipus.
        */

    // Comprovem que la figura cau dins la imatge

    if (figura.posicio.getX() < this.tamX && figura.posicio.getY() < this.tamY) {

      // I comprovem que el color estiga en
      // format hexadecimal.
      if (HexColorValidator.validate(figura.color)) {
        // Si tot és correte, afegim la figura a la llista.
        this.LlistaFigures.add(figura);
        System.out.println(Colors.Green + "OK" + Colors.Reset);
      } else {
        // En cas contrari, mostrem l'error
        System.out.println(Colors.Red + "Color no vàlid." + Colors.Reset);
      }

    } else {
      // En cas contrari, mostrem l'error
      System.out.println(Colors.Red + "La imatge cau fora de l'escena." + Colors.Reset);
    }
  }


  public void renderText() {
    /* Mostra la llista de figures i les seues propietats */

    // Recorrem la llista de figures i invoquem
    // el mètode renderText de cadascuna d'elles.
    for (Figura f : this.LlistaFigures) {
      f.renderText();
    }
  }

  public void renderScene(GraphicsContext gc) {
        /* Dibuixa la nostra escena dins un context
        gràfic de JavaFX. 
         */

    // Recorrem la llista de figures, invocant el mètode
    // render de cadascuna
    // La referència per dibuixar al graphicsContext
    // la podem trobar en:
    // https://docs.oracle.com/javase/8/javafx/api/javafx/scene/canvas/GraphicsContext.html
    for (Figura f : this.LlistaFigures) {
      f.render(gc);
    }
    // gc.fillText("text", 100, 100);


  }

  public String getDimensionsAsText(){
    return "dimensions "+this.getX()+" "+this.getY();
  }


}


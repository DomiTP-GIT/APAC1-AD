package com.ieseljust.ad.figures;

// Llibreríes per a poder dibuixar 

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;

// Definim la classe rectangle a partir de la classe figura
// Heretarem per tant, la posició i el color
class Rectangle extends Figura {

  // Té un nou atribut que serà el radi
  private Integer llarg;
  private Integer alt;

  // Constructors
  Rectangle() {
    // Sense paràmetres:
    super(); // Invoca al constructor del pare
    this.llarg = 0;
    this.llarg = 0;
  }

  Rectangle(int x, int y, int llarg, int alt, String color) {
    // Amb paràmetres:
    super(x, y, color); // Invoquem al constructor pare
    this.llarg = llarg; // Indiquem el valor de la llargària
    this.alt = alt; // Indiquem el valor de l'altura
  }

  public void renderText() {
    // Escriu les propietats de la figura
    System.out.println("Rectangle en (" + this.posicio.getX() + "," + this.posicio.getY() + ") de llarg " + this.llarg + ", altura " + this.alt + " i color " + this.color);
  }

  public void render(GraphicsContext gc) {
    // Dibuixem el rectangle amb el seu color, la posició i les dimensions
    Color color = Color.web(this.color);
    gc.setFill(color);

    gc.fillRect(this.posicio.getX(), this.posicio.getY(), this.llarg, this.alt);
    //gc.fillOval(this.posicio.getX(), this.posicio.getY(), this.radi*2, this.radi*2);
  }

  @Override
  public String getAsText() {
    return "rectangle " + this.posicio.getX() + " " + this.posicio.getY() + " " + this.llarg + " " + this.alt + " " + this.color;
  }

  @Override
  public JSONObject getAsJSON() {
    JSONObject object = new JSONObject();
    object.put("x", this.posicio.getX());
    object.put("y", this.posicio.getY());
    object.put("llarg", this.llarg);
    object.put("alt", this.alt);
    object.put("fill", this.color);

    JSONObject rectangle = new JSONObject();
    rectangle.put("rectangle", object);
    return rectangle;
  }

  @Override
  public Element getAsXML(Document doc) {
    Element e = doc.createElement("rect");
    e.setAttribute("fill", this.color);
    e.setAttribute("height", String.valueOf(this.alt));
    e.setAttribute("width", String.valueOf(this.llarg));
    e.setAttribute("x", String.valueOf(this.posicio.getX()));
    e.setAttribute("y", String.valueOf(this.posicio.getY()));

    return e;
  }
}

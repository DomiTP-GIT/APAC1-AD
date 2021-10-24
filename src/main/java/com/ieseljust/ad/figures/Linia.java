package com.ieseljust.ad.figures;

// Llibreríes per a poder dibuixar 

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

// Definim la classe Línia a partir de la classe figura
// Heretarem per tant, la posició i el color
class Linia extends Figura {

  // Té un nou atribut que serà el radi
  private final Punt vector;

  // Constructors
  Linia() {
    // Sense paràmetres:
    super(); // Invoca al constructor del pare
    this.vector = new Punt(0, 0);
  }

  Linia(int x, int y, int x2, int y2, String color) {
    // Amb paràmetres:
    super(x, y, color); // Invoquem al constructor pare
    this.vector = new Punt(x2, y2); // Indiquem el vector de la línia
  }

  public void renderText() {
    // Escriu les propietats de la figura
    System.out.println("Linia de (" + this.posicio.getX() + "," + this.posicio.getY() + ") fins a (" + this.vector.getX() + ", " + this.vector.getY() + ") i color " + this.color);
  }

  public void render(GraphicsContext gc) {
    // Dibuixem la linia amb el seu color
    Color color = Color.web(this.color);
    gc.setLineWidth(3); // Grossor per defecte de la línia: 3
    gc.setStroke(color);
    gc.strokeLine(this.posicio.getX(), this.posicio.getY(), this.vector.getX(), this.vector.getY());

  }

  @Override
  public String getAsText() {
    return "linia " + this.posicio.getX() + " " + this.posicio.getY() + " " + this.vector.getX() + " " + this.vector.getY() + " " + this.color;
  }

  @Override
  public JSONObject getAsJSON() {
    JSONObject object = new JSONObject();
    object.put("x1", this.posicio.getX());
    object.put("y1", this.posicio.getY());
    object.put("x2", this.vector.getX());
    object.put("y2", this.vector.getY());
    object.put("stroke-width", 3);
    object.put("stroke", this.color);

    JSONObject linia = new JSONObject();
    linia.put("linia", object);
    return linia;
  }

  @Override
  public Element getAsXML(Document doc) {
    Element e = doc.createElement("line");
    e.setAttribute("stroke", this.color);
    e.setAttribute("stroke-width","3");
    e.setAttribute("x1", String.valueOf(this.posicio.getX()));
    e.setAttribute("x2", String.valueOf(this.vector.getX()));
    e.setAttribute("y1", String.valueOf(this.posicio.getY()));
    e.setAttribute("y2", String.valueOf(this.vector.getY()));

    return e;
  }

}

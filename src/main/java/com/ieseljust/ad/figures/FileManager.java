package com.ieseljust.ad.figures;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

class FileManager {

  public FileManager() {

  }


  private boolean validaInt(String s) {
    try {
      Integer.parseInt(s);
    } catch (NumberFormatException | NullPointerException e) {
      return false;
    }
    // only got here if we didn't return false
    return true;
  }

  public Boolean Exists(String file) {
    File f = new File(file);

    return f.exists();
  }

  public Escena importFromText(String file) {

    /**
     * *********************************************************
     * TO-DO: Mètode a implementar: * Llegirà el fitxer indicat, en format
     * text, i importarà * la llista de figures. *
     * **********************************************************
     */
    /*
            dimensions 500 500
            rectangle 10 10 480 480 #ccccee
            cercle 250 250 100 #aaaaaa
         */
    Escena escena = new Escena();

    FileReader fr = null;
    try {
      fr = new FileReader(file);
      BufferedReader bfr = new BufferedReader(fr);

      while (bfr.ready()) {
        String linea = bfr.readLine();
        String[] items = linea.split(" ");
        switch (items[0]) {
          case "rectangle":
            escena.LlistaFigures.add(new Rectangle(Integer.parseInt(items[1]), Integer.parseInt(items[2]), Integer.parseInt(items[3]), Integer.parseInt(items[4]), items[5]));
            break;
          case "cercle":
            escena.LlistaFigures.add(new Cercle(Integer.parseInt(items[1]), Integer.parseInt(items[2]), Integer.parseInt(items[3]), items[4]));
            break;
          case "linia":
            escena.LlistaFigures.add(new Linia(Integer.parseInt(items[1]), Integer.parseInt(items[2]), Integer.parseInt(items[3]), Integer.parseInt(items[4]), items[5]));
            break;
          case "dimensions":
            escena.dimensions(Integer.parseInt(items[1]), Integer.parseInt(items[2]));
            break;
          default:
            System.out.println("IMPORTAR FIGURA TEXTO ERROR");
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        fr.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    return escena;

  }

  public Escena importFromObj(String file) {

    /**
     * **********************************************************************
     * TO-DO: Mètode a implementar: * Llegirà el fitxer indicat, en format
     * d'objectes seriats, i importa * la llista de figures. *
     * **********************************************************************
     */
    // Comentar o elimina aquestes línies quan implementeu el mètode
    Escena escena = new Escena();

    FileInputStream fis = null;

    try {
      fis = new FileInputStream(file);
      ObjectInputStream ois = new ObjectInputStream(fis);

      while (fis.available() > 0) {
        Figura f = (Figura) ois.readObject(); // Igual simplemente hay que añadirlas al arraylist de escena
        escena.LlistaFigures.add(f);
      }
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    } finally {
      try {
        fis.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    return escena;

  }

  public Boolean exportText(Escena escena, String file) {

    /**
     * ************************************************
     * TO-DO: Mètode a implementar: * exporta l'escena donada a un fitxer de
     * text, * en format per poder ser importat posteriorment.*
     * ************************************************
     */
    // Comentar o elimina aquestes línies quan implementeu el mètode
    boolean out = false;
    FileWriter fw = null;
    try {
      fw = new FileWriter(file);
      BufferedWriter bfw = new BufferedWriter(fw);
      bfw.write(escena.getDimensionsAsText() + "\n");

      for (Figura f : escena.LlistaFigures) {
        bfw.write(f.getAsText() + "\n");
      }

    } catch (IOException | NumberFormatException e) {
      e.printStackTrace();
    } finally {
      try {
        fw.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    return out;

  }

  public Boolean exportObj(Escena escena, String file) {

    /**
     * **********************************************************
     * TO-DO: Mètode a implementar: * exporta l'escena donada a un fitxer
     * binari d'objectes, * per poder ser importat posteriorment. *
     * **********************************************************
     */
    // Comentar o elimina aquestes línies quan implementeu el mètode
    boolean out = false;

    FileOutputStream fos = null;

    try {
      fos = new FileOutputStream(file);
      ObjectOutputStream oos = new ObjectOutputStream(fos);

      for (Figura f : escena.LlistaFigures) {
        oos.writeObject(f);
      }
      out = true;
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        fos.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    return out;

  }

  public Boolean exportSVG(Escena escena, String file) {
    /**
     * **********************************************************
     * TO-DO: Mètode a implementar: * exporta l'escena donada a un fitxer
     * SVG (format XML). * El fitxer s'haurà de poder obrir amb Inkscape. *
     * **********************************************************
     */
        /*
            <?xmlversion="1.0"encoding="UTF-8"standalone="no"?> 2 <svgheight="500"width="500">
            <rect fill="#ccccee" height="480" width="480" x="10" y="10"/>
            <circle cx="250" cy="250" fill="#aaaaaa" r="100"/>
            <line stroke="#aaaaaa" stroke-width="3" x1="50" x2="450" y1="250" y2="250"/>
            <line stroke="#aaaaaa" stroke-width="3" x1="50" x2="50" y1="50" y2="
            450"/>
            <line stroke="#aaaaaa" stroke-width="3" x1="450" x2="450" y1="40" y2= "450"/>
            </svg>
         */

    // Comentar o elimina aquestes línies quan implementeu el mètode
    boolean out = false;

    try {
      Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

      Element raiz = doc.createElement("svg");
      raiz.setAttribute("height", String.valueOf(escena.getY()));
      raiz.setAttribute("width", String.valueOf(escena.getX()));

      doc.appendChild(raiz);

      for (Figura f : escena.LlistaFigures) {
        raiz.appendChild(f.getAsXML(doc));
      }

      Transformer trans = TransformerFactory.newInstance().newTransformer();
      DOMSource source = new DOMSource(doc);
      StreamResult result = new StreamResult(new FileOutputStream(file));

      trans.transform(source, result);
      out = true;
    } catch (ParserConfigurationException | FileNotFoundException | TransformerException e) {
      e.printStackTrace();
    }
    return out;

  }

  public Boolean exportJSON(Escena scene, String filename) {

    /**
     * **********************************************
     * TO-DO: Mètode a implementar: * exporta l'escena donada a un fitxer
     * JSON. * **********************************************
     */
    // Comentar o elimina aquestes línies quan implementeu el mètode
    boolean out = false;

    FileWriter fw = null;

    try {
      JSONObject escena = new JSONObject();
      escena.put("width", scene.getX());
      escena.put("height", scene.getY());
      JSONArray figuras = new JSONArray();
      for (Figura f : scene.LlistaFigures) {
        figuras.put(f.getAsJSON());
      }
      escena.put("figures", figuras);

      JSONObject raiz = new JSONObject();
      raiz.put("escena", escena);

      fw = new FileWriter(filename);
      fw.write(raiz.toString(4));
      fw.close();

      out = true;
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        fw.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    return out;

  }

}

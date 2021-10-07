package com.ieseljust.ad.figures;

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
    /**
     * **************************************
     * TO-DO: Mètode a implementar: * Retorna si el fitxer existeix o no *
     * ***************************************
     */

    // Comentar o elimina aquestes línies quan implementeu el mètode
    return false;

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
    Escena escena = null;

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
    Escena escena = null;

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

    return out;

  }

  public Boolean exportJSON(Escena escena, String filename) {

    /**
     * **********************************************
     * TO-DO: Mètode a implementar: * exporta l'escena donada a un fitxer
     * JSON. * **********************************************
     */
    // Comentar o elimina aquestes línies quan implementeu el mètode
    boolean out = false;

    return out;

  }

}

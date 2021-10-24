package com.ieseljust.ad.figures;

// Imports per a entrada de dades

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import java.util.Scanner;


//public class App extends Application{
public class App extends Application {
    /* La nostra classe App principal és una
    subclasse de Application, per disposar de
    tot el context gràfic que  aquesta oferix*/

  // PrimaryStage és el contenidor de l'escena en JavaFX
  static Stage primaryStage;

  // Escena és la classe que representarà la nostra escena
  // Definim un objecte AppEscena d'aquesta classe
  static Escena AppEscena;


  public static void main(String[] args) {


    Scanner keyboard = new Scanner(System.in);

    // FileManager serà el gestor d'emmagatzemamanet
    FileManager fm;

    // Dimensions de la finestra
    int width, height;

    try {
      // Si s'especifiquen les dimensions, les inicialitzem
      width = Integer.parseInt(args[0]);
      height = Integer.parseInt(args[1]);
    } catch (Exception e) {
      // Si no s'especifiquen, la imatge serà de 800x600
      width = 800;
      height = 600;
    }

    // Inicialitzem l'escena, amb una finestra de width x height
    AppEscena = new Escena(width, height);

    // Iniciem el bucle infinit fins que escriga "quit" o dibuixe l'escena.
    String figura;

    do {

      // Preguntem la següent figura a emmagatzemar
      System.out.print(Colors.Blue + "# Figura: " + Colors.Reset);
      String ordre = keyboard.nextLine();

      // Separem l'ordre introduida pel teclat en la forma: "Figura Posicio Mida Color"
      String[] components = ordre.split(" ");

      figura = components[0];

      switch (figura) {
        case "cercle":
          // Creació d'una figura de la classe cercle
          try {
            // Extraiem les dimensions
            int x = Integer.parseInt((components[1]));
            int y = Integer.parseInt((components[2]));
            int radi = Integer.parseInt((components[3]));
            String color = components[4];

            // Si tot és correcte creem la figura cercle
            Cercle nouCercle = new Cercle(x, y, radi, color);
            // I l'afegim a la llista
            AppEscena.add(nouCercle);


          } catch (Exception e) {
            // Si s'ha produït algun error als paràmetres, s'indica un error de sintaxi
            System.out.println("\u001B[31m Error de sintaxi. La sintaxi correcta és:\ncercle x y radi color \u001B[0m");
          }
          break;

        case "rectangle":
          // Creació d'una figura de la classe rectangle
          try {
            // Extraiem les dimensions
            int x = Integer.parseInt((components[1]));
            int y = Integer.parseInt((components[2]));
            int llarg = Integer.parseInt((components[3]));
            int alt = Integer.parseInt((components[4]));
            String color = components[5];

            // Si tot és correcte creem la figura rectangle
            Rectangle nouRectangle = new Rectangle(x, y, llarg, alt, color);
            // I l'afegim a la llista
            AppEscena.add(nouRectangle);


          } catch (Exception e) {
            // Si s'ha produït algun error als paràmetres, s'indica un error de sintaxi
            System.out.println("\u001B[31m Error de sintaxi. La sintaxi correcta és:\nrectangle x y llargària altura color \u001B[0m");
          }
          break;

        case "linia":
          // Creació d'una figura de la classe rectangle
          try {
            // Extraiem les dimensions
            int x = Integer.parseInt((components[1]));
            int y = Integer.parseInt((components[2]));
            int x2 = Integer.parseInt((components[3]));
            int y2 = Integer.parseInt((components[4]));
            String color = components[5];

            // Si tot és correcte creem la figura linia
            Linia liniaNova = new Linia(x, y, x2, y2, color);
            // I l'afegim a la llista
            AppEscena.add(liniaNova);


          } catch (Exception e) {
            // Si s'ha produït algun error als paràmetres, s'indica un error de sintaxi
            System.out.println("\u001B[31m Error de sintaxi. La sintaxi correcta és:\nlinia x y x2 y2 color \u001B[0m");
            //System.out.println(e);
          }
          break;

        case "dimensions":
          // Redimensiona el marc de la imatge
          try {
            int x = Integer.parseInt((components[1]));
            int y = Integer.parseInt((components[2]));
            AppEscena.dimensions(x, y);
            System.out.println("\u001B[32m OK \u001B[0m");
          } catch (Exception e) {
            System.out.println("\u001B[31m Error de sintaxi. La sintaxi correcta és:\ndimensions x y \u001B[0m");
          }

          break;

        case "import":
          // Instanciem el Gestor d'Emmagatzemamanet
          fm = new FileManager();

          // que llegirà del fitxer indicat
          // com a primer paràmetre (emmagatzemat en components[1])
          // i rep una nova escena
          if (!fm.Exists(components[1]))
            System.out.println("El fitxer no existeix");
          else {
            System.out.println("Important fitxer...");

            String extensio = components[1].substring(components[1].length() - 4);


            switch (extensio) {
              case ".txt": {
                Escena novaEscena = fm.importFromText(components[1]);
                // Si torna algun resultat, l'escena canvia per la nova
                if (!novaEscena.esBuida()) AppEscena = novaEscena;
              }
              break;
              case ".obj": {
                Escena novaEscena = fm.importFromObj(components[1]);
                // Si torna algun resultat, l'escena canvia per la nova
                if (!novaEscena.esBuida()) AppEscena = novaEscena;
              }

              break;

              default:
                System.out.println("Format no reconegut..");
                break;

            }
            AppEscena.renderText();

          }

          break;

        case "export":
          try {
            String extensio = components[1].substring(components[1].length() - 4);

            // Comprovem si el fitxer existeix
            fm = new FileManager();
            if (fm.Exists(components[1])) {
              // Si el fitxer ja existeix, eixim de l'exportació mostrant un error
              System.out.println("\u001B[31m El fitxer ja existeix\u001B[0m");
              break;

            }

            switch (extensio) {
              case ".txt":
                if (fm.exportText(AppEscena, components[1]))
                  System.out.println(Colors.Bright_Cyan + "Ok. Exportació Correcta a TXT" + Colors.Reset);
                else
                  System.out.println(Colors.Bright_Red + "Error. Exportació Incorrecta" + Colors.Reset);
                break;

              case ".obj":
                if (fm.exportObj(AppEscena, components[1]))
                  System.out.println(Colors.Bright_Cyan + "Ok. Exportació Correcta a OBJ" + Colors.Reset);
                else
                  System.out.println(Colors.Bright_Red + "Error. Exportació Incorrecta" + Colors.Reset);
                break;

              case ".svg":
                if (fm.exportSVG(AppEscena, components[1]))
                  System.out.println(Colors.Bright_Cyan + "Ok. Exportació Correcta a SVG" + Colors.Reset);
                else
                  System.out.println(Colors.Bright_Red + "Error. Exportació Incorrecta" + Colors.Reset);
                break;

              case "json":
                if (fm.exportJSON(AppEscena, components[1]))
                  System.out.println(Colors.Bright_Cyan + "Ok. Exportació Correcta a JSON" + Colors.Reset);
                else
                  System.out.println(Colors.Bright_Red + "Error. Exportació Incorrecta" + Colors.Reset);
                break;

              default:
                System.out.println("\u001B[31m Format no suportat\u001B[0m");
                break;


            }

          } catch (Exception e) {
            System.out.println("\u001B[31m Error d'exportació\u001B[0m");
          }

          break;

        case "llista":
          // Si l'ordre indicada és llista, imprimirem la llista de figures
          AppEscena.renderText();
          break;

        case "draw":
          // Si l'ordre indicada és draw, carregarem l'aplicació JavaFX
          // El mètode "launch" està definit en Application, pel que no l'hem de definir.
          // El que sí que implementarem és el mètode start (amb @override), que és
          // qui s'encarregarà de dibuixar la nostra escena.
          launch(args);
          System.exit(0);

          break;

        case "quit":
          System.out.println(Colors.Magenta + "Acabant el programa." + Colors.Reset);
          break;
        default:
          // Si hem arribat aci, l'ordre no es coneix
          System.out.println(Colors.Bright_Red + "Figura no reconeguda" + Colors.Reset);
      }


    } while (!figura.equals("quit"));
    System.exit(0);
  }


  @Override
  public void start(Stage primaryStage) {
        /*
        En aquesta funció sobreescrivim la funció "start" de la classe Application.
        Aquesta funció s'encarrega de dibuixar la nostra escena.
         */

    // Preparem el context gràfic (GraphicsContent) sobre el que dibuixarem
    Group root = new Group();
    Scene scene = new Scene(root);
    Canvas canvas = new Canvas(App.AppEscena.getX(), App.AppEscena.getY());
    GraphicsContext gc = canvas.getGraphicsContext2D();

    // Una vegada tenim el context gràfic preparat, el passem al mètode
    // renderScene de la nostra escena, per a que aquest mètode
    // dibuixe sobre el context els diferents elements que té.
    App.AppEscena.renderScene(gc);

    // Una vegada tenim l'escena dibuixada al context gràfic del canvas,
    // Afegim aquest a la finestra principal, establim algunes propietats,
    // com el títol, i finalment, la dibuixem (Show)
    root.getChildren().add(canvas);
    primaryStage.setTitle("Escena");
    primaryStage.setScene(scene);
    primaryStage.show();
  }


}
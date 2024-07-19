package dictionnaire;

import java.util.Scanner;

public abstract class Affichage {
    public final static void afficherActions() {
        System.out.println("Bienvenue dans le système de gestion d'un dictionnaire à partir d'un CSV.");
        System.out.println("Pour afficher la liste des mots présents dans le dictionnaire, vous pouvez taper 1. Tapez 2 pour enregistrer la liste de mots, ");
        System.out.println("tapez 3 pour ajouter un nouveau mot et sa définition et taper un mot pour connaitre sa définition.");
        System.out.println("Enfin, vous pouvez taper 'stop' lorsque vous souhaitez arrêter le programme.");
        System.out.print("Quel est votre choix ? (1, 2, 3, mot ou stop) : ");
        Scanner scan = new Scanner(System.in);

        String selection = scan.nextLine();

        Dictionnaire monDictionnaire = new Dictionnaire();
        monDictionnaire.initialiser("./entree.csv");

        selection = selection.toLowerCase();

        while (!selection.equals("stop")) {
            if (selection.equals("1")) {
                monDictionnaire.afficheMotEtDef();
            }
            else if (selection.equals("2")) {
                monDictionnaire.sauvegarderListeMot("./sortie.csv");
            }
            else if (selection.equals("3")) {
                monDictionnaire.ajouterMotEtDef(scan);
            }
            else if (selection.equals("stop")) {
                continue;
            }
            else {
                monDictionnaire.getDefinitionByMot(selection);
            }

            System.out.print("Quel est votre choix ? (1, 2, 3, mot ou stop) : ");
            selection = scan.nextLine();
        }

        scan.close();
    } 
}
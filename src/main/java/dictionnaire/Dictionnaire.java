package dictionnaire;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

public class Dictionnaire {
    private final Map<String, String> motDef = new TreeMap<>();

    public void initialiser(String cheminFichier) {
        try (FileReader fileReader = new FileReader(cheminFichier); CSVReader csvReader = new CSVReader(fileReader);) {
            String[] ligne = csvReader.readNext();

            while (ligne != null) {
                String mot = ligne[0].toLowerCase();
                String definition = ligne[1];
                motDef.put(mot, definition);
                ligne = csvReader.readNext();
            }
        } catch (FileNotFoundException e) {
            ErreurCustom.afficheErreur();
            e.printStackTrace();
        } catch (IOException e) {
            ErreurCustom.afficheErreur();
            e.printStackTrace();
        } catch (CsvValidationException e) {
            ErreurCustom.afficheErreur();
            e.printStackTrace();
        }
    }

    public final void afficheMotEtDef() {
        Set<String> keySet = motDef.keySet();

        if (keySet.size() > 0) {
            for (String mot : keySet) {
                System.out.println(mot + ", Définition : " + motDef.get(mot));
            }
        }
        else {
            System.out.println("Aucuns mots dans le dictionnaire");
        }
    }

    public final void sauvegarderListeMot(String cheminFichier) {
        Set<String> keySet = motDef.keySet();

        if (keySet.size() > 0) {
            try (FileWriter fileWriter = new FileWriter(cheminFichier)) {
                CSVWriter csvWriter = new CSVWriter(fileWriter);
    
                String[] firstLine = { "Mot", "Definition" };
    
                csvWriter.writeNext(firstLine);
    
                for (String key : keySet) {
                    String[] currentLine = { key, motDef.get(key) };
                    csvWriter.writeNext(currentLine);
                }
    
                csvWriter.close();
            } catch (FileNotFoundException e) {
                ErreurCustom.afficheErreur();
                e.printStackTrace();
            } catch (IOException e) {
                ErreurCustom.afficheErreur();
                e.printStackTrace();
            }

            System.out.println("Le fichier 'sortie.csv' a bien été enregistré");
        }
        else {
            System.out.println("Aucuns mots dans le dictionnaire");
        }
    }

    public final void getDefinitionByMot(String mot) {
        String definition = motDef.get(mot);

        if (definition != null) {
            System.out.println("Définition du mot " + mot + " : " + definition);
        }
        else {
            System.out.println("Le mot " + mot + " n'est pas dans le dictionnaire, impossible de connaitre sa définition");
        }
    }
}
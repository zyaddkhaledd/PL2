package AdminManagement;

import java.io.*;
import java.util.ArrayList;

public class TypeManager {
    private ArrayList<String> types;
    private static final String FILE_PATH = "src/AdminManagement/DF/types.txt";

    public TypeManager() {
        this.types = loadTypes();
    }

    public void addType(String typeName) {
        types.add(typeName);
        saveTypes();
    }

    public void deleteType(String typeName) {
        types.remove(typeName);
        saveTypes();
    }

    private void saveTypes() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            writer.println("TypeName");
            for (String t : types) {
                writer.println(t);
            }
        } catch (IOException e) { }
    }

    private ArrayList<String> loadTypes() {
        ArrayList<String> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                list.add(line);
            }
        } catch (IOException e) { }
        return list;
    }

    public ArrayList<String> getAllTypes() {
        return types;
    }
}
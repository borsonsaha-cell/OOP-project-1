package File;
import java.io.*;

public class FileIO {
    public static void saveInFile(String data) {
        try {
            File folder = new File("HospitalRecords");
            if (!folder.exists()) folder.mkdir();
            FileWriter writer = new FileWriter("File/data.txt", false);
            writer.write(data);
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }
}
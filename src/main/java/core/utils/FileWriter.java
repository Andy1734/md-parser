package core.utils;

import java.io.*;
import java.util.Objects;

public class FileWriter {

    public static void write(String text) throws IOException {
        FileOutputStream fileOutputStream = FileWriter.class.getClassLoader().getResourceAsStream("output/index.html");
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileOutputStream())) {
            bufferedWriter.write(text);
        }
    }

}

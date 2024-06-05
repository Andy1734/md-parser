package core.utils;

import java.io.*;
import java.util.Objects;

public class FileReader {

    public static String read(String path) throws IOException {
        try(InputStream is = new FileInputStream(Objects.requireNonNull(FileReader.class.getClassLoader().getResource(path)).getFile())) {
            StringBuilder result = new StringBuilder();
            try(BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
                String line;
                while((line = br.readLine()) != null) {
                    result.append(line).append("\n");
                }
                return result.toString();
            }
        }
    }

}

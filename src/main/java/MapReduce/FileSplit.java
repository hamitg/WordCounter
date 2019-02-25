package MapReduce;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileSplit {

    final static int KB = 1024;
    final static int MB = 1024 * KB;

    public static List<File> splitFile(File f) throws IOException {
        int partCounter = 1;//I like to name parts from 001, 002, 003, ...
        //you can change it to 0 if you want 000, 001, ...

        int sizeOfFiles = 5 * MB;
        byte[] buffer = new byte[sizeOfFiles];

        String fileName = f.getName();

        List<File> files = new ArrayList<>();

        //try-with-resources to ensure closing stream
        try (FileInputStream fis = new FileInputStream(f);
             BufferedInputStream bis = new BufferedInputStream(fis)) {

            int bytesAmount = 0;
            while ((bytesAmount = bis.read(buffer)) > 0) {
                //write each chunk of data into separate file with different number in name
                String filePartName = String.format("%s.%03d", fileName, partCounter++);
                File newFile = new File(f.getParent(), filePartName);
                files.add(newFile);
                try (FileOutputStream out = new FileOutputStream(newFile)) {
                    out.write(buffer, 0, bytesAmount);
                }
            }
        }
        return files;
    }
}

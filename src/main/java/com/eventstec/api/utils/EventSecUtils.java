package com.eventstec.api.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Objects;

public class EventSecUtils {
    private static final int BUFFER_SIZE = 1024 * 8; // 8KB

    /**
     * Convert multipart to file.
     *
     * @param file the file
     * @return the file
     * @throws IOException the io exception
     */
    public static File convertMultipartToFile(MultipartFile file) throws IOException {
        File convFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    /**
     * Copy file.
     *
     * @param sourceFile      the source file
     * @param destinationPath the destination path
     * @return absolute path destination
     * @throws IOException the io exception
     */
    public static String copyFile(File sourceFile, String destinationPath) throws IOException {
        File destFile = new File(destinationPath);
        try (FileOutputStream fos = new FileOutputStream(destFile);
             FileInputStream fis = new FileInputStream(sourceFile)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
        }
        return destFile.getAbsolutePath();
    }
}

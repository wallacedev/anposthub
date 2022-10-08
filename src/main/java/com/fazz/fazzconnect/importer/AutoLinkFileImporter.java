package com.fazz.fazzconnect.importer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

@Component
public class AutoLinkFileImporter {

    @Value("${basePath}")
    private String basePath;

    public ArrayList<String> importAutoLinkFile(String workDirectory, String fileName) {
        var file = new File(String.format("%s/%s/anpost/%s.txt", basePath, workDirectory, fileName));

        var lines = new ArrayList<String>();
        Scanner myReader;
        try {
            myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                lines.add(myReader.nextLine());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return lines;
    }
}

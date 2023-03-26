package org.example.neledva;

import java.io.FileWriter;
import java.io.IOException;

class WriteFile {

    public void write(String equation, int countNum) {
        try (FileWriter file = new FileWriter("saveFile.txt", true)) {
            file.write("equation -> " + equation + " count_of_numbers: " + countNum + "\n");
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

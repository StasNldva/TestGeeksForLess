package org.example.neledva;

import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class WriteFileTest {

    @Test
    public void write() {
        String content = "smth for test!";
        int count = 4;
        try {
            File file = File.createTempFile("testfile", ".txt");
            FileWriter writer = new FileWriter(file);

            writer.write(content + " " + count);
            writer.close();

            // Read the contents of the file
            String fileContent = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));

            // Verify that the file was written correctly
            assertEquals(content + " " + count, fileContent);
        } catch (IOException e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }
}
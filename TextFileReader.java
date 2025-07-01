package com.wordcount;

import java.nio.file.*;
import java.io.IOException;

public class TextFileReader {
    public static String readFile(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }
}

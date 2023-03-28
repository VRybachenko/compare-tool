package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> serverList = new ArrayList<>();
        List<String> cloudList = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/server.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                serverList.add(line);
            }
            reader.close();

            reader = new BufferedReader(new FileReader("src/main/resources/cloud.txt"));
            while ((line = reader.readLine()) != null) {
                cloudList.add(line);
            }
            reader.close();
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return;
        }


        List<String> uniqueToFirstList = serverList.stream()
                .filter(s -> cloudList.stream()
                        .noneMatch(s2 -> s2.contains(s)))
                .collect(Collectors.toList());


        System.out.println("Unique to Server list:");
        uniqueToFirstList.forEach(t -> System.out.println(t));
//        System.out.println("\n Unique to Cloud list:");
//        uniqueToSecondList.forEach(t -> System.out.println(t));
    }
}
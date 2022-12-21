package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String json = readString("data.json");
        System.out.println(json);

        List<Employee> list = jsonToList(json);
        System.out.println(list);
    }

    private static List<Employee> jsonToList(String json) {
        JSONParser parser = new JSONParser();
        try {
            JSONArray jsonArray = (JSONArray) parser.parse(json);
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            List<Employee> employeeList = new ArrayList<>();
            for (Object obj : jsonArray.toArray()) {
                Employee employee = gson.fromJson(String.valueOf(obj), Employee.class);
                employeeList.add(employee);
            }
            return employeeList;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private static String readString(String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String s;
            StringBuilder result = new StringBuilder();
            while ((s = br.readLine()) !=null) {
                result.append(s);
            }
            return result.toString();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
}
package com.dataart.selenium.dataproviders;


import org.testng.annotations.DataProvider;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyDataProvider {
    @DataProvider(name="usersregister")
    public static Iterator<Object[]> CSVdata() throws IOException {
        List<Object[]> data = new ArrayList<Object[]>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/Data.csv")));
        String line = reader.readLine();
        while (line != null) {
            String[] split = line.split(";");
            data.add(split);
            line = reader.readLine();
        }
        return data.iterator();
    }
}

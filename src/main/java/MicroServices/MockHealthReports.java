package MicroServices;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class MockHealthReports {
    public static void readFile(String s){

        File f = new File(s);
        ArrayList<String> list = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(f));
            String line = null;
            while((line = reader.readLine()) != null) {
                list.add(line);

                // System.out.println(line);

                if (list.size() == 10000) {// when size reach 10000 send
                    //send and empty

                    //sendMessages(list);

                    list.clear();
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // test here
        // readFile("D:\\test.txt");
    }
}
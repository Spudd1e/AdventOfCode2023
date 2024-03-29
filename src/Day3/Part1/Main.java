package Day3.Part1;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/* DESCRIPTION
Find the numbers adjacent to symbols and adds them to the total sum
 */
public class Main {

    public static void main(String[] args) {
        Path input = Paths.get("./src/Day3/input.txt");
        try (BufferedReader br = Files.newBufferedReader(input)) {
            int sum = 0;
            String l;
            ArrayList<String> lines = new ArrayList<>();

            while ((l = br.readLine()) != null) {
                lines.add(l);
            }
            for(int i =0; i < lines.size(); i ++) {

                String currentLine = lines.get(i);
                String prevLine = "";
                String nextLine = "";
                if(i > 0)
                    prevLine = lines.get(i - 1);
                if(i < lines.size() - 1)
                    nextLine = lines.get(i + 1);

                String[] lineList = {prevLine, currentLine, nextLine};

                ArrayList<Integer> indexes = getIndexes(currentLine);
                for (int index : indexes) {
                    for (String s : lineList) {
                        Integer[] lineRes = getNumbers(s, index);
                        for (int num : lineRes) {
                            sum += num;
                        }
                    }
                }
            }
            System.out.println("Result: " + sum);


        } catch (
                IOException ioe) {
            ioe.printStackTrace();
        }
    }


    public static ArrayList<Integer> getIndexes(String line) {
        ArrayList<Integer> results = new ArrayList<>();
        for (int i = 0; i < line.length(); i++) {
            if (!Character.isDigit(line.charAt(i)) && line.charAt(i) != '.') {
                results.add(i);
            }
        }
        return results;
    }

    public static Integer[] getNumbers(String line, int index) {
        if (line.isEmpty()) {
            return new Integer[0];
        }
        ArrayList<Integer> results = new ArrayList<>();

        int[] surrounding = {index - 1, index, index + 1};
        for (int num : surrounding) {
            if (Character.isDigit(line.charAt(num))) {
                int pointer = num;

                while ( pointer > -1 &&  Character.isDigit(line.charAt(pointer))) {
                    pointer--;
                }
                pointer++;
                StringBuilder sb = new StringBuilder();

                while (pointer < line.length() && Character.isDigit(line.charAt(pointer))) {
                    sb.append(line.charAt(pointer));
                    pointer++;
                }
                if (!sb.toString().isEmpty())
                    results.add(Integer.parseInt(sb.toString()));
            }
        }
        ArrayList<Integer> endResults = new ArrayList<>();
        if (Character.isDigit(line.charAt(index))) {
            for (int res : results) {
                if (!endResults.contains(res)) {
                    endResults.add(res);
                }
            }
        } else {
            endResults = results;
        }


        return endResults.toArray(new Integer[0]);
    }
}
package jef5ez.advent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Day1 {

    public static int extraFuel(int startingFuel) {
        int total = 0;
        int curFuel = startingFuel;
        while (curFuel > 0) {
            curFuel = Math.max(0, (curFuel / 3) - 2);
            total += curFuel;
        }
        return total;
    }

    public static void main(String[] args) {
        InputStream day1 = Day1.class.getResourceAsStream("/day1.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(day1));
        int part1 = 0;
        int part2 = 0;
        try {
            while (reader.ready()) {
                String line = reader.readLine();
                int i = Integer.parseInt(line);
                int partFuel = (i / 3) - 2;
                part1 += partFuel;
                part2 += partFuel + extraFuel(partFuel);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("part1");
        System.out.println(part1);
        System.out.println("part2");
        System.out.println(part2);
    }
}
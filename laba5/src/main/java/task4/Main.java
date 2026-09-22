package task4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть URL сторінки: ");
        String urlAddress = scanner.nextLine();

        Map<String, Integer> tagFrequency = new HashMap<>();

        try {
            URLConnection connection =
                    URI.create(urlAddress).toURL().openConnection();

            connection.setRequestProperty(
                    "User-Agent",
                    "Mozilla/5.0"
            );

            try (BufferedReader reader =
                         new BufferedReader(
                                 new InputStreamReader(
                                         connection.getInputStream(),
                                         StandardCharsets.UTF_8))) {

                Pattern pattern =
                        Pattern.compile(
                                "<\\s*/?\\s*([a-zA-Z][a-zA-Z0-9:-]*)\\b[^>]*>"
                        );

                String line;

                while ((line = reader.readLine()) != null) {

                    Matcher matcher = pattern.matcher(line);

                    while (matcher.find()) {

                        String tag =
                                matcher.group(1).toLowerCase();

                        tagFrequency.put(
                                tag,
                                tagFrequency.getOrDefault(tag, 0) + 1
                        );
                    }
                }
            }

            System.out.println();
            System.out.println(
                    "===== ТЕГИ В ЛЕКСИКОГРАФІЧНОМУ ПОРЯДКУ ====="
            );

            Map<String, Integer> sortedByName =
                    new TreeMap<>(tagFrequency);

            for (Map.Entry<String, Integer> entry
                    : sortedByName.entrySet()) {

                System.out.println(
                        entry.getKey()
                                + " - "
                                + entry.getValue()
                );
            }

            System.out.println();
            System.out.println(
                    "===== ТЕГИ ЗА ЗРОСТАННЯМ ЧАСТОТИ ====="
            );

            List<Map.Entry<String, Integer>> sortedByFrequency =
                    new ArrayList<>(tagFrequency.entrySet());

            sortedByFrequency.sort(
                    Comparator
                            .comparingInt(
                                    Map.Entry<String, Integer>::getValue
                            )
                            .thenComparing(
                                    Map.Entry::getKey
                            )
            );

            for (Map.Entry<String, Integer> entry
                    : sortedByFrequency) {

                System.out.println(
                        entry.getKey()
                                + " - "
                                + entry.getValue()
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "Помилка роботи з URL: "
                            + e.getMessage()
            );
        }

        scanner.close();
    }
}
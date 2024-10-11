import java.io.BufferedReader;
import java.io.FileReader;

public class Main {
    public static void main(String[] args) {

        String line;


        int totalLines = 0;

        int googlebotCount = 0;
        int yandexbotCount = 0;

        try {

            FileReader fileReader = new FileReader("access.log");

            BufferedReader reader = new BufferedReader(fileReader);

            while ((line = reader.readLine()) != null) {
                totalLines++;


                String[] parts = line.split("\\s+");


                String userAgent = parts[10];


                if (userAgent.contains("(") && userAgent.contains(")")) {
                    String firstBrackets = userAgent.substring(userAgent.indexOf("(") + 1, userAgent.indexOf(")"));
                    String[] bracketParts = firstBrackets.split(";");

                    if (bracketParts.length >= 2) {
                        String cleanedFragment = bracketParts[1].trim();
                        String secondFragment = cleanedFragment.substring(0, cleanedFragment.indexOf("/"));


                        if (secondFragment.equals("Googlebot")) {
                            googlebotCount++;
                        } else if (secondFragment.equals("YandexBot")) {
                            yandexbotCount++;
                        }
                    }
                }

            }

            System.out.println("Доля запросов от Googlebot: " + (double) googlebotCount / totalLines * 100 + "%");
            System.out.println("Доля запросов от YandexBot: " + (double) yandexbotCount / totalLines * 100 + "%");

            reader.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
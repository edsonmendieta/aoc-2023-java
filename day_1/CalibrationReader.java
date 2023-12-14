import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CalibrationReader {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String userInput;

        do {
            System.out.print("Enter a path to a .txt file: ");
            userInput = scanner.nextLine();

            if (isValidFile(userInput)) {
                System.out.println("The sum of all of the calibration values is " + extractCalibrationSum(userInput));

                break;
            } else {
                System.out.println("That's not a valid file path. Make sure the path is correct and is a text file ending in '.txt'");
            }
        } while (true); 

        scanner.close();
    }

    private static boolean isValidFile(String filePath) {
        File userFile = new File(filePath);

        return filePath.endsWith(".txt") && userFile.isFile() && userFile.exists();
    }

    private static int extractCalibrationSum(String filePath) throws IOException {
        FileReader fileReader = new FileReader(filePath); 
        BufferedReader br = new BufferedReader(fileReader);

        int sum = 0;
        String line;

        while((line = br.readLine()) != null) {
            // now we parse the line for the 1st and last digits
            sum += firstLastDigitsValue(line);
        }
       
        br.close();
        return sum;
    }

    private static int firstLastDigitsValue(String line) {
        String lineNumbers = line.replaceAll("[^0-9]", "");
        String value = lineNumbers.charAt(0) + "" + lineNumbers.charAt(lineNumbers.length() - 1);

        return Integer.parseInt(value);
    }

 
}
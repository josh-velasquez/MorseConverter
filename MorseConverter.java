import java.util.Scanner;
import java.util.Objects;
import java.util.HashMap;
import java.util.Map;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.util.concurrent.TimeUnit;
import java.io.BufferedReader;
import java.io.FileReader;

public class MorseConverter {
    public static Map<String, String> morseMap;

    /**
     * Initializes the dictionary
     */
    private static Map<String, String> populateDictionary() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("", "");
        map.put("", null);
        map.put("", "");
        map.put("a", ".-");
        map.put("b", "-...");
        map.put("c", "-.-.");
        map.put("d", "-..");
        map.put("e", ".");
        map.put("f", "..-.");
        map.put("g", "--.");
        map.put("h", "....");
        map.put("i", "..");
        map.put("j", ".---");
        map.put("k", "-.-");
        map.put("l", ".-..");
        map.put("m", "--");
        map.put("n", "-.");
        map.put("o", "---");
        map.put("p", ".--.");
        map.put("q", "--.-");
        map.put("r", ".-.");
        map.put("s", "...");
        map.put("t", "-");
        map.put("u", "..-");
        map.put("v", "...-");
        map.put("w", ".--");
        map.put("x", "-..-");
        map.put("y", "-.--");
        map.put("z", "--..");
        map.put("0", "-----");
        map.put("1", ".----");
        map.put("2", "..---");
        map.put("3", "...--");
        map.put("4", "....-");
        map.put("5", ".....");
        map.put("6", "-....");
        map.put("7", "--...");
        map.put("8", "---..");
        map.put("9", "----.");
        return map;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        morseMap = populateDictionary();
        int userChoice = -1;
        while (userChoice != 0) {
            System.out.println("\n\n###########################################################");
            System.out.print("Morse Converter \n" + "0. Exit \n" + "1. Morse to string \n" + "2. String to morse \n"
                    + "3. Morse file to string \n" + "4. String file to morse \n" + "5. Play morse sound \n"
                    + "6. Play morse light \n" + ">> ");
            userChoice = input.nextInt();
            switch (userChoice) {
            case 0:
                break;
            case 1:
                MorseToString();
                break;
            case 2:
                stringToMorse();
                break;
            case 3:
                // test();
                fileMorseToString();
                break;
            case 4:
                fileStringToMorse();
                break;
            case 5:
                playMorseSound();
                break;
            default:
                System.out.println("Invalid input.");
            }
        }
    }

    /**
     * Converts morse code to string. Input is separated by spaces for every letter
     */
    private static void MorseToString() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter morse to convert (- .)");
        System.out.print(">> ");
        String morse = input.nextLine();
        String output = convertMorseToString(morse);
        System.out.println("String: \n" + output);
    }

    /**
     * Converts morst code to string by splitting the morse code and mapping it to
     * the dictionary
     */
    private static String convertMorseToString(String morse) {
        String output = "";
        String[] morseString = morse.split(" ");
        String value;
        for (int i = 0; i < morseString.length; i++) {
            value = morseString[i];
            if (value != null || value != " ") {
                output += getKey(value);
            }
            output += " ";
        }
        return output;
    }

    /**
     * Gets the letter based on the morse code value
     */
    private static String getKey(String morse) {
        for (String key : morseMap.keySet()) {
            if (Objects.equals(morseMap.get(key), morse)) {
                return key;
            }
        }
        return null;
    }

    /**
     * Gets the morse code value based on the letter
     */
    private static String getVal(String letter) {
        return morseMap.get(letter);
    }

    /**
     * Converts string to morse code
     */
    private static void stringToMorse() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter string to convert");
        System.out.print(">> ");
        String morse = input.nextLine().toLowerCase();
        String output = convertStringToMorse(morse);
        System.out.println("Morse Code: \n" + output);
    }

    /**
     * Converts string to morse. Maps the dictionary keys to its value.
     */
    private static String convertStringToMorse(String str) {
        String output = "";
        String value;
        for (int i = 0; i < str.length(); i++) {
            value = Character.toString(str.charAt(i));
            if (!value.equals(" ")) {
                output += getVal(value);
            }
            output += " ";
        }
        return output;
    }

    /**
     * Reads files and converts contents to morse
     */
    private static void fileStringToMorse() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter file name");
        System.out.print(">> ");
        String filePath = input.nextLine();
        BufferedReader reader;
        String translatedLine = "";
        try {
            reader = new BufferedReader(new FileReader(filePath));
            String line = reader.readLine();
            while (line != null) {
                translatedLine += convertStringToMorse(line);
                line = reader.readLine();
            }
            reader.close();

            System.out.println("Translated: \n" + translatedLine);
        } catch (Exception e) {
            System.out.println("Error reading file");
        }

    }

    /**
     * Reads files and converts morse code files to string
     */
    private static void fileMorseToString() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter file name");
        System.out.print(">> ");
        String filePath = input.nextLine();
        String translatedLine = "";
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(filePath));
            String line = reader.readLine();
            while (line != null) {
                translatedLine += convertMorseToString(line);
                line = reader.readLine();
            }
            reader.close();

            System.out.println("Translated: \n" + translatedLine);
        } catch (Exception e) {
            System.out.println("Error reading file");
        }

    }

    /**
     * Plays the morse code
     */
    private static void playMorseSound() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter morse code");
        System.out.print(">> ");
        String morse = input.nextLine();
        String[] codes = morse.split("");
        for (int i = 0; i < codes.length; i++) {
            // System.out.println("TEST |" + codes[i] + "|");
            if (codes[i].equals(".")) {
                playDot();
                try {
                    TimeUnit.MILLISECONDS.sleep(400);
                } catch (Exception e) {
                    System.out.println("Failed to delay");
                }
            } else if (codes[i].equals("-")) {
                playDash();
                try {
                    TimeUnit.MILLISECONDS.sleep(400);
                } catch (Exception e) {
                    System.out.println("Failed to delay");
                }
            } else if (codes[i].equals(" ")) {
                try {
                    TimeUnit.MILLISECONDS.sleep(700);
                } catch (Exception e) {
                    System.out.println("Failed to delay");
                }
            }
        }
    }

    /**
     * Plays the dot sound when reading the morse code
     */
    private static void playDot() {
        try {
            String dotPath = System.getProperty("user.dir") + "/dot.wav";
            AudioInputStream audio = AudioSystem.getAudioInputStream(new File(dotPath).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    /**
     * Plays the dash sound when reading the morse code
     */
    private static void playDash() {
        try {
            String dashPath = System.getProperty("user.dir") + "/dash.wav";
            AudioInputStream audio = AudioSystem.getAudioInputStream(new File(dashPath).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    private static void createWavFile() {
        // TO-DO
        // Auto create a wav file when translated. File name is the date and time
    }
}
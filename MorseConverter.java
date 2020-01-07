
import java.util.*;

public class MorseConverter {
    public static Map<String,String> morseMap;

    private static Map<String,String> populateDictionary() {
        Map<String,String> map = new HashMap<String,String>();
        map.put("a",".-");
        map.put("b","-...");
        map.put("c","-.-.");
        map.put("d","-..");
        map.put("e",".");
        map.put("f","..-.");
        map.put("g","--.");
        map.put("h","....");
        map.put("i","..");
        map.put("j",".---");
        map.put("k","-.-");
        map.put("l",".-..");
        map.put("m","--");
        map.put("n","-.");
        map.put("o","---");
        map.put("p",".--.");
        map.put("q","--.-");
        map.put("r",".-.");
        map.put("s","...");
        map.put("t","-");
        map.put("u","..-");
        map.put("v","...-");
        map.put("w",".--");
        map.put("x","-..-");
        map.put("y","-.--");
        map.put("z","--..");
        map.put("0","-----");
        map.put("1",".----");
        map.put("2","..---");
        map.put("3","...--");
        map.put("4","....-");
        map.put("5",".....");
        map.put("6","-....");
        map.put("7","--...");
        map.put("8","---..");
        map.put("9","----.");
        return map;
    }


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        morseMap = populateDictionary();
        int userChoice = -1;
        while(userChoice != 0) {
            System.out.println("\n\n###########################################################");
            System.out.print("Morse Converter \n" +
                                "0. Exit \n" +
                                "1. Morse to string \n" +
                                "2. String to morse \n" +
                                ">> ");
            userChoice = input.nextInt();
            switch(userChoice) {
                case 0:
                    break;
                case 1:
                    MorseToString();
                    break;
                case 2:
                    StringToMorse();
                    break;
                default:
                    System.out.println("Invalid input.");
            }
        }
    }

    private static void MorseToString() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter morse to convert (- .)");
        System.out.print(">> ");
        String morse = input.nextLine();
        String output = "";
        String[] morseString = morse.split(" ");
        
        for (int i = 0; i< morseString.length; i++) {
            output += getKey(morseString[i]) + " ";
        }
        System.out.println("String: " + output);
    }

    private static String getKey(String morse) {
        for(String key : morseMap.keySet()) {
            if (Objects.equals(morseMap.get(key),morse)) {
                return key;
            }
        }
        return null;
    }

    private static String getVal(String letter) {
        return morseMap.get(letter);
    }

    private static void StringToMorse() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter string to convert");
        System.out.print(">> ");
        String morse = input.nextLine();
        String output = "";
        for (int i = 0; i < morse.length(); i++) {
            String value = Character.toString(morse.charAt(i));
            output += getVal(value) + " ";
        }
        System.out.println("Morse Code: " + output);
    }
}
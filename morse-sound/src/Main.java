import javax.sound.sampled.LineUnavailableException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) throws LineUnavailableException, InterruptedException {
        Morse ms = new Morse();

        System.out.println("Write something: ");
        String word = scan.nextLine();
        //normalizing letters
        word = Normalizer.normalize(word, Normalizer.Form.NFD)
                .replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        List<String> morse = new ArrayList<>();
        for (char c:  word.toLowerCase().toCharArray()) {
            morse.add(ms.toMorse(c));
        }
        System.out.println("Original text: " + word);
        System.out.print("Morse text: ");
        for(String s: morse){
            System.out.print(s + " ");
            ms.playMorseSound(s);
        }
        System.out.println();
        String backToText = "";
        for(String s: morse) {
            backToText += ms.toASCII(s);
        }
        System.out.println("Recoverd text: " + backToText);
    }
}
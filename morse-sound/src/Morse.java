import javax.sound.sampled.*;

import static javax.sound.sampled.FloatControl.Type.SAMPLE_RATE;

public class Morse {
    public String toMorse(final char c) {
        int ascii = Integer.valueOf(c);
        if(ascii > 47 && ascii < 58)
            return numberToMorse(c);
        else if(ascii > 96 && ascii < 123)
            return letterToMorse(c);
        return "/";
    }
    public char toASCII(final String str) {
        if(str.equals("/")) return ' ';
        switch (str) {
            case "-----": return '0';
            case ".----": return '1';
            case "..---": return '2';
            case "...--": return '3';
            case "....-": return '4';
            case ".....": return '5';
            case "-....": return '6';
            case "--...": return '7';
            case "---..": return '8';
            case "----.": return '9';
            case ".-": return 'a';
            case "-...": return 'b';
            case "-.-.": return 'c';
            case "-..": return 'd';
            case ".": return 'e';
            case "..-.": return 'f';
            case "--.": return 'g';
            case "....": return 'h';
            case "..": return 'i';
            case ".---": return 'j';
            case "-.-": return 'k';
            case ".-..": return 'l';
            case "--": return 'm';
            case "-.": return 'n';
            case "---": return 'o';
            case ".--.": return 'p';
            case "--.-": return 'q';
            case ".-.": return 'r';
            case "...": return 's';
            case "-": return 't';
            case "..-": return 'u';
            case "...-": return 'v';
            case ".--": return 'w';
            case "-..-": return 'x';
            case "-.--": return 'y';
            case "--..": return 'z';
            default: return '\0';
        }
    }
    private String numberToMorse(final char c) {
        switch (c) {
            case '0': return "-----";
            case '1': return ".----";
            case '2': return "..---";
            case '3': return "...--";
            case '4': return "....-";
            case '5': return ".....";
            case '6': return "-....";
            case '7': return "--...";
            case '8': return "---..";
            case '9': return "----.";
            default: return null;
        }
    }
    private String letterToMorse(final char c) {
        switch (c) {
            case 'a': return ".-";
            case 'b': return "-...";
            case 'c': return "-.-.";
            case 'd': return "-..";
            case 'e': return ".";
            case 'f': return "..-.";
            case 'g': return "--.";
            case 'h': return "....";
            case 'i': return "..";
            case 'j': return ".---";
            case 'k': return "-.-";
            case 'l': return ".-..";
            case 'm': return "--";
            case 'n': return "-.";
            case 'o': return "---";
            case 'p': return ".--.";
            case 'q': return "--.-";
            case 'r': return ".-.";
            case 's': return "...";
            case 't': return "-";
            case 'u': return "..-";
            case 'v': return "...-";
            case 'w': return ".--";
            case 'x': return "-..-";
            case 'y': return "-.--";
            case 'z': return "--..";
            default: return null;
        }
    }
    public void playMorseSound(final String morseText) throws LineUnavailableException, InterruptedException {
        for (char c : morseText.toCharArray()) {
            switch (c) {
                case '.': //beep for '.'
                    playTone(800, 200);
                    Thread.sleep(100);
                    break;
                case '-': //beep for '-'
                    playTone(800, 600);
                    Thread.sleep(100);
                    break;
                case '/': //beep for '/'
                    Thread.sleep(300);
                    break;
            }
        }
    }
    private static void playTone(int hz, int durationMs) throws LineUnavailableException {
        byte[] buf = new byte[1];
        AudioFormat af = new AudioFormat(8000.0F, 8, 1, true, false);
        SourceDataLine sdl = AudioSystem.getSourceDataLine(af);
        sdl.open(af);
        sdl.start();
        for (int i = 0; i < durationMs * 8; i++) {
            double angle = i / (8000.0 / hz) * 2.0 * Math.PI;
            buf[0] = (byte) (Math.sin(angle) * 10);
            sdl.write(buf, 0, 1);
        }
        sdl.drain();
        sdl.stop();
        sdl.close();
    }
}

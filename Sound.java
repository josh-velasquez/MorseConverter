import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {

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
}
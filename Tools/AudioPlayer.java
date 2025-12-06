package Tools;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;



public class AudioPlayer{
    public static void playAudio(String filepath) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        
        try {
            File file = new File(filepath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip

            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();

        } catch (UnsupportedAudioFileException e) {
            System.out.println("Error: The file format is not supported.");
        } catch (IOException e) {
            System.out.println("Error: Could not read the audio file.");
        } catch (LineUnavailableException e) {
            System.out.println("Error: Audio line unavailable.");
        }
    }
}
package Engine.Tools;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;



public class AudioPlayer{
    public static void playAudio(String filepath){
        
        try {
            File file = new File(filepath);
            System.out.print(file.getAbsolutePath());
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);

            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();

        } catch (UnsupportedAudioFileException e) {
            System.out.println("Error: The file format is not supported.");
        } catch (IOException e) {
            System.out.println("Error: Could not read the audio file.");
        } catch (LineUnavailableException e) {
            System.out.println("Error: Audio line unavailable." + e.getMessage());
            e.printStackTrace();
        }
    }
}
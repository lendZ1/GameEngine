package Tools;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;



public class AudioPlayer{
    public static void playAudio(String filepath) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        
        File file = new File(filepath);
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
    }
}
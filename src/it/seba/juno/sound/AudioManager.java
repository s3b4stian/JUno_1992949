package it.seba.juno.sound;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioManager {

    private boolean sound = true;

    private static AudioManager instance;
    private Map<String, byte[]> sounds;

    public static AudioManager getInstance() {
        if (instance == null)
            instance = new AudioManager();
        return instance;
    }

    private AudioManager() {
        sounds = new HashMap<String, byte[]>();
    }

    public boolean isSound() {
        return sound;
    }

    public void setSound(boolean sound) {
        this.sound = sound;
    }

    public void addToPlayList(String key, String filename) {
        Path path = Paths.get(filename);

        try {
            sounds.put(key, Files.readAllBytes(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void playSoundEffect(String key) {

        if (sound) {
            try {

                InputStream in = new ByteArrayInputStream(sounds.get(key));
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(in);
                Clip clip = AudioSystem.getClip();
                clip.open(audioIn);
                clip.start();
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (UnsupportedAudioFileException e1) {
                e1.printStackTrace();
            } catch (LineUnavailableException e1) {
                e1.printStackTrace();
            }
        }
    }
}

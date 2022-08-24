package it.seba.juno.manger;

import java.io.ByteArrayInputStream;
import java.io.File;
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

/**
 * Provide a way to play sound in game, started from code present in UniTelma,
 * implemented using singleton pattern.
 *
 * <p>
 * All sound tracks should be placed in resources/sound folder
 * </p>
 *
 * @author Sebastian Rapetti
 *
 */
public class AudioManager {

    private boolean sound = true;

    private static AudioManager instance;
    private Map<String, byte[]> sounds;

    /**
     * Returns the only one instance of the AudioManager.
     * 
     * @return the audio manager.
     */
    public static AudioManager getInstance() {
        if (instance == null)
            instance = new AudioManager();
        return instance;
    }

    /**
     * Class Constructor.
     */
    private AudioManager() {
        sounds = new HashMap<String, byte[]>();
    }

    /**
     * Check if sound is on or off.
     * 
     * @return true if the sound is on, false otherwise.
     */
    public boolean isSound() {
        return sound;
    }

    /**
     * Set the sound on or off.
     * 
     * @param sound the next state of the sound.
     */
    public void setSound(boolean sound) {
        this.sound = sound;
    }

    /**
     * Add a sound to play list, sound is loaded into memory so that when the
     * manager play doesn't need to read it from disk.
     * 
     * @param key      the name to retrieve the sound.
     * @param filename the filename to load the sound from disk.
     */
    public void addToPlayList(String key, String filename) {
        Path path = Paths.get((new File("")).getAbsolutePath() + "/resources/sound/" + filename);

        try {
            sounds.put(key, Files.readAllBytes(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Play a sound from play list.
     * 
     * @param key the name of the sound want to play.
     */
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

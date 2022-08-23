package it.seba.juno.manger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import it.seba.juno.model.OptionsModel;
import it.seba.juno.model.PlayersModel;
import it.seba.juno.model.PlayersProfileModel;

/**
 * Provide a way to save game preferences to disk, implemented using singleton
 * pattern.
 * 
 * @author Sebastian Rapetti
 *
 */
public class SerializationManager {

    private final String userDir;
    private final String jUnoDir;
    private final String profilesDir;

    private final String fileOptions;

    private static SerializationManager instance;

    /**
     * Returns the only one instance of the SerializationManager.
     * 
     * @return the serialization manager.
     */
    public static SerializationManager getInstance() {
        if (instance == null)
            instance = new SerializationManager();
        return instance;
    }

    /**
     * Class Constructor.
     */
    private SerializationManager() {
        // get user Directory, works on windows, have to be tested on Linux and Mac
        userDir = System.getProperty("user.home").replace("\\", "/");
        // save directory
        jUnoDir = userDir + "/JUno";
        // profiles directory
        profilesDir = jUnoDir + "/profiles";
        // option file, to save the options
        fileOptions = jUnoDir + "/options.bin";
    }

    /**
     * Returns the profiles directory.
     * 
     * @return the profiles directory.
     */
    public String getProfilesDir() {
        return profilesDir;
    }

    /**
     * Create a the game folder if not exists.
     * 
     * @param newDir the name of the new directory.
     */
    private void createGamefolder(String newDir) {

        File dir = new File(newDir);

        if (!dir.exists()) {
            dir.mkdir();
        }
    }

    /**
     * Serialize an object.
     * 
     * @param o        object to be serialized
     * @param fileName the file name to store serialized object.
     * 
     * @throws IOException
     */
    private void serialize(Object o, String fileName) throws IOException {
        FileOutputStream file = new FileOutputStream(fileName);
        ObjectOutputStream out = new ObjectOutputStream(file);

        out.writeObject(o);
        out.flush();
        out.close();

        file.close();
    }

    /**
     * Unserialize an object.
     * 
     * @param fileName the file name containing the object to be unserialized.
     * 
     * @return the unserialized object.
     * 
     * @throws ClassNotFoundException
     * @throws IOException
     */
    private Object unserialize(String fileName) throws ClassNotFoundException, IOException {
        FileInputStream file = new FileInputStream(fileName);
        ObjectInputStream in = new ObjectInputStream(file);

        Object o = in.readObject();

        in.close();
        file.close();

        return o;
    }

    /**
     * Save options to disk.
     * 
     * @param model the option model instance to be saved to disk.
     */
    public void saveOptions(OptionsModel model) {

        createGamefolder(jUnoDir);

        try {
            serialize(model, fileOptions);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Save player profile to disk
     * 
     * @param model the player profile model instance to be saved to disk.
     */
    public void savePlayers(PlayersProfileModel model) {

        createGamefolder(profilesDir);

        try {
            serialize(model, profilesDir + "/" + model.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Load a players model from disk.
     * 
     * @return the players model instance.
     */
    public PlayersModel loadPlayer() {
        PlayersModel playersModel = new PlayersModel();

        File dir = new File(profilesDir);

        if (dir.exists()) {
            // for every profile saved to disk
            for (File file : dir.listFiles()) {
                if (file.isFile()) {

                    try {
                        // unserialize the profile
                        PlayersProfileModel playerProfileModel = (PlayersProfileModel) unserialize(
                                profilesDir + "/" + file.getName());
                        // and add it to the players model
                        playersModel.addPlayer(playerProfileModel.getName(), playerProfileModel);

                    } catch (ClassNotFoundException | IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return playersModel;
    }

    /**
     * Load options from disk.
     * 
     * @return the options model instance.
     */
    public OptionsModel loadOptions() {

        OptionsModel optionsModel = new OptionsModel();

        if ((new File(fileOptions)).exists()) {
            try {
                optionsModel = (OptionsModel) unserialize(fileOptions);

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        return optionsModel;
    }
}

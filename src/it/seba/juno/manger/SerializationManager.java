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

public class SerializationManager {

    private String userDir;
    private String jUnoDir;
    private String profilesDir;
    private String fileOptions;

    private static SerializationManager instance;

    public static SerializationManager getInstance() {
        if (instance == null)
            instance = new SerializationManager();
        return instance;
    }

    public SerializationManager() {
        // get user Directory, works on windows, have to be tested on Linux and Mac
        userDir = System.getProperty("user.home").replace("\\", "/");
        // save directory
        jUnoDir = userDir + "/JUno";
        // profiles directory
        profilesDir = jUnoDir + "/profiles";
        // option file, to save the options
        fileOptions = jUnoDir + "/options.bin";
    }

    public void createGamefolder(String newDir) {

        File dir = new File(newDir);

        if (!dir.exists()) {
            dir.mkdir();
        }
    }

    private void serialize(Object o, String fileName) throws IOException {
        FileOutputStream file = new FileOutputStream(fileName);
        ObjectOutputStream out = new ObjectOutputStream(file);

        out.writeObject(o);
        out.flush();
        out.close();

        file.close();
    }

    private Object unserialize(String fileName) throws ClassNotFoundException, IOException {
        FileInputStream file = new FileInputStream(fileName);
        ObjectInputStream in = new ObjectInputStream(file);

        Object o = in.readObject();

        in.close();
        file.close();

        return o;
    }

    public void saveOptions(OptionsModel model) {

        createGamefolder(jUnoDir);

        try {
            serialize(model, fileOptions);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void savePlayers(PlayersProfileModel model) {

        createGamefolder(profilesDir);

        try {
            serialize(model, profilesDir + "/" + model.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public PlayersModel loadPlayer() {
        PlayersModel playersModel = new PlayersModel();

        File dir = new File(profilesDir);

        if (dir.exists()) {
            for (File file : dir.listFiles()) {
                if (file.isFile()) {

                    try {
                        PlayersProfileModel playerProfileModel = (PlayersProfileModel) unserialize(
                                profilesDir + "/" + file.getName());
                        playersModel.addPlayer(playerProfileModel.getName(), playerProfileModel);

                    } catch (ClassNotFoundException | IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return playersModel;
    }

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

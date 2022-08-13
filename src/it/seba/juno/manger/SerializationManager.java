package it.seba.juno.manger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import it.seba.juno.model.OptionsModel;
import it.seba.juno.model.PlayersModel;

public class SerializationManager {

    private String userDir;
    private String jUnoDir;
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
        // option file, to save the options
        fileOptions = jUnoDir + "/options.bin";

        
    }

    public void createGamefolder() {

        File dir = new File(jUnoDir);

        if (!dir.exists()) {
            dir.mkdir();
        }
    }

    public void saveOptions(OptionsModel model) {

        createGamefolder();

        try {
            FileOutputStream file = new FileOutputStream(fileOptions);
            ObjectOutputStream out = new ObjectOutputStream(file);

            out.writeObject(model);
            out.flush();
            out.close();

            file.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void savePlayers() {

    }

    public PlayersModel loadPlayer() {
        return null;
    }

    public OptionsModel loadOptions() {

        OptionsModel optionsModel = new OptionsModel();

        if ((new File(fileOptions)).exists()) {
            try {
                FileInputStream file = new FileInputStream(fileOptions);
                ObjectInputStream in = new ObjectInputStream(file);

                optionsModel = (OptionsModel) in.readObject();

                in.close();
                file.close();

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        return optionsModel;
    }
}

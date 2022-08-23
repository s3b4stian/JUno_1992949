package it.seba.juno.controller;

import it.seba.juno.view.MainView;
import it.seba.juno.view.MenuView;

/**
 * Controls the {@link it.seba.juno.view.MainView} of the program composed by
 * JFrame and set as current view the {@link it.seba.juno.view.MenuView}.
 * 
 * @author Sebastian Rapetti
 *
 */
public class MainController {

    private MainView mainView;
    private MenuView menuView;

    /**
     * Class Constructor.
     * 
     * @param mainView the main view, used to host all others view.
     * @param menuView the menu view, show the main menu.
     */
    public MainController(MainView mainView, MenuView menuView) {
        this.mainView = mainView;
        this.menuView = menuView;
        initView();
    }

    /**
     * Init actions in view.
     */
    private void initView() {
        mainView.setCurrentView(menuView);
        mainView.setVisible(true);
    }
}

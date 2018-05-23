package controller;

import views.UsersView;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Created by manuel on 21/5/18.
 */
public class CustomListSelectionListener implements ListSelectionListener {

    private UsersView usersView;
    private int indice;

    public CustomListSelectionListener(UsersView usersView) {

        this.usersView = usersView;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

        indice = ((JList) e.getSource()).getSelectedIndex();
        usersView.setIndice(indice);

    }


}

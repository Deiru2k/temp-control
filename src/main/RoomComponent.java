package main;

import house.Room;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

/**
 * Created by deiru on 14/01/2017.
 */

public class RoomComponent {
    public JPanel panel1;
    private JLabel roomName;
    private JLabel gasSpent;
    private JCheckBox presence;
    private JSpinner curentTemp;
    private JSpinner desiredTemp;
    private Room room;

    public void tick() {
        roomName.setText(room.getName());
        curentTemp.setValue(room.getCurrentTemp());
        desiredTemp.setValue(room.getTemp());
        gasSpent.setText(String.valueOf(room.getGasSpent()));
        presence.setSelected(room.getSensor().getPresence());
    }

    public String getName() {
        return roomName.getText();
    }

    public RoomComponent(Room room) {
        this.room = room;
        panel1.setPreferredSize(new Dimension(200, 200));
        presence.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                AbstractButton button = (AbstractButton) e.getSource();
                boolean value = button.getModel().isSelected();
                room.setPresence(value);
            }
        });
    }
}

package main;

import com.sun.xml.internal.xsom.impl.scd.Iterators;
import house.DateTime;
import house.Room;
import house.Weekday;
import tempcontrol.TempController;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class Simulation {
    private DateTime dateTime;
    private TempController controller;
    private JTextPane textPane1;
    private JPanel mainpanel;
    private JButton startSimButton;
    private JScrollPane roomsScrollable;
    private JPanel roomsPanel;
    private JLabel timeLabel;
    private ArrayList<RoomComponent> roomsComponents;

    public Simulation(int hours, int minutes, Weekday day, TempController ctrl) {
        dateTime = new DateTime(hours, minutes, day);
        controller = ctrl;
        startSimButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                start();
            }
        });
    }

    private void updateRooms() {
        ArrayList<Room> rooms = controller.getRooms();
        Stream<String> names = rooms.stream().map(Room::getName);

    }

    private void tick() {
//        updateRooms();
        roomsComponents.forEach(RoomComponent::tick);
        String time = dateTime.getTime();
        Weekday day = dateTime.getDay();
        timeLabel.setText(String.format("%s %s", day.toString(), time));
        controller.tick(time, day);
        dateTime.advanceTime();
    }

    private void start() {
        System.out.println("Started");
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        controller.getRooms().forEach(room -> {
            RoomComponent cmp = new RoomComponent(room);
            roomsComponents.add(cmp);
            roomsPanel.add(cmp.panel1);
        });
        Runnable task = this::tick;
        executor.scheduleAtFixedRate(task, 0, 1, TimeUnit.SECONDS);
    }

    public void run() {
        roomsComponents = new ArrayList<>();
        JFrame frame = new JFrame("App");
        this.mainpanel.setPreferredSize(new Dimension(800, 600));
        frame.setContentPane(this.mainpanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();
    }
}

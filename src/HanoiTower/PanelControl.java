package HanoiTower;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelControl extends JPanel{

    private HanoiTowers hanoiTowers;
    private JSlider slider;
    private JSlider speedSlider;
    private JButton startBtn;
    private JButton resetBtn;
    private JLabel levelLb;
    private JLabel speedLb;


    public PanelControl(HanoiTowers hanoiTowers)
    {
        super();
        this.hanoiTowers = hanoiTowers;
        this.slider = new JSlider(JSlider.VERTICAL,1,10,
                hanoiTowers.getN());
        this.slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                hanoiTowers.setN(slider.getValue());
                hanoiTowers.restart();
            }
        });

        this.levelLb = new JLabel("Nivel");

        this.speedSlider = new JSlider(JSlider.VERTICAL,1,10,
                hanoiTowers.getSpeed());
        this.speedSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                hanoiTowers.setSpeed(speedSlider.getValue());
            }
        });

        this.speedLb = new JLabel("Speed");

        this.startBtn = new JButton("Start");
        this.startBtn.setHorizontalAlignment(AbstractButton.CENTER);
        this.startBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
               hanoiTowers.start();
            }
        });

        this.resetBtn = new JButton("Reset");
        this.resetBtn.setHorizontalAlignment(AbstractButton.CENTER);
        this.resetBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                hanoiTowers.restart();
            }
        });

        JPanel sliderPanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        sliderPanel.add(slider, BorderLayout.WEST);
        sliderPanel.add(levelLb, BorderLayout.WEST);
        sliderPanel.add(speedSlider, BorderLayout.EAST);
        sliderPanel.add(speedLb, BorderLayout.EAST);
        buttonPanel.add(startBtn, BorderLayout.NORTH);
        buttonPanel.add(resetBtn, BorderLayout.SOUTH);

        this.add(sliderPanel);
        this.add(buttonPanel);
//        this.add(slider);
//        this.add(speedSlider);
//        this.add(startBtn);
//        this.add(resetBtn);

    }
}

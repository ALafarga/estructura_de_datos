package HanoiTower;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelControl extends JPanel{

    private HanoiTowers hanoiTowers;
    private JSlider slider;
    private JButton startBtn;
    private JButton resetBtn;

    public PanelControl(HanoiTowers hanoiTowers)
    {
        super();
        this.hanoiTowers = hanoiTowers;
        this.slider = new JSlider(JSlider.VERTICAL,1,10, 2);
        this.slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                hanoiTowers.setN(slider.getValue());
            }
        });
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

        this.add(slider);
        this.add(startBtn);
        this.add(resetBtn);

    }
}

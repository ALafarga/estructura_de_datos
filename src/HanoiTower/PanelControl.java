package HanoiTower;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PanelControl extends JPanel{

    private HanoiTowers hanoiTowers;
    private JSlider slider;

    public PanelControl(HanoiTowers hanoiTowers)
    {
        super();
        this.hanoiTowers = hanoiTowers;
        this.slider = new JSlider(JSlider.VERTICAL,1,10, 5);
        this.slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                hanoiTowers.setNivel(slider.getValue());
            }
        });
        this.add(slider);

    }
}

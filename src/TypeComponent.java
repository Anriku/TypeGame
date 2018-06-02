import javax.swing.*;
import java.awt.*;

public class TypeComponent extends JComponent {

    private NumLocation[] numLocations = new NumLocation[4];
    private Timer timer;

    public TypeComponent(int width,int height) {
        setSize(width,height);

        for (int i = 0; i < 4; i++) {
            numLocations[i] = new NumLocation(this,i);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        for (NumLocation numlocation :
                numLocations) {
            numlocation.paintNum();
        }
    }
}

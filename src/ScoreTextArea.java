import javax.swing.*;

public class ScoreTextArea extends JTextField{

    public ScoreTextArea(int width,int height) {
        setSize(width,height);
        setEditable(false);
        setHorizontalAlignment(CENTER);
        setText("0");
    }
}

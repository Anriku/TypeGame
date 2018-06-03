import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * 此类是数字输入的地方
 */
public class InputTextField extends JTextField {

    public InputTextField(int width, int height) {
        setSize(width, height);
        setText("");
        addKeyListener(new InputListener());
        setHorizontalAlignment(CENTER);
    }


    /**
     * 用于控制文本的输入
     */
    class InputListener extends KeyAdapter {

        @Override
        public void keyTyped(KeyEvent e) {
            if (!(e.getKeyChar() >= '0' && e.getKeyChar() <= '9') || getText().length() >= 3) {
                e.consume();
            }
            super.keyTyped(e);
        }
    }
}

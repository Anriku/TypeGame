import menu.OptionsMenu;
import menu.TypeGameMenuBar;

import javax.swing.*;
import java.awt.*;

public class TypeFrame extends JFrame implements OptionsMenu.OnClickListener {

    private TypeComponent typeComponent;
    private InputTextField inputTextField;
    private ScoreTextArea scoreTextArea;
    private int score = 0;

    public TypeFrame() throws HeadlessException {
        typeComponent = new TypeComponent(500, 500);
        inputTextField = new InputTextField(500, 50);
        scoreTextArea = new ScoreTextArea(500, 50);
        TypeGameMenuBar menuBar = new TypeGameMenuBar(this);


        setLayout(new BorderLayout());
        setJMenuBar(menuBar);
        add(scoreTextArea, BorderLayout.NORTH);
        add(typeComponent, BorderLayout.CENTER);
        add(inputTextField, BorderLayout.SOUTH);


        setSize(typeComponent.getWidth(), typeComponent.getHeight() + inputTextField.getHeight());
        setLocation(400, 200);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        controlGame();
    }

    /**
     * 此方法用于控制游戏之间的交互
     */
    private void controlGame() {
        new Thread(() -> {
            while (true) {
                String text = inputTextField.getText();
                //进行一定的延迟不然有bug
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (text != null && text.length() == 3) {
                    for (int i = 0; i < TypeComponent.nums.length; i++) {
                        if (text.equals(TypeComponent.nums[i])) {
                            score += 2;
                            typeComponent.resetNum(i);
                            typeComponent.speedIncreaseAsScoreIncrease(score);

                            scoreTextArea.setText(String.valueOf(score));
                        }
                        inputTextField.setText("");
                    }
                }
            }
        }).start();
    }

    @Override
    public void onClickStart() {
        typeComponent.startGame();
    }

    @Override
    public void onClickPause() {
        typeComponent.pauseGame();
    }

    @Override
    public void onClickGoOn() {
        typeComponent.goOnGame();
    }

    @Override
    public void onClickEasy() {
        typeComponent.easyGame();
    }

    @Override
    public void onClickMedium() {
        typeComponent.mediumGame();
    }

    @Override
    public void onClickDifficulty() {
        typeComponent.difficultGame();
    }
}

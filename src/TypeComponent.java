import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

public class TypeComponent extends JComponent {

    private NumLocation[] numLocations = new NumLocation[4];
    //nums用于保存四个数字
    public static String nums[] = new String[4];
    private static final int DELAY = 50;
    private Timer timer;
    private double speed;
    private double speedIncrease = 0;
    private boolean isGameOver = false;


    public TypeComponent(int width, int height) {
        setSize(width, height);

        for (int i = 0; i < 4; i++) {
            numLocations[i] = new NumLocation(this, i);
        }

        easyGame();
        timer = new Timer(DELAY, new DropListener());
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!isGameOver) {
            for (NumLocation numlocation :
                    numLocations) {
                numlocation.paintNum((Graphics2D) g);
            }
            g.dispose();
        } else {
            Graphics2D graphics2D = (Graphics2D) g;
            FontRenderContext context = graphics2D.getFontRenderContext();
            Font font = new Font("Serif", Font.BOLD, 36);
            graphics2D.setFont(font);

            String gameOver = "GAME OVER";
            Rectangle2D bounds = font.getStringBounds(gameOver, context);
            double x = (getWidth() - bounds.getWidth()) / 2;
            double y = (getHeight() - bounds.getHeight()) / 2;
            double ascent = -bounds.getY();
            double baseY = y + ascent;

            graphics2D.drawString(gameOver, (int) x, (int) baseY);
        }
    }


    /**
     * 进行对应位置数字的重置
     *
     * @param location 哪一个数字
     */
    public void resetNum(int location) {
        numLocations[location].resetNum();
    }

    /**
     * 开始游戏
     */
    public void startGame() {
        isGameOver = false;

        for (NumLocation numLocation :
                numLocations) {
            numLocation.resetNum();
        }
        timer.start();
    }

    /**
     * 用于暂停游戏
     */
    public void pauseGame() {
        timer.stop();
    }


    /**
     * 用于继续游戏
     */
    public void goOnGame() {
        timer.start();
    }


    /**
     * 简单模式
     */
    public void easyGame() {
        speed = 1;
    }

    /**
     * 中等模式
     */
    public void mediumGame() {
        speed = 3;
    }

    /**
     * 困难模式
     */
    public void difficultGame() {
        speed = 7;
    }


    /**
     * 随着分数越来越高，下落速度越快
     *
     * @param score 当然得分
     */
    public void speedIncreaseAsScoreIncrease(int score) {
        speedIncrease = (score / 10) * 0.5;
    }

    public void onGameOver() {
        isGameOver = true;
    }


    /**
     * 此类用于控制数字的下降
     */
    class DropListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            for (NumLocation numLocation :
                    numLocations) {
                numLocation.fontDrop(speed + speedIncrease);
            }
            repaint();
        }
    }
}

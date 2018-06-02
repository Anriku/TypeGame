import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

/**
 * 此类用于获取字体的位置
 */
public class NumLocation {
    private double x;
    private double y;
    private double baseY;
    private static String num[] = new String[4];
    private int location;
    private JComponent component;
    private Graphics2D graphics2D;
    private Rectangle2D bounds;


    /**
     * 用于计算字体的初始位置
     *
     * @param component 画字的组件
     * @param location  第几列字
     */
    public NumLocation(JComponent component, int location) {
        this.location = location;
        this.component = component;

        num[location] = generateNum(location);
        graphics2D = (Graphics2D) component.getGraphics();

        FontRenderContext context = graphics2D.getFontRenderContext();
        Font font = new Font("Serif", Font.BOLD, 36);
        bounds = font.getStringBounds(num[location], context);

        x = (component.getWidth() - bounds.getWidth()) * location / 5;
        y = 0;
        baseY = -bounds.getY();

    }

    /**
     * 此方法用于绘制数字
     */
    public void paintNum() {
        graphics2D.drawString(num[location], (int) x, (int) baseY);
    }


    /**
     * 此方法用于随机生成数字
     *
     * @param location 第几列数字
     * @return 返回生成的数字
     */
    private String generateNum(int location) {
        //个、十、百分别进行生成
        int hundredPlace = 0;
        int decadePlace;
        int unitPlace;
        //用于表示生成的百位数是否合适
        Boolean isSuitable = false;
        //获取其它三个数字的百位
        int[] otherNumHundreds = new int[3];
        for (int j = 0, k = 0; j < 4; j++) {
            if (j == location) {
                continue;
            }
            otherNumHundreds[k] = Integer.valueOf(String.valueOf(num[j].charAt(0)));
            k++;
        }
        //进行百位数字的生成直到生合适的为止
        while (!isSuitable) {
            hundredPlace = (int) (Math.random() * 10);
            isSuitable = true;
            for (int j = 0; j < 3; j++) {
                if (otherNumHundreds[j] == hundredPlace) {
                    isSuitable = false;
                    break;
                }
            }
        }

        decadePlace = (int) (Math.random() * 10);
        unitPlace = (int) (Math.random() * 10);
        return String.valueOf(hundredPlace) + String.valueOf(decadePlace) + String.valueOf(unitPlace);
    }

    /**
     * 此列用于控制字体的下落
     */
    public void fontDrop(int speed) {
        baseY += speed;
        if (baseY >= component.getHeight()){
            gameOver();
        }
        component.repaint();
    }

    /**
     * 这个方法用于重置数字的位置
     */
    public void resetNum(){
        baseY = -bounds.getY();
    }

    /**
     * 如果一个数字落地就游戏结束
     */
    private void gameOver(){
        System.exit(0);
    }
}

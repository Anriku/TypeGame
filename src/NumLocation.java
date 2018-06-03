import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

/**
 * 此类用于获取字体的位置
 */
public class NumLocation {
    private double baseY = 0;
    private int location;
    private JComponent component;


    /**
     * 用于计算字体的初始位置
     *
     * @param component 画字的组件
     * @param location  第几列字
     */
    public NumLocation(JComponent component, int location) {
        this.location = location;
        this.component = component;

        TypeComponent.nums[location] = generateNum(location);
    }

    /**
     * 此方法用于绘制数字
     */
    public void paintNum(Graphics2D graphics2D) {
        FontRenderContext context = graphics2D.getFontRenderContext();
        Font font = new Font("Serif", Font.BOLD, 15);
        graphics2D.setFont(font);
        Rectangle2D bounds = font.getStringBounds(TypeComponent.nums[location], context);
        double x = (component.getWidth() - bounds.getWidth()) * (location + 1) / 5;

        graphics2D.drawString(TypeComponent.nums[location], (float) x, (float) baseY);
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
            if (TypeComponent.nums[j] != null) {
                otherNumHundreds[k] = Integer.valueOf(String.valueOf(TypeComponent.nums[j].charAt(0)));
            } else {
                otherNumHundreds[k] = -1;
            }
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
    public void fontDrop(double speed) {
        baseY += speed;
        if (baseY >= component.getHeight()) {
            gameOver();
        }
    }


    /**
     * 如果一个数字落地就游戏结束
     */
    private void gameOver() {
        ((TypeComponent)component).onGameOver();
    }

    /**
     * 此方法用于重置数字
     */
    public void resetNum() {
        TypeComponent.nums[location] = generateNum(location);
        baseY = 0;
    }
}

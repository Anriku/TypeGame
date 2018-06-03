package menu;

import javax.swing.*;


/**
 * 选项菜单
 */
public class OptionsMenu extends JMenu {
    private OnClickListener listener;

    public OptionsMenu(String s, OnClickListener listener) {
        super(s);
        this.listener = listener;

        //添加选项
        JMenuItem start = new JMenuItem("(重新)开始");
        add(start);

        JMenuItem goOn = new JMenuItem("继续");
        add(goOn);
        goOn.setEnabled(false);

        JMenuItem pause = new JMenuItem("暂停");
        add(pause);
        pause.setEnabled(false);

        DifficultiesMenu difficultiesMenu = new DifficultiesMenu("难度");
        add(difficultiesMenu);

        //添加监听器
        start.addActionListener(e -> {
            listener.onClickStart();
            pause.setEnabled(true);
        });

        pause.addActionListener(e -> {
            listener.onClickPause();
            pause.setEnabled(false);
            goOn.setEnabled(true);
        });

        goOn.addActionListener(e -> {
            listener.onClickGoOn();
            goOn.setEnabled(false);
            pause.setEnabled(true);
        });

    }


    /**
     * 难度选择子菜单
     */
    class DifficultiesMenu extends JMenu {
        public DifficultiesMenu(String s) {
            super(s);

            //添加选项
            ButtonGroup buttonGroup = new ButtonGroup();

            JRadioButtonMenuItem easy = new JRadioButtonMenuItem("简单");
            buttonGroup.add(easy);
            add(easy);

            JRadioButtonMenuItem medium = new JRadioButtonMenuItem("中等");
            buttonGroup.add(medium);
            add(medium);

            JRadioButtonMenuItem difficulty = new JRadioButtonMenuItem("困难");
            buttonGroup.add(difficulty);
            add(difficulty);


            //添加监听器
            easy.addActionListener(e -> listener.onClickEasy());
            medium.addActionListener(e -> listener.onClickMedium());
            difficulty.addActionListener(e -> listener.onClickDifficulty());


        }
    }


    //用于回调的接口
    public interface OnClickListener {

        void onClickStart();

        void onClickPause();

        void onClickGoOn();

        void onClickEasy();

        void onClickMedium();

        void onClickDifficulty();

    }
}

package menu;

import javax.swing.*;

/**
 * 帮助菜单
 */
public class HelpMenu extends JMenu {
    public HelpMenu(String s) {
        super(s);

        JMenuItem help = new JMenuItem("帮助");
        add(help);
    }

}

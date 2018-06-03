package menu;

import javax.swing.*;


public class TypeGameMenuBar extends JMenuBar {
    public TypeGameMenuBar(OptionsMenu.OnClickListener listener) {
        OptionsMenu optionsMenu = new OptionsMenu("选项", listener);
        HelpMenu helpMenu = new HelpMenu("帮助");

        add(optionsMenu);
        add(helpMenu);
    }
}

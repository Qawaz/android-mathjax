package com.wakaztahir.composejlatex.swing;

import com.wakaztahir.composejlatex.awt.Component;
import com.wakaztahir.composejlatex.awt.Graphics;


public interface Icon {
    void paintIcon(Component c, Graphics g, int x, int y);

    int getIconWidth();

    int getIconHeight();
}


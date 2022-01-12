package com.wakaztahir.mathjax.swing;

import com.wakaztahir.mathjax.awt.Component;
import com.wakaztahir.mathjax.awt.Graphics;


public interface Icon {
    void paintIcon(Component c, Graphics g, int x, int y);

    int getIconWidth();

    int getIconHeight();
}


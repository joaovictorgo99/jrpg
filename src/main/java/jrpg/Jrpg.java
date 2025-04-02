// João Víctor Gomes de Oliveira - 2025
// A JRPG written in Java programming language.
// How to use: run and have a good game.

package jrpg;

import jrpg.view.View;

import javax.swing.*;

public class Jrpg {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new View().setVisible(true);
            }
        });
    }
}

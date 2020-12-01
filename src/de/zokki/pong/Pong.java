package de.zokki.pong;

import de.zokki.pong.GUI.PongGUI;

public class Pong {

    public static PongGUI pongGUI;

    public static void main(String[] args) {
	pongGUI = new PongGUI("Pong!", 500, 400);
	pongGUI.startGame();
    }
}

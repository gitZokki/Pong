package de.zokki.pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Paddle implements Runnable {

    int yDir;

    public Rectangle paddle;

    public Paddle(int x, int y, int width, int height) {
	paddle = new Rectangle(x, y, width, height);
    }

    @Override
    public void run() {
	try {
	    while (true) {
		move();
		Thread.sleep(5);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public void paint(Graphics g) {
	g.setColor(Color.WHITE);
	g.fillRect(paddle.x, paddle.y, paddle.width, paddle.height);
    }

    public void setYDir(int dir) {
	yDir = dir;
    }

    public void setX(int x) {
	paddle.x = x;
    }

    public void setHeight(int height) {
	paddle.height = height;
    }

    private void move() {
	if ((paddle.y - (paddle.height / 2) > 0 || yDir == 1)
		&& (paddle.y + paddle.height < Pong.pongGUI.height || yDir == -1)) {
	    paddle.y += yDir;
	}
    }
}

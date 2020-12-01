package de.zokki.pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import de.zokki.pong.GUI.PongGUI;

public class Ball implements Runnable {

    public int p1score, p2score;

    private int xDir, yDir, offset;

    private PongGUI gui = Pong.pongGUI;

    public Rectangle ball;

    public Ball() {
	offset = gui.height - gui.contentHeight;
	resetBall();
    }

    public void paint(Graphics g) {
	g.setColor(Color.WHITE);
	g.fillOval(ball.x, ball.y, ball.width, ball.height);
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

    private void resetBall() {
	ball = new Rectangle(gui.width / 2 - (15 / 2), gui.height / 2 - (15 / 2), 15, 15);
	if (Math.random() < 0.5) {
	    xDir = 1;
	} else {
	    xDir = -1;
	}

	if (Math.random() < 0.5) {
	    yDir = 1;
	} else {
	    yDir = -1;
	}
    }

    private void move() {
	ball.x += xDir;
	ball.y += yDir;

	if (ball.intersects(gui.playerOne.paddle) || ball.intersects(gui.playerTwo.paddle)) {
	    xDir *= -1;
	}

	if (ball.y + ball.height > gui.height) {
	    yDir *= -1;
	} else if (ball.y + ball.height < 0 + offset) {
	    yDir *= -1;
	}

	if (ball.x + ball.width > gui.width) {
	    p1score++;
	    resetBall();
	} else if (ball.x - (ball.width / 2) < 0) {
	    resetBall();
	    p2score++;
	}
    }
}

package de.zokki.pong.GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import de.zokki.pong.Ball;
import de.zokki.pong.Paddle;

public class PongGUI extends JFrame {

    private static final long serialVersionUID = 1L;

    Ball ball;

    public Paddle playerOne, playerTwo;

    public int contentWidth, contentHeight, width, height;

    public PongGUI(String title, int width, int height) {
	super(title);
	setSize(width, height);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setResizable(true);
	setVisible(true);
	setBackground(Color.DARK_GRAY);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setLocationRelativeTo(null);

	setContentDimension();
	setDimension();

	playerOne = new Paddle(10, contentHeight / 2, 10, 45);
	playerTwo = new Paddle(width - 20, contentHeight / 2, 10, 45);

	addListeners();
    }

    @Override
    public void paint(Graphics g) {
	Image image = createImage(getWidth(), getHeight());
	Graphics graphics = image.getGraphics();

	ball.paint(graphics);
	playerOne.paint(graphics);
	playerTwo.paint(graphics);

	String score = ball.p1score + " : " + ball.p2score;

	graphics.setColor(Color.WHITE);
	graphics.drawString(score, contentWidth / 2 - (g.getFontMetrics().stringWidth(score) / 2),
		getHeight() - contentHeight + 15);

	g.drawImage(image, 0, 0, this);
	repaint();
    }

    public void startGame() {
	ball = new Ball();
	Thread ballThread = new Thread(ball);
	Thread p1Thread = new Thread(playerOne);
	Thread p2Thread = new Thread(playerTwo);
	ballThread.start();
	p1Thread.start();
	p2Thread.start();
    }

    private void addListeners() {
	addComponentListener(new ComponentAdapter() {
	    @Override
	    public void componentResized(ComponentEvent e) {
		setContentDimension();
		setDimension();
		playerOne.setHeight(height / 10);
		playerTwo.setHeight(height / 10);
		playerTwo.setX(width - 20);
	    }
	});

	addKeyListener(new KeyAdapter() {
	    @Override
	    public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W) {
		    playerOne.setYDir(-1);
		} else if (e.getKeyCode() == KeyEvent.VK_S) {
		    playerOne.setYDir(1);
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
		    playerTwo.setYDir(-1);
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
		    playerTwo.setYDir(1);
		}
	    }

	    @Override
	    public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W) {
		    playerOne.setYDir(0);
		} else if (e.getKeyCode() == KeyEvent.VK_S) {
		    playerOne.setYDir(0);
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
		    playerTwo.setYDir(0);
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
		    playerTwo.setYDir(0);
		}
	    }
	});
    }

    private void setContentDimension() {
	contentWidth = getContentPane().getWidth();
	contentHeight = getContentPane().getHeight();
    }

    private void setDimension() {
	width = getWidth();
	height = getHeight();
    }
}

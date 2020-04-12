import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*; 
import java.io.*; 
import javax.imageio.*;
import java.util.*;

public class Player {
	public static final int BOY = 0, GIRL = 1, UP = 1, RIGHT = 4, DOWN = 7, LEFT = 10, IDLE = 0;
	private int frame, dir, extra, wait, delay;
	private static int px,py;
	private Image[] sprites;

	public Player(int gen) {
		px = 285;
		py = 280;
		frame = 6;
		extra = 0;
		wait = 0;
		delay = 30;
		if (gen == BOY) {
			load(BOY);
		} else {
			load(GIRL);
		}
	}

	public void load(int gen) {
		sprites = new Image[12];
		for (int i = 0; i < 12; i++) {
			String path = String.format("%s/%s/%s%d.png", "Sprites", "TrainerSprites", "Trainer", i + 1);
			if (gen == GIRL) {
				path += "G";
			}
			try {
				Image pic = ImageIO.read(new File(path));
				pic = pic.getScaledInstance(30, 25, Image.SCALE_SMOOTH);
				sprites[i] = pic;
			} catch (IOException e) {
			}
		}
	}

	public void draw(Graphics g) {
		g.drawImage(sprites[frame], px, py, null);
	}

	public void move(int dir) {
		if (dir == UP) {
			py -= 1;
			dir = UP;
		} else if (dir == RIGHT) {
			px += 1;
			dir = RIGHT;
		} else if (dir == DOWN) {
			py += 1;
			dir = DOWN;
		} else if (dir == LEFT) {
			px -= 1;
			dir = LEFT;
		}

		updateFrame();
		frame = dir + extra;
		wait++;
	}

	public void idle(int direction) {
		if (direction == UP) {
			frame = 0;
		}
		else if (direction == RIGHT) {
			frame = 3;
		}
		else if (direction == DOWN) {
			frame = 6;
		}
		else if (direction == LEFT) {
			frame = 9;
		}
	}

	public void updateFrame() {
		if (wait % delay == 0) {
			extra++;
			if (extra > 1) {
				extra = 0;
			}
		}
	}

	public void resetExtra() {
		extra = 0;
	}

	public static int getPx(){return px;}
	public static int getPy(){return py;}
}
	

package cn.sxt.game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;

/**
 * 飞机类
 * @author 陈洪洋
 *
 */

public class Plane extends GameObject {

	boolean live = true;
	double speed = 0.5;  // 飞机的速度
	boolean left, up, right, down;  // 控制飞机飞向
	
	// 子类不继承父类的构造方法，但是可以用super()调用父类的构造方法
	public Plane(Image image, double x, double y) {
		this.image = image;
		this.x = x;
		this.y = y;
		this.width = image.getWidth(null);
		this.height = image.getHeight(null);
	}
	
	// 重写父类的方法
	public void drawSelf(Graphics g) {
		if(live) {
			g.drawImage(image, (int)x, (int)y, null);
			
			if(left) {
				x -= speed;
			} else if(up) {
				y -= speed;
			} else if(right) {
				x += speed;
			} else if(down) {
				y += speed;
			}
		} else {
//			System.out.println("飞机机被摧毁！hahaha");
		}
	}

	// 按下某键的响应
	public void addDirection(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			left = true;
			break ;
		case KeyEvent.VK_UP:
			up = true;
			break ;
		case KeyEvent.VK_RIGHT:
			right = true;
			break ;
		case KeyEvent.VK_DOWN:
			down = true;
			break ;
		}
	}
	
	// 释放某键的响应
	public void minusDirection(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			left = false;
			break ;
		case KeyEvent.VK_UP:
			up = false;
			break ;
		case KeyEvent.VK_RIGHT:
			right = false;
			break ;
		case KeyEvent.VK_DOWN:
			down = false;
			break ;
		}
	}
}

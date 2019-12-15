package cn.sxt.game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;

/**
 * �ɻ���
 * @author �º���
 *
 */

public class Plane extends GameObject {

	boolean live = true;
	double speed = 0.5;  // �ɻ����ٶ�
	boolean left, up, right, down;  // ���Ʒɻ�����
	
	// ���಻�̳и���Ĺ��췽�������ǿ�����super()���ø���Ĺ��췽��
	public Plane(Image image, double x, double y) {
		this.image = image;
		this.x = x;
		this.y = y;
		this.width = image.getWidth(null);
		this.height = image.getHeight(null);
	}
	
	// ��д����ķ���
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
//			System.out.println("�ɻ������ݻ٣�hahaha");
		}
	}

	// ����ĳ������Ӧ
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
	
	// �ͷ�ĳ������Ӧ
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

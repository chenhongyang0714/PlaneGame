package cn.sxt.game;

import java.awt.Graphics;
import java.awt.Image;

/**
 * 飞机爆炸的效果实现
 * @author 陈洪洋
 *
 */

public class Explode {
	double x, y;  // 炮炸的位置
	
	public Explode(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	// 定义存放照片的 容量为16的数组
	static Image[] images = new Image[16];  // static 不需要每次都加载
	
	// 静态方法
	static {
		for(int i=0; i < 16; i ++) {
			// 加载图片
			images[i] = GameUtil.getImage("images/explode/e" + (i + 1) + ".gif");
//			images[i].getWidth(null);  // 不知道什么意思  
		}
	}
	
	int count;  // 记录播放到了哪张图片的下标
	
	public void draw(Graphics g) {
		if(count <= 15) {
			g.drawImage(images[count], (int)x, (int)y, null);
			count ++;
		}
	}
}


























package cn.sxt.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

import javax.swing.JFrame;

/**
 * 飞机游戏的主窗口
 * @author
 *
 */

public class MyGameFrame  extends  JFrame {
	static Date startDate;
	static Date endDate;
	static int period;  // 游戏持续的时间
	
	// 双缓冲技术(加快绘图的速度)
	private Image offScreenImage = null;
	
	public void update(Graphics g) {
		if(offScreenImage == null) {	
			offScreenImage = this.createImage(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);  // 游戏窗口的宽度和高度
			
			Graphics gOffGraphics = offScreenImage.getGraphics();
			paint(gOffGraphics);
			g.drawImage(offScreenImage, 0, 0, null);
		}
	}
	
	// 加载背景图片、飞机、子弹
	Image planeImg  = GameUtil.getImage("images/plane.png");  // 获取图片对象
	Image bgImg  = GameUtil.getImage("images/bg.jpg");
	
	Plane plane = new Plane(planeImg, 100, 100);
	Shell [] shells = new Shell[50];  // 声明 存放子弹的数组
	Explode explode = null;
	
	public void paint(Graphics g) {
		g.drawImage(bgImg, 0, 0, null);
		
		// 画出炮弹
		for(int i=0; i < 15; i ++) {
			shells[i].drawSelf(g);
			
			// 实现矩形的碰撞检测(实时监测)
			boolean peng = shells[i].getRectangle().intersects(plane.getRectangle());
			if(peng) {
				plane.live = false;  // 飞机消失
				
				if(explode == null) {  // 防止飞机一直处于爆炸状态，则炮炸的图片一直从 e1.gif 开始播放
					explode = new Explode(plane.x, plane.y);
					endDate = new Date();
					period = (int)(endDate.getTime() - startDate.getTime()) / 1000;
				}
				explode.draw(g);
			}
			
			// 如果飞机爆炸  打印存活时间
			if(!plane.live) {
				Color color = g.getColor();
				Font font = g.getFont();
				
				g.setColor(Color.WHITE);
				g.setFont(new Font("宋体", Font.BOLD, 25));
				
				g.drawString("探险时间:" + period + "秒", (int)plane.x, (int)plane.y);
				
				g.setColor(color);	
				g.setFont(font);
				
			}
		}
		
		plane.drawSelf(g);  // 画飞机
	}
	
	// 帮助我们反复的重画窗口(内部类实现多线程)
	class PaintThread extends Thread{
		@Override
		public void run() {
			while(true) {
				repaint();  // 重画(paint不能人为调用)
				
				try {
					Thread.sleep(1);  // 每秒刷新1000次
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	// 添加键盘监听(继承适配器)
	class KeyMonitor extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			plane.addDirection(e);
		}
		
		public void keyReleased(KeyEvent e) {
			plane.minusDirection(e);
		}
	}
	
	// 初始化窗口
	public  void  launchFrame(){
		
		this.setTitle("Plane Game");
		this.setVisible(true);
		this.setSize(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
		this.setLocation(300, 300);
		
//		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		new PaintThread().start();  // 启动重画窗口的线程
		addKeyListener(new KeyMonitor());  // 键盘监听
		
		// 初始化50个炮弹
		for(int i=0; i < shells.length; i ++) {
			shells[i] = new Shell();
		}
	}
	
	public static void main(String[] args) {
		startDate = new Date();  // 记录游戏开始时间
		
		MyGameFrame  f = new MyGameFrame();
		f.launchFrame();
	}
	
}

package cwwlxy;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.UIManager;

public class GamePanel extends JPanel{

	public int x, y;
	public int x2, y2;
	public int aix, aiy;
	private int changespeed;
	public int direction;// 保存方向值
	public int direction2;// 保存方向值
	public static final int SOUTH = 0, NORTH = 1, EAST = 2, WEST = 3;// 向南、北、东、西运动
	public Snake sk;// 建立贪吃蛇对象
	public Snake sk2;// 建立贪吃蛇对象
	public AISnake aisk;
	public Food bk;// 建立食物对象
	Image im;
	Graphics g;
	ImageIcon ima=new ImageIcon("timg.jpg");
	public int flag=1;
	public JLabel alabel;
	public JLabel blabel;
	
	public GamePanel()
	{
		x=50;
		y=50;
		changespeed=8;
		direction=SOUTH;
	}
	public GamePanel(int i,int j,JLabel label)
	{
		changespeed=i;
		x=50;
		y=50;
		direction=SOUTH;
		flag=j;
		System.out.println("flag:"+flag);
		if(flag==1)
		{
			this.alabel=label;
		}
		if(flag==2)
		{
			this.blabel=label;
		}
		if(changespeed==10||changespeed==12)
		{
			aix=400;
			aiy=80;
		}
	}
	public GamePanel(int i,int j,JLabel label1,JLabel label2)
	{
		changespeed=i;
		x=50;
		y=50;
		flag=j;
		direction=SOUTH;
		x2=450;
		y2=50;
		direction2=SOUTH;
		System.out.println("flag:"+flag);
		if(flag==3)
		{
			this.alabel=label1;
			this.blabel=label2;
		}
	}
	
	
	public void gameUpdate() {
		
		bk.update(this);// 更新食物坐标位置

		sk.update();// 更新贪吃蛇坐标位置
		switch (direction) {
		case SOUTH:
			y = y + changespeed;
			break;
		case NORTH:
			y = y - changespeed;
			break;
		case EAST:
			x = x + changespeed;
			break;
		case WEST:
			x = x - changespeed;
			break;
		}	
		if(flag==3)
		{
			sk2.update2();// 更新贪吃蛇坐标位置
			switch (direction2) {
			case SOUTH:
				y2 = y2 + changespeed;
				break;
			case NORTH:
				y2 = y2 - changespeed;
				break;
			case EAST:
				x2 = x2 + changespeed;
				break;
			case WEST:
				x2 = x2 - changespeed;
				break;
			}	
		}
		if(flag!=3&&(changespeed==10||changespeed==12))
		{
			aisk.update();			
		}
	}
	
	public void gameRender(Image im) {
		Graphics bg = im.getGraphics();
		bg.drawImage(ima.getImage(),0,0,this.getWidth(),this.getHeight(),null);
		if(flag!=3)
		{
			if(changespeed==10||changespeed==12)
			{
				sk.draw(bg,aisk);
				//aisk.draw1(bg);
			}
			else
			{
				sk.draw(bg);// 在后备缓冲区绘制贪吃蛇图形
			}
		}
		else
		{
			sk2.draw(bg,sk);// 在后备缓冲区绘制贪吃蛇图形
		}
		bk.draw(bg);// 在后备缓冲区绘制食物图形
	}
	
	public void gamePaint(Image im) {
		g = this.getGraphics();
		if(g!=null)
		{
			g.drawImage(im, 0, 0, null);// 将后备缓冲区的内容在屏幕上显示出来
		}
	}
	
	public void createSnakeAndFood()
	{
		if(flag!=3)
		{
			// 实例化贪吃蛇的对象，并传递一个GamePanel对象的引用，划重点
			sk = new Snake(this,changespeed);
			// 实例化食物对象并传递一个GamePanel对象和Snake对象的引用
			if(changespeed==10||changespeed==12)
			{
				aisk=new AISnake(this,changespeed);
				bk = new Food(this, sk,aisk,flag);
			}
			else
			{
				bk = new Food(this, sk,flag);
			}
		}
		else
		{
			sk = new Snake(this,changespeed);
			sk2 = new Snake(this,changespeed,flag);
			bk = new Food(this, sk,sk2,flag);
		}
		
	}
	
	public void setLabelText(int score)
	{
		if(flag==1)
		{
			alabel.setText("玩家一分数为："+score);
		}
		if(flag==2)
		{
			blabel.setText("玩家二分数为："+score);
		}
		if(flag==3)
		{
			alabel.setText("玩家一分数为："+score);
		}
	}
	public void setLabelText2(int score)
	{

		blabel.setText("玩家二分数为："+score);
	}
	
	
	public void setDirection(int direction) {
		this.direction = direction;
		System.out.println("gamepaneL flag:"+flag+" direction:"+direction);
	}
	public int getDirection() {
		return direction;
	}
	
	
	public int getDirection2() {
		return direction2;
	}
	public void setDirection2(int direction2) {
		this.direction2 = direction2;
		System.out.println("gamepaneL flag:"+flag+" direction2:"+direction2);
	}
	public int getScore()
	{
		return bk.score;
	}
	public int getScore2()
	{
		return bk.score2;
	}
	
}

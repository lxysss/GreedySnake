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
	public int direction;// ���淽��ֵ
	public int direction2;// ���淽��ֵ
	public static final int SOUTH = 0, NORTH = 1, EAST = 2, WEST = 3;// ���ϡ������������˶�
	public Snake sk;// ����̰���߶���
	public Snake sk2;// ����̰���߶���
	public AISnake aisk;
	public Food bk;// ����ʳ�����
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
		
		bk.update(this);// ����ʳ������λ��

		sk.update();// ����̰��������λ��
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
			sk2.update2();// ����̰��������λ��
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
				sk.draw(bg);// �ں󱸻���������̰����ͼ��
			}
		}
		else
		{
			sk2.draw(bg,sk);// �ں󱸻���������̰����ͼ��
		}
		bk.draw(bg);// �ں󱸻���������ʳ��ͼ��
	}
	
	public void gamePaint(Image im) {
		g = this.getGraphics();
		if(g!=null)
		{
			g.drawImage(im, 0, 0, null);// ���󱸻���������������Ļ����ʾ����
		}
	}
	
	public void createSnakeAndFood()
	{
		if(flag!=3)
		{
			// ʵ����̰���ߵĶ��󣬲�����һ��GamePanel��������ã����ص�
			sk = new Snake(this,changespeed);
			// ʵ����ʳ����󲢴���һ��GamePanel�����Snake���������
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
			alabel.setText("���һ����Ϊ��"+score);
		}
		if(flag==2)
		{
			blabel.setText("��Ҷ�����Ϊ��"+score);
		}
		if(flag==3)
		{
			alabel.setText("���һ����Ϊ��"+score);
		}
	}
	public void setLabelText2(int score)
	{

		blabel.setText("��Ҷ�����Ϊ��"+score);
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

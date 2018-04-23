package cwwlxy;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



public class GameFrame extends JFrame implements Runnable, KeyListener{

	public static JLabel alabel;
	public static JLabel blabel;
	public static int flag=1;
	private static int changespeed;
	public static GamePanel g1;
    public static GamePanel g2;
    //public static GameFrame f;
    public static SwingJDialog sjd;
    private int direction;// 保存方向值
    private int direction2;// 保存方向值
	public static final int SOUTH = 0, NORTH = 1, EAST = 2, WEST = 3;// 向南、北、东、西运动
	
	Image im;
	  File f;
	  URI uri;
	  URL url; 
	public static AudioClip aau;
	
	public GameFrame(int mode,int degree)
	{
		super("贪吃蛇");
		System.out.println("模式为："+mode+" 难度为："+degree);
		this.setResizable(false);//固定大小
		Container c=this.getContentPane();
		c.setLayout(null);
		//初始化音乐
		f = new File("bg.wav"); 
	    uri = f.toURI();
	    try {
			url = uri.toURL();
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  //解析地址 
	    aau = Applet.newAudioClip(url);
	    
	    
		if(degree==1)
		{
			changespeed=8;
		}
		if(degree==2)
		{
			changespeed=10;
		}
		if(degree==3)
		{
			changespeed=12;
		}
		System.out.println("changespeed:"+changespeed);
		if(mode==1)
		{
			flag=1;
			this.setLocation(200, 80);//设置在屏幕上显示的位置
			this.setSize(600, 700);
			JPanel p1=new JPanel();
			alabel=new JLabel("玩家一分数为：");
			alabel.setFont(new Font("", Font.BOLD, 22)); // 设置文字的字体及大小
			g1 = new GamePanel(changespeed,flag,alabel);
			p1.setBackground(Color.pink);
			g1.setBackground(Color.white);
			p1.setBounds(50, 0, 500, 100);
			p1.setLayout(new FlowLayout(FlowLayout.CENTER));
			p1.add(alabel);
			g1.setBounds(50, 100, 500, 500);
			g1.createSnakeAndFood();
			c.add(g1);
			c.add(p1);		
		}
		if(mode==2)
		{
			flag=2;
			this.setLocation(10, 80);//设置在屏幕上显示的位置
			this.setSize(1200, 700);
		    JPanel p1=new JPanel();
		    JPanel p2=new JPanel();
		    alabel=new JLabel("玩家一分数为：");
		    alabel.setFont(new Font("", Font.BOLD, 22)); // 设置文字的字体及大小
		    blabel=new JLabel("玩家二分数为：");
		    blabel.setFont(new Font("", Font.BOLD, 22)); // 设置文字的字体及大小
		    g1 = new GamePanel(changespeed,1,alabel);
		    g2 =new GamePanel(changespeed,flag,blabel);
		    p1.setBackground(Color.pink);
		    p2.setBackground(Color.pink);
		    g1.setBackground(Color.white);
		    g2.setBackground(Color.white);
		    p1.setBounds(50, 0, 500, 500);
		    p2.setBounds(600, 0, 500, 500);
		    p1.setLayout(new FlowLayout(FlowLayout.CENTER));
		    p1.add(alabel);
		    p2.setLayout(new FlowLayout(FlowLayout.CENTER));
		    p2.add(blabel);
		    g1.setBounds(50, 100, 500, 500);
		    g2.setBounds(600, 100, 500, 500);
		    g1.createSnakeAndFood();
		    g2.createSnakeAndFood();
		    c.add(g1);
			c.add(g2);
			c.add(p1);
			c.add(p2);
		}
		if(mode==3)
		{
			flag=3;
			this.setLocation(200, 80);//设置在屏幕上显示的位置
			this.setSize(600, 700);
			JPanel p1=new JPanel();
			alabel=new JLabel("玩家一分数为：");
			blabel=new JLabel("玩家二分数为：");
			alabel.setFont(new Font("", Font.BOLD, 22)); // 设置文字的字体及大小
			blabel.setFont(new Font("", Font.BOLD, 22)); // 设置文字的字体及大小
			g1 = new GamePanel(changespeed,flag,alabel,blabel);
			p1.setBackground(Color.pink);
			g1.setBackground(Color.white);
			p1.setBounds(50, 0, 500, 100);
			p1.setLayout(new FlowLayout(FlowLayout.LEFT));
			p1.add(alabel);
			p1.add(blabel);
			g1.setBounds(50, 100, 500, 500);
			g1.createSnakeAndFood();
			c.add(g1);
			c.add(p1);		
		}
		this.setDefaultCloseOperation(3);
		addKeyListener(this);// 注册键盘事件监听器
		this.requestFocus();
		this.setVisible(true);	
	}
	
	boolean isPaused = false;
	@Override
	public void run() {
		
		//播放音乐
	    aau.loop();  //循环播放
	    
		while (true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			im = new BufferedImage(this.getWidth(), this.getHeight(),
					BufferedImage.TYPE_4BYTE_ABGR);
		
			
			if (isPaused == false) 
			{    
				if(flag==1)
				{
					g1.gameUpdate();
					if(g1.x >= 500 || g1.x <= 0 || g1.y >=500 || g1.y <= 0 ) 
					{
						isPaused = !isPaused;						
						
						int res=JOptionPane.showConfirmDialog(null, "玩家一你碰到边界啦！"+"玩家一您的得分为  "+g1.getScore()+"  请选择是否重新开始", "游戏结束！", JOptionPane.YES_NO_OPTION);
						              if(res==JOptionPane.YES_OPTION){ 
						            	  sjd.jf.dispose();
						            	  sjd.f.dispose();
						            	  aau.stop();  //音乐停
						            	  sjd=new SwingJDialog();
						              }else{
						            	  System.exit(0);  
						             } 
					}
					else
					{
						if(changespeed==10||changespeed==12)
						{   
							int lll=1;
							int jj=g1.sk.tail;
							System.out.println("g1.sk.tail"+g1.sk.tail);
							System.out.println("g1.sk.head"+g1.sk.head);
							while(jj<=g1.sk.head && jj!=-1)
							{
								System.out.println("蛇的距离"+(g1.sk.body[jj].x-g1.aisk.aibody[g1.aisk.aihead].x)*(g1.sk.body[jj].x-g1.aisk.aibody[g1.aisk.aihead].x)+(g1.sk.body[jj].y-g1.aisk.aibody[g1.aisk.aihead].y)*(g1.sk.body[jj].y-g1.aisk.aibody[g1.aisk.aihead].y));
								if((g1.sk.body[jj].x-g1.aisk.aibody[g1.aisk.aihead].x)*(g1.sk.body[jj].x-g1.aisk.aibody[g1.aisk.aihead].x)+(g1.sk.body[jj].y-g1.aisk.aibody[g1.aisk.aihead].y)*(g1.sk.body[jj].y-g1.aisk.aibody[g1.aisk.aihead].y)<(changespeed*changespeed))
								{
									isPaused = !isPaused;						
									lll=2;
									int res=JOptionPane.showConfirmDialog(null, "你们撞在一起拉！"+"玩家您的得分为  "+g1.getScore()+"  请选择是否重新开始", "游戏结束！", JOptionPane.YES_NO_OPTION);
									              if(res==JOptionPane.YES_OPTION){ 
									            	  sjd.jf.dispose();
									            	  sjd.f.dispose();
									            	  aau.stop();  //音乐停
									            	  sjd=new SwingJDialog();
									              }else{
									            	  System.exit(0);   
									             
									             } 
								}
								jj=jj+1;
							}
							if(lll==1) 
							{
								int jj1=g1.aisk.aitail;
								//System.out.println("g1.sk.tail"+g1.sk.tail);
								//System.out.println("g1.sk.head"+g1.sk.head);
								while(jj1<=g1.aisk.aihead && jj1!=-1)
								{
									//System.out.println("蛇的距离"+(g1.sk.body[jj1].x-g1.aisk.aibody[g1.aisk.aihead].x)*(g1.sk.body[jj1].x-g1.aisk.aibody[g1.aisk.aihead].x)+(g1.sk.body[jj1].y-g1.aisk.aibody[g1.aisk.aihead].y)*(g1.sk.body[jj1].y-g1.aisk.aibody[g1.aisk.aihead].y));
									if((g1.aisk.aibody[jj1].x-g1.sk.body[g1.sk.head].x)*(g1.aisk.aibody[jj1].x-g1.sk.body[g1.sk.head].x)+(g1.aisk.aibody[jj1].y-g1.sk.body[g1.sk.head].y)*(g1.aisk.aibody[jj1].y-g1.sk.body[g1.sk.head].y)<(changespeed*changespeed))
									{
										isPaused = !isPaused;						
										
										int res=JOptionPane.showConfirmDialog(null, "你们撞在一起拉！"+"玩家您的得分为  "+g1.getScore()+"  请选择是否重新开始", "游戏结束！", JOptionPane.YES_NO_OPTION);
										              if(res==JOptionPane.YES_OPTION){ 
										            	  sjd.jf.dispose();
										            	  sjd.f.dispose();
										            	  aau.stop();  //音乐停
										            	  sjd=new SwingJDialog();
										              }else{
										            	  System.exit(0);   
										             
										             } 
									}
									jj1=jj1+1;
								}
							}
						}
					}
				}
				if(flag==2)
				{
					g1.gameUpdate();
					g2.gameUpdate();
					if(g1.x >= 500 || g1.x <= 0 || g1.y >=500 || g1.y <= 0 ) 
					{
						isPaused = !isPaused;
						
						int res=JOptionPane.showConfirmDialog(null, "玩家一你碰到边界啦！"+"玩家一您的得分为  "+g1.getScore()+" 玩家二您的得分为  "+g2.getScore()+"  请选择是否重新开始", "游戏结束！", JOptionPane.YES_NO_OPTION);
						              if(res==JOptionPane.YES_OPTION){ 
						            	  sjd.jf.dispose();
						            	  sjd.f.dispose();
						            	  aau.stop();  //音乐停
						            	  sjd=new SwingJDialog();
						              }else{
						            	  System.exit(0);   
						             } 
					}
					else
					{
						if(g2.x >= 500 || g2.x <= 0 || g2.y >=500 || g2.y <= 0 ) 
						{
							isPaused = !isPaused;
							
							int res=JOptionPane.showConfirmDialog(null, "玩家二你碰到边界啦！"+"玩家一您的得分为  "+g1.getScore()+" 玩家二您的得分为  "+g2.getScore()+"  请选择是否重新开始", "游戏结束！", JOptionPane.YES_NO_OPTION);
							              if(res==JOptionPane.YES_OPTION){ 
							            	  sjd.jf.dispose();
							            	  sjd.f.dispose();
							            	  aau.stop();  //音乐停
							            	  sjd=new SwingJDialog();
							              }else{
							            	  System.exit(0);  
							             
							             } 
						}
						else
						{
							if(changespeed==10||changespeed==12)
							{
								int fff=1;
								int lll=1;
								int jj=g1.sk.tail;
								System.out.println("g1.sk.tail"+g1.sk.tail);
								System.out.println("g1.sk.head"+g1.sk.head);
								while(jj<=g1.sk.head && jj!=-1 &&fff==1)
								{
									System.out.println("蛇的距离"+(g1.sk.body[jj].x-g1.aisk.aibody[g1.aisk.aihead].x)*(g1.sk.body[jj].x-g1.aisk.aibody[g1.aisk.aihead].x)+(g1.sk.body[jj].y-g1.aisk.aibody[g1.aisk.aihead].y)*(g1.sk.body[jj].y-g1.aisk.aibody[g1.aisk.aihead].y));
									if((g1.sk.body[jj].x-g1.aisk.aibody[g1.aisk.aihead].x)*(g1.sk.body[jj].x-g1.aisk.aibody[g1.aisk.aihead].x)+(g1.sk.body[jj].y-g1.aisk.aibody[g1.aisk.aihead].y)*(g1.sk.body[jj].y-g1.aisk.aibody[g1.aisk.aihead].y)<(changespeed*changespeed))
									{
										isPaused = !isPaused;						
										fff=2;
										lll=2;
										int res=JOptionPane.showConfirmDialog(null, "你们撞在一起拉！"+"玩家您的得分为  "+g1.getScore()+"  请选择是否重新开始", "游戏结束！", JOptionPane.YES_NO_OPTION);
										              if(res==JOptionPane.YES_OPTION){ 
										            	  sjd.jf.dispose();
										            	  sjd.f.dispose();
										            	  aau.stop();  //音乐停
										            	  sjd=new SwingJDialog();
										              }else{
										            	  System.exit(0);   
										             
										             } 
									}
									jj=jj+1;
								}
								
								if(lll==1)
								{
								int jj1=g1.aisk.aitail;
								//System.out.println("g1.sk.tail"+g1.sk.tail);
								//System.out.println("g1.sk.head"+g1.sk.head);
								while(jj1<=g1.aisk.aihead && jj1!=-1)
								{
									//System.out.println("蛇的距离"+(g1.sk.body[jj1].x-g1.aisk.aibody[g1.aisk.aihead].x)*(g1.sk.body[jj1].x-g1.aisk.aibody[g1.aisk.aihead].x)+(g1.sk.body[jj1].y-g1.aisk.aibody[g1.aisk.aihead].y)*(g1.sk.body[jj1].y-g1.aisk.aibody[g1.aisk.aihead].y));
									if((g1.aisk.aibody[jj1].x-g1.sk.body[g1.sk.head].x)*(g1.aisk.aibody[jj1].x-g1.sk.body[g1.sk.head].x)+(g1.aisk.aibody[jj1].y-g1.sk.body[g1.sk.head].y)*(g1.aisk.aibody[jj1].y-g1.sk.body[g1.sk.head].y)<(changespeed*changespeed))
									{
										isPaused = !isPaused;						
										
										int res=JOptionPane.showConfirmDialog(null, "你们撞在一起拉！"+"玩家您的得分为  "+g1.getScore()+"  请选择是否重新开始", "游戏结束！", JOptionPane.YES_NO_OPTION);
										              if(res==JOptionPane.YES_OPTION){ 
										            	  sjd.jf.dispose();
										            	  sjd.f.dispose();
										            	  aau.stop();  //音乐停
										            	  sjd=new SwingJDialog();
										              }else{
										            	  System.exit(0);   
										             
										             } 
									}
									jj1=jj1+1;
								}
								}
								
								
								if(fff==1)
								{
									int bbb=1;
									int jj2=g2.sk.tail;
									System.out.println("g2.sk.tail"+g2.sk.tail);
									System.out.println("g2.sk.head"+g2.sk.head);
									while(jj2<=g2.sk.head && jj2!=-1 &&fff==1)
									{
										if((g2.sk.body[jj2].x-g2.aisk.aibody[g2.aisk.aihead].x)*(g2.sk.body[jj2].x-g2.aisk.aibody[g2.aisk.aihead].x)+(g2.sk.body[jj2].y-g2.aisk.aibody[g2.aisk.aihead].y)*(g2.sk.body[jj2].y-g2.aisk.aibody[g2.aisk.aihead].y)<(changespeed*changespeed))
										{
											isPaused = !isPaused;						
											fff=2;
											bbb=2;
											int res=JOptionPane.showConfirmDialog(null, "你们撞在一起拉！"+"玩家您的得分为  "+g1.getScore()+"  请选择是否重新开始", "游戏结束！", JOptionPane.YES_NO_OPTION);
											              if(res==JOptionPane.YES_OPTION){ 
											            	  sjd.jf.dispose();
											            	  sjd.f.dispose();
											            	  aau.stop();  //音乐停
											            	  sjd=new SwingJDialog();
											              }else{
											            	  System.exit(0);   
											             
											             } 
										}
										jj2=jj2+1;
									}
									
									if(bbb==1) {
									int jj11=g2.aisk.aitail;
									//System.out.println("g1.sk.tail"+g1.sk.tail);
									//System.out.println("g1.sk.head"+g1.sk.head);
									while(jj11<=g2.aisk.aihead && jj11!=-1)
									{
										//System.out.println("蛇的距离"+(g1.sk.body[jj1].x-g1.aisk.aibody[g1.aisk.aihead].x)*(g1.sk.body[jj1].x-g1.aisk.aibody[g1.aisk.aihead].x)+(g1.sk.body[jj1].y-g1.aisk.aibody[g1.aisk.aihead].y)*(g1.sk.body[jj1].y-g1.aisk.aibody[g1.aisk.aihead].y));
										if((g2.aisk.aibody[jj11].x-g2.sk.body[g2.sk.head].x)*(g2.aisk.aibody[jj11].x-g2.sk.body[g2.sk.head].x)+(g2.aisk.aibody[jj11].y-g2.sk.body[g2.sk.head].y)*(g2.aisk.aibody[jj11].y-g2.sk.body[g2.sk.head].y)<(changespeed*changespeed))
										{
											isPaused = !isPaused;						
											
											int res=JOptionPane.showConfirmDialog(null, "你们撞在一起拉！"+"玩家您的得分为  "+g1.getScore()+"  请选择是否重新开始", "游戏结束！", JOptionPane.YES_NO_OPTION);
											              if(res==JOptionPane.YES_OPTION){ 
											            	  sjd.jf.dispose();
											            	  sjd.f.dispose();
											            	  aau.stop();  //音乐停
											            	  sjd=new SwingJDialog();
											              }else{
											            	  System.exit(0);   
											           
											             } 
										}
										jj11=jj11+1;
									}
								}
								}
							}
						}
					}
				}
				if(flag==3)
				{
					g1.gameUpdate();
					System.out.println("模式3 小蛇一的坐标: ("+g1.x+","+g1.y+")");
					System.out.println("模式3 小蛇二的坐标: ("+g1.x2+","+g1.y2+")");
					if(g1.x >= 500 || g1.x <= 0 || g1.y >=500 || g1.y <= 0 ) 
					{
						isPaused = !isPaused;						
						
						int res=JOptionPane.showConfirmDialog(null, "玩家一你碰到边界啦！"+"玩家一您的得分为  "+g1.getScore()+"玩家二您的得分为  "+g1.getScore2()+"  请选择是否重新开始", "游戏结束！", JOptionPane.YES_NO_OPTION);
						              if(res==JOptionPane.YES_OPTION){ 
						            	  sjd.jf.dispose();
						            	  sjd.f.dispose();
						            	  aau.stop();  //音乐停
						            	  sjd=new SwingJDialog();
						              }else{
						            	  System.exit(0);   
						             
						             } 
					}
					else
					{
						if(g1.x2 >= 500 || g1.x2 <= 0 || g1.y2 >=500 || g1.y2 <= 0 ) 
						{
							isPaused = !isPaused;						
							
							int res=JOptionPane.showConfirmDialog(null, "玩家二你碰到边界啦！"+"玩家二您的得分为  "+g1.getScore2()+"玩家一您的得分为  "+g1.getScore()+"  请选择是否重新开始", "游戏结束！", JOptionPane.YES_NO_OPTION);
							              if(res==JOptionPane.YES_OPTION){ 
							            	  sjd.jf.dispose();
							            	  sjd.f.dispose();
							            	  aau.stop();  //音乐停
							            	  sjd=new SwingJDialog();
							              }else{
							            	  System.exit(0);   
							             
							             } 
						}
					}					
					int jj=g1.sk.tail;
					System.out.println("g1.sk.tail"+g1.sk.tail);
					System.out.println("g1.sk.head"+g1.sk.head);
					while(jj<=g1.sk.head && jj!=-1)
					{
						System.out.println("蛇的距离"+(g1.sk.body[jj].x-g1.sk2.body2[g1.sk2.head2].x)*(g1.sk.body[jj].x-g1.sk2.body2[g1.sk2.head2].x)+(g1.sk.body[jj].y-g1.sk2.body2[g1.sk2.head2].y)*(g1.sk.body[jj].y-g1.sk2.body2[g1.sk2.head2].y));
						if((g1.sk.body[jj].x-g1.sk2.body2[g1.sk2.head2].x)*(g1.sk.body[jj].x-g1.sk2.body2[g1.sk2.head2].x)+(g1.sk.body[jj].y-g1.sk2.body2[g1.sk2.head2].y)*(g1.sk.body[jj].y-g1.sk2.body2[g1.sk2.head2].y)<(changespeed*changespeed))
						{
							isPaused = !isPaused;						
							
							int res=JOptionPane.showConfirmDialog(null, "你们撞在一起拉！"+"玩家二您的得分为  "+g1.getScore2()+"玩家一您的得分为  "+g1.getScore()+"  请选择是否重新开始", "游戏结束！", JOptionPane.YES_NO_OPTION);
							              if(res==JOptionPane.YES_OPTION){ 
							            	  sjd.jf.dispose();
							            	  sjd.f.dispose();
							            	  aau.stop();  //音乐停
							            	  sjd=new SwingJDialog();
							              }else{
							            	  System.exit(0);   
							             
							             } 
						}
						jj=jj+1;
					}
				}
				if(flag==1)
				{
					g1.gameRender(im);
					g1.gamePaint(im);
				}
				if(flag==2)
				{
					g1.gameRender(im);
					g1.gamePaint(im);
					g2.gameRender(im);
					g2.gamePaint(im);
				}
				if(flag==3)
				{
					g1.gameRender(im);
					g1.gamePaint(im);
				}
				
			}
			
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println(111);
		int keycode = e.getKeyCode();// 获取按键信息
		System.out.println("keycode=" + keycode);
		if (keycode == KeyEvent.VK_SPACE)// 若按下的是“空格”键。则切换
			isPaused = !isPaused;
		if(flag==1)
		{
			switch (keycode) {// 根据不同的按键为direction赋值
			case KeyEvent.VK_DOWN:// 如果按键盘“下”方向键
				if(direction!=NORTH) {
					direction = SOUTH;
				}  
				g1.setDirection(direction);
				System.out.println("g1.flag"+g1.flag+" direction:"+direction);
				break;
			case KeyEvent.VK_UP:// 如果按键盘“上”方向键
				if(direction!=SOUTH) {
					direction = NORTH;
				}
				g1.setDirection(direction);
				System.out.println("g1.flag"+g1.flag+" direction:"+direction);
				break;
			case KeyEvent.VK_RIGHT:// 如果按键盘“右”方向键
				if(direction!=WEST) {
					direction = EAST;
				}
				g1.setDirection(direction);
				System.out.println("g1.flag"+g1.flag+" direction:"+direction);
				break;
			case KeyEvent.VK_LEFT:// 如果按键盘“左”方向键
				if(direction!=EAST) {
					direction = WEST;
				}
				g1.setDirection(direction);
				System.out.println("g1.flag"+g1.flag+" direction:"+direction);
				break;
			}
		}
		if(flag==2)
		{
			switch (keycode) {// 根据不同的按键为direction赋值
			case KeyEvent.VK_DOWN:// 如果按键盘“下”方向键
				if(direction!=NORTH) {
					direction = SOUTH;
				} 
				g1.setDirection(direction);
				System.out.println("g1.flag"+g1.flag+" direction:"+direction);
				break;
			case KeyEvent.VK_UP:// 如果按键盘“上”方向键
				if(direction!=SOUTH) {
					direction = NORTH;
				}
				g1.setDirection(direction);
				System.out.println("g1.flag"+g1.flag+" direction:"+direction);
				break;
			case KeyEvent.VK_RIGHT:// 如果按键盘“右”方向键
				if(direction!=WEST) {
					direction = EAST;
				}
				g1.setDirection(direction);
				System.out.println("g1.flag"+g1.flag+" direction:"+direction);
				break;
			case KeyEvent.VK_LEFT:// 如果按键盘“左”方向键
				if(direction!=EAST) {
					direction = WEST;
				}
				g1.setDirection(direction);
				System.out.println("g1.flag"+g1.flag+" direction:"+direction);
				break;
				
			case KeyEvent.VK_S:// 如果按键盘“下”方向键
				if(direction2!=NORTH) {
					direction2 = SOUTH;
				}
				g2.setDirection(direction2);
				System.out.println("g2.flag"+g2.flag+" direction2:"+direction2);
				break;
			case KeyEvent.VK_W:// 如果按键盘“上”方向键
				if(direction2!=SOUTH) {
					direction2 = NORTH;
				}
				g2.setDirection(direction2);
				System.out.println("g2.flag"+g2.flag+" direction2:"+direction2);
				break;
			case KeyEvent.VK_D:// 如果按键盘“右”方向键
				if(direction2!=WEST) {
					direction2 = EAST;
				}
				g2.setDirection(direction2);
				System.out.println("g2.flag"+g2.flag+" direction2:"+direction2);
				break;
			case KeyEvent.VK_A:// 如果按键盘“左”方向键
				if(direction2!=EAST) {
					direction2 = WEST;
				}
				g2.setDirection(direction2);
				System.out.println("g2.flag"+g2.flag+" direction2:"+direction2);
				break;
			}
		}
		if(flag==3)
		{
			switch (keycode) {// 根据不同的按键为direction赋值
			case KeyEvent.VK_DOWN:// 如果按键盘“下”方向键
				if(direction!=NORTH) {
					direction = SOUTH;
				}
				g1.setDirection(direction);
				System.out.println("g1.flag"+g1.flag+" direction:"+direction);
				break;
			case KeyEvent.VK_UP:// 如果按键盘“上”方向键
				if(direction!=SOUTH) {
					direction = NORTH;
				}
				g1.setDirection(direction);
				System.out.println("g1.flag"+g1.flag+" direction:"+direction);
				break;
			case KeyEvent.VK_RIGHT:// 如果按键盘“右”方向键
				if(direction!=WEST) {
					direction = EAST;
				}
				g1.setDirection(direction);
				System.out.println("g1.flag"+g1.flag+" direction:"+direction);
				break;
			case KeyEvent.VK_LEFT:// 如果按键盘“左”方向键
				if(direction!=EAST) {
					direction = WEST;
				}
				g1.setDirection(direction);
				System.out.println("g1.flag"+g1.flag+" direction:"+direction);
				break;
			case KeyEvent.VK_S:// 如果按键盘“下”方向键
				if(direction2!=NORTH) {
					direction2 = SOUTH;
				}
				g1.setDirection2(direction2);
				System.out.println("g1.flag"+g1.flag+" direction2:"+direction2);
				break;
			case KeyEvent.VK_W:// 如果按键盘“上”方向键
				if(direction2!=SOUTH) {
					direction2 = NORTH;
				}
				g1.setDirection2(direction2);
				System.out.println("g1.flag"+g1.flag+" direction2:"+direction2);
				break;
			case KeyEvent.VK_D:// 如果按键盘“右”方向键
				if(direction2!=WEST) {
					direction2 = EAST;
				}
				g1.setDirection2(direction2);
				System.out.println("g1.flag"+g1.flag+" direction2:"+direction2);
				break;
			case KeyEvent.VK_A:// 如果按键盘“左”方向键
				if(direction2!=EAST) {
					direction2 = WEST;
				}
				g1.setDirection2(direction2);
				System.out.println("g1.flag"+g1.flag+" direction2:"+direction2);
				break;
			}
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		sjd=new SwingJDialog();
		
	}
	

}

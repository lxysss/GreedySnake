package cwwlxy;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JOptionPane;


public class Snake {

	GamePanel gameP;
	public Point[] body;//点类型数组，保存蛇身个小球坐标
	public Point[] body2;//点类型数组，保存蛇身个小球坐标
	public static final int MAXLENTH=50;//蛇身最大长度
	public int head;//指示蛇头位置
	public int tail;//指示蛇尾位置
	public int length;//蛇身长度
	public int head2;//指示蛇头位置
	public int tail2;//指示蛇尾位置
	public int length2;//蛇身长度
	public int speed;//运行速度
	public int x,y;//蛇头小球的横纵坐标
	public int x2,y2;//蛇头小球的横纵坐标
	public int r;//蛇身小球的半径
	
	public int GameLocX = 0 , GameLocY = 0 , GameWidth = 500 , GameHeight = 500;
	
	public Snake(GamePanel gp,int changespeed){
		gameP=gp;//通过构造方法的参数来获取对GamePanel对象的引用
		speed=changespeed;
		body=new Point[MAXLENTH];
		head=-1;
		tail=-1;
		length=1;
		x=50;
		y=50;
		System.out.println("创造了小蛇，小蛇的位置为：（"+x+","+y+")");
		r=10;	
	}
	public Snake(GamePanel gp,int changespeed,int flag){
		if(flag==3)
		{
			gameP=gp;//通过构造方法的参数来获取对GamePanel对象的引用
			speed=changespeed;
			body2=new Point[MAXLENTH];
			head2=-1;
			tail2=-1;
			length2=1;
			x2=450;
			y2=50;
			System.out.println("创造了小蛇2，小蛇的位置为：（"+x2+","+y2+")");
			r=10;
		}
	}
	
	public void draw(Graphics g)
	{
		g.setColor(Color.white);
	    g.fillRect(GameLocX, GameLocY, GameWidth, GameHeight);  //刷新界面
	    g.setColor(Color.black);
	    g.drawRect(GameLocX, GameLocY, GameWidth, GameHeight);  //绘制边界
	    g.setColor(Color.BLUE);//设置蛇身为蓝色
		
		if(length>1){
			int i=tail;
			while(i!=head){//循环绘制蛇身各个小球
				g.fillOval(body[i].x, body[i].y, r, r);
				i=(i+1)%body.length;
				
			}
		}
		g.setColor(Color.RED);//设置蛇头为红色
		if(head>=0)
		{
			g.fillOval(body[head].x, body[head].y, r, r);
		}
		if(length==1){
			length++;
		}
	}
	
	public void draw(Graphics g,Snake sk){//绘制贪吃蛇的图形
		
	    g.setColor(Color.white);
	    g.fillRect(GameLocX, GameLocY, GameWidth, GameHeight);  //刷新界面
	    g.setColor(Color.black);
	    g.drawRect(GameLocX, GameLocY, GameWidth, GameHeight);  //绘制边界
	    
	    g.setColor(Color.BLUE);//设置蛇身为蓝色
		
		if(sk.length>1){
			int i=sk.tail;
			while(i!=sk.head){//循环绘制蛇身各个小球
				g.fillOval(sk.body[i].x, sk.body[i].y, r, r);
				i=(i+1)%sk.body.length;
				
			}
		}
		g.setColor(Color.RED);//设置蛇头为红色
		if(sk.head>=0)
		{
			g.fillOval(sk.body[sk.head].x, sk.body[sk.head].y, r, r);
		}
		if(sk.length==1){
			sk.length++;
		}
	    
	    g.setColor(Color.BLUE);//设置蛇身为蓝色
		
		if(length2>1){
			int i=tail2;
			while(i!=head2){//循环绘制蛇身各个小球
				g.fillOval(body2[i].x, body2[i].y, r, r);
				i=(i+1)%body2.length;
				
			}
		}
		g.setColor(Color.RED);//设置蛇头为红色
		if(head2>=0)
		{
			g.fillOval(body2[head2].x, body2[head2].y, r, r);
		}
		if(length2==1){
			length2++;
		}
	}
	
	public void draw(Graphics g,AISnake aisk){//绘制贪吃蛇的图形
		g.setColor(Color.white);
	    g.fillRect(GameLocX, GameLocY, GameWidth, GameHeight);  //刷新界面
	    g.setColor(Color.black);
	    g.drawRect(GameLocX, GameLocY, GameWidth, GameHeight);  //绘制边界
	    
//	    g.setColor(Color.BLUE);//设置蛇身为蓝色
//		
//		if(aisk.ailength>1&&aisk!=null){
//			int i=aisk.aitail;
//			while(i!=aisk.aihead){//循环绘制蛇身各个小球
//				g.fillOval(aisk.aibody[i].x, aisk.aibody[i].y, r, r);
//				i=(i+1)%aisk.aibody.length;
//				
//			}
//		}
//		g.setColor(Color.RED);//设置蛇头为红色
//		if(aisk.aihead>=0)
//		{
//			g.fillOval(aisk.aibody[aisk.aihead].x, aisk.aibody[aisk.aihead].y, r, r);
//		}
//		if(aisk.ailength==1){
//			aisk.ailength++;
//		}
	    aisk.draw1(g);
	    
	    g.setColor(Color.BLUE);//设置蛇身为蓝色
		
		if(length>1){
			int i=tail;
			while(i!=head){//循环绘制蛇身各个小球
				g.fillOval(body[i].x, body[i].y, r, r);
				i=(i+1)%body.length;
				
			}
		}
		g.setColor(Color.RED);//设置蛇头为红色
		if(head>=0)
		{
			g.fillOval(body[head].x, body[head].y, r, r);
		}
		if(length==1){
			length++;
		}
	}

	public void  update(){//更新游戏逻辑（贪吃蛇的坐标）
		
	//	issnake=false;
		int direction=gameP.getDirection();
		switch(direction){
			case GamePanel.SOUTH:
				y+=speed;
				break;
			case GamePanel.NORTH:
				y-=speed;
				break;
			case GamePanel.EAST:
				x+=speed;
				break;
			case GamePanel.WEST:
				x-=speed;
				break;
		}
		System.out.println("小蛇当前方向为 "+gameP.getDirection()+" 小蛇当前位置为("+x+","+y+")");
		head=(head+1)%body.length;//更新蛇头指针位置
		tail=(head+body.length-length+1)%body.length;//更新蛇尾指针坐标
		body[head]=new Point(x,y);//保存蛇头小球坐标值
		
		if(length>2){ //自己撞自己的检测
			int i=tail;
			System.out.println("i = "+i);
			while(i!=head && i!=head-1){//循环绘制蛇身各个小球
				if((body[i].x-body[head].x)*(body[i].x-body[head].x)+(body[i].y-body[head].y)*(body[i].y-body[head].y)<(speed*speed))
				{
					
					int res=JOptionPane.showConfirmDialog(null, "你碰到自己啦！"+"您的得分为  "+GameFrame.g1.getScore()+"  请选择是否重新开始", "游戏结束！", JOptionPane.YES_NO_OPTION);
		              if(res==JOptionPane.YES_OPTION){ 
		            	  System.exit(0);
		            	  GameFrame.aau.stop();  //音乐停
		            	  //点击“是”后执行这个代码块
		              }else{
		            	  System.exit(0);   //点击“否”后执行这个代码块
		             } 
				}
				i=(i+1)%body.length;
			}
		}

	}
	


public void update2(){//更新游戏逻辑（贪吃蛇的坐标）
		
		int direction2=gameP.getDirection2();
		switch(direction2){
			case GamePanel.SOUTH:
				y2+=speed;
				break;
			case GamePanel.NORTH:
				y2-=speed;
				break;
			case GamePanel.EAST:
				x2+=speed;
				break;
			case GamePanel.WEST:
				x2-=speed;
				break;
		}
		System.out.println("小蛇2当前方向为 "+gameP.getDirection2()+" 小蛇当前位置为("+x2+","+y2+")");
		head2=(head2+1)%body2.length;//更新蛇头指针位置
		tail2=(head2+body2.length-length2+1)%body2.length;//更新蛇尾指针坐标
		body2[head2]=new Point(x2,y2);//保存蛇头小球坐标值
		
		if(length2>2){ //自己撞自己的检测
			int i=tail2;
			System.out.println("i = "+i);
			while(i!=head2 && i!=head2-1){//循环绘制蛇身各个小球
				if((body2[i].x-body2[head2].x)*(body2[i].x-body2[head2].x)+(body2[i].y-body2[head2].y)*(body2[i].y-body2[head2].y)<(speed*speed))
				{
					
					int res=JOptionPane.showConfirmDialog(null, "有玩家碰到自己啦！"+"玩家一您的得分为  "+GameFrame.g1.getScore()+" 玩家二您的得分为  "+GameFrame.g2.getScore()+"  请选择是否重新开始", "游戏结束！", JOptionPane.YES_NO_OPTION);
		              if(res==JOptionPane.YES_OPTION){ 
		            	  System.exit(0);
		            	  GameFrame.aau.stop();  //音乐停
		            	  //点击“是”后执行这个代码块
		              }else{
		            	  System.exit(0);   //点击“否”后执行这个代码块
		             
		             } 
				}
				i=(i+1)%body2.length;
			}
		}

	}
   


	
}
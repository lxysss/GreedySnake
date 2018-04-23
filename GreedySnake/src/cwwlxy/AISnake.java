package cwwlxy;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

import javax.swing.JOptionPane;


public class AISnake {

	GamePanel gameP;
	public Point[] aibody;//点类型数组，保存蛇身个小球坐标
	public static final int MAXLENTH=50;//蛇身最大长度
	public int aihead;//指示蛇头位置
	public int aitail;//指示蛇尾位置
	public int ailength;//蛇身长度
	private int speed;//运行速度
	public int x,y;//蛇头小球的横纵坐标
	public int r;//蛇身小球的半径
	private int m,n,ain,aim;  //用于方向判断
	private int direction2=0;
	
	static final int GameLocX = 0 , GameLocY = 0 , GameWidth = 500 , GameHeight = 500;
	
	public AISnake(GamePanel gp,int changespeed){
		gameP=gp;//通过构造方法的参数来获取对GamePanel对象的引用
		aibody=new Point[MAXLENTH];
		aihead=-1;
		aitail=-1;
		ailength=1;
		speed=changespeed;
		x=400;
		y=80;
		r=10;
	}
	
	
	public void draw1(Graphics g){//绘制贪吃蛇的图形
		
		if(ailength>1){
			int i=aitail;
			System.out.println("i:"+i);
			while(i!=aihead){//循环绘制蛇身各个小球
				g.fillOval(aibody[i].x, aibody[i].y, r, r);
				i=(i+1)%aibody.length;
				
			}
		}
		g.setColor(Color.RED);//设置蛇头为红色
		if(aihead>=0)
		{
			g.fillOval(aibody[aihead].x, aibody[aihead].y, r, r);
		}
		if(ailength==1){
			ailength++;
		}

	}
	
	public void update(){//更新游戏逻辑（贪吃蛇的坐标）
		int direction;
		m=gameP.bk.x;
		n=gameP.bk.y;
		aim=m-x;
		ain=n-y;
		int ab=0;
		
		do {
		if(aim>0&&ain>0)  
			direction=new Random().nextInt(2)*2;
		else if(aim>0&&ain<0) 
			direction=new Random().nextInt(2)+1;
		else if(aim<0&&ain<0) 
			direction=new Random().nextInt(2)*2+1;
		else if(aim<0&&ain>0)
			direction=new Random().nextInt(2)*3;
		else if(aim==0&&ain>0)
			direction=0;
		else if(aim==0&&ain<0)
			direction=1;
		else if(aim>0&&ain==0)
			direction=2;
		else 
			direction=3;
		}while((direction2+direction==5)||(direction2+direction==1));
		direction2=direction;
		
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
		gameP.aix=x;
		gameP.aiy=y;
		System.out.println("ai小蛇的坐标为（"+x+","+y+")");
		
		
		if(ailength>1)
		{ //自己撞自己的检测
			int ii=aitail;
			while(ii!=aihead && ii!=aihead-1)
			{//循环绘制蛇身各个小球
				if((aibody[ii].x-aibody[aihead].x)*(aibody[ii].x-aibody[aihead].x)+(aibody[ii].y-aibody[aihead].y)*(aibody[ii].y-aibody[aihead].y)<(10*10))
				{
					ab=1;
                    break;
		        }
				else
				{
		            ab=0;
		        } 
				ii=(ii+1)%aibody.length;
			}
		}
		
		if(ab==1) 
		{
		    while(direction==direction2) 
		    {
		    	direction=new Random().nextInt(4);
		    }
	        direction2=direction;
		}
        
		aihead=(aihead+1)%aibody.length;//更新蛇头指针位置
		aitail=(aihead+aibody.length-ailength+1)%aibody.length;//更新蛇尾指针坐标
		aibody[aihead]=new Point(x,y);//保存蛇头小球坐标值

		
		
//		if(ailength>=2){ 
//			//Ai撞我们的检测
//			int jj=aitail;
//			System.out.println("jj = "+jj);
//			while(jj<=aihead)
//			{//循环绘制蛇身各个小球
//				System.out.println("小蛇离ai蛇的距离："+(aibody[jj].x-gameP.x)*(aibody[jj].x-gameP.x)+(aibody[jj].y-gameP.y)*(gameP.sk.body[jj].y-gameP.y));
//				if((aibody[jj].x-gameP.x)*(aibody[jj].x-gameP.x)+(aibody[jj].y-gameP.y)*(gameP.sk.body[jj].y-gameP.y)<(10))
//				{
//					int res=JOptionPane.showConfirmDialog(null, "您的得分为  "+gameP.bk.score+"  请选择是否重新开始", "游戏结束！", JOptionPane.YES_NO_OPTION);
//					if(res==JOptionPane.YES_OPTION){ 
//		          	    
//		            }
//					else{
//		            	  System.exit(0);   //点击“否”后执行这个代码块
//		             } 
//				}
//				jj=jj+1;
//			}
//		}
	}
}

	

package cwwlxy;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;


public class Food {
	
	private GamePanel gameP;
	private Snake snk;
	private Snake snk2;
	private AISnake aisk;
	public Point location;//食物的坐标
	public Point size;//食物方块的尺寸
	private Random rand;//随机类对象
	public int score = 0 ;
	public int score2 = 0 ;
	
	public int x,y;//食物的坐标点
	public int flag=1;
	

	public Food(GamePanel gp,Snake sk,int flag1){
		flag=flag1;
		gameP=gp;//通过构造方法的参数来获取对GamePanel对象的引用
		snk=sk;//通过构造方法的参数来获取对Snake对象的引用
		rand=new Random();
		//食物随机的出现在屏幕上某个位置
		x = (Math.abs(rand.nextInt(gp.getWidth())%gameP.getWidth())) % 480 + 10;//用于设定食物的投放范围
		y = (Math.abs(rand.nextInt(gp.getHeight())%gameP.getHeight())) % 480 + 10;
		System.out.println("食物出现的位置：("+x+","+y+")");		
		
		location=new Point(x,y);
		size=new Point(sk.r,sk.r);//食物尺寸与贪吃蛇小球大小相同
	}
	
	public Food(GamePanel gp,Snake sk,Snake sk2,int flag1){
		flag=flag1;
		gameP=gp;//通过构造方法的参数来获取对GamePanel对象的引用
		snk=sk;//通过构造方法的参数来获取对Snake对象的引用
		snk2=sk2;
		rand=new Random();
		//食物随机的出现在屏幕上某个位置
		x = (Math.abs(rand.nextInt(gp.getWidth())%gameP.getWidth())) % 480 + 10;//用于设定食物的投放范围
		y = (Math.abs(rand.nextInt(gp.getHeight())%gameP.getHeight())) % 480 + 10;
		System.out.println("食物出现的位置：("+x+","+y+")");		
		
		location=new Point(x,y);
		size=new Point(sk.r,sk.r);//食物尺寸与贪吃蛇小球大小相同
	}
	
	public Food(GamePanel gp,Snake sk,AISnake sk2,int flag1){
		flag=flag1;
		gameP=gp;//通过构造方法的参数来获取对GamePanel对象的引用
		snk=sk;//通过构造方法的参数来获取对Snake对象的引用
		aisk=sk2;
		rand=new Random();
		//食物随机的出现在屏幕上某个位置
		x = (Math.abs(rand.nextInt(gp.getWidth())%gameP.getWidth())) % 480 + 10;//用于设定食物的投放范围
		y = (Math.abs(rand.nextInt(gp.getHeight())%gameP.getHeight())) % 480 + 10;
		System.out.println("食物出现的位置：("+x+","+y+")");		
		
		location=new Point(x,y);
		size=new Point(sk.r,sk.r);//食物尺寸与贪吃蛇小球大小相同
	}
	
	public void draw(Graphics g){//绘制食物图形
		g.setColor(Color.PINK);//设置食物颜色
		g.fillRect(location.x, location.y, size.x, size.y);//绘制食物
	}
	
	public void update(GamePanel gp){//更新游戏逻辑（食物坐标）
		//碰撞检测（中心检测法），判断贪吃蛇是否吃到了食物
		if((snk.x-location.x)*(snk.x-location.x)+(snk.y-location.y)*(snk.y-location.y)<(snk.r*snk.r)){
			//若贪吃蛇的蛇头与食物发生碰撞，则随机生成新的食物位置
			
			x = (Math.abs(rand.nextInt(gp.getWidth())%gameP.getWidth())) % 480 + 10;
			y = (Math.abs(rand.nextInt(gp.getHeight())%gameP.getHeight())) % 480 + 10;
			System.out.println("食物新出现的位置：("+x+","+y+")");
			
			location=new Point(x,y);
			if(snk.length<Snake.MAXLENTH){
				snk.length++;//若蛇身长度未达到最大值，则蛇身伸长一个单位
				score ++;
				gameP.setLabelText(score);
			}	
		}
		if(flag==3&&snk2!=null)
		{
			if((snk2.x2-location.x)*(snk2.x2-location.x)+(snk2.y2-location.y)*(snk2.y2-location.y)<(snk2.r*snk2.r)){
				//若贪吃蛇的蛇头与食物发生碰撞，则随机生成新的食物位置
				
				x = (Math.abs(rand.nextInt(gp.getWidth())%gameP.getWidth())) % 480 + 10;
				y = (Math.abs(rand.nextInt(gp.getHeight())%gameP.getHeight())) % 480 + 10;
				System.out.println("食物新出现的位置：("+x+","+y+")");
				
				location=new Point(x,y);
				if(snk2.length2<Snake.MAXLENTH){
					snk2.length2++;//若蛇身长度未达到最大值，则蛇身伸长一个单位
					score2 ++;
					gameP.setLabelText2(score2);
				}	
			}
		}
		System.out.println("food flag:"+flag+"snk.speed:"+snk.speed);
		if(flag!=3&&(snk.speed==10||snk.speed==12)&&aisk.aihead!=-1)
		{
			System.out.println("ai蛇离食物的距离");
			System.out.println((aisk.aibody[aisk.aihead].x-location.x)*(aisk.aibody[aisk.aihead].x-location.x)+(aisk.aibody[aisk.aihead].y-location.y)*(aisk.aibody[aisk.aihead].y-location.y)<(snk.r*snk.r));
			if((aisk.aibody[aisk.aihead].x-location.x)*(aisk.aibody[aisk.aihead].x-location.x)+(aisk.aibody[aisk.aihead].y-location.y)*(aisk.aibody[aisk.aihead].y-location.y)<(snk.r*snk.r)){
				//若贪吃蛇的蛇头与食物发生碰撞，则随机生成新的食物位置
				
				x = (Math.abs(rand.nextInt(gp.getWidth())%gameP.getWidth())) % 480 + 10;
				y = (Math.abs(rand.nextInt(gp.getHeight())%gameP.getHeight())) % 480 + 10;
				System.out.println("食物新出现的位置：("+x+","+y+")");
				
				location=new Point(x,y);
				if(aisk.ailength<Snake.MAXLENTH){
					aisk.ailength++;//若蛇身长度未达到最大值，则蛇身伸长一个单位
				}	
			}
		}
	}
}
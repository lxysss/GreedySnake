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
	public Point location;//ʳ�������
	public Point size;//ʳ�﷽��ĳߴ�
	private Random rand;//��������
	public int score = 0 ;
	public int score2 = 0 ;
	
	public int x,y;//ʳ��������
	public int flag=1;
	

	public Food(GamePanel gp,Snake sk,int flag1){
		flag=flag1;
		gameP=gp;//ͨ�����췽���Ĳ�������ȡ��GamePanel���������
		snk=sk;//ͨ�����췽���Ĳ�������ȡ��Snake���������
		rand=new Random();
		//ʳ������ĳ�������Ļ��ĳ��λ��
		x = (Math.abs(rand.nextInt(gp.getWidth())%gameP.getWidth())) % 480 + 10;//�����趨ʳ���Ͷ�ŷ�Χ
		y = (Math.abs(rand.nextInt(gp.getHeight())%gameP.getHeight())) % 480 + 10;
		System.out.println("ʳ����ֵ�λ�ã�("+x+","+y+")");		
		
		location=new Point(x,y);
		size=new Point(sk.r,sk.r);//ʳ��ߴ���̰����С���С��ͬ
	}
	
	public Food(GamePanel gp,Snake sk,Snake sk2,int flag1){
		flag=flag1;
		gameP=gp;//ͨ�����췽���Ĳ�������ȡ��GamePanel���������
		snk=sk;//ͨ�����췽���Ĳ�������ȡ��Snake���������
		snk2=sk2;
		rand=new Random();
		//ʳ������ĳ�������Ļ��ĳ��λ��
		x = (Math.abs(rand.nextInt(gp.getWidth())%gameP.getWidth())) % 480 + 10;//�����趨ʳ���Ͷ�ŷ�Χ
		y = (Math.abs(rand.nextInt(gp.getHeight())%gameP.getHeight())) % 480 + 10;
		System.out.println("ʳ����ֵ�λ�ã�("+x+","+y+")");		
		
		location=new Point(x,y);
		size=new Point(sk.r,sk.r);//ʳ��ߴ���̰����С���С��ͬ
	}
	
	public Food(GamePanel gp,Snake sk,AISnake sk2,int flag1){
		flag=flag1;
		gameP=gp;//ͨ�����췽���Ĳ�������ȡ��GamePanel���������
		snk=sk;//ͨ�����췽���Ĳ�������ȡ��Snake���������
		aisk=sk2;
		rand=new Random();
		//ʳ������ĳ�������Ļ��ĳ��λ��
		x = (Math.abs(rand.nextInt(gp.getWidth())%gameP.getWidth())) % 480 + 10;//�����趨ʳ���Ͷ�ŷ�Χ
		y = (Math.abs(rand.nextInt(gp.getHeight())%gameP.getHeight())) % 480 + 10;
		System.out.println("ʳ����ֵ�λ�ã�("+x+","+y+")");		
		
		location=new Point(x,y);
		size=new Point(sk.r,sk.r);//ʳ��ߴ���̰����С���С��ͬ
	}
	
	public void draw(Graphics g){//����ʳ��ͼ��
		g.setColor(Color.PINK);//����ʳ����ɫ
		g.fillRect(location.x, location.y, size.x, size.y);//����ʳ��
	}
	
	public void update(GamePanel gp){//������Ϸ�߼���ʳ�����꣩
		//��ײ��⣨���ļ�ⷨ�����ж�̰�����Ƿ�Ե���ʳ��
		if((snk.x-location.x)*(snk.x-location.x)+(snk.y-location.y)*(snk.y-location.y)<(snk.r*snk.r)){
			//��̰���ߵ���ͷ��ʳ�﷢����ײ������������µ�ʳ��λ��
			
			x = (Math.abs(rand.nextInt(gp.getWidth())%gameP.getWidth())) % 480 + 10;
			y = (Math.abs(rand.nextInt(gp.getHeight())%gameP.getHeight())) % 480 + 10;
			System.out.println("ʳ���³��ֵ�λ�ã�("+x+","+y+")");
			
			location=new Point(x,y);
			if(snk.length<Snake.MAXLENTH){
				snk.length++;//��������δ�ﵽ���ֵ���������쳤һ����λ
				score ++;
				gameP.setLabelText(score);
			}	
		}
		if(flag==3&&snk2!=null)
		{
			if((snk2.x2-location.x)*(snk2.x2-location.x)+(snk2.y2-location.y)*(snk2.y2-location.y)<(snk2.r*snk2.r)){
				//��̰���ߵ���ͷ��ʳ�﷢����ײ������������µ�ʳ��λ��
				
				x = (Math.abs(rand.nextInt(gp.getWidth())%gameP.getWidth())) % 480 + 10;
				y = (Math.abs(rand.nextInt(gp.getHeight())%gameP.getHeight())) % 480 + 10;
				System.out.println("ʳ���³��ֵ�λ�ã�("+x+","+y+")");
				
				location=new Point(x,y);
				if(snk2.length2<Snake.MAXLENTH){
					snk2.length2++;//��������δ�ﵽ���ֵ���������쳤һ����λ
					score2 ++;
					gameP.setLabelText2(score2);
				}	
			}
		}
		System.out.println("food flag:"+flag+"snk.speed:"+snk.speed);
		if(flag!=3&&(snk.speed==10||snk.speed==12)&&aisk.aihead!=-1)
		{
			System.out.println("ai����ʳ��ľ���");
			System.out.println((aisk.aibody[aisk.aihead].x-location.x)*(aisk.aibody[aisk.aihead].x-location.x)+(aisk.aibody[aisk.aihead].y-location.y)*(aisk.aibody[aisk.aihead].y-location.y)<(snk.r*snk.r));
			if((aisk.aibody[aisk.aihead].x-location.x)*(aisk.aibody[aisk.aihead].x-location.x)+(aisk.aibody[aisk.aihead].y-location.y)*(aisk.aibody[aisk.aihead].y-location.y)<(snk.r*snk.r)){
				//��̰���ߵ���ͷ��ʳ�﷢����ײ������������µ�ʳ��λ��
				
				x = (Math.abs(rand.nextInt(gp.getWidth())%gameP.getWidth())) % 480 + 10;
				y = (Math.abs(rand.nextInt(gp.getHeight())%gameP.getHeight())) % 480 + 10;
				System.out.println("ʳ���³��ֵ�λ�ã�("+x+","+y+")");
				
				location=new Point(x,y);
				if(aisk.ailength<Snake.MAXLENTH){
					aisk.ailength++;//��������δ�ﵽ���ֵ���������쳤һ����λ
				}	
			}
		}
	}
}
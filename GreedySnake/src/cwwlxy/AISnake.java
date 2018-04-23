package cwwlxy;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

import javax.swing.JOptionPane;


public class AISnake {

	GamePanel gameP;
	public Point[] aibody;//���������飬���������С������
	public static final int MAXLENTH=50;//������󳤶�
	public int aihead;//ָʾ��ͷλ��
	public int aitail;//ָʾ��βλ��
	public int ailength;//������
	private int speed;//�����ٶ�
	public int x,y;//��ͷС��ĺ�������
	public int r;//����С��İ뾶
	private int m,n,ain,aim;  //���ڷ����ж�
	private int direction2=0;
	
	static final int GameLocX = 0 , GameLocY = 0 , GameWidth = 500 , GameHeight = 500;
	
	public AISnake(GamePanel gp,int changespeed){
		gameP=gp;//ͨ�����췽���Ĳ�������ȡ��GamePanel���������
		aibody=new Point[MAXLENTH];
		aihead=-1;
		aitail=-1;
		ailength=1;
		speed=changespeed;
		x=400;
		y=80;
		r=10;
	}
	
	
	public void draw1(Graphics g){//����̰���ߵ�ͼ��
		
		if(ailength>1){
			int i=aitail;
			System.out.println("i:"+i);
			while(i!=aihead){//ѭ�������������С��
				g.fillOval(aibody[i].x, aibody[i].y, r, r);
				i=(i+1)%aibody.length;
				
			}
		}
		g.setColor(Color.RED);//������ͷΪ��ɫ
		if(aihead>=0)
		{
			g.fillOval(aibody[aihead].x, aibody[aihead].y, r, r);
		}
		if(ailength==1){
			ailength++;
		}

	}
	
	public void update(){//������Ϸ�߼���̰���ߵ����꣩
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
		System.out.println("aiС�ߵ�����Ϊ��"+x+","+y+")");
		
		
		if(ailength>1)
		{ //�Լ�ײ�Լ��ļ��
			int ii=aitail;
			while(ii!=aihead && ii!=aihead-1)
			{//ѭ�������������С��
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
        
		aihead=(aihead+1)%aibody.length;//������ͷָ��λ��
		aitail=(aihead+aibody.length-ailength+1)%aibody.length;//������βָ������
		aibody[aihead]=new Point(x,y);//������ͷС������ֵ

		
		
//		if(ailength>=2){ 
//			//Aiײ���ǵļ��
//			int jj=aitail;
//			System.out.println("jj = "+jj);
//			while(jj<=aihead)
//			{//ѭ�������������С��
//				System.out.println("С����ai�ߵľ��룺"+(aibody[jj].x-gameP.x)*(aibody[jj].x-gameP.x)+(aibody[jj].y-gameP.y)*(gameP.sk.body[jj].y-gameP.y));
//				if((aibody[jj].x-gameP.x)*(aibody[jj].x-gameP.x)+(aibody[jj].y-gameP.y)*(gameP.sk.body[jj].y-gameP.y)<(10))
//				{
//					int res=JOptionPane.showConfirmDialog(null, "���ĵ÷�Ϊ  "+gameP.bk.score+"  ��ѡ���Ƿ����¿�ʼ", "��Ϸ������", JOptionPane.YES_NO_OPTION);
//					if(res==JOptionPane.YES_OPTION){ 
//		          	    
//		            }
//					else{
//		            	  System.exit(0);   //������񡱺�ִ����������
//		             } 
//				}
//				jj=jj+1;
//			}
//		}
	}
}

	

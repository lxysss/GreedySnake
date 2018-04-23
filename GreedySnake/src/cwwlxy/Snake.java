package cwwlxy;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JOptionPane;


public class Snake {

	GamePanel gameP;
	public Point[] body;//���������飬���������С������
	public Point[] body2;//���������飬���������С������
	public static final int MAXLENTH=50;//������󳤶�
	public int head;//ָʾ��ͷλ��
	public int tail;//ָʾ��βλ��
	public int length;//������
	public int head2;//ָʾ��ͷλ��
	public int tail2;//ָʾ��βλ��
	public int length2;//������
	public int speed;//�����ٶ�
	public int x,y;//��ͷС��ĺ�������
	public int x2,y2;//��ͷС��ĺ�������
	public int r;//����С��İ뾶
	
	public int GameLocX = 0 , GameLocY = 0 , GameWidth = 500 , GameHeight = 500;
	
	public Snake(GamePanel gp,int changespeed){
		gameP=gp;//ͨ�����췽���Ĳ�������ȡ��GamePanel���������
		speed=changespeed;
		body=new Point[MAXLENTH];
		head=-1;
		tail=-1;
		length=1;
		x=50;
		y=50;
		System.out.println("������С�ߣ�С�ߵ�λ��Ϊ����"+x+","+y+")");
		r=10;	
	}
	public Snake(GamePanel gp,int changespeed,int flag){
		if(flag==3)
		{
			gameP=gp;//ͨ�����췽���Ĳ�������ȡ��GamePanel���������
			speed=changespeed;
			body2=new Point[MAXLENTH];
			head2=-1;
			tail2=-1;
			length2=1;
			x2=450;
			y2=50;
			System.out.println("������С��2��С�ߵ�λ��Ϊ����"+x2+","+y2+")");
			r=10;
		}
	}
	
	public void draw(Graphics g)
	{
		g.setColor(Color.white);
	    g.fillRect(GameLocX, GameLocY, GameWidth, GameHeight);  //ˢ�½���
	    g.setColor(Color.black);
	    g.drawRect(GameLocX, GameLocY, GameWidth, GameHeight);  //���Ʊ߽�
	    g.setColor(Color.BLUE);//��������Ϊ��ɫ
		
		if(length>1){
			int i=tail;
			while(i!=head){//ѭ�������������С��
				g.fillOval(body[i].x, body[i].y, r, r);
				i=(i+1)%body.length;
				
			}
		}
		g.setColor(Color.RED);//������ͷΪ��ɫ
		if(head>=0)
		{
			g.fillOval(body[head].x, body[head].y, r, r);
		}
		if(length==1){
			length++;
		}
	}
	
	public void draw(Graphics g,Snake sk){//����̰���ߵ�ͼ��
		
	    g.setColor(Color.white);
	    g.fillRect(GameLocX, GameLocY, GameWidth, GameHeight);  //ˢ�½���
	    g.setColor(Color.black);
	    g.drawRect(GameLocX, GameLocY, GameWidth, GameHeight);  //���Ʊ߽�
	    
	    g.setColor(Color.BLUE);//��������Ϊ��ɫ
		
		if(sk.length>1){
			int i=sk.tail;
			while(i!=sk.head){//ѭ�������������С��
				g.fillOval(sk.body[i].x, sk.body[i].y, r, r);
				i=(i+1)%sk.body.length;
				
			}
		}
		g.setColor(Color.RED);//������ͷΪ��ɫ
		if(sk.head>=0)
		{
			g.fillOval(sk.body[sk.head].x, sk.body[sk.head].y, r, r);
		}
		if(sk.length==1){
			sk.length++;
		}
	    
	    g.setColor(Color.BLUE);//��������Ϊ��ɫ
		
		if(length2>1){
			int i=tail2;
			while(i!=head2){//ѭ�������������С��
				g.fillOval(body2[i].x, body2[i].y, r, r);
				i=(i+1)%body2.length;
				
			}
		}
		g.setColor(Color.RED);//������ͷΪ��ɫ
		if(head2>=0)
		{
			g.fillOval(body2[head2].x, body2[head2].y, r, r);
		}
		if(length2==1){
			length2++;
		}
	}
	
	public void draw(Graphics g,AISnake aisk){//����̰���ߵ�ͼ��
		g.setColor(Color.white);
	    g.fillRect(GameLocX, GameLocY, GameWidth, GameHeight);  //ˢ�½���
	    g.setColor(Color.black);
	    g.drawRect(GameLocX, GameLocY, GameWidth, GameHeight);  //���Ʊ߽�
	    
//	    g.setColor(Color.BLUE);//��������Ϊ��ɫ
//		
//		if(aisk.ailength>1&&aisk!=null){
//			int i=aisk.aitail;
//			while(i!=aisk.aihead){//ѭ�������������С��
//				g.fillOval(aisk.aibody[i].x, aisk.aibody[i].y, r, r);
//				i=(i+1)%aisk.aibody.length;
//				
//			}
//		}
//		g.setColor(Color.RED);//������ͷΪ��ɫ
//		if(aisk.aihead>=0)
//		{
//			g.fillOval(aisk.aibody[aisk.aihead].x, aisk.aibody[aisk.aihead].y, r, r);
//		}
//		if(aisk.ailength==1){
//			aisk.ailength++;
//		}
	    aisk.draw1(g);
	    
	    g.setColor(Color.BLUE);//��������Ϊ��ɫ
		
		if(length>1){
			int i=tail;
			while(i!=head){//ѭ�������������С��
				g.fillOval(body[i].x, body[i].y, r, r);
				i=(i+1)%body.length;
				
			}
		}
		g.setColor(Color.RED);//������ͷΪ��ɫ
		if(head>=0)
		{
			g.fillOval(body[head].x, body[head].y, r, r);
		}
		if(length==1){
			length++;
		}
	}

	public void  update(){//������Ϸ�߼���̰���ߵ����꣩
		
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
		System.out.println("С�ߵ�ǰ����Ϊ "+gameP.getDirection()+" С�ߵ�ǰλ��Ϊ("+x+","+y+")");
		head=(head+1)%body.length;//������ͷָ��λ��
		tail=(head+body.length-length+1)%body.length;//������βָ������
		body[head]=new Point(x,y);//������ͷС������ֵ
		
		if(length>2){ //�Լ�ײ�Լ��ļ��
			int i=tail;
			System.out.println("i = "+i);
			while(i!=head && i!=head-1){//ѭ�������������С��
				if((body[i].x-body[head].x)*(body[i].x-body[head].x)+(body[i].y-body[head].y)*(body[i].y-body[head].y)<(speed*speed))
				{
					
					int res=JOptionPane.showConfirmDialog(null, "�������Լ�����"+"���ĵ÷�Ϊ  "+GameFrame.g1.getScore()+"  ��ѡ���Ƿ����¿�ʼ", "��Ϸ������", JOptionPane.YES_NO_OPTION);
		              if(res==JOptionPane.YES_OPTION){ 
		            	  System.exit(0);
		            	  GameFrame.aau.stop();  //����ͣ
		            	  //������ǡ���ִ����������
		              }else{
		            	  System.exit(0);   //������񡱺�ִ����������
		             } 
				}
				i=(i+1)%body.length;
			}
		}

	}
	


public void update2(){//������Ϸ�߼���̰���ߵ����꣩
		
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
		System.out.println("С��2��ǰ����Ϊ "+gameP.getDirection2()+" С�ߵ�ǰλ��Ϊ("+x2+","+y2+")");
		head2=(head2+1)%body2.length;//������ͷָ��λ��
		tail2=(head2+body2.length-length2+1)%body2.length;//������βָ������
		body2[head2]=new Point(x2,y2);//������ͷС������ֵ
		
		if(length2>2){ //�Լ�ײ�Լ��ļ��
			int i=tail2;
			System.out.println("i = "+i);
			while(i!=head2 && i!=head2-1){//ѭ�������������С��
				if((body2[i].x-body2[head2].x)*(body2[i].x-body2[head2].x)+(body2[i].y-body2[head2].y)*(body2[i].y-body2[head2].y)<(speed*speed))
				{
					
					int res=JOptionPane.showConfirmDialog(null, "����������Լ�����"+"���һ���ĵ÷�Ϊ  "+GameFrame.g1.getScore()+" ��Ҷ����ĵ÷�Ϊ  "+GameFrame.g2.getScore()+"  ��ѡ���Ƿ����¿�ʼ", "��Ϸ������", JOptionPane.YES_NO_OPTION);
		              if(res==JOptionPane.YES_OPTION){ 
		            	  System.exit(0);
		            	  GameFrame.aau.stop();  //����ͣ
		            	  //������ǡ���ִ����������
		              }else{
		            	  System.exit(0);   //������񡱺�ִ����������
		             
		             } 
				}
				i=(i+1)%body2.length;
			}
		}

	}
   


	
}
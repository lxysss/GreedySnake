package cwwlxy;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class SwingJDialog {
	
	public int mode=1;
	public int degree=1;
	public GameFrame f;
	final JFrame jf;
	
	public SwingJDialog(){
		jf=new JFrame("��Ϸ����");
		Dimension screensize=Toolkit.getDefaultToolkit().getScreenSize(); 
		int Swing1x=800;
		int Swing1y=600;
		jf.setBounds(screensize.width/2-Swing1x/2,screensize.height/2-Swing1y/2,Swing1x,Swing1y);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c=jf.getContentPane();
		c.setBackground(Color.pink);
		c.setLayout(null);
		
		JLabel label1=new JLabel("�����Ϸ������ģʽ���ֱ�Ϊ����ģʽ��˫��ģʽA��˫��ģʽB");
		label1.setBounds(0, 0, 800, 100);
		label1.setFont(new Font("", Font.BOLD, 20)); // �������ֵ����弰��С
		JLabel label2=new JLabel("����˫��ģʽA����������ҷֱ��������������̰���ߣ�˫��ģʽB�������������һ��������档");		
		label2.setBounds(0, 100, 800, 100);
		label2.setFont(new Font("", Font.BOLD, 20)); // �������ֵ����弰��С
		JLabel label3=new JLabel("�����Ϸ�������Ѷȣ��ֱ�Ϊ�򵥣�һ�㣬����");		
		label3.setBounds(0, 200, 800, 100);
		label3.setFont(new Font("", Font.BOLD, 20)); // �������ֵ����弰��С
		JLabel label4=new JLabel("�����Ѷȵ��ٶȲ�һ����һ������ѻ���ai�ߣ�����˫��ģʽB��һ�������û��ai�ߡ�");		
		label4.setBounds(0, 300, 800, 100);
		label4.setFont(new Font("", Font.BOLD, 20)); // �������ֵ����弰��С

		Dimension Swing1size=jf.getSize();
		JButton jb=new JButton("��ʼ��Ϸ");
		int jbx=100;
		int jby=30;
		jb.setBounds(Swing1size.width/2-jbx/2,400,jbx,jby);
		c.add(label1);
		c.add(label2);
		c.add(label3);
		c.add(label4);
		c.add(jb);

		class Dialog1 {
			JDialog jd=new JDialog(jf,"ѡ��ģʽ",true);
			Dialog1(final int flag){
				int Swing1x=600;
				int Swing1y=400;
				Dimension screensize=Toolkit.getDefaultToolkit().getScreenSize(); 
				jd.setBounds(screensize.width/2-Swing1x/2,screensize.height/2-Swing1y/2,Swing1x,Swing1y);
				Container c2=jd.getContentPane();
				c2.setLayout(null);
				JLabel jl;
				JButton singal;
				JButton doubleA;
				JButton doubleB;
				if(flag==1)
				{
					jl=new JLabel("��ѡ��ģʽ");
					jl.setBounds(0,-20,Swing1x,100);
					jl.setFont(new Font("", Font.BOLD, 20)); // �������ֵ����弰��С
					singal=new JButton("����ģʽ");
					singal.setBounds(50,150,100,50);
					doubleA=new JButton("˫��ģʽA");
					doubleA.setBounds(200,150,100,50);
					doubleB=new JButton("˫��ģʽB");
					doubleB.setBounds(350,150,100,50);
				}
				else
				{
					jl=new JLabel("��ѡ���Ѷ�");
					jl.setBounds(0,-20,Swing1x,100);
					jl.setFont(new Font("", Font.BOLD, 20)); // �������ֵ����弰��С
					singal=new JButton("��");
					singal.setBounds(50,150,100,50);
					doubleA=new JButton("һ��");
					doubleA.setBounds(200,150,100,50);
					doubleB=new JButton("����");
					doubleB.setBounds(350,150,100,50);
				}
				c2.add(jl);
				c2.add(singal);
				c2.add(doubleA);
				c2.add(doubleB);
				singal.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						if(flag==1)
						{
							mode=1;
							jd.dispose();
							new Dialog1(2).jd.setVisible(true);//�����Ի���
						}
						else
						{
							degree=1;
							jd.dispose();
							f=new GameFrame(mode,degree);
							Thread thread = new Thread(f);
							thread.start();
							
						}
					}
				});
				doubleA.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						if(flag==1)
						{
							mode=2;
							jd.dispose();
							new Dialog1(2).jd.setVisible(true);//�����Ի���
						}
						else
						{
							degree=2;
							jd.dispose();
							f=new GameFrame(mode,degree);
							Thread thread = new Thread(f);
							thread.start();
						}
					}
				});
				doubleB.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						if(flag==1)
						{
							mode=3;
							jd.dispose();
							new Dialog1(2).jd.setVisible(true);//�����Ի���
						}
						else
						{
							degree=3;
							jd.dispose();
							f=new GameFrame(mode,degree);
							Thread thread = new Thread(f);
							thread.start();
						}
					}
				});
				jd.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

			}
		}
		
		
		jb.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new Dialog1(1).jd.setVisible(true);//�����Ի���
			}
		});
	}

}
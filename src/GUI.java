import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;

import javax.swing.JFrame;
import javax.swing.JLabel;





public class GUI extends JFrame {
	
	
	private JLabel a[][];
	private GridBagConstraints c=new GridBagConstraints();
	private JLabel l;
	private Tavola t;
	
	private Engine e;
	private ImageIcon cvn =new ImageIcon("cvn.jpg");
	private ImageIcon pb=new ImageIcon("pb.jpg");
	private ImageIcon pn =new ImageIcon("pn.jpg");
	private ImageIcon psb=new ImageIcon("psb.jpg");
	private ImageIcon psn =new ImageIcon("psn.jpg");
	private ImageIcon cvb =new ImageIcon("cvb.jpg");
	private ImageIcon dn =new ImageIcon("dn.jpg");
	private ImageIcon dsb=new ImageIcon("dsb.jpg");
	private ImageIcon dsn =new ImageIcon("dsn.jpg");
	private ImageIcon db =new ImageIcon("db.jpg");
	
	
	
	public GUI(String name,Tavola t){		
		super(name);
		e=new Engine(0);
		this.t=t;
		this.setLayout(new GridBagLayout());
		this.a=new JLabel [8][8];
		for(int i=0;i<a.length;i++){
			for(int j=0;j<a[0].length;j++){
				c.gridx=j;
				c.gridy=i;
				
				a[i][j]=new JLabel();
				a[i][j].addMouseListener(new GestoreEventi());
				
				this.add(a[i][j],c);
				
			}			
		}
			l=new JLabel();
			c.gridx=0;
			c.gridy=8;
			c.gridwidth=8;
			this.add(l,c);
			
			this.aggiorna();
			
			this.setSize(400,400);
			this.setVisible(true);
		}
		private void aggiorna(){
			for(int i=0;i<a.length;i++)
				for(int j=0;j<a[0].length;j++){
					if(t.getText(i,j)==' ')
						a[i][j].setIcon(cvn);
					if(t.getText(i,j)=='b')
						a[i][j].setIcon(pb);
					if(t.getText(i,j)=='n')
						a[i][j].setIcon(pn);
					if(t.getText(i,j)=='x')
						a[i][j].setIcon(cvb);	
					if(t.getText(i,j)=='B')
						a[i][j].setIcon(db);
					if(t.getText(i,j)=='N')
						a[i][j].setIcon(dn);
					
				}
			l.setText(e.getArbitro().getTurnoToString());
		}
		private void aggiornaSM(){
			if(e.getArbitro().getTurnoToString().contains("bianco")){
				if(t.getText(e.getArbitro().getSource())=='b')
				a[e.getArbitro().getSource()[0]][e.getArbitro().getSource()[1]].setIcon(psb);
				if(t.getText(e.getArbitro().getSource())=='B')
					a[e.getArbitro().getSource()[0]][e.getArbitro().getSource()[1]].setIcon(dsb);
			}
			if(e.getArbitro().getTurnoToString().contains("nero")){
				if(t.getText(e.getArbitro().getSource())=='n')
				a[e.getArbitro().getSource()[0]][e.getArbitro().getSource()[1]].setIcon(psn);
				if(t.getText(e.getArbitro().getSource())=='N')
					a[e.getArbitro().getSource()[0]][e.getArbitro().getSource()[1]].setIcon(dsn);
			}
			
		}
		
		

		
		

		
		
		private class GestoreEventi implements MouseListener{
			
			
			
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				
				for(int i=0;i<a.length;i++){
					for(int j=0;j<a[0].length;j++){
						if(arg0.getSource()==a[i][j]){
							int r=e.receivedinput(t, i, j);
							if(r==0)
								aggiornaSM();
							if(r==1)
								aggiorna();
							if(r==3){
								aggiorna();//toglie pedina vecchia;
								aggiornaSM();
							}
						}
						
						
							
					}
				}
			}
							
				
				
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
				
			

			
			
		}
	}
	



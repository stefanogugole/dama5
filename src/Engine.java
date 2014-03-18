

import javax.swing.JOptionPane;


public class Engine {
	private Arbitro ar=new Arbitro();
	private boolean mangiato=false;	
	private int user;
	
	public Engine(int user){
		this.user=user;
		
	}
	public char getUserColor(){
		if(user==1)
			return 'n';
		else
			return 'b';
	}
	public Arbitro getArbitro(){
		return ar;
	}
	
	public int receivedinput(Tavola t,int i,int j) {//0 mezza mossa 1 mossa intera 3 speciale doppia mangiata
		
		boolean f=true;
		
				if(!(ar.inseritaSource())){//moveSource[0]==-1
					//moveSource[0]=i;
					//moveSource[1]=j;
					
					ar.setSource(i, j);
					f=false;
					if(!ar.controlSource(t)){
						ar.resettaMossa();
						JOptionPane.showMessageDialog(null,"casella sorgente non valida");
					}else
						return 0;//seleziona la pedina col giallo
				}
				
				if(ar.inseritaSource()&&f){//moveSource[0]!=-1
					//t.setText(t.getText(moveSource[0], moveSource[1]), i, j);
					//t.setText(' ',moveSource[0], moveSource[1]);
					//resettaMossa();
					//aggiorna();
					//moveDestination[0]=i;
					//moveDestination[1]=j;
					if(ar.getSource()[0]==0&&ar.getSource()[1]==0&&t.getText(ar.getSource())=='B')
						 System.out.print("");
					boolean c=false;
					if(this.mangiabili(t)!=null)
						c=true;
						
					
					ar.setDestination(i, j);
					
					if(!mangiato){
						if(!ar.control(t)){
							
								
									JOptionPane.showMessageDialog(null,"mossa non valida1");
									ar.resettaMossa();
									return 1;
								
									
							
							
						}else{
							if(c&&!ar.controlMangiata(t)){
								
									JOptionPane.showMessageDialog(null,"pedina soffiata");
									
									t.setPedina(' ', this.mangiabili(t));
									
									
									ar.resettaMossa();
									return 1;
								}
								
						}
							
					}else{
						if(!ar.controlMangiata(t)){
						System.out.print(ar.getSource()[0]+";"+ar.getSource()[1]+";"+t.getText(ar.getSource()));
						JOptionPane.showMessageDialog(null,"mossa non valida2");
						ar.setDestination(-1, -1);
						return 0;
						}
					}
					
				}						
				if(ar.inseritaSource()&&ar.inseritaDestinazione()){
					System.out.println(t.toString());
					if(ar.controlMangiata(t)){
						eseguiMangiata(t);//togli le pedine
						t.setText(t.getText(ar.getSource()), ar.getDestination());//t.setText(t.getText(moveSource[0], moveSource[1]), moveDestination[0], moveDestination[1]);
						t.setText(' ',ar.getSource());
						//System.out.print("mangiaato");
						Arbitro arr=new Arbitro(ar);
						arr.setSource(arr.getDestination());//copio su un a,tro ar
						
						
						if(simulaMangiata(t,arr)){
							ar.setSource(ar.getDestination());
							ar.nexTurn();ar.nexTurn();
							mangiato=true;
							return 3;
							
							
						}else{
							if(avvenutaPromozione(t))
								t.setPedina((""+getUserColor()).toUpperCase().charAt(0),pedinaPromossa(t));//cambia pedina incriminata in damone
							ar.nexTurn();
							ar.resettaMossa();
							mangiato=false;
							return 1;
						}
						
					}else{
						
						t.setText(t.getText(ar.getSource()), ar.getDestination());//t.setText(t.getText(moveSource[0], moveSource[1]), moveDestination[0], moveDestination[1]);
						t.setText(' ',ar.getSource());
						if(avvenutaPromozione(t))
							t.setPedina(ar.getTurnoToString().toUpperCase().charAt(10),pedinaPromossa(t));
						ar.nexTurn();//turno++
						ar.resettaMossa();
						return 1;
					}
					
					
				}
				return 9;		
				
					
			}
	public boolean avvenutaPromozione(Tavola t){
		if(pedinaPromossa(t)!=null)
			return true;
		return false;
	}
	public Pedina pedinaPromossa(Tavola t){
		if(this.getUserColor()=='b'){
			
			if(ar.getTurnoToString().contains("bianco"))
					if(ar.getDestination()[0]==0)
						return t.getPedina(ar.getDestination());
			if(ar.getTurnoToString().contains("nero"))
				if(ar.getDestination()[0]==7)
					return t.getPedina(ar.getDestination());
		}
		if(this.getUserColor()=='n'){
			if(ar.getDestination()[0]==7)
				return t.getPedina(ar.getDestination());
		}
		return null;
	}
	public Pedina mangiabili(Tavola t){//pedinaChePuoMangiare
		Arbitro arr=new Arbitro();
		if(ar.getTurnoToString().contains("nero"))
			arr.nexTurn();
		
			for(int j=0;j<8;j++)
				for(int i=0;i<8;i++)
				if((""+t.getText(i, j)).equalsIgnoreCase((arr.getTurnoToString().charAt(10)+""))){
					arr.setSource(i, j);
					if(simulaMangiata(t,arr))
						return this.getsimulaMangiata(t,arr);
				}
		return null;
					
	}
	public boolean simulaMangiata(Tavola t,Arbitro arr){//true se la mossa è una mangiata e si può fare
		if(this.getsimulaMangiata(t,arr)!=null)
			return true;
		else
			return false;
			
	}
	public Pedina getsimulaMangiata(Tavola t,Arbitro arr){//true se la mossa è una mangiata e si può fare
		Arbitro temp=new Arbitro(arr);
		//posso provare a mangiare
		Pedina b=null;	
					try{
					temp.setDestination(arr.getSource()[0]-2,arr.getSource()[1]-2);
					if(temp.controlMangiata(t))
						return temp.mangiante(t);
					}catch(ArrayIndexOutOfBoundsException e){b= null;}
					try{
						temp.setDestination(arr.getSource()[0]-2,arr.getSource()[1]+2);
					if(temp.controlMangiata(t))
						return temp.mangiante(t);
					}catch(ArrayIndexOutOfBoundsException e){b= null;}
					if(t.getText(arr.getSource())=='N'||t.getText(arr.getSource())=='B'){
						try{
							temp.setDestination(arr.getSource()[0]+2,arr.getSource()[1]+2);
							if(temp.controlMangiata(t))
								return temp.mangiante(t);
							}catch(ArrayIndexOutOfBoundsException e){b= null;}
							try{
								temp.setDestination(arr.getSource()[0]+2,arr.getSource()[1]-2);
							if(temp.controlMangiata(t))
								return temp.mangiante(t);
							}catch(ArrayIndexOutOfBoundsException e){b= null;}						
					}
		
		
		
		return b;
	}
	public void eseguiMangiata(Tavola t){
		
		//posso provare a mangiare
		Pedina p=ar.mangiata(t);
		t.setPedina(' ', p);
	}
		
		
		
				

}

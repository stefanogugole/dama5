
public class Arbitro {
	private int moveSource []=new int [2];
	private int moveDestination []= new int[2];
	private int turno=0;
	
	public Arbitro(){
		this.resettaMossa();
	}
	public Arbitro(Arbitro a){
		for(int i=0;i<this.moveDestination.length;i++){
			this.moveDestination[i]=a.moveDestination[i];
			this.moveSource[i]=a.moveSource[i];
		}
		
		this.turno=a.turno;
	}
	public void resettaMossa(){
		for(int i=0;i<moveSource.length;i++){ 
			moveSource[i]=-1;
			this.moveDestination[i]=-1;
		}
	}
	public void nexTurn(){
		turno++;
	}
	public boolean inseritaSource(){
		boolean b=false;
		if(this.moveSource[0]!=-1)
			b=true;
		return b;
	}
	public boolean inseritaDestinazione(){
		
			boolean b=false;
			if(this.moveDestination[0]!=-1)
				b=true;
			return b;
		
	}
	public void setSource(int i,int j){
		this.moveSource[0]=i;
		this.moveSource[1]=j;
	}
	public void setSource(int[]i){
		this.moveSource[0]=i[0];
		this.moveSource[1]=i[1];
	}
	public void setDestination(int i,int j){
		this.moveDestination[0]=i;
		this.moveDestination[1]=j;
	}
	public int[] getSource(){
		return moveSource;
	}
	public int[] getDestination(){
		return moveDestination;
	}
	
	private String getTurno(){
		
		if(turno%2==0)
			return "turno b";
		else
			return "turno n";
	}
	public boolean turnoBianco(){
		if(turno%2==0)
			return true;
		else return false;
	}
	public boolean turnoNero(){
		if(turno%2==1)
			return true;
		else return false;
	}
	
	public String getTurnoToString(){
		
		if(turno%2==0)
			return "turno del bianco";
		else
			return "turno del nero";
	}
	public char getTurnoChar(){//restuisce il colore da mangiare
		if(getTurno().equals("turno b"))
			return 'n';
		else return 'b';
	}
	public boolean controlSource(Tavola t){//controlla se sorgente è giusta
		boolean b=true;
		if(t.getText(moveSource)==' ')//t[moveSource[0]][moveSource[1]].getText().equals(" ")
			b=false;
		
		if(!turno(t))
			b=false;				
		
		
		return b;
	}
	public boolean control(Tavola t){//true se si può muovere sulla destinazione scelta
		boolean b=true;
		if(t.getText(this.moveSource)==t.getText(this.moveDestination))//this.moveDestination[0]==this.moveSource[0]&&this.moveDestination[1]==this.moveSource[1] aumentato a tutti
			b=false;//si pu� togliere quuesta condizione...
		if(!(t.getText(this.moveDestination)==' '))
			b=false;
		
		//mossa consentita solo in alto a destra o a sinistra
			if((this.moveSource[1]!=this.moveDestination[1]+1&&this.moveSource[1]+1!=this.moveDestination[1])||(this.moveSource[0]-1!=this.moveDestination[0]))
				if(t.getText(this.moveSource)=='B'||t.getText(this.moveSource)=='N'){
					if((this.moveSource[1]!=this.moveDestination[1]-1&&this.moveSource[1]-1!=this.moveDestination[1])||(this.moveSource[0]+1!=this.moveDestination[0]))	
						if(!this.controlMangiata(t)){
							b=false;
						}
						
				}else 
					if(!this.controlMangiata(t)){
						b=false;
					}
			
		
			
		if(t.getText(this.moveSource)=='n')
			b=true;
		return b;	
				
	}
	public Pedina mangiata(Tavola t){//true se la mossa è una mangiata e si può fare
		
		//posso provare a mangiare
			
				
						if(t.getText(this.moveDestination)==' '){
							if(this.moveDestination[0]==moveSource[0]-2&&this.moveDestination[1]==moveSource[1]-2&&(""+t.getText(moveSource[0]-1,moveSource[1]-1)).equalsIgnoreCase(""+this.getTurnoChar())){
								return t.getPedina(this.moveSource[0]-1, this.moveSource[1]-1);
								
							}
						}
				
				
					if(t.getText(this.moveDestination)==' '){
						if(this.moveDestination[0]==moveSource[0]-2&&this.moveDestination[1]==moveSource[1]+2&&(""+t.getText(moveSource[0]-1,moveSource[1]+1)).equalsIgnoreCase(""+this.getTurnoChar())){
							return t.getPedina(this.moveSource[0]-1, this.moveSource[1]+1);
							
						}
					}
				
				if(t.getText(this.moveSource)=='B'||t.getText(this.moveSource)=='N'){
					if(t.getText(this.moveDestination)==' '){
						if(this.moveDestination[0]==moveSource[0]+2&&this.moveDestination[1]==moveSource[1]+2&&(""+t.getText(moveSource[0]+1,moveSource[1]+1)).equalsIgnoreCase(""+this.getTurnoChar())){
							return t.getPedina(this.moveSource[0]+1, this.moveSource[1]+1);
							
						}
					}
				}
			
					if(t.getText(this.moveSource)=='B'||t.getText(this.moveSource)=='N'){
				if(t.getText(this.moveDestination)==' '){
					if(this.moveDestination[0]==moveSource[0]+2&&this.moveDestination[1]==moveSource[1]-2&&(""+t.getText(moveSource[0]+1,moveSource[1]-1)).equalsIgnoreCase(""+this.getTurnoChar())){
						return t.getPedina(this.moveSource[0]+1, this.moveSource[1]-1);
						
					}
				}
					}
			
		
		
		
		return null;
		
	}
public Pedina mangiante(Tavola t){//true se la mossa è una mangiata e si può fare
		
		//posso provare a mangiare
			

	if(t.getText(this.moveDestination)==' '){
		if(this.moveDestination[0]==moveSource[0]-2&&this.moveDestination[1]==moveSource[1]-2&&(""+t.getText(moveSource[0]-1,moveSource[1]-1)).equalsIgnoreCase(""+this.getTurnoChar())){
			return t.getPedina(this.moveSource);
			
		}
	}


if(t.getText(this.moveDestination)==' '){
	if(this.moveDestination[0]==moveSource[0]-2&&this.moveDestination[1]==moveSource[1]+2&&(""+t.getText(moveSource[0]-1,moveSource[1]+1)).equalsIgnoreCase(""+this.getTurnoChar())){
		return t.getPedina(this.moveSource);
		
	}
}

if(t.getText(this.moveSource)=='B'||t.getText(this.moveSource)=='N'){
if(t.getText(this.moveDestination)==' '){
	if(this.moveDestination[0]==moveSource[0]+2&&this.moveDestination[1]==moveSource[1]+2&&(""+t.getText(moveSource[0]+1,moveSource[1]+1)).equalsIgnoreCase(""+this.getTurnoChar())){
		return t.getPedina(this.moveSource);
		
	}
}
}

if(t.getText(this.moveSource)=='B'||t.getText(this.moveSource)=='N'){
if(t.getText(this.moveDestination)==' '){
if(this.moveDestination[0]==moveSource[0]+2&&this.moveDestination[1]==moveSource[1]-2&&(""+t.getText(moveSource[0]+1,moveSource[1]-1)).equalsIgnoreCase(""+this.getTurnoChar())){
	return t.getPedina(this.moveSource);
	
}
}
}




return null;

}
		
		
	
	public boolean controlMangiata(Tavola t){//true se la mossa è una mangiata e si può fare
		
		//posso provare a mangiare
			
				if(this.mangiata(t)!=null)
					return true;
		
		
		
		return false;
		
	}
	private boolean turno(Tavola t){//controlla se turno  è giusto
		boolean b=false;
		if((t.getText(this.moveSource)=='b'||t.getText(this.moveSource)=='B')&&turno%2==0||(t.getText(this.moveSource)=='n'||t.getText(this.moveSource)=='N')&&turno%2==1)
			b=true;
		return b;
	}
}

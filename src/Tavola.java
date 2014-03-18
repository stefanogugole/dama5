
public class Tavola {
	private Pedina [][] tavoliere;
	
	public Tavola(){
		tavoliere=new Pedina[8][8];
		int r1;
		int f=0;
		for (r1=0;r1<3;r1++){//riempie le prime tre righe
			for(int j=0;j<8;j++){
				if(f==8||f==17)
					f++;
				if(f%2==0){
					this.tavoliere [r1][j]=new Pedina('n');		
					
				}else
					this.tavoliere[r1][j]=new Pedina('x');
				
				f++;
				
			}
		}
		f=1;
		for (r1=3;r1<5;r1++)//righe in mezzo
			for(int j=0;j<8;j++){
				if(f==9)
				f++;
			if(f%2==0){
				this.tavoliere [r1][j]=new Pedina(' ');		
				
			}else
				this.tavoliere[r1][j]=new Pedina('x');
			
			f++;
			
			}
	
					
				
			
		f=0;
		for (r1=5;r1<8;r1++){//ultime tre
			for(int j=0;j<8;j++){
				if(f==8||f==17)
					f++;
				
				if(f%2!=0){
					this.tavoliere [r1][j]=new Pedina('b');					
				}else
					this.tavoliere[r1][j]=new Pedina('x');
				f++;
			}
		}
		/*char a='a';
		for(int i=0;i<8;i++)
			for(int j=0;j<8;j++){
				tavoliere[i][j]=new Pedina(a);
				a++;
			}
		System.out.println(this.toString());
		System.out.println(this.getText(2, 3));*/
		
	}
	
	public int getLunghezza(){
		return tavoliere[0].length;
	}
	public int getAltezza(){
		return tavoliere.length;
	}
	public char getText(int i,int j){
		//if(i<this.getLunghezza()||j<this.getAltezza()) throw new IllegalArgumentException();
		 return this.tavoliere[i][j].getColor();		
		}
	public void setText(char s,int i,int j){
		//if(i<this.getLunghezza()||j<this.getAltezza()) throw new IllegalArgumentException();
		 this.tavoliere[i][j].setColor(s);		
		}
	
	public char getText(int cell[]){
		//if(i<this.getLunghezza()||j<this.getAltezza()) throw new IllegalArgumentException();
		 return this.tavoliere[cell[0]][cell[1]].getColor();		
		}
	public void setText(char s,int cell[]){
		//if(i<this.getLunghezza()||j<this.getAltezza()) throw new IllegalArgumentException();
		 this.tavoliere[cell[0]][cell[1]].setColor(s);		
		}
	public Pedina getPedina(int i,int j){
		return this.tavoliere[i][j];
	}
	public Pedina getPedina(int cell[]){
		return this.tavoliere[cell[0]][cell[1]];
	}
	public void setPedina(char h,Pedina p){
		for(int i=0;i<8;i++){
			for (int j=0;j<8;j++){
				if(p==this.getPedina(i, j))
					this.setText(h,i,j);
			}
		}
	}
	public String toString(){
		int i;
		String s="";
		
		for (i=0;i<8;i++){
			s+=i;
			for(int j=0;j<8;j++)
				s+=" | "+this.tavoliere[i][j].color;
			s+="\n";
		}
		s+=" ";
		for(int i1=0;i1<8;i1++)
			s+=" | "+i1;
		return s;
	}
}


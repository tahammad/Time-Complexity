public class Complexity {
	int nPower;
	int logPower;
	public static void main(String[] args) {
		Complexity c=new Complexity();
		c.setLogPower(1);
		c.setnPower(2);
		System.out.println(c);
	}
	// Getters and setters to be able to set the N power and also the log power if needed
	
	public int getnPower() {
		return nPower;
	}

	public void setnPower(int nPower) {
		this.nPower = nPower;
	}

	public int getLogPower() {
		return logPower;
	}

	public void setLogPower(int logPower) {
		this.logPower = logPower;
	}
	/**	The toString method checks to see what the N and log powers are set to and based on the combinations, 
	 * returns the complexity as a formatted string in Big O notation.
	*/
	
	public String toString() {
		String s="";
		String n="";
		String l="";
		String m="";

		if(nPower==0&&logPower==0) {
			s="O(1)";
		}
		else {
			if(nPower==0) {
				n="";
			}
			else if(nPower==1) {
				n="n";
			}
			else {
				n="n^"+nPower;
			}
		
		if(logPower==0) {
			l="";
		}
		else if(logPower==1) {
			l="log(n)";
		}
		else {
			l="log(n)^"+logPower;
		}
		if(logPower>0&&nPower>0) {
			m="*";
		}
		else {
			m="";
		}
		
		s+="O("+n+m+l+")";}

		return s;
	}
	//	Boolean used to compare complexities to see whether the first Complexity is larger
	public boolean greaterThan(Complexity t){
		boolean b=false;
		if(getnPower()>t.getnPower()) {
			b=true;
		}
		else if(getnPower()==t.getnPower()) {
			if(getLogPower()>t.getLogPower()) 
				b=true;	
			}
		
		return b;
	}

}

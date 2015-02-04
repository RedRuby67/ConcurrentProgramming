class Bakery {
	   int[] flag;
	   int[] label;
	   
	   public Bakery(int n) {
		   flag  = new int[n];
		   label = new int[n];
		   for (int i = 0; i < n; i++) { 
			   flag[i] = 0; label[i] = 0;
		   }
	   }
	   
	   public int maxlabel() {
	   int max = label[0];
	   for ( int i = 1; i < label.length; i++) {
	       if ( label[i] > max) {
	         max = label[i];
	   }
	   }
	   return max;
	   }
	   
	   public boolean noHigherFlag(int me) {
		   for (int i = 0; i < flag.length; i++) {
			   if (flag[i] == 1 && label[i] < label[me]) {
				   return true;
			   }
		   }
		   return false;
	   }
	   
	   
	   public void lock(int me) {  
		   flag[me] = 1;  
		   label[me] = maxlabel() + 1;
		   //while ($k flag[k]
		   //         && (label[i],i) > (label[k],k));  {};
		   while (noHigherFlag(me)) {}
	   }

	   public void unlock(int me) {  
		   flag[me] = 0;
		 }
}
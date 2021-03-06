class Peterson  {

    //The following variables are used for implementing a lock using
    //Peterson's protocol.  We use to variables for flags instead of
    //an array, as there is now way to make array elements volatile. 
    private static volatile int victim;
    private static volatile int flag0;
    private static volatile int flag1;

    public void lock(int me) {
      int j = 1 - me;
      	if (me==0) {
      		flag0 = 1;	// I'm interested
      		victim = 0;	// You go first
      		while ((flag1==1) && (victim==0)) {}	// wait
      	} else {
      		flag1 = 1;
      		victim = 1;
      		while ((flag0==1) && (victim==1)) {}
      	}
    }
    
    public void unlock(int me) {
      if (me==0) flag0=0; else flag1=0;	// Not interested
    }
}
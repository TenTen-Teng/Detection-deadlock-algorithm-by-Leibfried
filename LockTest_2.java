public class LockTest_2 {
   public static String obj1 = "resource 1";
   public static String obj2 = "resource 2";

   public static LA_check state_check = new LA_check(2);
   //public static long  startTime = System.nanoTime();
   public static long startTime = System.currentTimeMillis();
     
   public static void main(String[] args) {	  
      LockA la = new LockA();
      new Thread(la).start();
      LockB lb = new LockB();
      new Thread(lb).start();
   }
}

class LockA implements Runnable{
   public void run() {
	  Thread.currentThread().setName("Process A");
      try {
         System.out.println(Thread.currentThread().getName() + " starts...");
       
         while(true){
        	//111
            synchronized (LockTest_2.obj1) {          	
               System.out.println(Thread.currentThread().getName() + " locked resource 1");
               Thread.sleep(2000L); 
               System.out.println(Thread.currentThread().getName() + " trying to get resource 2");
               //120
               if(!LockTest_2.state_check.detectDeadlock(1, 2))
               {
            	   long endTime = System.nanoTime();
            	   System.out.println("running time： " + (endTime - LockTest_2.startTime) + "ms");
               }
               
               synchronized (LockTest_2.obj2) {
            	  System.out.println("if you see this line, you fail");
                  System.out.println(Thread.currentThread().getName() + " LockA locked resource 2");
                  Thread.sleep(2000L);
               }
            }
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}
class LockB implements Runnable{
	//LA_check state_check_2 = new LA_check();
   public void run() {
	  Thread.currentThread().setName("Process B");
      try {
         System.out.println(Thread.currentThread().getName() + " starts...");
         //221
         while(true){
            synchronized (LockTest_2.obj2) {
               System.out.println(Thread.currentThread().getName() + " locked resource 2");
               Thread.sleep(2000L);
               System.out.println(Thread.currentThread().getName() + " trying to get resource 1");
               //210
               Thread.sleep(50);
               
               if(!LockTest_2.state_check.detectDeadlock(2, 1))
               {
            	   long endTime = System.nanoTime();
            	   System.out.println("running time： " + (endTime - LockTest_2.startTime) + "ms");
               }
               
               synchronized (LockTest_2.obj1) {
            	  System.out.println("if you see this line, you fail");
                  System.out.println(Thread.currentThread().getName() + " locked resource 1");
                  Thread.sleep(2000L); 
               }
            }
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}
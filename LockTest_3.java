//3 p; 3 r; 121 231 311 110 220 330
public class LockTest_3 {
   public static String obj1 = "resource 1";
   public static String obj2 = "resource 2";
   public static String obj3 = "resource 3";
   
   public static LA_check state_check_3 = new LA_check(3);
   public static long startTime = System.currentTimeMillis();
   
   public static void main(String[] args) {
      LockA_3 la = new LockA_3();
      new Thread(la).start();
      LockB_3 lb = new LockB_3();
      new Thread(lb).start();
      LockC_3 lc = new LockC_3();
      new Thread(lc).start();
   }
}

class LockA_3 implements Runnable{
   public void run() {
	  Thread.currentThread().setName("Process A");
      try {
         System.out.println(Thread.currentThread().getName() + " starts...");
         while(true){
            synchronized (LockTest_3.obj2) {
               System.out.println(Thread.currentThread().getName() + " locked resource 2");
               Thread.sleep(2000L); 
               System.out.println(Thread.currentThread().getName() + " trying to get resource 1");
               //110
               if(!LockTest_3.state_check_3.detectDeadlock(3, 1))
               {
            	   long endTime = System.currentTimeMillis();
            	   System.out.println("running time： " + (endTime - LockTest_3.startTime) + "ms");
               }          
               synchronized (LockTest_3.obj1) {
            	  System.out.println("if you see this line, you fail");
                  System.out.println(Thread.currentThread().getName() + " LockA locked resource 1");
                  Thread.sleep(2000L); 
               }
            }
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}
class LockB_3 implements Runnable{
   public void run() {
	  Thread.currentThread().setName("Process B");
      try {
         System.out.println(Thread.currentThread().getName() + " starts...");
         while(true){
            synchronized (LockTest_3.obj3) {
               System.out.println(Thread.currentThread().getName() + " locked resource 3");
               Thread.sleep(2000L); 
               System.out.println(Thread.currentThread().getName() + " trying to get resource 2");
               //220
               Thread.sleep(50);
               if(!LockTest_3.state_check_3.detectDeadlock(1, 2))
               {
            	   long endTime = System.currentTimeMillis();
            	   System.out.println("running time： " + (endTime - LockTest_3.startTime) + "ms");
               }
               synchronized (LockTest_3.obj2) {
            	  System.out.println("if you see this line, you fail");
                  System.out.println(Thread.currentThread().getName() + " locked resource 2");
                  Thread.sleep(2000L); 
               }
            }
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}
class LockC_3 implements Runnable{
	   public void run() {
		  Thread.currentThread().setName("Process C");
	      try {
	         System.out.println(Thread.currentThread().getName() + " starts...");
	         while(true){
	            synchronized (LockTest_3.obj1) {
	               System.out.println(Thread.currentThread().getName() + " locked resource 1");
	               Thread.sleep(2000L); 
	               System.out.println(Thread.currentThread().getName() + " trying to get resource 3");
	               //330
	               Thread.sleep(100);
	               //Thread.sleep(2000L);

	               if(!LockTest_3.state_check_3.detectDeadlock(2, 3))
	               {
	            	   long endTime = System.currentTimeMillis();
	            	   System.out.println("running time： " + (endTime - LockTest_3.startTime) + "ms");
	               }
	               
	               synchronized (LockTest_3.obj3) {
	            	  System.out.println("if you see this line, you fail");
	                  System.out.println(Thread.currentThread().getName() + " LockA locked resource 3");
	                  Thread.sleep(2000L); 
	               }
	            }
	         }
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	   }
	}
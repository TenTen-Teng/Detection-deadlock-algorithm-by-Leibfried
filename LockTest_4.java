//4 p; 4 r; 121 241 311 431 110 220 330 440
import java.util.Date;
public class LockTest_4 {
   public static String obj1 = "resource 1";
   public static String obj2 = "resource 2";
   public static String obj3 = "resource 3";
   public static String obj4 = "resource 4";
   
   public static LA_check state_check_4 = new LA_check(4);
   public static long startTime = System.currentTimeMillis();
   
   public static void main(String[] args) {
      LockA_4 la = new LockA_4();
      new Thread(la).start();
      LockB_4 lb = new LockB_4();
      new Thread(lb).start();
      LockC_4 lc = new LockC_4();
      new Thread(lc).start();
      LockD_4 ld = new LockD_4();
      new Thread(ld).start();
   }
}

class LockA_4 implements Runnable{
   public void run() {
	  Thread.currentThread().setName("Process A");
      try {
         System.out.println(new Date().toString() + " -> " + Thread.currentThread().getName() + " starts...");
         while(true){
            synchronized (LockTest_4.obj2) {
               System.out.println(new Date().toString() + " -> " + Thread.currentThread().getName() + " locked resource 2");
               Thread.sleep(2000L); 
               System.out.println(new Date().toString() + " -> " + Thread.currentThread().getName() + " trying to get resource 1");
               //110
               if(!LockTest_4.state_check_4.detectDeadlock(3, 1))//p3 held the r1 which p1 is asking for
               {
            	   long endTime = System.currentTimeMillis();
            	   System.out.println("running time： " + (endTime - LockTest_4.startTime) + "ms");
               }
            	   synchronized (LockTest_4.obj4) {
            	  System.out.println("if you see this line, you fail");
                  System.out.println(new Date().toString() + " -> " + Thread.currentThread().getName() + " locked resource 1");
                  Thread.sleep(2000L); 
               }
            }
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}
class LockB_4 implements Runnable{
   public void run() {
	  Thread.currentThread().setName("Process B");
      try {
         System.out.println(new Date().toString() + " -> " + Thread.currentThread().getName() + " starts...");
         while(true){
            synchronized (LockTest_4.obj4) {
               System.out.println(new Date().toString() + " -> " + Thread.currentThread().getName() + " locked resource 4");
               Thread.sleep(2000L); 
               System.out.println(new Date().toString() + " -> " + Thread.currentThread().getName() + " trying to get resource 2");
               //220
               Thread.sleep(50);
               if(!LockTest_4.state_check_4.detectDeadlock(1, 2))
               {
            	   long endTime = System.currentTimeMillis();
            	   System.out.println("running time： " + (endTime - LockTest_4.startTime) + "ms");
               }
               //p1 held the r2 which p2 is asking for
               synchronized (LockTest_4.obj2) {
            	  System.out.println("if you see this line, you fail");
                  System.out.println(new Date().toString() + " -> " + Thread.currentThread().getName() + " locked resource 2");
                  Thread.sleep(2000L); 
               }
            }
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}
class LockC_4 implements Runnable{
	   public void run() {
		  Thread.currentThread().setName("Process C");
	      try {
	         System.out.println(new Date().toString() + " -> " + Thread.currentThread().getName() + " starts...");
	         while(true){
	            synchronized (LockTest_4.obj1) {
	               System.out.println(new Date().toString() + " -> " + Thread.currentThread().getName() + " locked resource 1");
	               Thread.sleep(2000L); 
	               System.out.println(new Date().toString() + " -> " + Thread.currentThread().getName() + " trying to get resource 3");
	               //330
	               Thread.sleep(100);
	               if(!LockTest_4.state_check_4.detectDeadlock(4, 3))//p4 held the r3 which p3 is asking for 
	               {
	            	   long endTime = System.currentTimeMillis();
	            	   System.out.println("running time： " + (endTime - LockTest_4.startTime) + "ms");
	               }
	            	   synchronized (LockTest_4.obj3) {
	            	  System.out.println("if you see this line, you fail");
	                  System.out.println(new Date().toString() + " -> " + Thread.currentThread().getName() + " locked resource 3");
	                  Thread.sleep(2000L);
	               }
	            }
	         }
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	   }
	}
class LockD_4 implements Runnable{
	   public void run() {
		  Thread.currentThread().setName("Process D");
	      try {
	         System.out.println(new Date().toString() + " -> " + Thread.currentThread().getName() + " starts...");
	         while(true){
	            synchronized (LockTest_4.obj3) {
	               System.out.println(new Date().toString() + " -> " + Thread.currentThread().getName() + " locked resource 3");
	               Thread.sleep(2000L); 
	               System.out.println(new Date().toString() + " -> " + Thread.currentThread().getName() + " trying to get resource 4");
	               //440
	               Thread.sleep(200);

	               if(!LockTest_4.state_check_4.detectDeadlock(2,4))//p2 held the r4 which p4 is asking for	           
	               {
	            	   long endTime = System.currentTimeMillis();
	            	   System.out.println("running time： " + (endTime - LockTest_4.startTime) + "ms");
	               }
	            	   synchronized (LockTest_4.obj4) {
	            	  System.out.println("if you see this line, you fail");
	                  System.out.println(new Date().toString() + " -> " + Thread.currentThread().getName() + " locked resource 4");
	                  Thread.sleep(2000L); 
	               }
	            }
	         }
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	   }
	}
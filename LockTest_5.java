import java.util.Date;
//5p; 5r; 241 311 451 531 110 220 330 440 550
public class LockTest_5 {
   public static String obj1 = "resource 1";
   public static String obj2 = "resource 2";
   public static String obj3 = "resource 3";
   public static String obj4 = "resource 4";
   public static String obj5 = "resource 5";  
   
   public static LA_check state_check_5 = new LA_check(5);
   public static long startTime = System.currentTimeMillis();
   
   public static void main(String[] args) {
      LockA_5 la = new LockA_5();
      new Thread(la).start();
      LockB_5 lb = new LockB_5();
      new Thread(lb).start();
      LockC_5 lc = new LockC_5();
      new Thread(lc).start();
      LockD_5 ld = new LockD_5();
      new Thread(ld).start();
      LockE_5 le = new LockE_5();
      new Thread(le).start();
   }
}

class LockA_5 implements Runnable{
   public void run() {
	  Thread.currentThread().setName("Process A");
      try {
         System.out.println(new Date().toString() + " -> " + Thread.currentThread().getName() + " starts...");
         while(true){
            synchronized (LockTest_5.obj2) {
               System.out.println(new Date().toString() + " -> " + Thread.currentThread().getName() + " locked " + LockTest_5.obj2.toString());
               Thread.sleep(2000L); 
               System.out.println(new Date().toString() + " -> " + Thread.currentThread().getName() + " trying to get resource 1");
               //110
               if(!LockTest_5.state_check_5.detectDeadlock(3, 1))//p3 held the r1 which p1 is asking for
               {
            	   long endTime = System.currentTimeMillis();
            	   System.out.println("running time： " + (endTime - LockTest_5.startTime) + "ms");
               }
            	   synchronized (LockTest_5.obj1) {
            	  System.out.println("if you see this line, you fail");
                  System.out.println(new Date().toString() + " -> " + Thread.currentThread().getName() + " locked " + LockTest_5.obj1.toString());
                  Thread.sleep(2000L); 
               }
            }
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}
class LockB_5 implements Runnable{
   public void run() {
	  Thread.currentThread().setName("Process B");
      try {
         System.out.println(new Date().toString() + " -> " + Thread.currentThread().getName() + " starts...");
         while(true){
            synchronized (LockTest_5.obj4) {
               System.out.println(new Date().toString() + " -> " + Thread.currentThread().getName() + " locked " + LockTest_5.obj4.toString());
               Thread.sleep(2000L); 
               System.out.println(new Date().toString() + " -> " + Thread.currentThread().getName() + " trying to get resource 2");
               //220
               Thread.sleep(50);
               if(!LockTest_5.state_check_5.detectDeadlock(1, 2))//p1 held the r2 which p2 is asking for
               {
            	   long endTime = System.currentTimeMillis();
            	   System.out.println("running time： " + (endTime - LockTest_5.startTime) + "ms");
               }
            	   synchronized (LockTest_5.obj2) {
            	  System.out.println("if you see this line, you fail");
                  System.out.println(new Date().toString() + " -> " + Thread.currentThread().getName() + " locked " + LockTest_5.obj2.toString());
                  Thread.sleep(2000L); 
               }
            }
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}
class LockC_5 implements Runnable{
	   public void run() {
		  Thread.currentThread().setName("Process C");
	      try {
	         System.out.println(new Date().toString() + " -> " + Thread.currentThread().getName() + " starts...");
	         while(true){
	            synchronized (LockTest_5.obj1) {
	               System.out.println(new Date().toString() + " -> " + Thread.currentThread().getName() + " locked " + LockTest_5.obj1.toString());
	               System.out.println(new Date().toString() + " -> " + Thread.currentThread().getName() + " trying to get resource 3");
	               //330
	               Thread.sleep(100);
	               if(!LockTest_5.state_check_5.detectDeadlock(5, 3))//p5 held the r3 which p3 is asking for 
	               {
	            	   long endTime = System.currentTimeMillis();
	            	   System.out.println("running time： " + (endTime - LockTest_5.startTime) + "ms");
	               }
	            	   synchronized (LockTest_5.obj3) {
	            	  System.out.println("if you see this line, you fail");
	                  System.out.println(new Date().toString() + " -> " + Thread.currentThread().getName() + " locked " + LockTest_5.obj3.toString());
	                  Thread.sleep(2000L); 
	               }
	            }
	         }
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	   }
	}
class LockD_5 implements Runnable{
	   public void run() {
		  Thread.currentThread().setName("Process D");
	      try {
	         System.out.println(new Date().toString() + " -> " + Thread.currentThread().getName() + " starts...");
	         while(true){
	            synchronized (LockTest_5.obj5) {
	               System.out.println(new Date().toString() + " -> " + Thread.currentThread().getName() + " locked " + LockTest_5.obj5.toString());
	               Thread.sleep(2000L); 
	               System.out.println(new Date().toString() + " -> " + Thread.currentThread().getName() + " trying to get resource 4");
	               //440
	               Thread.sleep(200);
	               if(!LockTest_5.state_check_5.detectDeadlock(2,4))//p2 held the r4 which p4 is asking for	  
	               {
	            	   long endTime = System.currentTimeMillis();
	            	   System.out.println("running time： " + (endTime - LockTest_5.startTime) + "ms");
	               }
	            	   synchronized (LockTest_5.obj4) {
	            	  System.out.println("if you see this line, you fail");
	                  System.out.println(new Date().toString() + " -> " + Thread.currentThread().getName() + " locked "  + LockTest_5.obj4.toString());
	                  Thread.sleep(2000L);
	               }
	            }
	         }
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	   }
	}
class LockE_5 implements Runnable{
	   public void run() {
		  Thread.currentThread().setName("Process E");
	      try {
	         System.out.println(new Date().toString() + " -> " + Thread.currentThread().getName() + " starts...");
	         while(true){
	            synchronized (LockTest_5.obj3) {
	               System.out.println(new Date().toString() + " -> " + Thread.currentThread().getName() + " locked " + LockTest_5.obj3.toString());
	               Thread.sleep(2000L); 
	               System.out.println(new Date().toString() + " -> " + Thread.currentThread().getName() + " trying to get resource 5");
	               //550
	               Thread.sleep(400);
	               if(!LockTest_5.state_check_5.detectDeadlock(4,5))//p4 held the r5 which p5 is asking for	  
	               {
	            	   long endTime = System.currentTimeMillis();
	            	   System.out.println("running time： " + (endTime - LockTest_5.startTime) + "ms");
	               }
	            	   synchronized (LockTest_5.obj5) {
	            	  System.out.println("if you see this line, you fail");
	                  System.out.println(new Date().toString() + " -> " + Thread.currentThread().getName() + " locked "  + LockTest_5.obj5.toString());
	                  Thread.sleep(2000L); 
	               }
	            }
	         }
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	   }
	}
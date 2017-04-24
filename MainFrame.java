import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class MainFrame extends javax.swing.JFrame {
    public MainFrame() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {  	
        textFiled_1 = new javax.swing.JLabel();
        labelText = new javax.swing.JLabel();
        textFiled_2 = new javax.swing.JLabel();
        fieldText = new javax.swing.JLabel();
        button1 = new javax.swing.JButton();
        button2 = new javax.swing.JButton();
        button3 = new javax.swing.JButton();
        button4 = new javax.swing.JButton();
        button5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(600, 225);
        //setBounds(600, 225, 300, 400);

        textFiled_1.setText("Process A:");

        labelText.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        textFiled_2.setText("Process B: ");
        button1.setText("R1 is granted to PA");
        button2.setText("R2 is granted to PB");
        button3.setText("    PA requests R2   ");
        button4.setText("    PB requests R1   ");
        button5.setText("       Release R2       " );
        
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) 
            {
                buttonActionPerformed(evt);
            }
        });
        
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) 
            {
                buttonActionPerformed_2(evt);
            }
        });
        button3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) 
            {
                buttonActionPerformed_3(evt);
            }
        });
        
        button4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) 
            {
                buttonActionPerformed_4(evt);
            }
        });
        button5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) 
            {
                buttonActionPerformed_5(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                //.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)  
                .addComponent(button1)
                            .addComponent(button2)
                            .addComponent(button3)
                            .addComponent(button4)
                            .addComponent(button5)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(textFiled_1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelText, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(textFiled_2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fieldText, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textFiled_1)
                    .addComponent(labelText, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textFiled_2)
                    .addComponent(fieldText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(button1)
                .addComponent(button2)
                .addComponent(button3)
                .addComponent(button4)
                .addComponent(button5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    private void buttonActionPerformed(java.awt.event.ActionEvent evt) {                                       
        new Thread(new Runnable() {
            @Override
            public void run() {
            	  Thread.currentThread().setName("Process A");
                  try {
                     System.out.println(Thread.currentThread().getName() + " starts...");
                     //221             
                     while(true){
                        synchronized (LockTest_2.obj1) {
	                        changeText_1(LockTest_2.obj1);
	                  	  	Thread.sleep(2000L);
	                      	  for (int i = 10000; i > 0; i--) 
	                      	  {
	                       		 changeText_1((i) + " ");
	                               try {
	                                   Thread.sleep(1000);
	                               } catch (InterruptedException ex) {
	                                   ex.printStackTrace();
	                               }
	                               if(count_1)
	                               {
	                            	   changeText_1(Thread.currentThread().getName() + " trying to get resource 2");
	                            	   break;
	                               } 
	                           }	                     
	                      	  	
		                   	   synchronized (LockTest_2.obj2) {
		                   		changeText_1(LockTest_2.obj2);
		                  	  	Thread.sleep(2000L);
		                   		for (int i = 0; i < 10000; i++) 
		                      	  {
		                       		 changeText_1((i) + " ");
		                               try {
		                                   Thread.sleep(1000);
		                               } catch (InterruptedException ex) {
		                                   ex.printStackTrace();
		                               }
		                      	  }
		                   		   	System.out.println("if you see this line, you fail");
		                            System.out.println(Thread.currentThread().getName() + " locked resource 1");
		                            Thread.sleep(2000L); // 为测试，占用了就不放
		                         }                                          
                        }
                     }
                  } catch (Exception e) {
                     e.printStackTrace();
                  }
               }
          }).start();
    }            
    
    private void buttonActionPerformed_2(java.awt.event.ActionEvent evt) {                                       
        new Thread(new Runnable() {
            @Override
            public void run() {
          	  Thread.currentThread().setName("Process B");
                try {
                   System.out.println(Thread.currentThread().getName() + " starts...");
                   //221
                   while(true){
                      synchronized (LockTest_2.obj2) {
                    	  changeText_2(LockTest_2.obj2);
                    	  Thread.sleep(1000);
                    	  for (int i = 0; i < 10000; i++) {
                     		 changeText_2((i) + " ");
                             try {
                                 Thread.sleep(1000);
                             } catch (InterruptedException ex) {
                                 ex.printStackTrace();
                             }
                             if(count_2)
                             {
                          	   changeText_2(Thread.currentThread().getName() + " trying to get resource 1");
                          	   break;
                             } 
                             if(situation == 2)
                       	  	{
                       		  changeText_2("release resource 2");
                       		  break;
                       	  	}
                         }
                    	  if(situation == 1)
                    	  {
                    		  
                    		  synchronized (LockTest_2.obj1) {
    	                      	  System.out.println("if you see this line, you fail");
    	                            System.out.println(Thread.currentThread().getName() + " locked resource 1");
    	                            Thread.sleep(2000L); // 为测试，占用了就不放
    	                         }
                    	  }
                    	  if(situation == 2)
                    	  {
                    		  changeText_2("Release resource 2");
                    		  Thread.sleep(1000);
                    		  break;                 		  
                    	  }
                    	  
                         System.out.println();
                         Thread.sleep(50);
                      }
                   }
                } catch (Exception e) {
                   e.printStackTrace();
                }
             }
        }).start();
    }
    
    private void buttonActionPerformed_3(java.awt.event.ActionEvent evt) {                                       
        new Thread(new Runnable() {
            @Override
            public void run() {
          	  count_1 = true;
             }
        }).start();
    }
    private void buttonActionPerformed_4(java.awt.event.ActionEvent evt) {                                       
        new Thread(new Runnable() {
            @Override
            public void run() {
          	  count_2 = true;
             }
        }).start();
    }
    private void buttonActionPerformed_5(java.awt.event.ActionEvent evt) {                                       
        new Thread(new Runnable() {
            @Override
            public void run() {
            	situation = 2;
             }
        }).start();
    }

    private void changeText_1(String text) {
        labelText.setText(text);
    }

    private void changeText_2(String text) {
        fieldText.setText(text);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify                     
    private javax.swing.JButton button1;
    private javax.swing.JButton button2;
    private javax.swing.JButton button3;
    private javax.swing.JButton button4;
    private javax.swing.JButton button5;
    private javax.swing.JLabel fieldText;
    private javax.swing.JLabel textFiled_1;
    private javax.swing.JLabel textFiled_2;
    private javax.swing.JLabel labelText;
    private boolean count_1 = false;
    private boolean count_2 = false;
    private int situation = 1;

    // End of variables declaration 
}

 class LockTest_2 {
	   public static String obj1 = "resource 1";
	   public static String obj2 = "resource 2";	     
	}
 


    // End of variables declaration 
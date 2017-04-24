
public class LA_check {
	int num;

	int[][] adjacencyMatrix;
	int[][] deadlockMatrix;
	int[][] reachabilityMatrix;
	int[][] trueDeadlockMatrix;
	int[] deadlockVector;
	
	MatrixMulti mu = new MatrixMulti();
	
	public LA_check(int num) {
		// TODO Auto-generated constructor stub
		this.num = num;
		this.adjacencyMatrix = new int[this.num][this.num];
		this.deadlockMatrix = new int[this.num][this.num];
		this.reachabilityMatrix = new int[this.num][this.num];
		this.trueDeadlockMatrix = new int[this.num][this.num];
		this.deadlockVector = new int[this.num];
	}


	public int[][] getAdjacencyMatrix() {
		System.out.println("getAdjacencyMatrix!");
        for(int m = 0; m < adjacencyMatrix.length; m++)  
        {  
            for(int n = 0; n < adjacencyMatrix[0].length; n++)  
            {  
                System.out.print(adjacencyMatrix[m][n]+" ");  
            }  
            System.out.println();  
        }
		return adjacencyMatrix;
	}
	
	public void setAdjacencyMatrix(int[][] adjacencyMatrix) {
		this.adjacencyMatrix = adjacencyMatrix;
        for(int i = 0; i < this.adjacencyMatrix.length; i++){
            for(int j = 0; j < this.adjacencyMatrix[0].length; j++){
                this.adjacencyMatrix[i][j] = 0;
            }
        }
	}

	public int[][] getDeadlockMatrix() {
		System.out.println("getDeadlockMatrix!");
        for(int m = 0; m < deadlockMatrix.length; m++)  
        {  
            for(int n = 0; n < deadlockMatrix[0].length; n++)  
            {  
                System.out.print(deadlockMatrix[m][n]+" ");  
            }  
            System.out.println();  
        }
		return deadlockMatrix;
	}
	
	public void setDeadlockMatrix(int[][] deadlockMatrix, int[][] reachabilityMatrix, int col) {
		this.deadlockMatrix = deadlockMatrix;
		
        for(int i = 0; i < this.deadlockMatrix.length; i++){
        	this.deadlockMatrix[i][col] = reachabilityMatrix[i][i];          
        }
	}
	
	public int[][] getReachabilityMatrix() {
		System.out.println("getReachabilityMatrix!");
        for(int m = 0; m < reachabilityMatrix.length; m++)  
        {  
            for(int n = 0; n < reachabilityMatrix[0].length; n++)  
            {  
                System.out.print(reachabilityMatrix[m][n]+" ");  
            }  
            System.out.println();  
        }
		return reachabilityMatrix;
	}
	
	public void setReachabilityMatrix(int[][] reachabilityMatrix) {
		this.reachabilityMatrix = reachabilityMatrix;
	}
	
	public int[][] getTrueDeadlockMatrix() {
		return trueDeadlockMatrix;
	}
	
	public void setTrueDeadlockMatrix(int[][] deadlockMatrix) {
		this.trueDeadlockMatrix = deadlockMatrix;
		
        for(int m = 0; m < trueDeadlockMatrix.length; m++)  
        {  
            for(int n = 0; n < trueDeadlockMatrix[0].length; n++)  
            {  
                if(trueDeadlockMatrix[m][n] > 1)
                {
                	trueDeadlockMatrix[m][n] = 1;
                }
            }   
        }
	}
	
	boolean detectDeadlock(int p, int r)
	{
		//A1.1
		adjacencyMatrix[p-1][r-1] = 1;	
		getAdjacencyMatrix();
        //System.out.println("get the whole matrix");
        
        //A1.2 allocate space for a "deadlock" matrix
        //DM: column one is the diagonal of the first RM(1st RM = AM)
        
        for(int count = 0; count < adjacencyMatrix[0].length; count++)
        {
        	//getAdjacencyMatrix();
            if(count == 0)
            {  		
                for(int i = 0; i < reachabilityMatrix.length; i++){
                    for(int j = 0; j < reachabilityMatrix[0].length; j++){
                        reachabilityMatrix[i][j] = adjacencyMatrix[i][j];
                    }
                }
                System.out.println("count = " + count);
                getReachabilityMatrix();                
            	setDeadlockMatrix(deadlockMatrix, adjacencyMatrix, 0);            	
            	getDeadlockMatrix();
            }
            else{
            	System.out.println("count = " + count);            	
            	setReachabilityMatrix(mu.multiMatrix(adjacencyMatrix, reachabilityMatrix));
            	getReachabilityMatrix();
            	setDeadlockMatrix(deadlockMatrix, reachabilityMatrix, count);
            	getDeadlockMatrix();         	
            }    
        }
        //get true deadlock matrix

        setTrueDeadlockMatrix(deadlockMatrix);
        
        for(int m = 0; m < trueDeadlockMatrix.length; m++)  
        {  	int sum = 0;
            for(int n = 0; n < trueDeadlockMatrix[0].length; n++)  
            {  
                sum = trueDeadlockMatrix[m][n];
            }   
            deadlockVector[m] = sum;
        }
        
        System.out.print("deadlockVector = ");
        for(int i = 0; i < num; i++)
        {
        	System.out.print(deadlockVector[i] + " ");
        }
        System.out.println("");
        
        int c = 0;
        for(int n = 0; n < num; n++)  
        {  
        	if(deadlockVector[n] == 0)
        	{
        		System.out.println("checked deadlock! no deadlock!");
        		break;
        	}
        	else{
        		c++;
        	}
        } 
        
        if(c == num)
        {
        	System.out.println("DEADLOCK!!!!");
        	return false;
        }
        return true;
	}
}



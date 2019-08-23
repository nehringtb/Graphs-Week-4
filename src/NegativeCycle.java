import java.util.ArrayList;
import java.util.Scanner;

public class NegativeCycle {
    private static int negativeCycle(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost) {
    	
    	int[] dist = new int[adj.length];
    	int[] prev = new int[adj.length];
    	int s=0;
    	for (int i=0; i<adj.length; i++){
    		dist[i]=Integer.MAX_VALUE-10000;
    	}
    	
    	//ArrayList<Integer[]> h = createH(adj.length);
    	Integer[] newS={s, 0};
    	//h.set(s, newS);
    	for(int c=0; c<adj.length-1; c++){
    		for(int u=0; u< adj.length; u++){
    			for(int i=0;i<adj[u].size();i++){
	    			int v=adj[u].get(i);
	    			int currCost=dist[u]+cost[u].get(i);
	    			if(dist[v]> currCost){
	    				
	    				dist[v]=currCost;
	    				prev[v]=u;
	    			}
    			}
    		}
    	}
    	
    	for(int u=0; u< adj.length; u++){
			for(int i=0;i<adj[u].size();i++){
    			int v=adj[u].get(i);
    			int currCost=dist[u]+cost[u].get(i);
    			if(dist[v]> currCost){
    				return 1;
    			}
			}
    	}
        return 0;
        
    }

    
    private static void changePriority(ArrayList<Integer[]>h, int v, int dv){
    	for(int i=0; i< h.size(); i++){
    		if(h.get(i)[0]==v){
    			Integer[] newVal= {v, dv};
    			h.set(i, newVal);
    			break;
    		}
    	}
    }
    
    private static int extractMin(ArrayList<Integer[]> h){
    	
    	int minVal= Integer.MAX_VALUE;
    	int minInd=-1;
    	int toRemove=-1;
    	for(int i=0; i< h.size(); i++){
    		Integer[] a=h.get(i);
    		if(a[1]<=minVal){
    			minVal=a[1];
    			minInd=a[0];
    			toRemove=i;
    		}
    	}
    	h.remove(toRemove);
    	return minInd;
    }
    
    private static ArrayList<Integer[]> createH(int n){
    	
    	ArrayList<Integer[]> toReturn = new ArrayList<Integer[]>();
    	
    	for(int i=0; i<n; i++){
    		Integer[] currArr= new Integer[2];
    		currArr[0]=i;
    		currArr[1]=Integer.MAX_VALUE;
    		toReturn.add(currArr);
    	}
    	
    	
    	return toReturn;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        ArrayList<Integer>[] cost = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
            cost[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y, w;
            x = scanner.nextInt();
            y = scanner.nextInt();
            w = scanner.nextInt();
            adj[x - 1].add(y - 1);
            cost[x - 1].add(w);
        }
        System.out.println(negativeCycle(adj, cost));
    }
}


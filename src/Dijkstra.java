import java.util.*;

public class Dijkstra {
    private static int distance(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int s, int t) {
    	int[] dist = new int[adj.length];
    	int[] prev = new int[adj.length];

    	for (int i=0; i<adj.length; i++){
    		dist[i]=Integer.MAX_VALUE;
    	}
    	
    	dist[s]=0;
    	ArrayList<Integer[]> h = createH(adj.length);
    	Integer[] newS={s, 0};
    	h.set(s, newS);
    	while(!h.isEmpty()){
    		int u= extractMin(h);
    		for(int i=0; i< adj[u].size(); i++){
    			int v=adj[u].get(i);
    			int currCost=dist[u]+cost[u].get(i);
    			if(dist[v]> currCost && currCost>0){
    				dist[v]=currCost;
    				prev[v]=u;
    				changePriority(h,v,dist[v]);
    			}
    		}
    	}
        return (dist[t]>100000) ? -1 : dist[t];
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
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(distance(adj, cost, x, y));
    }
    
}


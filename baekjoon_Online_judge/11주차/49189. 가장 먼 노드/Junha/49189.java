import java.io.*;
import java.util.*;

class Solution {

    public int [] visit;
    public ArrayList<ArrayList> list = new ArrayList<>();
    public PriorityQueue<node> pq = new PriorityQueue<>();
    
    public class node implements Comparable<node>{
        int y;
        int d;
        
        node(int y, int d){
            this.y = y;
            this.d = d;
        }
        
        public int compareTo(node o){
            if(this.d > o.d)
                return 1;
            else
                return -1;
        }
    }
    
    public int solution(int n, int[][] edge) {
        int answer = 0;
        visit = new int[n];
        
        for(int i=0;i<n;i++){
            ArrayList<Integer> alist = new ArrayList<>();
            list.add(alist);
        }
        
        for(int i=0;i<edge.length;i++){
            list.get(edge[i][0]-1).add(edge[i][1]-1);
            list.get(edge[i][1]-1).add(edge[i][0]-1);
        }
        
        int max = 0;
        
        pq.add(new node(0,0));
        Arrays.fill(visit,-1);
        while(!pq.isEmpty()){
            node nd = pq.poll();

            if(visit[nd.y] > -1)
                continue;
            
            visit[nd.y] = nd.d;
            max = Math.max(max, nd.d);
            
            for(int i=0;i<list.get(nd.y).size();i++){
                pq.add(new node((int)list.get(nd.y).get(i),nd.d+1));
            }
        }
        
        for(int i=0;i<n;i++){
            if(visit[i] == max){
                answer++;
            }
        }

        return answer;
    }
}

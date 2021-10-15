class Solution {
    
     public int []parent;
     int find(int x){
         if(parent[x] == x){
             return x;
         }
         parent[x] = find(parent[x]);
         return parent[x];
     }
        
     void union(int a, int b){
         parent[find(a)] = find(b);   
     }
  
    public int solution(int n, int[][] computers) {
        int answer = 0;
        int len = computers.length;
        parent = new int[computers.length];
        
        for(int i=0;i<len;i++){
            parent[i] = i;
        }
        
        for(int i=0;i<len;i++){
            for(int j=0;j<len;j++){
                if(i!=j && computers[i][j] == 1&& find(i)!=find(j)){
                    union(i,j);
                }
            }
        }
        
        for(int i=0;i<len;i++){
            if(parent[i] == i){
                answer ++;
            }
        }
        
        return answer;
    }
}

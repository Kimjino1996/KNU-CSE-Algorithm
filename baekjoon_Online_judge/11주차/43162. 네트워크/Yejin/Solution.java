class Solution {
    static int[][] memo;
    static boolean[][] visit;
    static int[][] triangle;
    
    static int DP(int n, int idx){
        if(visit[n][idx]){
            return memo[n][idx];
        }
        if(n == triangle.length - 1){
            memo[n][idx] = triangle[n][idx];
            visit[n][idx] = true;
            return memo[n][idx];
        }
        int left = triangle[n][idx];
        int right = triangle[n][idx];
        if(!visit[n+1][idx])
            left += DP(n+1, idx);
        else
            left += memo[n+1][idx];
        if(!visit[n+1][idx+1])
            right += DP(n+1, idx+1);
        else
            right += memo[n+1][idx+1];
        if(left < right){
            memo[n][idx] = right;
        }
        else{
            memo[n][idx] = left;
        }
        visit[n][idx] = true;

        return memo[n][idx];
    }
    
    public int solution(int[][] triangl) {
        int answer = 0;
        memo = new int[triangl.length][triangl.length];
        visit = new boolean[triangl.length][triangl.length];
        triangle = new int[triangl.length][triangl.length];
        
        for(int i = 0; i < triangl.length; i++){
            for(int j = 0; j < triangl[i].length; j++){
                triangle[i][j] = triangl[i][j];
            }
        }
        
        answer = DP(0, 0);
        return answer;
    }
}

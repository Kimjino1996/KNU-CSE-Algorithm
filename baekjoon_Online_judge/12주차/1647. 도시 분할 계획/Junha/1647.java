import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, M;
    static int parent[];

    public static class edge implements Comparable<edge>{
        int a;
        int b;
        int c;

        edge(int a, int b, int c){
            this.a = a;
            this.b = b;
            this.c = c;
        }

        public int compareTo(edge o){
            if(this.c >= o.c)
                return 1;
            else
                return -1;
        }
    }

    public static int find(int a){
        if(parent[a] == a){
            return a;
        }
        parent[a] = find(parent[a]);
        return parent[a];
    }

    public static void union(int a, int b){
        int x = find(a);
        int y = find(b);

        if(x != y){
            parent[y] = a;
        }
    }

    static PriorityQueue<edge> pq = new PriorityQueue<>();

    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        parent = new int[N+1];
        for (int i = 0; i < N+1; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            pq.add(new edge(a,b,c));
        }

        int result = 0;
        int max = 0;
        while(!pq.isEmpty()){
            edge e = pq.poll();
            int v = e.a;
            int w = e.b;

            if(find(v) == find(w))
                continue;

            union(v,w);
            result += e.c;
            max = Math.max(max,e.c);
        }
        bw.write(String.valueOf(result - max));
        bw.flush();
        bw.close();
        br.close();
    }

}

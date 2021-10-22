import java.io.*;
import java.util.*;

class Main {
    // Dijkstra를 이용하면 쉽게 해결할 수 있다.
    // 시작점이 0이 아님. 0~n 모든 노드에서 한번 씩 출발해줘야 함.
    // dist 배열 초기화 해주기.

    static class node implements Comparable<node> {
        int y;
        int c;

        node(int y, int c) {
            this.y = y;
            this.c = c;
        }

        public int compareTo(node o) {
            return this.c - o.c;
        }
    }

    static ArrayList<ArrayList<node>> list;
    static PriorityQueue<node> pq = new PriorityQueue<>();
    static int[] dist;
    static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        list = new ArrayList<>();

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int[] item = new int[n];
        dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            item[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n; i++) { // 각 노드로부터 갈 수 있는 노드를 저장하기 위해.
            ArrayList<node> al = new ArrayList<>();
            list.add(al);
        }

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());

            list.get(a).add(new node(b, c)); // 각 노드로부터 갈 수 있는 노드를 저장
            list.get(b).add(new node(a, c));
        }

        for (int start = 0; start < n; start++) { // start 지점을 0~n
            pq.add(new node(start, 0));
            dist[start] = 0; // 출발점에서 출발점까지의 거리는 당연히 0

            while (!pq.isEmpty()) {
                node nn = pq.poll(); // pq에서 노드 하나를 poll

                for (int i = 0; i < list.get(nn.y).size(); i++) {
                    node nnn = (node) list.get(nn.y).get(i); // 해당 노드에서 새롭게 갈 수 있는 노드를 찾아서

                    if (dist[nnn.y] > dist[nn.y] + nnn.c) { // 기존의 dist 배열보다 새로운 경로의 dist가 더 짧을 때
                        dist[nnn.y] = dist[nn.y] + nnn.c; //  dist 배열 업데이트
                        pq.add(nnn); // pq에 해당 경로 추가
                    }
                }
            }

            int sum = 0;
            for (int i = 0; i < n; i++) {
                if (dist[i] <= m) // m보다 작은 dist의 경우
                    sum += item[i]; // item을 획득할 수 있다.
            }
            max = Math.max(max, sum);
            Arrays.fill(dist, Integer.MAX_VALUE); // dist 배열 초기화해주기.
        }

        bw.write(String.valueOf(max));
        bw.flush();
        br.close();
        bw.close();
    }
}

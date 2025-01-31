import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int node = Integer.parseInt(st.nextToken());
        int edge = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());
        
        Map<Integer, List<Integer>> graph = new HashMap<>();
        
        for (int i = 0; i < edge; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            
            graph.putIfAbsent(n1, new ArrayList<>());
            graph.putIfAbsent(n2, new ArrayList<>());
            
            graph.get(n1).add(n2);
            graph.get(n2).add(n1);
        }
        
        // 정점 리스트를 오름차순으로 정렬
        for (List<Integer> neighbors : graph.values()) {
            Collections.sort(neighbors);
        }
        
        System.out.println(DFS(graph, start));
        System.out.println(BFS(graph, start));
    }

    public static String DFS(Map<Integer, List<Integer>> graph, int root) {
        StringBuilder result = new StringBuilder();
        Set<Integer> visited = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        
        stack.push(root);
        
        while (!stack.isEmpty()) {
            int n = stack.pop();
            if (!visited.contains(n)) {
                visited.add(n);
                result.append(n).append(" ");
                
                if (graph.containsKey(n)) {
                    List<Integer> temp = new ArrayList<>(graph.get(n));
                    Collections.reverse(temp); // 역순 정렬
                    for (int neighbor : temp) {
                        if (!visited.contains(neighbor)) {
                            stack.push(neighbor);
                        }
                    }
                }
            }
        }
        return result.toString().trim();
    }

    public static String BFS(Map<Integer, List<Integer>> graph, int root) {
        StringBuilder result = new StringBuilder();
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        
        queue.add(root);
        
        while (!queue.isEmpty()) {
            int n = queue.poll();
            if (!visited.contains(n)) {
                visited.add(n);
                result.append(n).append(" ");
                
                if (graph.containsKey(n)) {
                    for (int neighbor : graph.get(n)) {
                        if (!visited.contains(neighbor)) {
                            queue.add(neighbor);
                        }
                    }
                }
            }
        }
        return result.toString().trim();
    }
}

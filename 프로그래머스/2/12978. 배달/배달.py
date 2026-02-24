import heapq

def solution(N, road, K):
    # 1. 그래프 만들기 (인접 리스트)
    graph = [[] for _ in range(N + 1)]
    for a, b, c in road:
        graph[a].append((b, c))
        graph[b].append((a, c))
    
    # 2. 다익스트라 (1번에서 출발)
    dist = [float('inf')] * (N + 1)
    dist[1] = 0
    heap = [(0, 1)]  # (거리, 노드)
    
    while heap:
        d, node = heapq.heappop(heap)
        if d > dist[node]:
            continue
        for next_node, cost in graph[node]:
            new_dist = d + cost
            if new_dist < dist[next_node]:
                dist[next_node] = new_dist
                heapq.heappush(heap, (new_dist, next_node))
    
    # 3. K 이하인 마을 세기
    return sum(1 for i in range(1, N + 1) if dist[i] <= K)
from collections import deque

def solution(bridge_length, weight, truck_weights):
    time = 0
    bridge = deque([0] * bridge_length)
    cur_weight = 0 
    trucks = deque(truck_weights)
    
    while trucks or cur_weight > 0 :
        time += 1
        cur_weight -= bridge.popleft()
        
        if trucks and cur_weight + trucks[0] <= weight:
            t = trucks.popleft()
            bridge.append(t)
            cur_weight += t
        else :
            bridge.append(0)
    
    return time
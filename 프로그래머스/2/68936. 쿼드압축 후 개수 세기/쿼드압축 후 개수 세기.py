def solution(arr):
    count = [0, 0]
    
    def compress(x, y, size):
        first = arr[x][y]
        
        for i in range(x, x+size):
            for j in range(y, y+size):
                if arr[i][j] != first :
                    half = size // 2
                    compress(x, y, half)
                    compress(x, y + half, half)
                    compress(x + half, y, half)
                    compress(x + half, y + half, half)
                    return 
            
        count[first] += 1
        
    compress(0,0,len(arr))
    
    return count
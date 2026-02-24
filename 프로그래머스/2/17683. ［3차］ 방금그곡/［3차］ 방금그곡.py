def solution(m, musicinfos):
    def replace_sharp(s):
        s = s.replace('C#', 'c').replace('D#', 'd').replace('E#', 'e')
        s = s.replace('F#', 'f').replace('G#', 'g').replace('A#', 'a').replace('B#', 'b')
        return s
    
    m = replace_sharp(m)
    
    answer = '(None)'
    max_time = -1
    
    for info in musicinfos:
        start, end, title, notes = info.split(',')
        
        sh, sm = map(int, start.split(':'))
        eh, em = map(int, end.split(':'))
        play_time = (eh * 60 + em) - (sh * 60 + sm)
        
        if play_time <= 0:
            continue
        
        notes = replace_sharp(notes)
        full_notes = (notes * (play_time // len(notes) + 1))[:play_time]
        
        if m in full_notes:
            if play_time > max_time:
                max_time = play_time
                answer = title
    
    return answer
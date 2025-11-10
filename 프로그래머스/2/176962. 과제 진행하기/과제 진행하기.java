import java.util.*;

class Solution {
    public String[] solution(String[][] plans) {
        List<String> answer = new ArrayList<>();
        Working[] tasks = new Working[plans.length];
        for (int i = 0; i < plans.length; i++) {
            String name = plans[i][0];
            int startMin = timeToMinutes(plans[i][1]);
            int playtime = Integer.parseInt(plans[i][2]);
            tasks[i] = new Working(name, startMin, playtime);
        }

        Arrays.sort(tasks, Comparator.comparingInt(a -> a.start_min));

        Stack<Working> paused = new Stack<>();

        for (int i = 0; i < tasks.length - 1; i++) {
            Working current = tasks[i];
            Working next = tasks[i + 1];
            int endTime = current.start_min + current.playtime;
            int gap = next.start_min - endTime;

            if (gap >= 0) {
                answer.add(current.name);
                while (!paused.isEmpty() && gap > 0) {
                    Working pausedTask = paused.pop();
                    if (pausedTask.playtime <= gap) {
                        answer.add(pausedTask.name);
                        gap -= pausedTask.playtime;
                    } else {
                        pausedTask.playtime -= gap;
                        paused.push(pausedTask);
                        gap = 0;
                    }
                }
            } else {
                current.playtime = -gap;
                paused.push(current);
            }
        }

        answer.add(tasks[tasks.length - 1].name);
        while (!paused.isEmpty()) {
            answer.add(paused.pop().name);
        }

        return answer.toArray(new String[0]);
    }

    private int timeToMinutes(String time) {
        String[] parts = time.split(":");
        return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
    }

    static class Working {
        String name;
        int start_min;
        int playtime;

        Working(String name, int start_min, int playtime) {
            this.name = name;
            this.start_min = start_min;
            this.playtime = playtime;
        }
    }
}
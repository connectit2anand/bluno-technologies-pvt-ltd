package Assignment;

import java.util.*;

class Pair implements Comparable<Pair>{

    public String key;
    public Integer value;

    public Pair(String key, Integer value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public int compareTo(Pair pair) {
        return this.value - pair.value;
    }
}

public class Main {

    private static String[] solve(String[] songs,int size, int k) {
        Map<String, Integer> songsFreq = new HashMap<>();
        for(int i = 0; i < size; ++ i) {
            if(!songsFreq.containsKey(songs[i])) {
                songsFreq.put(songs[i], 1);
            } else {
                int value = songsFreq.get(songs[i]);
                songsFreq.put(songs[i], value + 1);
            }
        }
        PriorityQueue<Pair> priorityQueue = new PriorityQueue<>();
        for(Map.Entry<String, Integer> entry : songsFreq.entrySet()) {
            Pair pair = new Pair(entry.getKey(), entry.getValue());
            if(priorityQueue.size() == k) {
                if(priorityQueue.peek().value < entry.getValue()) {
                    priorityQueue.poll();
                    priorityQueue.add(pair);
                }
            } else {
                priorityQueue.add(pair);
            }
        }
        String[] result = new String[k];
        for(int i = k - 1; i >= 0; -- i) {
            Pair pair = priorityQueue.poll();
            String movie = pair.key;
            result[i] = movie;
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the size of the list");
        int size = sc.nextInt();
        System.out.println("Enter the movies name");
        String[] song = new String[size];
        for(int i = 0; i < size; ++ i) {
            song[i] = sc.nextLine();
        }
        System.out.println("Enter the value of k");
        int k = sc.nextInt();
        String[] result = solve(song, size, k);
        for(int i = 0; i < k; ++ i) {
            System.out.println(result[i]);
        }
    }
}

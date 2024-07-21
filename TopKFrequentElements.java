import java.util.*;
//time - O(nlogk), space - O(n)
class TopKFrequentElements {
    class Pair {
        int key, value;
        public Pair(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    public int[] topKFrequent(int[] nums, int k) {
        if(nums == null) {
            return nums;
        }
        Map<Integer, Integer> map = new HashMap<>();
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> a.value - b.value);

        for(int i=0; i<nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0)+1);
        }

        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if(pq.size() < k) {
                pq.add(new Pair(entry.getKey(), entry.getValue()));
            } else if(pq.size() == k) {
                if(entry.getValue() > pq.peek().value) {
                    pq.poll();
                    pq.add(new Pair(entry.getKey(), entry.getValue()));
                }
            }
        }

        int[] res = new int[pq.size()];
        int i=0;

        while(!pq.isEmpty()) {
            res[i] = pq.poll().key;
            i++;
        }

        return res;
    }
}

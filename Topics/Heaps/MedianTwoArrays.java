package Topics.Heaps;

import static org.junit.Assert.assertEquals;

import java.util.PriorityQueue;

import org.junit.Test;

public class MedianTwoArrays {
    @Test
    public void test1() {
        int[] nums1 = { 1, 3 }, nums2 = { 2 };
        assertEquals(2, this.findMedianSortedArrays(nums1, nums2), 0);
    }

    @Test
    public void test2() {
        int[] nums1 = { 1, 2 }, nums2 = { 3, 4 };
        assertEquals(2.5, this.findMedianSortedArrays(nums1, nums2), 0);
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        MedianFinder m = new MedianFinder();
        for (int num : nums1) {
            m.add(num);
        }
        for (int num : nums2) {
            m.add(num);
        }
        return m.getMedian();
    }

    class MedianFinder {
        PriorityQueue<Integer> lowerHalf, upperHalf;

        public MedianFinder() {
            this.lowerHalf = new PriorityQueue<>((a, b) -> b - a);
            this.upperHalf = new PriorityQueue<>((a, b) -> a - b);
        }

        void add(int num) {
            PriorityQueue<Integer> selectedHalf = this.upperHalf;
            PriorityQueue<Integer> otherHalf;
            if (this.upperHalf.size() == 0 || num >= this.upperHalf.peek()) {
                selectedHalf = this.upperHalf;
                otherHalf = this.lowerHalf;
            } else {
                selectedHalf = this.lowerHalf;
                otherHalf = this.upperHalf;
            }
            selectedHalf.add(num);
            if (selectedHalf.size() > otherHalf.size() + 1) {
                otherHalf.add(selectedHalf.poll());
            }
        }

        double getMedian() {
            if (this.upperHalf.size() > this.lowerHalf.size()) {
                return this.upperHalf.peek();
            } else if (this.upperHalf.size() < this.lowerHalf.size()) {
                return this.lowerHalf.peek();
            } else {
                return (this.upperHalf.peek() + this.lowerHalf.peek()) / 2.0;
            }
        }
    }
}
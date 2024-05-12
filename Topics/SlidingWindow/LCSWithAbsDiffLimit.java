package Topics.SlidingWindow;

// https://leetcode.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/
/*
 * To find absolute diff less than limit, we can find the longest subarray with a positive difference and the longest subarray with a negative difference and compare both and return the longest subarray
 * To find the longest subarray with positive difference, we can maintain a non increasing monotonic queue, so that the first element will be greater than the last element,
 * to find the longest subarray with negative difference, we can maintain a non decreasing monotonic queue, so that the first element will be smaller than the last element. 
 */
public class LCSWithAbsDiffLimit {

}

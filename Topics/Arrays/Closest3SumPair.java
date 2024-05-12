package Topics.Arrays;

/*
 * 
// [-1,2,1,-4]
// sort the array
/*
 [-4,-1,1,2]
    minDiff = Integer.max; 
    for each element outerIndex in the sortedarray
    pairSum = target-array[outerIndex];
    closestPairSum = slidingWindowPairSearch(pairSum, sortedarray, outerIndex+1);
    closestTripletSum = sortedarray[outerIndex]+closesPairSum;
    
        tripletdifference = Math.abs(closestTripletSum-target);
        if(tripletdifference < minDiff){
            leastClosestTripletSum = closestTripletSum;
            minDiff=tripletdifference;
        }

    return leastClosestTripletSum;
    // returns the closest sum to targetsum in the sorted array
    slidingwindowpairsearch(targetSum, sortedarray, startIndex){
        // take two pointers 
        start =startIndex, end=sortedarray.length-1;
        minDiff=Integer.max;
        while(start<end){
            currentSum = sortedarray[start]+sortedarray[end];
            difference = Math.abs(currentSum-targetSum);
            if(difference < minDiff){
                result = currentSum;
                minDiff=difference;
            }
            if (currSum < targetSum) {
                start++;
                continue;
            }
            else if (currSum > targetSum) {
                end--;
                continue;
            }
            else{ 
                // if currSum == targetSum, closest sum is target Sum itself
                return targetSum;
            }
        }
        return result;
    }

    // Time complexity - O(n^2)
    // outer loop O(n) * each time closesPair function is called - O(n) = O(n^2)

    */
public class Closest3SumPair {

}

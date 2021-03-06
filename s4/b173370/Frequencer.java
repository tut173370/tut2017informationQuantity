package s4.b173370; // Please modify to s4.Bnnnnnn, where nnnnnn is your student ID.
import java.lang.*;
import s4.specification.*;

public class Frequencer implements FrequencerInterface{
  // Code to start with: This code is not working, but good start point to work.
  byte [] myTarget;
  byte [] mySpace;
  boolean targetReady = false;
  boolean spaceReady = false;
  int [] suffixArray;
  // The variable, "suffixArray" is the sorted array of all suffixes of mySpace.
  // Each suffix is expressed by a interger, which is the starting position in mySpace.
  // The following is the code to print the variable
  private void printSuffixArray() {
      int cnt=0;
    if(spaceReady) {
      for(int i=0; i< mySpace.length; i++) {
        int s = suffixArray[i];
          System.out.print(cnt+":");
          cnt++;
        for(int j=s;j<mySpace.length;j++) {
          System.out.write(mySpace[j]);
        }
        System.out.write('\n');
      }
    }
  }

  private int suffixCompare(int i, int j) {
    // comparing two suffixes by dictionary order.
    // i and j denoetes suffix_i, and suffix_j
    // if suffix_i > suffix_j, it returns 1
    // if suffix_i < suffix_j, it returns -1
    // if suffix_i = suffix_j, it returns 0;
    // It is not implemented yet,
    // It should be used to create suffix array.
    // Example of dictionary order
    // "i"  < "o"    : compare by code
    // "Hi" < "Ho"   ; if head is same, compare the next element
    // "Ho" < "Ho "  ; if the prefix is identical, longer string is big
    int si = suffixArray[i];
    int sj = suffixArray[j];
    int s = 0;
    if(si > s) s = si;
    if(sj > s) s = sj;
    int n = mySpace.length - s;
    for(int k = 0; k < n; k++) {
      if(mySpace[si+k] > mySpace[sj+k]) return 1;
      if(mySpace[si+k] < mySpace[sj+k]) return -1;
    }
    if(si < sj) return 1;
    if(si > sj) return -1;
    return 0;
  }

    public int part(int [] suffixArray, int low, int high){
        int leftwall = low;
        for(int i = low + 1; i < high; i++){
            if(suffixCompare(i, low) == -1){
                leftwall += 1;
                int tmp = suffixArray[i];
                suffixArray[i] = suffixArray[leftwall];
                suffixArray[leftwall] = tmp;
            }
        }
        int tmp = suffixArray[low];
        suffixArray[low] = suffixArray[leftwall];
        suffixArray[leftwall] = tmp;
        return leftwall;
    }

    public void qsort(int[] suffixArray, int low, int high){
        int pivot;
        if(low < high){
            pivot = part(suffixArray, low, high);
            qsort(suffixArray, low, pivot);
            qsort(suffixArray, pivot+1, high);
        }
    }

  public void setSpace(byte []space) {
    mySpace = space;
    if(mySpace.length>0) spaceReady = true;
    suffixArray = new int[space.length];
    // put all suffixes in suffixArray. Each suffix is expressed by one interger.
    for(int i = 0; i< space.length; i++) {
      suffixArray[i] = i;
    }
    // Sorting is not implmented yet.
    /*
    Example from "Hi Ho Hi Ho"
    0: Hi Ho
    1: Ho
    2: Ho Hi Ho
    3:Hi Ho
    4:Hi Ho Hi Ho
    5:Ho
    6:Ho Hi Ho
    7:i Ho
    8:i Ho Hi Ho
    9:o
    A:o Hi Ho
    */

    /*バブルソート*//*
    for(int i = 0; i < mySpace.length; i++){
      for(int j = i + 1; j < mySpace.length; j++){
        if(suffixCompare(i, j) == 1){
          int tmp = suffixArray[i];
          suffixArray[i] = suffixArray[j];
          suffixArray[j] = tmp;
        }
      }
    }
    */

    qsort(suffixArray, 0, mySpace.length);

    //printSuffixArray();
  }

  private int targetCompare(int i, int start, int end) {
    // It is called from subBytesStartIndex, and subBytesEndIndex.
    // "start" and "end" are same as in subByteStartIndex, and subByteEndIndex **
    // target_start_end is subBytes(start, end) of target **
    // comparing suffix_i and target_start_end by dictonary order with limitation of length; ***
    // if the beginning of suffix_i matches target_start_end, and suffix is longer than target it returns 0;
    // if suffix_i > target_start_end it return 1; **
    // if suffix_i < target_start_end it return -1 **

    // It should be used to search the apropriate index of some suffix.
    // Example of search
    // suffix    target
    // "o"    >   "i"
    // "o"    <   "z"
    // "o"    =   "o"
    // "o"    <   "oo"
    // "Ho"   <   "oo"
    // "Ho"   >   "Hi"
    // "Ho"   <   "Hz"
    // "Ho"   =   "Ho"
    // "Ho"   <   "Ho "   : "Ho "is not in the head of suffix "Ho"
    // "Ho"   =   "H"     : "H" is in the head of suffix "Ho"
    int si = suffixArray[i];
    int sj = end - start;
    int min = (mySpace.length - si < sj)? mySpace.length - si : sj;
    for(int k = 0; k < min; k++) {
      if(mySpace[si+k] > myTarget[start+k]) return 1;
      if(mySpace[si+k] < myTarget[start+k]) return -1;
    }
    if(sj > mySpace.length - si) return -1;
    return 0;
  }

  private int subByteStartIndex(int start, int end) {
    // It returns the index of the first suffix which is equal or greater than subBytes;
    // not implemented yet;
    // For "Ho", it will return 5 for "Hi Ho Hi Ho".
    // For "Ho ", it will return 6 for "Hi Ho Hi Ho".
      /*線形探索*//*
      System.out.println(start+" "+end);
    for(int i = 0; i < mySpace.length; i++){
      if(targetCompare(i, start, end) == 0) return i;
    }*/
      /*二分探索*/
      int l = 0;
      int r = mySpace.length - 1;
      int mid = (l+r)/2;
      if(targetCompare(l, start, end) == 0) return l;
      while(l < r){
          if(targetCompare(mid, start, end) == 0 || targetCompare(mid, start, end) == 1) r = mid;
          else if(targetCompare(mid, start, end) == -1) l = mid;
          mid = (l+r)/2;
          //System.out.println("left = "+l+", mid = "+mid+", right = "+r);
          if(l == mid) return r;
      }
    return suffixArray.length;
  }

  private int subByteEndIndex(int start, int end) {
    // It returns the next index of the first suffix which is greater than subBytes;
    // not implemented yet
    // For "Ho", it will return 7 for "Hi Ho Hi Ho".
    // For "Ho ", it will return 7 for "Hi Ho Hi Ho".
      /*線形探索*//*
    for(int i = mySpace.length - 1; i > 0; i--){
      if(targetCompare(i, start, end) == 0) return i + 1;
    }*/
      /*二分探索*/
      int l = 0;
      int r = mySpace.length - 1;
      int mid = (l+r)/2;
      if(targetCompare(r, start, end) == 0) return mySpace.length;
      while(l < r){
          if(targetCompare(mid, start, end) == 0 || targetCompare(mid, start, end) == -1) l = mid;
          else if(targetCompare(mid, start, end) == 1) r = mid;
          mid = (l+r)/2;
          //System.out.println("left = "+l+", mid = "+mid+", right = "+r);
          if(l == mid) return r;
      }
    return suffixArray.length;
  }

  public int subByteFrequency(int start, int end) {
    //This method could be defined as follows though it is slow.
    int spaceLength = mySpace.length;
    int count = 0;
    for(int offset = 0; offset< spaceLength - (end - start); offset++) {
      boolean abort = false;
      for(int i = 0; i< (end - start); i++) {
        if(myTarget[start+i] != mySpace[offset+i]) {
          abort = true;
          break;
        }
      }
      if(abort == false) {
        count++;
      }
    }
    int first = subByteStartIndex(start,end);
    int last1 = subByteEndIndex(start, end);
    //inspection code
    /*
    for(int k=start;k<end;k++) {
      System.out.write(myTarget[k]);
    }
    System.out.printf(": first=%d last1=%d\n", first, last1);
    */
    return last1 - first;
  }

  public void setTarget(byte [] target) {
    myTarget = target;
    if(myTarget.length>0) targetReady = true;
  }
  public int frequency() {
    if(targetReady == false) return -1;
    if(spaceReady == false) return 0;
    return subByteFrequency(0, myTarget.length);
  }

  public static void main(String[] args) {
    Frequencer frequencerObject;
    try {
      frequencerObject = new Frequencer();
      frequencerObject.setSpace("Hi Ho Hi Ho".getBytes());
      frequencerObject.setTarget("H".getBytes());
      int result = frequencerObject.frequency();
      System.out.print("Freq = "+ result+" ");
      if(4 == result) {
        System.out.println("OK");
      }
      else {
        System.out.println("WRONG");
      }
    }
    catch(Exception e) {
      System.out.println("STOP");
    }
  }
}

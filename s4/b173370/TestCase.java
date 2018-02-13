package s4.b173370; // Please modify to s4.Bnnnnnn, where nnnnnn is your student ID.
import java.lang.*;
import s4.specification.*;

/*
interface FrequencerInterface {     // This interface provides the design for frequency counter.
    void setTarget(byte[]  target); // set the data to search.
    void setSpace(byte[]  space);  // set the data to be searched target from.
    int frequency(); //It return -1, when TARGET is not set or TARGET's length is zero
                    //Otherwise, it return 0, when SPACE is not set or Space's length is zero
                    //Otherwise, get the frequency of TAGET in SPACE
    int subByteFrequency(int start, int end);
    // get the frequency of subByte of taget, i.e target[start], taget[start+1], ... , target[end-1].
    // For the incorrect value of START or END, the behavior is undefined.
}
*/

/*
package s4.specification;
public interface InformationEstimatorInterface{
    void setTarget(byte target[]); // set the data for computing the information quantities
    void setSpace(byte space[]); // set data for sample space to computer probability
    double estimation(); // It returns 0.0 when the target is not set or Target's length is zero;
// It returns Double.MAX_VALUE, when the true value is infinite, or space is not set.
// The behavior is undefined, if the true value is finete but larger than Double.MAX_VALUE.
// Note that this happens only when the space is unreasonably large. We will encounter other problem anyway.
// Otherwise, estimation of information quantity,
}
*/


public class TestCase {
    public static void main(String[] args) {
	try {
	    FrequencerInterface  myObject;
	    int freq;
	    System.out.println("checking s4.b173370.Frequencer");
	    myObject = new s4.b173370.Frequencer();
	    myObject.setSpace("Hi Ho Hi Ho".getBytes());
	    myObject.setTarget("H".getBytes());
	    freq = myObject.frequency();
	    System.out.print("\"H\" in \"Hi Ho Hi Ho\" appears "+freq+" times. ");
	    if(4 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }

      /*追加したテストケース*/
      /*subByteFrequency*/
      System.out.println("------------------------------");
      myObject.setTarget("Hi Ho Hi Ho".getBytes());
      freq = myObject.subByteFrequency(0, 2);
      System.out.print("subbyte: \"Hi\" in \"Hi Ho Hi Ho\" appears "+freq+" times. ");
      if(2 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }
      myObject.setTarget("a".getBytes());
      freq = myObject.frequency();
      System.out.print("do not appears: \"a\" in \"Hi Ho Hi Ho\" appears "+freq+" times. ");
      if(0 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }
      /*Space is not set*/
      System.out.println("------------------------------");
      myObject = new s4.b173370.Frequencer();
      myObject.setTarget("H".getBytes());
      freq = myObject.frequency();
      System.out.print("SPACE is not set: output is "+freq+" ");
      if(0 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }
      /*Space's length is zero*/
      myObject.setSpace("".getBytes());
      freq = myObject.frequency();
      System.out.print("Space's length zero: output is "+freq+" ");
      if(0 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }
      /*Target is not set*/
      System.out.println("------------------------------");
      myObject = new s4.b173370.Frequencer();
      myObject.setSpace("Hi Ho Hi Ho".getBytes());
      freq = myObject.frequency();
      System.out.print("TARGET is not set: output is "+freq+" ");
      if(-1 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }
      /*Target's length is zero*/
      myObject.setTarget("".getBytes());
      freq = myObject.frequency();
      System.out.print("Target's length zero: output is "+freq+" ");
      if(-1 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }
      System.out.println("------------------------------");
      /*Space and Target is not set*/
      myObject = new s4.b173370.Frequencer();
      freq = myObject.frequency();
      System.out.print("SPACE and TARGET is not set: output is "+freq+" ");
      if(-1 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }
      /*Space's length and Target's length is zero*/
      myObject.setSpace("".getBytes());
      myObject.setTarget("".getBytes());
      freq = myObject.frequency();
      System.out.print("Space's length and Target's length zero: output is "+freq+" ");
      if(-1 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }
      System.out.println("------------------------------");
	}
	catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	}

	try {
	    InformationEstimatorInterface myObject;
	    double value;
	    System.out.println("checking s4.b173370.InformationEstimator");
	    myObject = new s4.b173370.InformationEstimator();
	    myObject.setSpace("3210321001230123".getBytes());
	    myObject.setTarget("0".getBytes());
	    value = myObject.estimation();
	    System.out.println(">0 "+value);
	    myObject.setTarget("01".getBytes());
	    value = myObject.estimation();
	    System.out.println(">01 "+value);
	    myObject.setTarget("0123".getBytes());
	    value = myObject.estimation();
	    System.out.println(">0123 "+value);
	    myObject.setTarget("00".getBytes());
	    value = myObject.estimation();
	    System.out.println(">00 "+value);

      /*追加したテストケース*/
      /*Target is not set*/
      System.out.println("------------------------------");
      myObject = new s4.b173370.InformationEstimator();
      myObject.setSpace("3210321001230123".getBytes());
      value = myObject.estimation();
      System.out.println("TARGET is not set: output is "+value+" ");
      /*Target's length is zero*/
      myObject.setTarget("".getBytes());
      value = myObject.estimation();
      System.out.println("Target's length zero: output is "+value+" ");
      /*Space is not set*/
      System.out.println("------------------------------");
      myObject = new s4.b173370.InformationEstimator();
      myObject.setTarget("0".getBytes());
      value = myObject.estimation();
      System.out.println("SPACE is not set: output is "+value+" ");
      /*Space's length is zero*/
      myObject.setSpace("".getBytes());
      value = myObject.estimation();
      System.out.println("Space's length zero: output is "+value+" ");
      /*Space and Target is not set*/
      System.out.println("------------------------------");
      myObject = new s4.b173370.InformationEstimator();
      value = myObject.estimation();
      System.out.println("SPACE and TARGET is not set:: output is "+value+" ");
      /*Space's length and Target's length is zero*/
      myObject.setSpace("".getBytes());
      myObject.setTarget("".getBytes());
      value = myObject.estimation();
      System.out.println("Space's length and Target's length zero: output is "+value+" ");
      System.out.println("------------------------------");
	}
	catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	}
}
}

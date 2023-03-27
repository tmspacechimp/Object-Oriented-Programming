import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

// CS108 HW1 -- String static methods

public class StringCode {

	/**
	 * Given a string, returns the length of the largest run.
	 * A a run is a series of adajcent chars that are the same.
	 * @param str
	 * @return max run length
	 */
	public static int maxRun(String str) {
		int run=0;
		int ans=0;
		char c;
		for (int i=0; i<str.length();){
			c= str.charAt(i);
			while(i!=str.length()){
				if (str.charAt(i)==c) {
					run++;
					i++;
				} else break;

			}
			if (run>ans) ans= run;
			run=0;
		}
		return ans; // YOUR CODE HERE
	}

	
	/**
	 * Given a string, for each digit in the original string,
	 * replaces the digit with that many occurrences of the character
	 * following. So the string "a3tx2z" yields "attttxzzz".
	 * @param str
	 * @return blown up string
	 */


	public static String blowup(String str) {
		String blownUp= new String("");
		char next;
		int cnt;
		for (int i=0; i<str.length(); i++){
			if(str.charAt(i) >= '0' && str.charAt(i) <= '9' ) {
				if(i == str.length()-1) break;
				else {
					next = str.charAt(i + 1);
					cnt = str.charAt(i) - '0';
					for (int j = 0; j < cnt; j++) blownUp += next;
				}
			} else blownUp+= str.charAt(i);
		}

		return blownUp;
	}


	
	/**
	 * Given 2 strings, consider all the substrings within them
	 * of length len. Returns true if there are any such substrings
	 * which appear in both strings.
	 * Compute this in linear time using a HashSet. Len will be 1 or more.
	 */
	public static boolean stringIntersect(String a, String b, int len) {
		Set<String> log= new HashSet<>();

		for(int i=0; i<=a.length()-len; i++){
			log.add(a.substring(i, i +len));
		}
		for(int i=0; i<=b.length()-len; i++){
			if(log.contains(b.substring(i, i +len))) {
				return true;
			}
		}
		return false;
	}
}

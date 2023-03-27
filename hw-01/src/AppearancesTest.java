import junit.framework.TestCase;

import java.nio.channels.AcceptPendingException;
import java.util.*;

public class AppearancesTest extends TestCase {
	// utility -- converts a string to a list with one
	// elem for each char.
	private List<String> stringToList(String s) {
		List<String> list = new ArrayList<String>();
		for (int i=0; i<s.length(); i++) {
			list.add(String.valueOf(s.charAt(i)));
			// note: String.valueOf() converts lots of things to string form
		}
		return list;
	}
	
	public void testSameCount1() {
		List<String> a = stringToList("abbccc");
		List<String> b = stringToList("cccbba");
		assertEquals(3, Appearances.sameCount(a, b));

		List<String> a1 = stringToList("");
		List<String> b1 = stringToList("");
		assertEquals(0, Appearances.sameCount(a1, b1));

		List<String> a2 = stringToList("giorgi lekva");
		List<String> b2 = stringToList("gvnatsa tsutskh");
		assertEquals(3, Appearances.sameCount(a2, b2));

	}
	
	public void testSameCount2() {
		// basic List<Integer> cases
		List<Integer> a = Arrays.asList(1, 2, 3, 1, 2, 3, 5);
		assertEquals(1, Appearances.sameCount(a, Arrays.asList(1, 9, 9, 1)));
		assertEquals(2, Appearances.sameCount(a, Arrays.asList(1, 3, 3, 1)));
		assertEquals(1, Appearances.sameCount(a, Arrays.asList(1, 3, 3, 1, 1)));
		List<Integer> empty = new ArrayList<>();
		assertEquals(0, Appearances.sameCount(a, empty));
	}

	public void testSameCount3(){
		List<Float> f = Arrays.asList(2.4f , 1.1f, 0.0f, 0.0f, 2f,4.2f, 4.2f, 1.1f, 1.1f, 3.2f, 0.0f, 333.2f);
		assertEquals(2, Appearances.sameCount(f, Arrays.asList(5.0f, 0.0f, 0.0f, 1.1f,2f, 0.0f )));
		assertEquals(0, Appearances.sameCount(f, Arrays.asList(5.0f, 4.0f, 0.2f, 1.1f,2.5f, 0.0f )));
		assertEquals(7, Appearances.sameCount(f, Arrays.asList(2.4f , 1.1f, 0.0f, 0.0f, 2f,4.2f, 4.2f, 1.1f, 1.1f, 3.2f, 0.0f, 333.2f)));

		List<Double> d = Arrays.asList(2.4d , 1.1d, 0.0d, 0.0d, 2d,4.2d, 4.2d, 1.1d, 1.1d, 3.2d, 0.0d, 333.2d);
		List<Double> empty = new ArrayList<>();
		assertEquals(0, Appearances.sameCount(empty, d));
		assertEquals(5, Appearances.sameCount(d,Arrays.asList(2.4d , 1.1d, 0.0d, 0.0d, 2d,4.2d, 4.2d, 1.1d, 1.1d, 3.2d)));

		List<Boolean> b = Arrays.asList(false, false, true, true, true, true, false, true, true, false);
		List<Boolean> emptyBool = new ArrayList<>();
		assertEquals(0, Appearances.sameCount(b,emptyBool));
		assertEquals(0, Appearances.sameCount(b,Arrays.asList(true, false, false, false, true, true,false, true, true, false, true, true, false)));
		assertEquals(1, Appearances.sameCount(b,Arrays.asList(false, false, false, false, true, true)));
		assertEquals(2, Appearances.sameCount(b,Arrays.asList(false, false, false, false, true, true, true, true, true, true)));

	}
}

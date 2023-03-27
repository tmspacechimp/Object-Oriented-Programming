// StringCodeTest
// Some test code is provided for the early HW1 problems,
// and much is left for you to add.

import junit.framework.TestCase;

public class StringCodeTest extends TestCase {
	//
	// blowup
	//
	public void testBlowup1() {
		// basic cases
		assertEquals("xxaaaabb", StringCode.blowup("xx3abb"));
		assertEquals("xxxZZZZ", StringCode.blowup("2x3Z"));
	}
	
	public void testBlowup2() {
		// things with digits
		
		// digit at end
		assertEquals("axxx", StringCode.blowup("a2x3"));
		
		// digits next to each other
		assertEquals("a33111", StringCode.blowup("a231"));
		
		// try a 0
		assertEquals("aabb", StringCode.blowup("aa0bb"));
	}
	
	public void testBlowup3() {
		// weird chars, empty string
		assertEquals("AB&&,- ab", StringCode.blowup("AB&&,- ab"));
		assertEquals("", StringCode.blowup(""));
		
		// string with only digits
		assertEquals("", StringCode.blowup("2"));
		assertEquals("33", StringCode.blowup("23"));
	}

	public void testBlowup4(){
		// bigger strings

		assertEquals("ggggg4iiiiio0rgiiiilekvvvvvva14ggggg4iiiiio0rgiiiilekvvvvvva14ggggg4iiiiio0rgiiiilekvvvvvva1", StringCode.blowup("4g14io10rg3il0ek5va114g14io10rg3il0ek5va114g14io10rg3il0ek5va11"));
		assertEquals("g3333333vvvvaaantsssssssssa99ttttttttttsuuutsskkkh33333666222222BBBetterSeminars222200g3333333vvvvaaantsssssssssa99ttttttttttsuuutsskkkh33333666222222BBBetterSeminars222200g3333333vvvvaaantsssssssssa99ttttttttttsuuutsskkkh33333666222222BBBetterSeminars222200", StringCode.blowup("00g73v2ant8sa29ts2ut1s2kh5362BetterSeminars42000g73v2ant8sa29ts2ut1s2kh5362BetterSeminars42000g73v2ant8sa29ts2ut1s2kh5362BetterSeminars420"));

	}
	
	
	//
	// maxRun
	//
	public void testRun1() {
		assertEquals(2, StringCode.maxRun("hoopla"));
		assertEquals(3, StringCode.maxRun("hoopllla"));
	}
	
	public void testRun2() {
		assertEquals(3, StringCode.maxRun("abbcccddbbbxx"));
		assertEquals(0, StringCode.maxRun(""));
		assertEquals(3, StringCode.maxRun("hhhooppoo"));
	}
	
	public void testRun3() {
		// "evolve" technique -- make a series of test cases
		// where each is change from the one above.
		assertEquals(1, StringCode.maxRun("123"));
		assertEquals(2, StringCode.maxRun("1223"));
		assertEquals(2, StringCode.maxRun("112233"));
		assertEquals(3, StringCode.maxRun("1112233"));
	}

	public void testRun4() {
		// longer strings
		assertEquals(6, StringCode.maxRun("ggggg4iiiiio0rgiiiilekvvvvvva14ggggg4iiiiio0rgiiiilekvvvvvva14ggggg4iiiiio0rgiiiilekvvvvvva1"));
		assertEquals(10, StringCode.maxRun("g3333333vvvvaaantsssssssssa99ttttttttttsuuutsskkkh33333666222222BBBetterSeminars222200g3333333vvvvaaantsssssssssa99ttttttttttsuuutsskkkh33333666222222BBBetterSeminars222200g3333333vvvvaaantsssssssssa99ttttttttttsuuutsskkkh33333666222222BBBetterSeminars222200") );
	}

	// Need test cases for stringIntersect

	public void testIntersect1(){
		// basic cases
		assertEquals(true, StringCode.stringIntersect("abcdefg", "defjamrecords", 3));
		assertEquals(false, StringCode.stringIntersect("abcdefg", "defjamrecords", 4));
	}

	public void testIntersect2(){
		// edge cases
		assertEquals(false, StringCode.stringIntersect("","", 1));
		assertEquals(false, StringCode.stringIntersect("abcd","abcd", 10));
	}

	public void testIntersect3(){
		// bigger strings
		assertEquals(true, StringCode.stringIntersect("g3333333vvvvaaantsssssssssa99ttttttttttsuuutsskkkh33333666222222BBBetterSeminars222200g3333333vvvvaaantsssssssssa99ttttttttttsuuutsskkkh33333666222222BBBetterSeminars222200g3333333vvvvaaantsssssssssa99ttttttttttsuuutsskkkh33333666222222BBBetterSeminars222200", "ggggg4iiiiio0rgiiiilekvvvvvva14ggggg4iiiiio0rgiiiilekvvvvvva14ggggg4iiiiio0rgiiiilekvvvvvva1", 3 ));
		assertEquals(true, StringCode.stringIntersect("g3333333vvvvaaantsssssssssa99ttttttttttsuuutsskkkh33333666222222BBBetterSeminars222200g3333333vvvvaaantsssssssssa99ttttttttttsuuutsskkkh33333666222222BBBetterSeminars222200g3333333vvvvaaantsssssssssa99ttttttttttsuuutsskkkh33333666222222BBBetterSeminars222200", "ggggg4iiiiio0rgiiiilekvvvvvva14ggggg4iiiiio0rgiiiilekvvvvvva14ggggg4iiiiio0rgiiiilekvvvvvva1", 5 ));
		assertEquals(false, StringCode.stringIntersect("g3333333vvvvaaantsssssssssa99ttttttttttsuuutsskkkh33333666222222BBBetterSeminars222200g3333333vvvvaaantsssssssssa99ttttttttttsuuutsskkkh33333666222222BBBetterSeminars222200g3333333vvvvaaantsssssssssa99ttttttttttsuuutsskkkh33333666222222BBBetterSeminars222200", "ggggg4iiiiio0rgiiiilekvvvvvva14ggggg4iiiiio0rgiiiilekvvvvvva14ggggg4iiiiio0rgiiiilekvvvvvva1", 6 ));

	}

	
}

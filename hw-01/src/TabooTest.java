// TabooTest.java
// Taboo class tests -- nothing provided.

import java.util.*;

import junit.framework.TestCase;

public class TabooTest extends TestCase {
    private  <T> void  copyFromArr(List<T> list, T[] arr){
        for(int i=0; i<arr.length; i++){
            list.add(arr[i]);
        }
    }

    public void testNoFollowChar(){
        Character[] arr= {'a', 'c', 'a', 'b'};
        List<Character> rules = Arrays.asList(arr);
        Set<Character> actual1 = new HashSet<>();
        actual1.add('c');
        actual1.add('b');
        Taboo<Character> classAnswer= new Taboo<>(rules);
        assertTrue(actual1.equals(classAnswer.noFollow('a')));

        Set<Character> actual2 = new HashSet<>();
        actual2.add('a');
        assertTrue(actual2.equals(classAnswer.noFollow('c')));

        Set<Character> actual3 = new HashSet<>();
        assertTrue(actual3.equals(classAnswer.noFollow('b')));

        Set<Character> actual4 = new HashSet<>();
        assertTrue(actual4.equals(classAnswer.noFollow('x')));

        Character[] arr1= {'a'};
        List<Character> rules1 = Arrays.asList(arr1);
        Set<Character> empty= new HashSet<>();
        Taboo<Character> classAnswer1= new Taboo<>(Arrays.asList(arr1));
        assertTrue(empty.equals(classAnswer1.noFollow('a')));
        assertTrue(empty.equals(classAnswer1.noFollow('x')));

    }

    public void testNoFollowCharInt(){
        Integer[] arr= {1,3,1,2};
        List<Integer> rules = Arrays.asList(arr);
        Set<Integer> actual1 = new HashSet<>();
        actual1.add(3);
        actual1.add(2);
        Taboo<Integer> classAnswer= new Taboo<>(rules);
        assertTrue(actual1.equals(classAnswer.noFollow(1)));

        Set<Integer> actual2 = new HashSet<>();
        actual2.add(1);
        assertTrue(actual2.equals(classAnswer.noFollow(3)));

        Set<Integer> actual3 = new HashSet<>();
        assertTrue(actual3.equals(classAnswer.noFollow(2)));

        Set<Integer> actual4 = new HashSet<>();
        assertTrue(actual4.equals(classAnswer.noFollow(33)));

        Integer[] arr1= {1};
        List<Integer> rules1 = Arrays.asList(arr1);
        Set<Integer> empty= new HashSet<>();
        Taboo<Integer> classAnswer1= new Taboo<>(Arrays.asList(arr1));
        assertTrue(empty.equals(classAnswer1.noFollow(1)));
        assertTrue(empty.equals(classAnswer1.noFollow(66)));

    }

    public void testReduce(){
        Character[] arr= {'a', 'c', 'a', 'b'};
        List<Character> rules = new ArrayList<>();
        copyFromArr(rules, arr);
        Character[] arrL= {'a', 'c', 'b', 'x', 'c', 'a'};
        List<Character> list = new ArrayList<>();
        copyFromArr(list, arrL);
        Character[] arrA= {'a', 'x', 'c'};
        List<Character> actual = new ArrayList<>();
        copyFromArr(actual, arrA);
        Taboo<Character> classAnswer= new Taboo<>(rules);
        classAnswer.reduce(list);
        assertEquals(actual, list);

    }

    public void testReduce1(){
        //edge case
        Character[] arr= {'a'};
        List<Character> rules = new ArrayList<>();
        copyFromArr(rules, arr);
        Character[] arrL= {};
        List<Character> list = new ArrayList<>();
        copyFromArr(list, arrL);
        Character[] arrA= {};
        List<Character> actual = new ArrayList<>();
        copyFromArr(actual, arrA);
        Taboo<Character> classAnswer= new Taboo<>(rules);
        classAnswer.reduce(list);
        assertEquals(actual, list);
    }

    public void testReduce2(){
        //edge case
        Character[] arr= {'a', 'a'};
        List<Character> rules = new ArrayList<>();
        copyFromArr(rules, arr);
        Character[] arrL= {'a', 'a', 'a', 'a'};
        List<Character> list = new ArrayList<>();
        copyFromArr(list, arrL);
        Character[] arrA= {'a'};
        List<Character> actual = new ArrayList<>();
        copyFromArr(actual, arrA);
        Taboo<Character> classAnswer= new Taboo<>(rules);
        classAnswer.reduce(list);
        assertEquals(actual, list);
    }


    public void testReduceInt(){
        Integer[] arr= {1, 3, 1, 2};
        List<Integer> rules = new ArrayList<>();
        copyFromArr(rules, arr);
        Integer[] arrL= {1, 3, 2, 88, 3, 1};
        List<Integer> list = new ArrayList<>();
        copyFromArr(list, arrL);
        Integer[] arrA= {1, 88, 3};
        List<Integer> actual = new ArrayList<>();
        copyFromArr(actual, arrA);
        Taboo<Integer> classAnswer= new Taboo<>(rules);
        classAnswer.reduce(list);
        assertEquals(actual, list);

    }

    public void testReduceStr(){
        String[] arr= {"x", "z","x", "y"};
        List<String> rules = new ArrayList<>();
        copyFromArr(rules, arr);
        String[] arrL= {"x", "z", "y", "oop", "z", "x"};
        List<String> list = new ArrayList<>();
        copyFromArr(list, arrL);
        String[] arrA= {"x", "oop", "z"};
        List<String> actual = new ArrayList<>();
        copyFromArr(actual, arrA);
        Taboo<String> classAnswer= new Taboo<>(rules);
        classAnswer.reduce(list);
        assertEquals(actual, list);

    }


}

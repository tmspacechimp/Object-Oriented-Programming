import java.util.*;

public class Appearances {
	
	/**
	 * Returns the number of elements that appear the same number
	 * of times in both collections. Static method. (see handout).
	 * @return number of same-appearance elements
	 */


	private static <T> Map< T, Integer> table(Collection< T > a) {
		Map< T, Integer> table = new HashMap<>();
		Integer tmpInt;
		T tmpT;

		Iterator <T>it = a.iterator();
		while(it.hasNext()){
			tmpT=it.next();
			if (table.containsKey(tmpT))
			{
				tmpInt=table.get(tmpT) ;
				table.put(tmpT, tmpInt+1);
			}
			else table.put(tmpT, Integer.valueOf(1));
		}
		return table;
	}



	public static <T> int sameCount(Collection<T> a, Collection<T> b) {
		Map< T, Integer> tableA = table(a);
		Map< T, Integer> tableB = table(b);
		Iterator <T> it = table(a).keySet().iterator();
		T tmpT;
		int ans=0;

		while(it.hasNext()){
			tmpT= it.next();
			if(tableA.get(tmpT).equals(tableB.get(tmpT))) ans++;
		}

		return ans;
	}
}

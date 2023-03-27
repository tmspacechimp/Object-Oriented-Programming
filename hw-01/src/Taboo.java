
/*
 HW1 Taboo problem class.
 Taboo encapsulates some rules about what objects
 may not follow other objects.
 (See handout).
*/

import java.util.*;

public class Taboo<T> {
	private List<T> rules;
	private Map<T, Set<T> > table;
	/**
	 * Constructs a new Taboo using the given rules (see handout.)
	 * @param rules rules for new Taboo
	 */
	public Taboo(List<T> rules) {
		this.rules= rules;
		Map<T, Set<T> > table = new HashMap<>();
		T tmp;
		for(int i=0; i< rules.size()-1; i++){
			tmp = rules.get(i);
			if (table.containsKey(tmp)){
				table.get(tmp).add(rules.get(i+1));
			} else {
				Set<T> illegals= new HashSet<>() ;
				illegals.add(rules.get(i+1));
				table.put(tmp, illegals);

			}
		}
		this.table=table;
	}
	
	/**
	 * Returns the set of elements which should not follow
	 * the given element.
	 * @param elem
	 * @return elements which should not follow the given element
	 */
	public Set<T> noFollow(T elem) {
		Set<T> empty = new HashSet<T>();
		if (table.containsKey(elem)) return table.get(elem);
		return empty;



	}
	
	/**
	 * Removes elements from the given list that
	 * violate the rules (see handout).
	 * @param list collection to reduce
	 */
	public void reduce(List<T> list) {
		List<T> ans= new ArrayList<>();
		int j;
		for (int i=0; i< list.size();){

			j = 1;
			ans.add(list.get(i));
			while (true) {
				if (i + j < list.size()){
					if(table.containsKey(list.get(i))) {
						if (table.get(list.get(i)).contains(list.get(i + j))) {
							j++;
						} else {
							i += j;
							break;
						}
					} else{i+=j; break;}
				} else {i+=j; break;}
			}
		}

		list.clear();
		list.addAll(ans);

	}

}

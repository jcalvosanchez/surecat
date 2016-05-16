package com.htic.hticarq.core.util;

import java.util.ArrayList;
import java.util.List;

public class ListUtils {

	public static final int SORT_ASC = 0;
	public static final int SORT_DSC = 1;


	public static List<Integer> sortList (List<Integer> factors, int criteria) {
		ArrayList<Integer> result	= new ArrayList<Integer> ();
		ArrayList<Integer> temp		= (ArrayList<Integer>) factors;
		int candidateValue, candidateIndex;

		while (temp.size() > 0) {
			candidateValue	= 0;
			candidateIndex	= 0;

			for (int i=0;i<temp.size();i++) {
				if (temp.get(i).intValue() > candidateValue) {
					candidateIndex = i;
				}
			}

			result.add(temp.get(candidateIndex));
			temp.remove(candidateIndex);
		}

		if (criteria == SORT_ASC) {
			temp	= result;
			result	= new ArrayList<Integer> ();

			for (int i=temp.size()-1;i>=0;i--) {
				result.add(temp.get(i));
			}
		}

		return result;
	}

}
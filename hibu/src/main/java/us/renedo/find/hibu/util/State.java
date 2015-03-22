package us.renedo.find.hibu.util;

import java.util.ArrayList;
import java.util.List;

public class State {
	private static List<Integer> pagesWhitOutReponse = new ArrayList<Integer>();
	private static List<Integer> pagesNodata = new ArrayList<Integer>();

	public synchronized static void addPageWhitOutReponse(Integer page) {
		pagesWhitOutReponse.add(page);
	}

	public synchronized static void addPageWhitNoData(Integer page) {
		pagesNodata.add(page);
	}

	public synchronized static void removePageWhitNoData(Integer page) {
		pagesNodata.remove(page);
	}

	public static List<Integer> getPagesWhitOutReponse() {
		return pagesWhitOutReponse;
	}

	public static List<Integer> getPagesWhitNoData() {
		return pagesWhitOutReponse;
	}

	public static void resetPagesWhitNoData() {
		pagesWhitOutReponse = new ArrayList<Integer>();
	}
}

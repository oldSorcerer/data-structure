package structures;

import structures.List.IList;

public class Utils {

    public static <T> boolean compare(T element1, T element2, boolean back) {
        if (back)
            return element1 == null || ((Comparable<T>) element1).compareTo(element2) < 0;
        else
            return element1 != null && ((Comparable<T>) element1).compareTo(element2) > 0;
    }

    public static <T> T extremum (IList<T> list, boolean max) {

        if (list.getSizeList() == 0)
            return null;

        T tmp = list.get(0);
        for (T element: list) {
            if (Utils.compare(tmp, element, max))
                tmp = element;
        }
        return tmp;
    }
}

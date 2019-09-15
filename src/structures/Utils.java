package structures;

class Utils {

    public static <T> boolean compare(T element1, T element2, boolean back) {
        if (back)
            return element1 == null || ((Comparable<T>) element1).compareTo(element2) < 0;
        else
            return element1 != null && ((Comparable<T>) element1).compareTo(element2) > 0;
    }

}

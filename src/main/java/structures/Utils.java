package structures;

import structures.List.List;

public class Utils {

    //TODO: Что будет, если T будет не Comparable?
    public static <T> boolean compare(T element1, T element2, boolean back) {

        if (back) {
            return element1 == null || ((Comparable<T>) element1).compareTo(element2) < 0;
        } else {
            return element1 != null && ((Comparable<T>) element1).compareTo(element2) > 0;
        }
    }
    //TODO: Зачем тебе вообще поиск екстремума, если в списках он не нужен,
    // это математическая операция жестко завязанная
    // сравнивать объекты у тебя лист может содержать элементы не Компарабельные,
    // что тогда твой екстремум будет возвращать?
    public static <T> T extremum (List<T> list, boolean max) {

        if (list.size() == 0) {
            return null;
        }
        T tmp = list.get(0);
        for (T element: list) {
            if (Utils.compare(tmp, element, max)) {
                tmp = element;
            }
        }
        return tmp;
    }
}

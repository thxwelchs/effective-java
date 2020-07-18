package item31;

import java.util.*;

public class CollectionUtils {

    // 반환타입에는 한정적 와일드카드 타입을 사용하지 않는다. (사용하면 오히려 유연성을 떨어뜨린다.)
    public static <E> Set<E> union(Set<? extends E> s1, Set<? extends E> s2) {
        Set<E> result = new HashSet<>(s1);
        result.addAll(s2);
        return result;
    }

    public static <E extends Comparable<? super E>> Optional<E> max(Collection<? extends E> c) {
        if(c.isEmpty()) {
            return Optional.empty();
        }

        E result = null;
        for (E e : c)
            if(result == null || e.compareTo(result) > 0) result = Objects.requireNonNull(e);

        return Optional.of(result);
    }

//    // list의 두 인덱스의 값을 swap 한다.
    private static <E> void swapHelper(List<E> list, int i , int j) {
        list.set(i, list.set(j, list.get(i)));
    }

    public static void swap(List<?> list, int i, int j) {
        swapHelper(list, i, j);
//        list.set(i, list.set(j, list.get(i)));
    }
}

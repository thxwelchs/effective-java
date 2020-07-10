package item26;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Lab<E, T extends Object> {

    /**
     * 타입 용어 정리
     */
    String string; // 실제 타입 매개변수 actual type parameter
    E e; // 정규 타입 매개변수 formal type parameter
    List<String> list1; // 매개변수화된 타입 parameterized type
    List<E> list2; // 제네릭 타입 generic type
    List<?> list3; // 비한정적 와일드카드 타입 unbounded wildcard type
    List list4; // 로타입 raw type
    List<? extends Object> list5; // 한정적 와일드카드 타입
    //필드에 선언할 수 없어서 컴파일 에러 때문에 주석처리
    // List<T extends Comparable<T>> list6; // 재귀적 타입 한정
    // List<E extends Object> list7;  // 한정적 타입 매개변수

    // 제네릭은 실제타입이 아닌 정규타입으로 타입을 정의함으로써 코드의 유연함을 가짐과 동시에
    // 컴파일 레벨에서 타입에 대한 검증을 완전하게 할 수 있다.
    public static <E> List<E> arrayToList(E[] array) {
        // 다음과 같이 해도 되지만 예시를 위해서 반복문으로 진행
        // List<E> list = Arrays.asList(array);
        List<E> list = new ArrayList<>(array.length);
        for (E e : array) {
            list.add(e);
        }

        return list;
    }


    public static void main(String[] args) {
        List<Integer> integers = arrayToList(new Integer[]{1,2,3,4,5});
        List<Long> longs = arrayToList(new Long[]{1L,2L,3L,4L,5L});
        List<String> strings = arrayToList(new String[]{"a", "b", "c"});

    }

}

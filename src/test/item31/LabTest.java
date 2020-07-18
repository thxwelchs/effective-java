package item31;

import org.junit.jupiter.api.Test;
import org.omg.PortableServer.POAPackage.ObjectNotActive;

import java.util.*;
import java.util.concurrent.ScheduledFuture;

import static org.junit.jupiter.api.Assertions.*;

class LabTest {

    @Test
    public void 생산자테스트() {
        Stack<Number> stack = new Stack<>();
        List<Integer> list = Arrays.asList(1, 2, 3);

        stack.pushAll(list);

        for(int i = stack.size() - 1; !stack.isEmpty(); i--) {
            assertEquals(list.get(i), (Integer) stack.pop());
        }
    }

    @Test
    public void 소비자테스트() {
        Stack<Number> stack = new Stack<>();
        List<Integer> list = Arrays.asList(1, 2, 3);
        stack.pushAll(Arrays.asList(1, 2, 3));
        Set<Object> set = new LinkedHashSet<>();

        stack.popAll(set);

        assertTrue(stack.isEmpty());
        list.forEach( integer -> assertTrue(set.contains(integer)));
    }

    @Test
    public void 생산자테스트UNION() {
        Set<Integer> integers = new HashSet<>(Arrays.asList(1, 3, 5));
        Set<Double> doubles = new HashSet<>(Arrays.asList(2.0, 4.0, 6.0));
        Set<Number> numbers = CollectionUtils.union(integers, doubles);

        List<Number> list = Arrays.asList(1, 3, 5, 2.0, 4.0, 6.0);
        // java 8 이전에서는 리턴타입의 타입추론이 안되었기 때문에 다음과 같이 타입 명시 후 호출해야 했음음 ( 목표타입지도 명시해야 추론 할 수 있음 )
        // Set<Number> numbers = Union.<Number>union(integers, doubles);

        list.forEach( n -> assertTrue(numbers.contains(n)));
    }

    @Test
    public void 생산자소비자테스트MAX() {
        List<Integer> integers = Arrays.asList(1, 10, 3, 50, 4, 56, 33);
        List<Physicist> physicists =Arrays.asList(
                new Physicist("폰노이만", 25, 10),
                new Physicist("오일러", 21, 5),
                new Physicist("리처드파인만", 34, 15),
                new Physicist("마틴헬먼", 30, 3));
        List<ScheduledFuture<Integer>> asdf = new ArrayList<>();

        Integer result = CollectionUtils.max(integers).orElse(0);
        Person result2 = CollectionUtils.max(physicists).orElseThrow(IllegalAccessError::new);

        assertEquals(56, result);
        assertEquals("리처드파인만", result2.getName());
    }

    @Test
    public void 와일드카드헬퍼테스트() {
        List<Integer> integers = Arrays.asList(-1, 1, 10, 50, 100);

        CollectionUtils.swap(integers, 0, integers.size() - 1);

        assertEquals(100, integers.get(0));
        assertEquals(-1, integers.get(integers.size() - 1));
    }

}
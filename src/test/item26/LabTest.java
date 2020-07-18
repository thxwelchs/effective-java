package item26;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LabTest {

    @Test
    public void 로타입사용시예외발생테스트() {
        final Collection monies = new ArrayList();

        // unchecked call 경고
        monies.add(new Integer(5));
        monies.add(new BigDecimal("10"));
        
        Throwable exception = null;

        for (Object money : monies) {
            exception = assertThrows(ClassCastException.class, () -> {
                try {
                    BigDecimal m = (BigDecimal) money;
                } catch (ClassCastException e) {
                    throw e;
                }
            });
            if(exception != null) {
                break;
            }
        }

        assertNotNull(exception);
        assertEquals(exception.getClass().getName(), ClassCastException.class.getName());
    }

    @Test
    public void 제네릭메소드테스트() {
        List<Integer> integers = Lab.arrayToList(new Integer[]{1,2,3,4,5});
        List<Long> longs = Lab.arrayToList(new Long[]{1L,2L,3L,4L,5L});
        List<String> strings = Lab.arrayToList(new String[]{"a", "b", "c"});

        integers.forEach(i -> assertEquals(i.getClass(), Integer.class));
        longs.forEach(l -> assertEquals(l.getClass(), Long.class));
        strings.forEach(s -> assertEquals(s.getClass(), String.class));
    }

    @Test
    public void 비한정적와일드카드테스트() {
        List<?> list = new ArrayList<>();
        list.add(null);
        boolean a = list instanceof List;

    }

}
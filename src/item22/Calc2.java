package item22;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Calc2 extends Calc {
    // 여기서는 굳이 FunnyConstants 에 정의되어 있는 상수가 필요 없는데.. 오염되버림
    @Test
    public void 더하기계산테스트() {
        int result = justSomethingPlus(1, 1);
        assertNotEquals(result, 2);
    }

    @Override
    public int justSomethingPlus(int a, int b) {
        System.out.println(FI); // 이런거 여기선 필요 없는데..
        System.out.println(E); // 이런거 여기선 필요 없는데..
        System.out.println(GOLDEN_RATIO); // 이런거 여기선 필요 없는데..
        // 무언가 계산..

        return ++a + ++b;
    }
}

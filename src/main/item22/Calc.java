package item22;

import java.util.Collections;
import java.util.concurrent.Callable;

/**
 * 상수 인터페이스 구현 예제
 */
public class Calc implements FunnyConstants, Callable<Double> {
    public int justSomethingPlus(int a, int b) {
        return a + b;
    }

    @Override
    public Double call() throws Exception {
        // 상수 인터페이스의 클래스 명을 네임스페이스로 붙이지 않고 바로 사용할 수 있다. (인터페이스 구현에 대한 이해가 있다면 당연한 얘기인듯?)
        //double radian = 180 / FunnyConstants.FI;
        double radian = 180 / FI;
        // 57.2957.....

        return radian;
    }
}

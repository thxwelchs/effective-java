# 인터페이스는 타입을 정의하는 용도로만 사용하라

Use interfaces only to define types

`interface라는 키워드의 근본적인 존재 이유에 대해 고찰해보자.`

---

## 인터페이스를 타입을 정의하는 용도로 사용하지 않는 케이스

### **상수 인터페이스**

상수 인터페이스? Method가 없이 static final field로만 구성되어 있는 인터페이스

상수 인터페이스 안티패턴? 상수를 사용하기 위해 클래스 내부에 상수 필드를 선언하지 않고 상수 인터페이스를 구현하는 것

객체지향 개발을 할 때 일종의 패턴으로 상수 인터페이스 안티패턴을 사용하는데, 이렇게 사용하지 말  

재밌는 수학 상수들로 이루어진 상수 인터페이스

```java
interface FunnyConstants {
    // 파이
    static final double FI = 3.141592653589793;
    // 자연상수 e
    static final double E = 2.718281828459045;
    // 황금비율
    static final double GOLDEN_RATIO = 1.618033988749894;
}
```

이러한 상수 인터페이스 안티패턴을 사용하면 편리한점?

- 인터페이스이기 때문에, 클래스에서 확장의 용도로 사용하기 쉽다.
- 인터페이스의 클래스 명을 네임스페이스로 붙이지 않고 바로 사용할 수 있다.

    ```java
    /**
     * 상수 인터페이스 구현 예제
     */
    public class Calc implements FunnyConstants, Callable<Double> {

    		public static int justSomethingPlus(int a, int b) {
    				return a + b
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
    ```

얼핏 보면 위와 같은 편리함 때문에 사용성이 좋아보이지만, 현대의 객체지향 개발에서는 이와 같은 **상수 인터페이스를 구현하는 것은 지양**하는 것을 권장한다. ( 그래서 안티패턴 )

**상수 인터페이스는 왜 안티패턴인가?**

- 상수 인터페이스를 구현하면, 어느 클래스일지는 모르겠지만 구현받아 사용되는 상수들의 값을 API로 노출하는 행위이다.
- 사실 클래스 입장에서는 어떤 상수 인터페이스를 사용하든 큰 의미가 없고, 사용자에게는 혼란을 주는 행위이기도 하다.
- 바이너리호환성을 위해 여전히 상수 인터페이스를 구현하고 있어야 함을 인지하고 있어야 한다.
바이너리호환성? 클래스를 변경할 때 클래스를 사용하는 클래스를 다시 컴파일 할 필요가 없어도 되는 것`*(쉽게 말해, 어떤 라이브러리를 사용하는 코드가 있었고, 어떤 특정 시점이후에 라이브러리 새버전이 릴리즈되어 코드변경이 일어났으면 바이너리호환이 되지 않아 컴파일을 다시해야 할 것임)*`
- final이 아닌 클래스가 상수 인터페이스를 구현한다면 모든 하위 클래스의 필드공간이 의미없이 상수 인터페이스의 상수들로 오염되어 버린다.

    ```java
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
    ```

- java.io.ObjectStreamConstatns 등, 자바 플랫폼 라이브러리에도 상수 인터페이스가 몇개 있으나, 대부분의 것들이 인터페이스를 잘못 활용한 예이므로 따라 해서는 안된다.

![Untitled%20c280783b77cd4c888d46a64dac9ae60d/Untitled.png](Untitled%20c280783b77cd4c888d46a64dac9ae60d/Untitled.png)

**상수를 공개해야 할 필요가 있다면, 다음과 같이 하는것이 좋다.**

- 특정 클래스나 인터페이스와 강하게 연관된 상수라면 그 클래스나 인터페이스 자체에 추가
(`Integer.MIN_VALUE, Integer.MAX_VALUE, Math.PI, Math.E` 라던지..)
- Enum으로 선언하여 사용(Item34)
- UtilityClass를 선언(Item4)하여 인스턴스화 할 수 없게 만들고, 그 안에 상수를 담아 공개

    ```java
    /**
     * 인스턴스화가 방지된 유틸리티 클래스
     */
    public class FunnyConstantsUtility {

        private FunnyConstantsUtility() { }

        // 파이
        static final double FI = 3.141_592_653_589_793;  // 값의 가독성을 위해 _를 활용 (java 7 이상)
        // 자연상수 e
        static final double E = 2.718_281_828_459_045;  // 값의 가독성을 위해 _를 활용 (java 7 이상)
        // 황금비율
        static final double GOLDEN_RATIO = 1.618_033_988_749_894;  // 값의 가독성을 위해 _를 활용 (java 7 이상)

        static final double SOMETHING_CONST = 4.923_123_455e-21; // 부동소수점도 사용 가능
    }
    ```

### 정적임포트

유틸리티 클래스의 상수를 빈번히 사용해야 한다면 정적임포트를 활용하면 클래스 이름을 생략하고 사용이 가능하다. 

```java
import org.junit.jupiter.api.Test;
import static item22.FunnyConstantsUtility.*; // 유틸리티 클래스의 상수를 활용하기 위한 정적임포트

public class StaticImportTest {
    @Test
    public void staticImportTest() {
        System.out.println(FI);
        System.out.println(E);
        System.out.println(GOLDEN_RATIO);
        System.out.println(SOMETHING_CONST);
    }
}
```
---
# 결론

인터페이스는 명확히 타입을 정의하는 용도로만 사용하자, 상수용으로 인터페이스를 사용하지 말자

---

# 참고

> [https://dev.to/ashkanent/effective-java-chapters-2-to-5-822](https://dev.to/ashkanent/effective-java-chapters-2-to-5-822)
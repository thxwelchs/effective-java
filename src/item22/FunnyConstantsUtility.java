package item22;

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

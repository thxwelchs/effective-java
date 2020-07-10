package item17;

/**
 * 1. 객체의 상태를 변경하는 메소드 X (setter를 만들지 말자.)
 * 2. 확장할 수 없는 클래스로 만들기 (final 선언)
 * 3. 모든 필드를 final로 선언
 * 4. 모든 필드를 private으로 선언
 * 5. 자신 외에는 내부의 가변 컴포넌트에 접근하지 못하도록 한다.
 */
public final class ComplexNumber {

    private final double re;
    private final double im;

    public static final ComplexNumber ZERO = new ComplexNumber(0, 0);
    public static final ComplexNumber ONE = new ComplexNumber(1, 0);
    public static final ComplexNumber I = new ComplexNumber(0, 1);

    // 생성자는 불변식 설정이 모두 완료된, 초기화가 완벽히 끝난 상태의 객체를 생성해야 한다.
//    public ComplexNumber(double re, double im) {
//        this.re = re;
//        this.im = im;
//    }

//     생성자 대신 정적팩토리 메소드로 제공할 수도 있다.
     private ComplexNumber(double re, double im) {
        this.re = re;
        this.im = im;
     }

     public static ComplexNumber valueOf(double re, double im) {
        return new ComplexNumber(re, im);
     }

    // Accessor: 멤버 변수를 수정하지 않고 단순히 읽거나 출력하는 함수
    // Mutator: 멤버 변수의 값을 수정하는 함수
    // getter가 있다고하여, setter를 무조건 만들지 않는다.
    // Accessors with no corresponding mutators
    public double getRealPart()      { return re; }
    public double getImaginaryPart() { return im; }

    // 메소드 명명을 add와 같은 동사 대신 plus와 같은 전치사로 사용했다.
    public ComplexNumber plus(ComplexNumber c) {
        return new ComplexNumber(re + c.re, im + c.im);
    }

    public ComplexNumber minus(ComplexNumber c) {
        return new ComplexNumber(re - c.re, im - c.im);
    }

    public ComplexNumber times(ComplexNumber c) {
        return new ComplexNumber(re * c.re - im * c.im, re * c.im + im * c.re);
    }

    public ComplexNumber divideBy(ComplexNumber c) {
        double tmp = c.re * c.re + c.im * c.im;
        return new ComplexNumber((re * c.re + im * c.im) / tmp, (im * c.re - re * c.im) / tmp);
    }

    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ComplexNumber))
            return false;
        ComplexNumber c = (ComplexNumber) o;
        // See page 43 to find out why we use compare instead of == return Double.compare(re, c.re) == 0 &&
        return Double.compare(im, c.im) == 0;
    }

    public int hashCode() {
        int result = 17 + hashDouble(re);
        result = 31 * result + hashDouble(im);
        return result;
    }

    private int hashDouble(double val) {
        long longBits = Double.doubleToLongBits(re);
        return (int) (longBits ^ (longBits >>> 32));
    }

    public String toString() {
        return "(" + this.re + " + " + this.im + "i)";
    }
}
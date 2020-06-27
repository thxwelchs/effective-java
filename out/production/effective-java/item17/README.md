# ITEM17 변경 가능성을 최소화하기 (Minimize Mutable)

1. 객체의 상태를 변경하는 메소드 X (setter를 만들지 말자.)
2. 확장할 수 없는 클래스로 만들기 (final 선언)
3. 모든 필드를 final로 선언
4. 모든 필드를 private으로 선언
5. 자신 외에는 내부의 가변 컴포넌트에 접근하지 못하도록 한다.
6. 확실한 이유가 없다면 생성자와 정적 팩터리 외에는 그 어떤 초기화 메서드도 public으로 제공 X

## 그래서 왜 불변이어야 하는가?
- 가변하지 않으므로 단순하다.
- 불변객체는 멀티쓰레드 환경에서 안전하다. (상태가 변경되더라도 새로운 객체이기 때문에 동기화 할 필요가 없다.)
- 불변객체의 내부 데이터를 안심하고 공유할 수 있다. (다른 요인에 의해 변경될리가 없기 떼문에)
- 실패 원자성을 보장한다. (실패 원자성: 예외가 발생한 후에도 여전히 유효한 상태)
- 객체가 가질 수 있는 상태의 수가 줄어들고, 그 객체를 예측하기 쉬워지며 오류가 생길 가능성이 줄어든다.

## 안좋은 점은?
- 객체의 상태를 변경하는데에 많은 비용이 소모된다. (하나의 값만 수정하려고 하더라도, 전체가 수정되어야 하기 때문에)
- 상태변경이 잦다면, 힙영역에 가비지가 많이 생긴다. (문자열 연산에 String을 쓰지 말라는 이유와 같음)

## 결론
- 비즈니스 도메인 해석에 따라 불변해야만 하는 상황에 잘 사용하자.
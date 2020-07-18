package item31;


import java.util.Collection;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Vector;

public class Stack<E> extends Vector<E> {

    public Stack() { }

    public void push(E e) {
        addElement(e);
    }

    public E pop() {
        int len = size();

        if(len == 0) throw new EmptyStackException();

        E element = get(--len);

        removeElementAt(len);

        return element;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    // 메소드의 매개변수화 타입 E와 클래스의 매개변수화 타입 E가 일치할 시에는 문제없이 작동한다.
    // 그런데 만약 메소드의 E(Integer)와 클래스의 E(Number)가 상속관계로 호출된다면? incompatible(공존할 수 없는) 컴파일 에러
    // public void pushAll(Iterable<E> src) {
    public void pushAll(Iterable<? extends E> source) {
        for (E e: source) {
            push(e);
        }
    }

    public void popAll(Collection<? super E> destination) {
        while (!isEmpty()) destination.add(pop());
    }
}

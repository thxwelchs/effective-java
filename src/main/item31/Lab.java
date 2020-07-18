package item31;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lab {
    public static void main(String[] args) {
        Stack<Number> stack = new Stack<>();

        stack.push(3);
        stack.push(5);
        stack.push(6);

        stack.pop();
        stack.pop();
        stack.pop();

        List<Integer> list = Arrays.asList(1, 2, 4);

        stack.pushAll(list);

    }
}

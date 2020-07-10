package item22;

import org.junit.jupiter.api.Test;

import static item22.FunnyConstantsUtility.*;

public class StaticImportTest {
    @Test
    public void staticImportTest() {
        System.out.println(FI);
        System.out.println(E);
        System.out.println(GOLDEN_RATIO);
        System.out.println(SOMETHING_CONST);
    }
}

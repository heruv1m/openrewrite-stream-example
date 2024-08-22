import org.junit.jupiter.api.Test;
import static org.openrewrite.java.Assertions.java;
import org.openrewrite.java.search.FindMissingTypes;
import org.openrewrite.test.RecipeSpec;
import org.openrewrite.test.RewriteTest;

public class TestNestedStream implements RewriteTest {

  @Override
  public void defaults(RecipeSpec spec) {
    spec.recipe(new FindMissingTypes());
  }

  @Test
  public void test() {
//language=java
    rewriteRun(
        java("""
                        class A{
            List<List<String>> list = List.of(List.of());
            }
                        """)
    );
  }
}

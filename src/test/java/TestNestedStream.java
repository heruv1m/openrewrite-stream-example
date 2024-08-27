import java.util.List;
import org.junit.jupiter.api.Test;
import static org.openrewrite.java.Assertions.java;
import org.openrewrite.java.search.FindMissingTypes;
import org.openrewrite.test.RecipeSpec;
import org.openrewrite.test.RewriteTest;

public class TestNestedStream implements RewriteTest {

  @Override
  public void defaults(RecipeSpec spec) {
    spec.recipe(new FormatStreamApiCalls());
  }

  public static void main(String[] args) {
    List<List<String>> list = List.of(List.of());
  }

  @Test
  public void test() {
//language=java
    rewriteRun(
        recipeSpec -> recipeSpec.recipe(new FormatStreamApiCalls()),
        java("""
              import java.util.ArrayList;
              import java.util.Collection;
              import java.util.List;
              import java.util.Optional;
              import java.util.Set;
              import java.util.stream.Collectors;
              import java.util.stream.Stream;

              public class WrapNewLinesMethodChainCallCheckTestInput {
                public static void main(String[] args) {
                   
                  //expression - NOT OK
                  List.of(1, 2, 3)
                      .stream().map(integer -> integer * integer).filter(integer -> integer % 2 == 0).map(String::valueOf).findAny()
                      .stream().collect(Collectors.toSet());
                  //expression - OK
                }
              }
                                    """)
    );
  }
}

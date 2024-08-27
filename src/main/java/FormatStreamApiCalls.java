import org.openrewrite.ExecutionContext;
import org.openrewrite.NlsRewrite;
import org.openrewrite.Recipe;
import org.openrewrite.java.JavaIsoVisitor;
import org.openrewrite.java.tree.J;
import org.openrewrite.java.tree.Space;

public class FormatStreamApiCalls extends Recipe {

  @Override
  public String getDisplayName() {
    return "Форматировать Stream API вызовы";
  }

  @Override
  public @NlsRewrite.Description String getDescription() {
    return "kjhg.";
  }

  @Override
  public org.openrewrite.TreeVisitor<?, ExecutionContext> getVisitor() {
    return new FormatStreamVisitor();
  }

  private static class FormatStreamVisitor extends JavaIsoVisitor<ExecutionContext> {

    @Override
    public J.MethodInvocation visitMethodInvocation(J.MethodInvocation methodInvocation, ExecutionContext p) {
      J.MethodInvocation m = super.visitMethodInvocation(methodInvocation, p);

      if (m.getSimpleName().equals("filter") ||
          m.getSimpleName().equals("map") ||
          m.getSimpleName().equals("stream") ||
          m.getSimpleName().equals("flatMap") ||
          m.getSimpleName().equals("forEach") ||
          m.getSimpleName().equals("of") ||
          m.getSimpleName().equals("collect")) {

        m = m.withPrefix(Space.format("\n "));

      }
      return m;
    }
  }
}

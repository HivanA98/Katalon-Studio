import org.codehaus.groovy.control.CompilationFailedException;
import org.codehaus.groovy.control.CompilationUnit;
import org.codehaus.groovy.control.CompilerConfiguration;
import org.codehaus.groovy.control.Phases;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Parses every *.groovy file under the given root up to the CONVERSION phase.
 *
 * This catches syntax errors (unbalanced braces, bad string literals, typos in keywords)
 * without needing the Katalon runtime on the classpath, so it can run on any CI agent.
 *
 * Usage: java -cp groovy-4.x.jar tools/GroovySyntaxCheck.java [root]
 */
public class GroovySyntaxCheck {

    public static void main(String[] args) throws Exception {
        Path root = Paths.get(args.length > 0 ? args[0] : ".").toAbsolutePath().normalize();
        List<Path> files;
        try (Stream<Path> walk = Files.walk(root)) {
            files = walk
                    .filter(p -> p.toString().endsWith(".groovy"))
                    .filter(p -> !isIgnored(root.relativize(p)))
                    .sorted()
                    .collect(Collectors.toList());
        }

        int failures = 0;
        for (Path file : files) {
            CompilationUnit unit = new CompilationUnit(new CompilerConfiguration());
            unit.addSource(file.toFile());
            try {
                unit.compile(Phases.CONVERSION);
            } catch (CompilationFailedException e) {
                failures++;
                String rel = root.relativize(file).toString().replace(File.separatorChar, '/');
                System.out.println("::error file=" + rel + "::Groovy syntax error");
                System.out.println(e.getMessage());
            }
        }

        System.out.printf("Parsed %d Groovy file(s), %d with syntax errors.%n", files.size(), failures);
        System.exit(failures == 0 ? 0 : 1);
    }

    private static boolean isIgnored(Path rel) {
        for (Path part : rel) {
            String name = part.toString();
            if (name.equals(".git") || name.equals("bin") || name.equals("Libs") || name.equals("Reports")
                    || name.equals(".gradle") || name.equals("build")) {
                return true;
            }
        }
        return false;
    }
}

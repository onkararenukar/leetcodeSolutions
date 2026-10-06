# Java 17 Specific Guide

## 🎯 Java 17 Features in This Learning Map

This guide explains the Java 17 specific features used throughout the DSA and System Design learning map and how they simplify coding for interview preparation.

## 🚀 Why Java 17?

Java 17 (LTS - Long Term Support) includes modern language features that make code more concise, readable, and maintainable - perfect for interview coding where clarity and speed are essential.

## 📦 Key Java 17 Features Used

### 1. Records (JEP 395)

**What it is:** Immutable data classes that automatically generate constructors, getters, equals(), hashCode(), and toString().

**Traditional Class:**
```java
public class Person {
    private final String name;
    private final int age;
    
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    public String getName() { return name; }
    public int getAge() { return age; }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age && Objects.equals(name, person.name);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
    
    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + "}";
    }
}
```

**Java 17 Record:**
```java
public record Person(String name, int age) {}
```

**Benefits:**
- Reduces boilerplate from ~40 lines to 1 line
- Immutable by default (thread-safe)
- Perfect for DTOs, value objects, and data structures

**Use in This Course:**
- Tree nodes: `record TreeNode(int val, TreeNode left, TreeNode right) {}`
- Graph nodes: `record GraphNode(int id, List<Integer> neighbors) {}`
- API responses: `record UserResponse(int id, String name, String email) {}`

### 2. Pattern Matching for instanceof (JEP 394)

**What it is:** Enhanced instanceof that includes pattern matching and automatic casting.

**Traditional instanceof:**
```java
if (obj instanceof String) {
    String str = (String) obj;  // Explicit cast needed
    System.out.println(str.length());
}
```

**Java 17 Pattern Matching:**
```java
if (obj instanceof String str) {  // Pattern variable automatically cast
    System.out.println(str.length());
}
```

**Benefits:**
- Eliminates explicit casting
- Reduces code by 50% for type checks
- More readable and less error-prone

**Use in This Course:**
- Type checking in tree traversals
- Graph node type checking
- Visitor pattern implementations

### 3. Text Blocks (JEP 378)

**What it is:** Multi-line string literals that preserve formatting.

**Traditional String Concatenation:**
```java
String sql = "SELECT * FROM users " +
             "WHERE name = '" + name + "' " +
             "AND age > " + age;
```

**Java 17 Text Blocks:**
```java
String sql = """
    SELECT * FROM users
    WHERE name = '%s'
    AND age > %d
    """.formatted(name, age);
```

**Benefits:**
- More readable SQL, JSON, XML
- No escape characters needed
- Better formatting preservation

**Use in This Course:**
- SQL queries in database examples
- JSON examples in API design
- Complex string formatting

### 4. Sealed Classes (JEP 409)

**What it is:** Restricted class hierarchies for better modeling and pattern matching.

**Traditional:**
```java
public abstract class Shape {}
public class Circle extends Shape {}
public class Square extends Shape {}
// Anyone can extend Shape
```

**Java 17 Sealed Classes:**
```java
public sealed class Shape permits Circle, Square {}
public final class Circle extends Shape {}
public final class Square extends Shape {}
```

**Benefits:**
- Better control over inheritance
- Enables exhaustive pattern matching
- Clearer API contracts

**Use in This Course:**
- Defining fixed data structure types
- API response types
- State machine modeling

### 5. Enhanced Stream API

**What it is:** New convenience methods in Stream API.

**Traditional Stream:**
```java
List<String> list = stream.collect(Collectors.toList());
int[] array = stream.toArray(size -> new int[size]);
```

**Java 17 Stream:**
```java
List<String> list = stream.toList();
int[] array = stream.toArray(int[]::new);
```

**Benefits:**
- More concise stream operations
- No need for Collectors utility class
- Better type inference

**Use in This Course:**
- Array conversions
- List operations
- Data transformations

### 6. Local Variable Type Inference (var)

**What it is:** Compiler infers variable types from initializer.

**Traditional:**
```java
Map<String, List<Integer>> graph = new HashMap<>();
List<TreeNode> nodes = new ArrayList<>();
```

**Java 17 var:**
```java
var graph = new HashMap<String, List<Integer>>();
var nodes = new ArrayList<TreeNode>();
```

**Benefits:**
- Reduces code duplication
- More readable complex type declarations
- Still type-safe

**Use in This Course:**
- Complex generic types
- Local variables in algorithms
- Loop variables

## 🎯 Interview Benefits

### Code Writing Speed
- **Records**: Write data classes in 1 line instead of 40
- **Pattern Matching**: Reduce type checking code by 50%
- **Stream API**: Convert collections 2x faster

### Code Readability
- **Text Blocks**: SQL and JSON 3x more readable
- **Records**: Data structures immediately understandable
- **Sealed Classes**: Clear inheritance boundaries

### Error Reduction
- **Records**: No manual equals/hashCode bugs
- **Pattern Matching**: No ClassCastException
- **Immutable Records**: Thread-safe by default

## 📊 Java 17 vs Traditional Java in Interviews

| Task | Traditional Java | Java 17 | Time Saved |
|------|-----------------|---------|------------|
| Create DTO | 40 lines | 1 line | 90% |
| Type check + cast | 3 lines | 1 line | 67% |
| Multi-line string | 5+ lines | 3 lines | 40% |
| Stream to list | 1 line | 1 line | Same but cleaner |
| Variable declaration | Type repetition | var | 30% |

## 🔧 Setting Up Java 17

### Prerequisites
1. **JDK 17**: Download from Oracle or OpenJDK
2. **IDE Support**: IntelliJ IDEA 2023+, VS Code with Java Extension Pack
3. **Build Tool**: Maven 3.8+ or Gradle 7.3+

### IDE Configuration

**IntelliJ IDEA:**
1. File → Project Structure → Project SDK → Select JDK 17
2. File → Project Structure → Modules → Language Level → 17

**VS Code:**
1. Install Java Extension Pack
2. Set `java.jdt.ls.java.home` to JDK 17 path
3. Set `java.configuration.runtimes` to include JDK 17

### Maven Configuration
```xml
<properties>
    <maven.compiler.source>17</maven.compiler.source>
    <maven.compiler.target>17</maven.compiler.target>
</properties>
```

### Gradle Configuration
```groovy
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
```

## 🎓 Common Patterns in This Course

### Record for Data Structures
```java
// Binary Tree Node
public record TreeNode(int val, TreeNode left, TreeNode right) {
    public TreeNode(int val) {
        this(val, null, null);
    }
}

// Graph Node
public record GraphNode(int id, List<Integer> neighbors) {}

// LeetCode Problem Input
public record ProblemInput(int[] nums, int target) {}
```

### Pattern Matching in Traversals
```java
public void traverse(Object node) {
    if (node instanceof TreeNode tn) {
        // tn is automatically cast to TreeNode
        System.out.println(tn.val());
        traverse(tn.left());
    } else if (node instanceof GraphNode gn) {
        // gn is automatically cast to GraphNode
        System.out.println(gn.id());
    }
}
```

### Text Blocks for Examples
```java
public String getExampleQuery() {
    return """
        SELECT * FROM users
        WHERE age > %d
        ORDER BY name
        LIMIT 10
        """.formatted(minAge);
}
```

### Stream API Enhancements
```java
// Convert stream to list directly
List<Integer> primes = IntStream.rangeClosed(2, 100)
    .filter(this::isPrime)
    .boxed()
    .toList();

// Convert stream to array directly
int[] squares = IntStream.rangeClosed(1, 10)
    .map(i -> i * i)
    .toArray();
```

## 🚨 Common Mistakes to Avoid

### Mistake 1: Using var Everywhere
```java
// ❌ Too much var loses type information
var data = processData();
var result = transform(data);

// ✅ Use var when type is obvious
var nodes = new ArrayList<TreeNode>();  // Clear from RHS
var userMap = new HashMap<String, User>();  // Clear from RHS
```

### Mistake 2: Records for Mutable Data
```java
// ❌ Records are immutable - this won't compile
public record MutablePerson(String name) {
    public void setName(String name) {
        this.name = name;  // Compilation error
    }
}

// ✅ Use class for mutable data
public class MutablePerson {
    private String name;
    // setters...
}
```

### Mistake 3: Ignoring Sealed Class Benefits
```java
// ❌ Not using permits - defeats the purpose
public sealed class Shape {}

// ✌ Properly using sealed classes
public sealed class Shape permits Circle, Square, Triangle {}
```

## 🎯 Interview Strategy with Java 17

### 1. Use Records for Data Structures
```java
// Quick to define, easy to understand
public record Point(int x, int y) {}
public record Interval(int start, int end) {}
```

### 2. Pattern Matching for Clean Type Checks
```java
// Clean and efficient
if (node instanceof TreeNode tn && tn.val() == target) {
    return tn;
}
```

### 3. Stream API for Transformations
```java
// Concise and readable
List<Integer> results = Arrays.stream(nums)
    .filter(n -> n > 0)
    .map(n -> n * 2)
    .toList();
```

### 4. Text Blocks for SQL/JSON
```java
// Clear and maintainable
String json = """
    {
        "name": "%s",
        "age": %d
    }
    """.formatted(name, age);
```

## 📚 Additional Resources

### Java 17 Documentation
- [Java 17 Release Notes](https://www.oracle.com/java/technologies/javase/17-relnotes.html)
- [JEP Index](https://openjdk.org/jeps/0)
- [Java Language Specification](https://docs.oracle.com/javase/specs/jls/se17/html/index.html)

### Practice Resources
- [Java 17 Features Tutorial](https://www.baeldung.com/java-17)
- [Records Deep Dive](https://www.baeldung.com/java-record-keyword)
- [Pattern Matching Guide](https://www.baeldung.com/java-pattern-matching-instanceof)

## 💡 Tips for Learning Java 17

1. **Start with Records**: They're the biggest time-saver
2. **Practice Pattern Matching**: It becomes natural quickly
3. **Use Text Blocks**: For any multi-line strings
4. **Leverage Stream API**: The new methods are cleaner
5. **Understand Sealed Classes**: They're powerful for API design

## 🎓 Migration from Earlier Java Versions

If you're coming from Java 8 or 11:

### What's New vs Java 8
- **Java 8**: Lambdas, Streams, Optional
- **Java 17**: Records, Pattern Matching, Text Blocks, Sealed Classes

### What's New vs Java 11
- **Java 11**: Local variable type inference (var)
- **Java 17**: Enhanced var, Records, Pattern Matching, Text Blocks

### Quick Migration Tips
1. Replace simple classes with records
2. Use pattern matching instead of instanceof + cast
3. Use text blocks for multi-line strings
4. Use enhanced Stream API methods
5. Consider sealed classes for hierarchies

## 🚀 Conclusion

Java 17 features are designed to make code cleaner, safer, and more maintainable. In interview settings, they help you:
- Write code faster
- Make fewer mistakes
- Produce more readable solutions
- Focus on algorithm logic rather than boilerplate

Master these features as you work through this learning map, and you'll have a significant advantage in coding interviews!

---

**Next**: Apply these Java 17 features as you work through the learning map files!

# quarkus--provider-quarkus-not-installed

reproduces bug in continuous testing with @Nested classes.

While one @Nested works beautifully @Nested-@Nested fails with
```
2024-06-14 16:18:07,996 ERROR [io.qua.test] (Test runner thread) Test itsANiceWayToStructureYourTests#testHelloEndpoint() failed 
: java.lang.RuntimeException: java.nio.file.FileSystemNotFoundException: Provider "quarkus" not installed
        at io.quarkus.test.junit.QuarkusTestExtension.throwBootFailureException(QuarkusTestExtension.java:642)
        at io.quarkus.test.junit.QuarkusTestExtension.interceptTestClassConstructor(QuarkusTestExtension.java:726)
        at java.base/java.util.Optional.orElseGet(Optional.java:364)
        at java.base/java.util.Optional.orElseGet(Optional.java:364)
        at java.base/java.util.Optional.orElseGet(Optional.java:364)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
Caused by: java.nio.file.FileSystemNotFoundException: Provider "quarkus" not installed
        at java.base/java.nio.file.Path.of(Path.java:213)
        at java.base/java.nio.file.Paths.get(Paths.java:98)
        at io.quarkus.runtime.util.ClassPathUtils.toLocalPath(ClassPathUtils.java:224)
        at io.quarkus.test.common.PathTestHelper.toPath(PathTestHelper.java:285)
        at io.quarkus.test.common.PathTestHelper.getTestClassesLocation(PathTestHelper.java:148)
        at io.quarkus.test.junit.AbstractJvmQuarkusTestExtension.createAugmentor(AbstractJvmQuarkusTestExtension.java:130)
        at io.quarkus.test.junit.QuarkusTestExtension.doJavaStart(QuarkusTestExtension.java:218)
        at io.quarkus.test.junit.QuarkusTestExtension.ensureStarted(QuarkusTestExtension.java:609)
        at io.quarkus.test.junit.QuarkusTestExtension.beforeAll(QuarkusTestExtension.java:659)
        ... 1 more
```

## reproduce it

```
git clone https://github.com/flyinfish/quarkus--provider-quarkus-not-installed.git
cd quarkus--provider-quarkus-not-installed
./mvnw quarkus:test
```

test is always the same - just that [GreetingResourceNestedNestedTest.java](src%2Ftest%2Fjava%2Forg%2Facme%2FGreetingResourceNestedNestedTest.java) fails while [GreetingResourceNestedTest.java](src%2Ftest%2Fjava%2Forg%2Facme%2FGreetingResourceNestedTest.java)
and [GreetingResourceTest.java](src%2Ftest%2Fjava%2Forg%2Facme%2FGreetingResourceTest.java) succeeds.

it is a problem of continous testing, works with `./mvnw test`. 

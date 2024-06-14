# quarkus--provider-quarkus-not-installed

reproduces bug in continuous testing with @Nested classes.

While one @Nested works beautifully @Nested-@Nested fails with
```
2024-06-14 16:00:30,873 ERROR [io.qua.test] (Test runner thread) Test CreateAnliegenfall failed 
 [Error Occurred After Shutdown]: java.lang.RuntimeException: java.nio.file.FileSystemNotFoundException: Provider "quarkus" not installed
        at io.quarkus.test.junit.QuarkusTestExtension.throwBootFailureException(QuarkusTestExtension.java:643)
        at io.quarkus.test.junit.QuarkusTestExtension.interceptBeforeAllMethod(QuarkusTestExtension.java:711)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
Caused by: java.nio.file.FileSystemNotFoundException: Provider "quarkus" not installed
        at java.base/java.nio.file.Path.of(Path.java:213)
        at java.base/java.nio.file.Paths.get(Paths.java:98)
        at io.quarkus.runtime.util.ClassPathUtils.toLocalPath(ClassPathUtils.java:224)
        at io.quarkus.test.common.PathTestHelper.toPath(PathTestHelper.java:283)
        at io.quarkus.test.common.PathTestHelper.getTestClassesLocation(PathTestHelper.java:146)
        at io.quarkus.test.junit.AbstractJvmQuarkusTestExtension.createAugmentor(AbstractJvmQuarkusTestExtension.java:129)
        at io.quarkus.test.junit.QuarkusTestExtension.doJavaStart(QuarkusTestExtension.java:219)
        at io.quarkus.test.junit.QuarkusTestExtension.ensureStarted(QuarkusTestExtension.java:610)
        at io.quarkus.test.junit.QuarkusTestExtension.beforeAll(QuarkusTestExtension.java:660)
        ... 1 more
```

## reproduce it

```
git clone https://github.com/flyinfish/quarkus--provider-quarkus-not-installed.git
cd quarkus--provider-quarkus-not-installed
./mvnw quarkus:test
```

is problem of continous testing works with `./mvnw test`. 

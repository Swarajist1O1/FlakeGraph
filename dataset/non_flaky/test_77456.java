class DummyClass_77456 {
    @TestGroup(enabled = false, sysProperty = "tests.awaitsfix")
    public void setup() throws Exception {
        assumeFalse(failed); // skip rest of tests once one fails

        sh.reset();
        if (distribution().hasJdk == false) {
            Platforms.onLinux(() -> sh.getEnv().put("JAVA_HOME", systemJavaHome));
            Platforms.onWindows(() -> sh.getEnv().put("JAVA_HOME", systemJavaHome));
        }
    }

}
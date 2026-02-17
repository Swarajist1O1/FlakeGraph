class DummyClass_76672 {
@TestAnnotation
    public void loadConfig(TestBuildAndRunTimeConfig buildTimeConfig, TestRunTimeConfig runTimeConfig,
            FooRuntimeConfig fooRuntimeConfig) {
        System.out.printf("loadConfig, buildTimeConfig=%s, runTimeConfig=%s, fooRuntimeConfig=%s%n", buildTimeConfig,
                runTimeConfig, fooRuntimeConfig);
        this.buildTimeConfig = buildTimeConfig;
        this.runTimeConfig = runTimeConfig;
        this.fooRuntimeConfig = fooRuntimeConfig;
    }

}
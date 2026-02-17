class DummyClass_159492 {
    // Disabled because it does not seem to find the generated interface file @Test
    public void compileSimpleProxyFile() throws Exception {
        ASSERT.about(javaSource())
                .that(simpleProxy)
                .compilesWithoutError();
    }

}
class DummyClass_76719 {
    @ParameterizedTest
    public void testGradleKotlinDSL(String language) throws Exception {
        final List<String> codestarts = getExtensionCodestarts();
        generateProjectRunTests("gradle-kotlin-dsl", language, codestarts);
    }

}
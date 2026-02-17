class DummyClass_76718 {
    @ParameterizedTest
    public void testGradle(String language) throws Exception {
        final List<String> codestarts = getExtensionCodestarts();
        generateProjectRunTests("gradle", language, codestarts);
    }

}
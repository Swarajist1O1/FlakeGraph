class DummyClass_76720 {
    @ParameterizedTest
    public void testRunAloneCodestartsJava(String codestart) throws Exception {
        generateProjectRunTests("maven", "java", singletonList(codestart));
    }

}
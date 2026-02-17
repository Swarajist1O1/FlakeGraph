class DummyClass_76722 {
    @ParameterizedTest
    public void testRunAloneCodestartsScala(String codestart) throws Exception {
        generateProjectRunTests("maven", "scala", singletonList(codestart));
    }

}
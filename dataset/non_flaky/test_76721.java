class DummyClass_76721 {
    @ParameterizedTest
    public void testRunAloneCodestartsKotlin(String codestart) throws Exception {
        generateProjectRunTests("maven", "kotlin", singletonList(codestart));
    }

}
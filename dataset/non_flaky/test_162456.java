class DummyClass_162456 {
    @Test
    public void keyWithNewLinesTest() throws Exception {
        assertStatement(new KeyValuesStatement("TEST", Collections.singletonMap("key\nwith\nnewlines", "1")));
    }

}
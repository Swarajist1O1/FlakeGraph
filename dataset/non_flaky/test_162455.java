class DummyClass_162455 {
    @Test
    public void keyWithSpacesTest() throws Exception {
        assertStatement(new KeyValuesStatement("TEST", Collections.singletonMap("key with spaces", "1")));
    }

}
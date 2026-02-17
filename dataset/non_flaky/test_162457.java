class DummyClass_162457 {
    @Test
    public void keyWithTabsTest() throws Exception {
        assertStatement(new KeyValuesStatement("TEST", Collections.singletonMap("key\twith\ttab", "1")));
    }

}
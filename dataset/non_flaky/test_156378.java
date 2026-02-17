class DummyClass_156378 {
    @Test
    public void testToFullyQualifiedNameClassString() {
        final String expected = "org.apache.commons.lang3.Test.properties";
        final String actual = ClassPathUtils.toFullyQualifiedName(ClassPathUtils.class, "Test.properties");

        assertEquals(expected, actual);
    }

}
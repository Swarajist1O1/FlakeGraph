class DummyClass_156384 {
    @Test
    public void testToFullyQualifiedPathClass() {
        final String expected = "org/apache/commons/lang3/Test.properties";
        final String actual = ClassPathUtils.toFullyQualifiedPath(ClassPathUtils.class, "Test.properties");

        assertEquals(expected, actual);
    }

}
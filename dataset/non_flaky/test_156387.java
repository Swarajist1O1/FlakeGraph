class DummyClass_156387 {
    @Test
    public void testToFullyQualifiedPathPackage() {
        final String expected = "org/apache/commons/lang3/Test.properties";
        final String actual = ClassPathUtils.toFullyQualifiedPath(ClassPathUtils.class.getPackage(), "Test.properties");

        assertEquals(expected, actual);
    }

}
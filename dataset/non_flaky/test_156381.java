class DummyClass_156381 {
    @Test
    public void testToFullyQualifiedNamePackageString() {
        final String expected = "org.apache.commons.lang3.Test.properties";
        final String actual = ClassPathUtils.toFullyQualifiedName(ClassPathUtils.class.getPackage(), "Test.properties");

        assertEquals(expected, actual);
    }

}
class DummyClass_156379 {
    @Test
    public void testToFullyQualifiedNameNullPackageString() {
        assertThrows(NullPointerException.class,
                () -> ClassPathUtils.toFullyQualifiedName((Package) null, "Test.properties"));
    }

}
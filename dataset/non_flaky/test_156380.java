class DummyClass_156380 {
    @Test
    public void testToFullyQualifiedNamePackageNull() {
        assertThrows(NullPointerException.class,
                () -> ClassPathUtils.toFullyQualifiedName(ClassPathUtils.class.getPackage(), null));
    }

}
class DummyClass_156386 {
    @Test
    public void testToFullyQualifiedPathPackageNull() {
        assertThrows(NullPointerException.class,
                () -> ClassPathUtils.toFullyQualifiedPath(ClassPathUtils.class.getPackage(), null));
    }

}
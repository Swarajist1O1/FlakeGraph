class DummyClass_156385 {
    @Test
    public void testToFullyQualifiedPathPackageNullString() {
        assertThrows(NullPointerException.class,
                () -> ClassPathUtils.toFullyQualifiedPath((Package) null, "Test.properties"));
    }

}
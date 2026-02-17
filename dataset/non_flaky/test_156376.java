class DummyClass_156376 {
    @Test
    public void testToFullyQualifiedNameNullClassString() {
        assertThrows(NullPointerException.class,
                () -> ClassPathUtils.toFullyQualifiedName((Class<?>) null, "Test.properties"));
    }

}
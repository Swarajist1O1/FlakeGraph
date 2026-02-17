class DummyClass_156382 {
    @Test
    public void testToFullyQualifiedPathClassNullString() {
        assertThrows(NullPointerException.class,
                () -> ClassPathUtils.toFullyQualifiedPath((Class<?>) null, "Test.properties"));
    }

}
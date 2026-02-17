class DummyClass_156383 {
    @Test
    public void testToFullyQualifiedPathClassNull() {
        assertThrows(NullPointerException.class, () -> ClassPathUtils.toFullyQualifiedPath(ClassPathUtils.class, null));
    }

}
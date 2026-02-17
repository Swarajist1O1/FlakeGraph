class DummyClass_156377 {
    @Test
    public void testToFullyQualifiedNameClassNull() {
        assertThrows(NullPointerException.class, () -> ClassPathUtils.toFullyQualifiedName(ClassPathUtils.class, null));
    }

}
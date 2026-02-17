class DummyClass_162360 {
    @Test
    public void testSuperClass() {
        assertThat(classNode.superName)
                .doesNotStartWith(SHADED_PACKAGE_PATH);
    }

}
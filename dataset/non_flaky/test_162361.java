class DummyClass_162361 {
    @Test
    public void testInterfaces() {
        assertThat(classNode.interfaces)
                .allSatisfy(it -> assertThat(it).doesNotStartWith(SHADED_PACKAGE_PATH));
    }

}
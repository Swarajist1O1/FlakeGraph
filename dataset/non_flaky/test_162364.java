class DummyClass_162364 {
    @Test
    public void testFields() {
        assertThat(classNode.fields)
                .filteredOn(it -> (it.access & (Opcodes.ACC_PUBLIC | Opcodes.ACC_PROTECTED)) != 0)
                .allSatisfy(it -> assertThat(Type.getType(it.desc).getClassName())
                        .doesNotStartWith(SHADED_PACKAGE)
                );
    }

}
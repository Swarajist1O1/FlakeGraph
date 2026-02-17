class DummyClass_162362 {
    @Test
    public void testMethodReturnTypes() {
        assertThat(classNode.methods)
                .filteredOn(it -> (it.access & (Opcodes.ACC_PUBLIC | Opcodes.ACC_PROTECTED)) != 0)
                .allSatisfy(it -> assertThat(Type.getReturnType(it.desc).getClassName()).doesNotStartWith(SHADED_PACKAGE));
    }

}
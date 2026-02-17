class DummyClass_162363 {
    @Test
    public void testMethodArguments() {
        assertThat(classNode.methods)
                .filteredOn(it -> (it.access & (Opcodes.ACC_PUBLIC | Opcodes.ACC_PROTECTED)) != 0)
                .allSatisfy(method -> assertThat(Arrays.asList(Type.getArgumentTypes(method.desc)))
                        .extracting(Type::getClassName)
                        .allSatisfy(it -> assertThat(it).doesNotStartWith(SHADED_PACKAGE))
                );
    }

}
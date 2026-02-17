class DummyClass_114055 {
    @Test
    public void customTypesWork() {
        EnhancedType<EnhancedTypeTest> enhancedType = new EnhancedType<EnhancedTypeTest>(){};
        assertThat(enhancedType.rawClass()).isEqualTo(EnhancedTypeTest.class);
    }

}
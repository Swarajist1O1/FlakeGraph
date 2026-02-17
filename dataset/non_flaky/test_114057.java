class DummyClass_114057 {
    @Test
    public void staticInnerTypesWork() {
        EnhancedType<InnerStaticType> enhancedType = new EnhancedType<InnerStaticType>(){};
        assertThat(enhancedType.rawClass()).isEqualTo(InnerStaticType.class);
    }

}
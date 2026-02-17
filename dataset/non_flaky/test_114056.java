class DummyClass_114056 {
    @Test
    public void nonStaticInnerTypesWork() {
        EnhancedType<InnerType> enhancedType = new EnhancedType<InnerType>(){};
        assertThat(enhancedType.rawClass()).isEqualTo(InnerType.class);
    }

}
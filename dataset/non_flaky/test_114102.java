class DummyClass_114102 {
    @Test
    public void defaultBuilder_defaultToFalse() {
        EnhancedTypeDocumentConfiguration configuration =
            EnhancedTypeDocumentConfiguration.builder().build();
        assertThat(configuration.ignoreNulls()).isFalse();
        assertThat(configuration.preserveEmptyObject()).isFalse();
    }

}
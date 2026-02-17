class DummyClass_114103 {
    @Test
    public void equalsHashCode() {
        EnhancedTypeDocumentConfiguration configuration =
            EnhancedTypeDocumentConfiguration.builder()
                                             .preserveEmptyObject(true)
                                             .ignoreNulls(false)
                                             .build();

        EnhancedTypeDocumentConfiguration another =
            EnhancedTypeDocumentConfiguration.builder()
                                             .preserveEmptyObject(true)
                                             .ignoreNulls(false)
                                             .build();

        EnhancedTypeDocumentConfiguration different =
            EnhancedTypeDocumentConfiguration.builder()
                                             .preserveEmptyObject(false)
                                             .ignoreNulls(true)
                                             .build();

        assertThat(configuration).isEqualTo(another);
        assertThat(configuration.hashCode()).isEqualTo(another.hashCode());
        assertThat(configuration).isNotEqualTo(different);
        assertThat(configuration.hashCode()).isNotEqualTo(different.hashCode());
    }

}
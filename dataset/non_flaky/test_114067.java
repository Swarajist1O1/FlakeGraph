class DummyClass_114067 {
    @Test
    public void collectionOf_ReturnsRawClassOfCollection_WhenSpecifyingEnhancedType() {
        EnhancedType<Collection<String>> type = EnhancedType.collectionOf(EnhancedType.of(String.class));

        assertThat(type.rawClass()).isEqualTo(Collection.class);
        assertThat(type.rawClassParameters()).containsExactly(EnhancedType.of(String.class));
    }

}
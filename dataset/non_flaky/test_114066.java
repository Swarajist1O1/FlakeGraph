class DummyClass_114066 {
    @Test
    public void collectionOf_ReturnsRawClassOfCollection_WhenSpecifyingClass() {
        EnhancedType<Collection<String>> type = EnhancedType.collectionOf(String.class);

        assertThat(type.rawClass()).isEqualTo(Collection.class);
        assertThat(type.rawClassParameters()).containsExactly(EnhancedType.of(String.class));
    }

}
class DummyClass_114063 {
    @Test
    public void sortedSetOf_ReturnsRawClassOfDeque_WhenSpecifyingEnhancedType() {
        EnhancedType<SortedSet<String>> type = EnhancedType.sortedSetOf(EnhancedType.of(String.class));

        assertThat(type.rawClass()).isEqualTo(SortedSet.class);
        assertThat(type.rawClassParameters()).containsExactly(EnhancedType.of(String.class));
    }

}
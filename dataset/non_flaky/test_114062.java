class DummyClass_114062 {
    @Test
    public void sortedSetOf_ReturnsRawClassOfDeque_WhenSpecifyingClass() {
        EnhancedType<SortedSet<String>> type = EnhancedType.sortedSetOf(String.class);

        assertThat(type.rawClass()).isEqualTo(SortedSet.class);
        assertThat(type.rawClassParameters()).containsExactly(EnhancedType.of(String.class));
    }

}
class DummyClass_114069 {
    @Test
    public void sortedMapOf_ReturnsRawClassOfSortedMap_WhenSpecifyingEnhancedType() {
        EnhancedType<SortedMap<String, Integer>> type =
            EnhancedType.sortedMapOf(EnhancedType.of(String.class), EnhancedType.of(Integer.class));

        assertThat(type.rawClass()).isEqualTo(SortedMap.class);
        assertThat(type.rawClassParameters()).containsExactly(EnhancedType.of(String.class), EnhancedType.of(Integer.class));
    }

}
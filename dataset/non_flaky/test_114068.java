class DummyClass_114068 {
    @Test
    public void sortedMapOf_ReturnsRawClassOfSortedMap_WhenSpecifyingClass() {
        EnhancedType<SortedMap<String, Integer>> type = EnhancedType.sortedMapOf(String.class, Integer.class);

        assertThat(type.rawClass()).isEqualTo(SortedMap.class);
        assertThat(type.rawClassParameters()).containsExactly(EnhancedType.of(String.class), EnhancedType.of(Integer.class));
    }

}
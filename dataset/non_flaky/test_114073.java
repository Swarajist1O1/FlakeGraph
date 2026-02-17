class DummyClass_114073 {
    @Test
    public void navigableMapOf_ReturnsRawClassOfNavigableMap_WhenSpecifyingEnhancedType() {
        EnhancedType<NavigableMap<String, Integer>> type =
            EnhancedType.navigableMapOf(EnhancedType.of(String.class), EnhancedType.of(Integer.class));

        assertThat(type.rawClass()).isEqualTo(NavigableMap.class);
        assertThat(type.rawClassParameters()).containsExactly(EnhancedType.of(String.class), EnhancedType.of(Integer.class));
    }

}
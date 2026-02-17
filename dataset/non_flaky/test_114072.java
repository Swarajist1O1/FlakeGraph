class DummyClass_114072 {
    @Test
    public void navigableMapOf_ReturnsRawClassOfNavigableMap_WhenSpecifyingClass() {
        EnhancedType<NavigableMap<String, Integer>> type = EnhancedType.navigableMapOf(String.class, Integer.class);

        assertThat(type.rawClass()).isEqualTo(NavigableMap.class);
        assertThat(type.rawClassParameters()).containsExactly(EnhancedType.of(String.class), EnhancedType.of(Integer.class));
    }

}
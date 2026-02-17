class DummyClass_114065 {
    @Test
    public void navigableSetOf_ReturnsRawClassOfNavigableSet_WhenSpecifyingEnhancedType() {
        EnhancedType<NavigableSet<String>> type = EnhancedType.navigableSetOf(EnhancedType.of(String.class));

        assertThat(type.rawClass()).isEqualTo(NavigableSet.class);
        assertThat(type.rawClassParameters()).containsExactly(EnhancedType.of(String.class));
    }

}
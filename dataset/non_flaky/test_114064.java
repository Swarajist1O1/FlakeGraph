class DummyClass_114064 {
    @Test
    public void navigableSetOf_ReturnsRawClassOfNavigableSet_WhenSpecifyingClass() {
        EnhancedType<NavigableSet<String>> type = EnhancedType.navigableSetOf(String.class);

        assertThat(type.rawClass()).isEqualTo(NavigableSet.class);
        assertThat(type.rawClassParameters()).containsExactly(EnhancedType.of(String.class));
    }

}
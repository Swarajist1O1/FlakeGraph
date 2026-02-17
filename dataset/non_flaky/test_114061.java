class DummyClass_114061 {
    @Test
    public void dequeOf_ReturnsRawClassOfDeque_WhenSpecifyingEnhancedType() {
        EnhancedType<Deque<String>> type = EnhancedType.dequeOf(EnhancedType.of(String.class));

        assertThat(type.rawClass()).isEqualTo(Deque.class);
        assertThat(type.rawClassParameters()).containsExactly(EnhancedType.of(String.class));
    }

}
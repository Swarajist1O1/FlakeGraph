class DummyClass_114060 {
    @Test
    public void dequeOf_ReturnsRawClassOfDeque_WhenSpecifyingClass() {
        EnhancedType<Deque<String>> type = EnhancedType.dequeOf(String.class);

        assertThat(type.rawClass()).isEqualTo(Deque.class);
        assertThat(type.rawClassParameters()).containsExactly(EnhancedType.of(String.class));
    }

}
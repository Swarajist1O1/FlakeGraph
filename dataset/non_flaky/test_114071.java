class DummyClass_114071 {
    @Test
    public void concurrentMapOf_ReturnsRawClassOfConcurrentMap_WhenSpecifyingEnhancedType() {
        EnhancedType<ConcurrentMap<String, Integer>> type =
            EnhancedType.concurrentMapOf(EnhancedType.of(String.class), EnhancedType.of(Integer.class));

        assertThat(type.rawClass()).isEqualTo(ConcurrentMap.class);
        assertThat(type.rawClassParameters()).containsExactly(EnhancedType.of(String.class), EnhancedType.of(Integer.class));
    }

}
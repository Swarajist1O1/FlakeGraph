class DummyClass_114070 {
    @Test
    public void concurrentMapOf_ReturnsRawClassOfConcurrentMap_WhenSpecifyingClass() {
        EnhancedType<ConcurrentMap<String, Integer>> type = EnhancedType.concurrentMapOf(String.class, Integer.class);

        assertThat(type.rawClass()).isEqualTo(ConcurrentMap.class);
        assertThat(type.rawClassParameters()).containsExactly(EnhancedType.of(String.class), EnhancedType.of(Integer.class));
    }

}
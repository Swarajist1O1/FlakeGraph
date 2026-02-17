class DummyClass_88801 {
    @Test
    public void testClear() {
        Map<UUID, List<UUID>> procIds = Collections.emptyMap();
        wrapper.clear(procIds);

        verify(delegate).clear(eq(procIds));
    }

}
class DummyClass_88799 {
    @Test
    public void testPing() {
        Map<UUID, List<UUID>> procIds = Collections.emptyMap();
        wrapper.ping(procIds);

        verify(delegate).ping(eq(procIds));
    }

}
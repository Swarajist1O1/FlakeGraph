class DummyClass_88800 {
    @Test
    public void testStop() {
        Map<UUID, List<UUID>> procIds = Collections.emptyMap();
        wrapper.stop(procIds, true);

        verify(delegate).stop(eq(procIds), eq(true));
    }

}
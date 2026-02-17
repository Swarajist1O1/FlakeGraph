class DummyClass_70766 {
    @Test
    public void shouldRecordStops() {
        assertEquals(0, counter.stops());
        counter.recordStop();
        assertEquals(1, counter.stops());
        counter.recordStop();
        assertEquals(2, counter.stops());
        assertEquals(2, counter.stops());
    }

}
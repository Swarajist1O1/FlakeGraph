class DummyClass_70765 {
    @Test
    public void shouldRecordStarts() {
        assertEquals(0, counter.starts());
        counter.recordStart();
        assertEquals(1, counter.starts());
        counter.recordStart();
        assertEquals(2, counter.starts());
        assertEquals(2, counter.starts());
    }

}
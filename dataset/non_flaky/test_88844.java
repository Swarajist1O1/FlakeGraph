class DummyClass_88844 {
    @Test
    public void testAdd() {
        double val = 100.0;
        hist1.addElement(val);
        Optional<Double> cntr = hist1.getValue(computeBucket(val));

        assertTrue(cntr.isPresent());
        assertEquals(1, cntr.get().intValue());
    }

}
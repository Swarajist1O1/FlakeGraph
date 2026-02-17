class DummyClass_94737 {
    @Test
    public void largeNegativeValueIsNotAvailable() {
        assertFalse(Longitude28.isAvailable(-1-180*60*10000));
    }

}
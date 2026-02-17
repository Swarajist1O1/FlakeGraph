class DummyClass_94751 {
    @Test
    public void largeNegativeValueIsNotAvailable() {
        assertFalse(Latitude27.isAvailable(-1-90*60*10000));
    }

}
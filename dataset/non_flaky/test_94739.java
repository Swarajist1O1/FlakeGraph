class DummyClass_94739 {
    @Test
    public void largeValueIsNotAvailable() {
        assertFalse(Longitude28.isAvailable(1+180*60*10000));
    }

}
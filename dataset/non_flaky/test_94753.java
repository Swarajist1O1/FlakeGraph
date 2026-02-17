class DummyClass_94753 {
    @Test
    public void largeValueIsNotAvailable() {
        assertFalse(Latitude27.isAvailable(1+90*60*10000));
    }

}
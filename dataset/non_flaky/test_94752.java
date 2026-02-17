class DummyClass_94752 {
    @Test
    public void largeNegativeValueIsNotCorrect() {
        assertFalse(Latitude27.isCorrect(-1-90*60*10000));
    }

}
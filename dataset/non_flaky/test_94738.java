class DummyClass_94738 {
    @Test
    public void largeNegativeValueIsNotCorrect() {
        assertFalse(Longitude28.isCorrect(-1-180*60*10000));
    }

}
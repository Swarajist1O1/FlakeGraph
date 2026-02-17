class DummyClass_94740 {
    @Test
    public void largeValueIsNotCorrect() {
        assertFalse(Longitude28.isCorrect(1+180*60*10000));
    }

}
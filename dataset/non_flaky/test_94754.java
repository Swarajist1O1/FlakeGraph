class DummyClass_94754 {
    @Test
    public void largeValueIsNotCorrect() {
        assertFalse(Latitude27.isCorrect(1+90*60*10000));
    }

}
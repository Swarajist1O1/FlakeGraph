class DummyClass_94728 {
    @Test
    public void conversionReturnsOnInvalidValues() {
        assertEquals(-10.1, SpeedOverGround.toKnots(-101), DELTA);
        assertEquals(102.3, SpeedOverGround.toKnots(1023), DELTA);
        assertEquals(4567.8, SpeedOverGround.toKnots(45678), DELTA);
    }

}
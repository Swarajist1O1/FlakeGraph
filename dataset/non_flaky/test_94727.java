class DummyClass_94727 {
    @Test
    public void conversionToKnotsWorks() {
        assertEquals(0.0, SpeedOverGround.toKnots(0), DELTA);
        assertEquals(0.1, SpeedOverGround.toKnots(1), DELTA);
        assertEquals(90.9, SpeedOverGround.toKnots(909), DELTA);
        assertEquals(102.2, SpeedOverGround.toKnots(1022), DELTA);
    }

}
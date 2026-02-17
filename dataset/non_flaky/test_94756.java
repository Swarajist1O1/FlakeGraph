class DummyClass_94756 {
    @Test
    public void conversionReturnsOnInvalidValues() {
        assertEquals(-101.1, Latitude27.toDegrees(Double.valueOf(-101.1*60*10000).intValue()), DELTA);
        assertEquals(91.1, Latitude27.toDegrees(Double.valueOf(91.1*60*10000).intValue()), DELTA);
        assertEquals(102.3, Latitude27.toDegrees(Double.valueOf(102.3*60*10000).intValue()), DELTA);
    }

}
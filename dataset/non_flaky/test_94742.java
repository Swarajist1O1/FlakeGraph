class DummyClass_94742 {
    @Test
    public void conversionReturnsOnInvalidValues() {
        assertEquals(-201.1, Longitude28.toDegrees(Double.valueOf(-201.1*60*10000).intValue()), DELTA);
        assertEquals(181.1, Longitude28.toDegrees(Double.valueOf(181.1*60*10000).intValue()), DELTA);
        assertEquals(202.3, Longitude28.toDegrees(Double.valueOf(202.3*60*10000).intValue()), DELTA);
    }

}
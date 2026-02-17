class DummyClass_94741 {
    @Test
    public void conversionToKnotsWorks() {
        assertEquals(-180.0, Longitude28.toDegrees(Double.valueOf(-180.0*60*10000).intValue()), DELTA);
        assertEquals(-45.1, Longitude28.toDegrees(Double.valueOf(-45.1*60*10000).intValue()), DELTA);
        assertEquals(0.0, Longitude28.toDegrees(0), 0.00001);
        assertEquals(45.9, Longitude28.toDegrees(Double.valueOf(45.9*60*10000).intValue()), DELTA);
        assertEquals(180.0, Longitude28.toDegrees(Double.valueOf(180.0*60*10000).intValue()), DELTA);
    }

}
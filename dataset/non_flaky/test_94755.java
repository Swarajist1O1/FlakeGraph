class DummyClass_94755 {
    @Test
    public void conversionToKnotsWorks() {
        assertEquals(-90.0, Latitude27.toDegrees(Double.valueOf(-90.0*60*10000).intValue()), DELTA);
        assertEquals(-45.1, Latitude27.toDegrees(Double.valueOf(-45.1*60*10000).intValue()), DELTA);
        assertEquals(0.0, Latitude27.toDegrees(0), 0.00001);
        assertEquals(45.9, Latitude27.toDegrees(Double.valueOf(45.9*60*10000).intValue()), DELTA);
        assertEquals(90.0, Latitude27.toDegrees(Double.valueOf(90.0*60*10000).intValue()), DELTA);
    }

}
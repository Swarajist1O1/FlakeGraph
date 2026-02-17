class DummyClass_112628 {
    @Test
    public void testEqualsWithMetadata() {
        String source = "test";
        Instant time = Instant.now();
        NMEAMessage nmea = NMEAMessage.fromString("!AIVDM,1,1,,A,13aEOK?P00PD2wVMdLDRhgvL289?,0*26");

        Metadata meta1 = new Metadata(source, time);
        Metadata meta2 = new Metadata(source, time);
        Metadata meta3 = new Metadata(source, time.plusMillis(1000));

        AISMessage ais1 = AISMessage.create(meta1, nmea);
        AISMessage ais2 = AISMessage.create(meta2, nmea);
        AISMessage ais3 = AISMessage.create(meta3, nmea);

        assertEquals(meta1, meta2);
        assertNotEquals(meta1, meta3);

        assertEquals(ais1, ais2);
        assertNotEquals(ais1, ais3);
    }

}
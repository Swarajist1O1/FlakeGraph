class DummyClass_112661 {
    @Test
    public void testDataFields() {
        AISMessage aisMessage = AISMessage.create(NMEAMessage.fromString("!AIVDO,1,1,,A,E>lt;MIas0h3V:@;4a::h0b7W005Jh4nq:3l800003v010,4*08"));
        Map<String, Object> dataFields = aisMessage.dataFields();

        assertNotNull(dataFields);
        assertEquals(22, dataFields.size());

        assertEquals("AidToNavigationReport", dataFields.get("messageType"));
        assertEquals(0, dataFields.get("repeatIndicator"));
        assertEquals(995036021, dataFields.get("sourceMmsi.MMSI"));
        assertEquals("BeaconSpecialMark", dataFields.get("aidType"));
        assertEquals("S6A GLT VIRTU ATON", dataFields.get("name"));
        assertEquals(false, dataFields.get("positionAccurate"));
        assertEquals(151.49791f, dataFields.get("longitude"));
        assertEquals(-23.917385f, dataFields.get("latitude"));
        assertEquals(0, dataFields.get("toStern"));
        assertEquals(0, dataFields.get("toBow"));
        assertEquals(0, dataFields.get("toPort"));
        assertEquals(0, dataFields.get("toStarboard"));
        assertEquals("Surveyed", dataFields.get("positionFixingDevice"));
        assertEquals(60, dataFields.get("second"));
        assertEquals(false, dataFields.get("offPosition"));
        assertEquals("00000000", dataFields.get("atoNStatus"));
        assertEquals(false, dataFields.get("raimFlag"));
        assertEquals(true, dataFields.get("virtualAid"));
        assertEquals(false, dataFields.get("assignedMode"));
        assertEquals(0, dataFields.get("spare1"));
        assertEquals(0, dataFields.get("spare2"));

        assertFalse(dataFields.containsKey("nameExtension"));
        assertFalse(dataFields.containsKey("class"));
    }

}
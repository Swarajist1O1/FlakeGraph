class DummyClass_112648 {
    @Test
    public void canDecode() {
        AISMessage aisMessage = AISMessage.create(NMEAMessage.fromString("!AIVDM,1,1,,B,4h3Ovk1udp6I9o>jPHEdjdW000S:,0*0C"));

        System.out.println(aisMessage.toString());

        assertEquals(AISMessageType.BaseStationReport, aisMessage.getMessageType());
        assertEquals((Integer) 3, aisMessage.getRepeatIndicator());
        BaseStationReport message = (BaseStationReport) aisMessage;
        assertEquals(MMSI.valueOf(3669708), message.getSourceMmsi());
        assertEquals((Integer) 2011, message.getYear());
        assertEquals((Integer) 3, message.getMonth());
        assertEquals((Integer) 16, message.getDay());
        assertEquals((Integer) 6, message.getHour());
        assertEquals((Integer) 25, message.getMinute());
        assertEquals((Integer) 9, message.getSecond());
        assertTrue(message.getPositionAccurate());
        assertEquals(Float.valueOf(37.923283f), message.getLatitude());
        assertEquals(Float.valueOf(-122.59837f), message.getLongitude());
        assertEquals(PositionFixingDevice.Surveyed, message.getPositionFixingDevice());
        assertFalse(message.getRaimFlag());
    }

}
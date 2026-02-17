class DummyClass_112663 {
    @Test
    public void canDecode2() {
        AISMessage aisMessage = AISMessage.create(NMEAMessage.fromString("!AIVDM,1,1,,B,K5DfMB9FLsM?P00d,0*70"));

        System.out.println(aisMessage.toString());

        assertEquals(AISMessageType.LongRangeBroadcastMessage, aisMessage.getMessageType());
        assertEquals((Integer) 0, aisMessage.getRepeatIndicator());
        assertEquals(MMSI.valueOf(357277000), aisMessage.getSourceMmsi());

        LongRangeBroadcastMessage message = (LongRangeBroadcastMessage) aisMessage;
        assertTrue(message.getPositionAccuracy());
        assertFalse(message.getRaim());
        assertEquals(NavigationStatus.Moored, message.getNavigationalStatus());
        assertEquals(Float.valueOf(176.18167f), message.getLongitude());
        assertEquals(Float.valueOf(-37.65333f), message.getLatitude());
        assertEquals(Float.valueOf(0f), message.getSpeedOverGround(), 1e-5);
        assertEquals((Integer)0, message.getRawSpeedOverGround());
        assertEquals(Float.valueOf(11f), message.getCourseOverGround(), 1e-5);
        assertEquals((Integer)11, message.getRawCourseOverGround());
    }

}
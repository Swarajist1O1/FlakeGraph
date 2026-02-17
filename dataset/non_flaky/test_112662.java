class DummyClass_112662 {
    @Test
    public void canDecode1() {
        AISMessage aisMessage = AISMessage.create(NMEAMessage.fromString("!AIVDM,1,1,,B,KC5E2b@U19PFdLbMuc5=ROv62<7m,0*16"));

        System.out.println(aisMessage.toString());

        assertEquals(AISMessageType.LongRangeBroadcastMessage, aisMessage.getMessageType());
        assertEquals((Integer) 1, aisMessage.getRepeatIndicator());
        assertEquals(MMSI.valueOf(206914217), aisMessage.getSourceMmsi());

        LongRangeBroadcastMessage message = (LongRangeBroadcastMessage) aisMessage;
        assertFalse(message.getPositionAccuracy());
        assertFalse(message.getRaim());
        assertEquals(NavigationStatus.NotUnderCommand, message.getNavigationalStatus());
        assertEquals(Float.valueOf(137.02333f), message.getLongitude());
        assertEquals(Float.valueOf(4.84f), message.getLatitude());
        assertEquals(Float.valueOf(57f), message.getSpeedOverGround(), 1e-5);
        assertEquals((Integer)57, message.getRawSpeedOverGround());
        assertEquals(Float.valueOf(167f), message.getCourseOverGround(), 1e-5);
        assertEquals((Integer)167, message.getRawCourseOverGround());
    }

}
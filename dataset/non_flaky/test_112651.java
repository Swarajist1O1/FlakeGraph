class DummyClass_112651 {
    @Test
    public void canDecode2() {
        AISMessage aisMessage = AISMessage.create(NMEAMessage.fromString("!AIVDM,1,1,,A,B>1VUFP00vK`auV0eUulKwv0RJGT,0*09"));

        System.out.println(aisMessage.toString());

        assertEquals(AISMessageType.StandardClassBCSPositionReport, aisMessage.getMessageType());
        StandardClassBCSPositionReport message = (StandardClassBCSPositionReport) aisMessage;
        assertEquals(Integer.valueOf(0), message.getRepeatIndicator());
        assertEquals(MMSI.valueOf(941204826), message.getSourceMmsi());
        assertEquals("00000000", message.getRegionalReserved1());
        assertEquals((Float) 0.3f, message.getSpeedOverGround());
        assertTrue(message.getPositionAccurate());
        assertEquals(Float.valueOf(42.020855f), message.getLatitude());
        assertEquals(Float.valueOf(-87.70006f), message.getLongitude());
        assertEquals(Float.valueOf(186.2f), message.getCourseOverGround());
        assertEquals((Integer) 511, message.getTrueHeading());
        assertEquals((Integer) 60, message.getSecond());
        assertEquals("00", message.getRegionalReserved2());
        assertFalse(message.getCsUnit());
        assertFalse(message.getDisplay());
        assertFalse(message.getDsc());
        assertTrue(message.getBand());
        assertFalse(message.getMessage22());
        assertFalse(message.getAssigned());
        assertFalse(message.getRaimFlag());
    }

}
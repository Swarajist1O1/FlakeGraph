class DummyClass_112660 {
    @Test
    public void canDecode2() {
        AISMessage aisMessage = AISMessage.create(NMEAMessage.fromString("!AIVDO,1,1,,A,E>lt;MIas0h3V:@;4a::h0b7W005Jh4nq:3l800003v010,4*08"));
        System.out.println(aisMessage.toString());

        assertEquals(AISMessageType.AidToNavigationReport, aisMessage.getMessageType());
        assertEquals((Integer) 0, aisMessage.getRepeatIndicator());
        AidToNavigationReport message = (AidToNavigationReport) aisMessage;
        assertEquals(MMSI.valueOf(995036021), message.getSourceMmsi());
        assertEquals(AidType.BeaconSpecialMark, message.getAidType());
        assertEquals(false, message.getAssignedMode());
        assertEquals("S6A GLT VIRTU ATON", message.getName());
        assertEquals(null, message.getNameExtension());
        assertEquals(false, message.getOffPosition());
        assertEquals(Integer.valueOf(60), message.getSecond());
        assertEquals(Integer.valueOf(0), message.getToBow());
        assertEquals(Integer.valueOf(0), message.getToPort());
        assertEquals(Integer.valueOf(0), message.getToStern());
        assertEquals(Integer.valueOf(0), message.getToStarboard());
        assertEquals(true, message.getVirtualAid());
        assertEquals(false, message.getPositionAccurate());
        assertEquals(Float.valueOf(-23.917385f), message.getLatitude());
        assertEquals(Float.valueOf(151.49791f), message.getLongitude());
        assertEquals(PositionFixingDevice.Surveyed, message.getPositionFixingDevice());
        assertFalse(message.getRaimFlag());
    }

}
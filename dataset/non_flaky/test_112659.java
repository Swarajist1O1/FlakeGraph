class DummyClass_112659 {
    @Test
    public void canDecode1() {
        AISMessage aisMessage = AISMessage.create(NMEAMessage.fromString("!AIVDO,1,1,,A,E>lt;Lqaps0h3V:@;4a:@0b7W005J`6Dq9e<000003v010,4*7E"));

        System.out.println(aisMessage.toString());

        assertEquals(AISMessageType.AidToNavigationReport, aisMessage.getMessageType());
        assertEquals((Integer) 0, aisMessage.getRepeatIndicator());
        AidToNavigationReport message = (AidToNavigationReport) aisMessage;
        assertEquals(MMSI.valueOf(995036019), message.getSourceMmsi());
        assertEquals(AidType.BeaconSpecialMark, message.getAidType());
        assertEquals(false, message.getAssignedMode());
        assertEquals("S16A GLT VIRT ATON", message.getName());
        assertEquals(null, message.getNameExtension());
        assertEquals(false, message.getOffPosition());
        assertEquals(Integer.valueOf(60), message.getSecond());
        assertEquals(Integer.valueOf(0), message.getToBow());
        assertEquals(Integer.valueOf(0), message.getToPort());
        assertEquals(Integer.valueOf(0), message.getToStern());
        assertEquals(Integer.valueOf(0), message.getToStarboard());
        assertEquals(true, message.getVirtualAid());
        assertEquals(false, message.getPositionAccurate());
        assertEquals(Float.valueOf(-23.936693f), message.getLatitude()); // lat = 111001001001101101001100000b = -23,9366933333
        assertEquals(Float.valueOf(151.44344f), message.getLongitude());
        assertEquals(PositionFixingDevice.Surveyed, message.getPositionFixingDevice());
        assertFalse(message.getRaimFlag());
    }

}
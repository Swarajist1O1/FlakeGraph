class DummyClass_112633 {
    @Test
    public void canDecode1() {
        AISMessage aisMessage = AISMessage.create(
            NMEAMessage.fromString("!AIVDM,2,1,3,A,55MuUD02;EFUL@CO;W@lU=<U=<U10V1HuT4LE:1DC@T>B4kC0DliSp=t,0*14"),
            NMEAMessage.fromString("!AIVDM,2,2,3,A,888888888888880,2*27")
        );

        System.out.println(aisMessage.toString());

        assertEquals(AISMessageType.ShipAndVoyageRelatedData, aisMessage.getMessageType());
        ShipAndVoyageData message = (ShipAndVoyageData) aisMessage;
        assertEquals(Integer.valueOf(0), message.getRepeatIndicator());
        assertEquals(MMSI.valueOf(366962000), message.getSourceMmsi());
        assertEquals(IMO.valueOf(9131369), message.getImo());
        assertEquals("WDD7294", message.getCallsign());
        assertEquals("MISSISSIPPI VOYAGER", message.getShipName());
        assertEquals(ShipType.TankerHazardousD, message.getShipType());
        assertEquals(Integer.valueOf(154), message.getToBow());
        assertEquals(Integer.valueOf(36), message.getToStern());
        assertEquals(Integer.valueOf(18), message.getToStarboard());
        assertEquals(Integer.valueOf(14), message.getToPort());
        assertEquals(PositionFixingDevice.Gps, message.getPositionFixingDevice());
        assertEquals(Float.valueOf("8.3"), message.getDraught());
        assertEquals((Integer) 83 , message.getRawDraught());
        assertEquals("06-03 19:00", message.getEta());
        assertEquals((Integer) 3, message.getEtaMonth());
        assertEquals((Integer) 6, message.getEtaDay());
        assertEquals((Integer) 19, message.getEtaHour());
        assertEquals((Integer) 0, message.getEtaMinute());
        assertEquals(Optional.empty(), message.getEtaAfterReceived());
        assertEquals("SFO 70", message.getDestination());
        assertFalse(message.getDataTerminalReady());
    }

}
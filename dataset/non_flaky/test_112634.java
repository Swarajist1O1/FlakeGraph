class DummyClass_112634 {
    @Test
    public void canDecode2() {
        ZonedDateTime now = ZonedDateTime.of(2010, 12, 31, 23, 59, 59, 0, ZoneOffset.UTC);
        AISMessage aisMessage = AISMessage.create(new Metadata("Test", now.toInstant()),
            NMEAMessage.fromString("!AIVDM,2,1,0,B,539S:k40000000c3G04PPh63<00000000080000o1PVG2uGD:00000000000,0*34"),
            NMEAMessage.fromString("!AIVDM,2,2,0,B,00000000000,2*27")
        );

        System.out.println(aisMessage.toString());

        assertEquals(AISMessageType.ShipAndVoyageRelatedData, aisMessage.getMessageType());
        ShipAndVoyageData message = (ShipAndVoyageData) aisMessage;
        assertEquals(Integer.valueOf(0), message.getRepeatIndicator());
        assertEquals(MMSI.valueOf(211339980), message.getSourceMmsi());
        assertEquals(IMO.valueOf(0), message.getImo());
        assertEquals("J050A", message.getCallsign());
        assertEquals("HHLA 3         B", message.getShipName());
        assertEquals(ShipType.LawEnforcement, message.getShipType());
        assertEquals(Integer.valueOf(12), message.getToBow());
        assertEquals(Integer.valueOf(38), message.getToStern());
        assertEquals(Integer.valueOf(2), message.getToStarboard());
        assertEquals(Integer.valueOf(23), message.getToPort());
        assertNull(message.getPositionFixingDevice());
        assertEquals(Float.valueOf("0"), message.getDraught());
        assertEquals("14-05 20:10", message.getEta());
        assertEquals((Integer) 5, message.getEtaMonth());
        assertEquals((Integer) 14, message.getEtaDay());
        assertEquals((Integer) 20, message.getEtaHour());
        assertEquals((Integer) 10, message.getEtaMinute());
        assertEquals(Optional.of(ZonedDateTime.of(2011, 5, 14, 20, 10, 0, 0, ZoneOffset.UTC)), message.getEtaAfterReceived());
        assertEquals("", message.getDestination());
        assertFalse(message.getDataTerminalReady());
    }

}
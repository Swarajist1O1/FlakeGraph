class DummyClass_112649 {
    @Test
    public void canDecodeCommunicationState() {
        AISMessage aisMessage = AISMessage.create(NMEAMessage.fromString("!AIVDM,1,1,,A,400TcdiuiT7VDR>3nIfr6>i00000,0*78"));

        System.out.println(aisMessage.toString());

        assertEquals(AISMessageType.BaseStationReport, aisMessage.getMessageType());
        assertEquals((Integer) 0, aisMessage.getRepeatIndicator());
        BaseStationReport message = (BaseStationReport) aisMessage;
        assertEquals(MMSI.valueOf(601011), message.getSourceMmsi());
        assertEquals((Integer) 2012, message.getYear());
        assertEquals((Integer) 6, message.getMonth());
        assertEquals((Integer) 8, message.getDay());
        assertEquals((Integer) 7, message.getHour());
        assertEquals((Integer) 38, message.getMinute());
        assertEquals((Integer) 20, message.getSecond());
        assertTrue(message.getPositionAccurate());
        assertEquals(Float.valueOf(-29.870832f), message.getLatitude());
        assertEquals(Float.valueOf(31.033514f), message.getLongitude());
        assertEquals(PositionFixingDevice.Gps, message.getPositionFixingDevice());
        assertFalse(message.getRaimFlag());

        SOTDMACommunicationState sotdmaCommunicationState = message.getCommunicationState();
        assertEquals(SyncState.UTCDirect, sotdmaCommunicationState.getSyncState());
        assertNull(sotdmaCommunicationState.getNumberOfReceivedStations());
        assertNull(sotdmaCommunicationState.getSlotNumber());
        assertEquals(Integer.valueOf(0), sotdmaCommunicationState.getSlotOffset());
        assertEquals(Integer.valueOf(0), sotdmaCommunicationState.getSlotTimeout());
        assertNull(sotdmaCommunicationState.getUtcHour());
        assertNull(sotdmaCommunicationState.getUtcMinute());
    }

}
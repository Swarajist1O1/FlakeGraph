class DummyClass_112652 {
    @Test
    public void canDecodeITDMACommunicationState() {
        AISMessage aisMessage = AISMessage.create(NMEAMessage.fromString("!AIVDM,1,1,,A,B6CdCm0t3`tba35f@V9faHi7kP06,0*58"));

        System.out.println(aisMessage.toString());

        assertEquals(AISMessageType.StandardClassBCSPositionReport, aisMessage.getMessageType());
        StandardClassBCSPositionReport message = (StandardClassBCSPositionReport) aisMessage;
        assertEquals(Integer.valueOf(0), message.getRepeatIndicator());
        assertEquals(MMSI.valueOf(423302100), message.getSourceMmsi());
        assertEquals("00001111", message.getRegionalReserved1());
        assertEquals((Float) 1.4f, message.getSpeedOverGround());
        assertTrue(message.getPositionAccurate());
        assertEquals(Float.valueOf(40.005283f), message.getLatitude());
        assertEquals(Float.valueOf(53.010998f), message.getLongitude());
        assertEquals(Float.valueOf(177f), message.getCourseOverGround());
        assertEquals((Integer) 177, message.getTrueHeading());
        assertEquals((Integer) 34, message.getSecond());
        assertEquals("00", message.getRegionalReserved2());
        assertTrue(message.getCsUnit());
        assertTrue(message.getDisplay());
        assertTrue(message.getDsc());
        assertTrue(message.getBand());
        assertTrue(message.getMessage22());
        assertFalse(message.getAssigned());
        assertFalse(message.getRaimFlag());
        assertTrue(message.getCommunicationStateSelectorFlag());

        CommunicationState communicationState = message.getCommunicationState();   // 1100000000000000110b = 3, slot incr = 6
        assertEquals(SyncState.BaseIndirect, communicationState.getSyncState());
        assertTrue(communicationState instanceof ITDMACommunicationState);
        ITDMACommunicationState itdmaCommunicationState = (ITDMACommunicationState) communicationState;
        assertEquals(Integer.valueOf(0), itdmaCommunicationState.getSlotIncrement());
        assertEquals(Integer.valueOf(3), itdmaCommunicationState.getNumberOfSlots());
        assertFalse(itdmaCommunicationState.getKeepFlag());
    }

}
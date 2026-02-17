class DummyClass_112654 {
    @Test
    public void canDecode() {
        AISMessage aisMessage = AISMessage.create(NMEAMessage.fromString("!AIVDM,1,1,,A,13@nePh01>PjcO4PGReoJEmL0HJg,0*67"));

        System.out.println(aisMessage.toString());

        assertEquals(AISMessageType.PositionReportClassAScheduled, aisMessage.getMessageType());
        assertEquals((Integer) 0, aisMessage.getRepeatIndicator());
        PositionReportClassAScheduled message = (PositionReportClassAScheduled) aisMessage;
        assertEquals(MMSI.valueOf(219000195), message.getSourceMmsi());
        assertEquals(NavigationStatus.UnderwayUsingEngine, message.getNavigationStatus());
        assertEquals((Integer) 0, message.getRateOfTurn());
        assertEquals((Float) 7.8f, message.getSpeedOverGround());
        assertTrue(message.getPositionAccuracy());
        assertEquals(Float.valueOf(56.56692f), message.getLatitude());
        assertEquals((Integer) 33940151, message.getRawLatitude());
        assertEquals(Float.valueOf(11.071096f), message.getLongitude());
        assertEquals((Integer) 6642658, message.getRawLongitude());
        assertEquals(Float.valueOf(189.7f), message.getCourseOverGround());
        assertEquals((Integer) 1897, message.getRawCourseOverGround());
        assertEquals((Integer) 46, message.getSecond());
        assertEquals((Integer) 186, message.getTrueHeading());
        assertEquals(ManeuverIndicator.NotAvailable, message.getSpecialManeuverIndicator());
        assertFalse(message.getRaimFlag());
    }

}
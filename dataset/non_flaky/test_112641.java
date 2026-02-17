class DummyClass_112641 {
    @Test
    public void canDecodeUnknownApplicationSpecificMessage() {
        AISMessage aisMessage = AISMessage.create(NMEAMessage.fromString("!AIVDM,1,1,,A,8@30oni?1j020@00,0*23"));

        System.out.println(aisMessage.toString());

        assertEquals(316, ((BinaryBroadcastMessage) aisMessage).getDesignatedAreaCode().intValue());
        assertEquals(7, ((BinaryBroadcastMessage) aisMessage).getFunctionalId().intValue());
        assertEquals(UnknownApplicationSpecificMessage.class, ((BinaryBroadcastMessage) aisMessage).getApplicationSpecificMessage().getClass());
    }

}
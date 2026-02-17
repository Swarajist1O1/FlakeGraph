class DummyClass_112645 {
    @Test(expected = InvalidMessage.class)
    public void failsWithInvalidMessageWhenDecodingShortMessage() {
        AISMessage aisMessage = AISMessage.create(NMEAMessage.fromString("!AIVDM,1,1,,A,83aDCr@,0*5F"));
        BinaryBroadcastMessage binaryBroadcastMessage = (BinaryBroadcastMessage) aisMessage;

        ApplicationSpecificMessage asm = binaryBroadcastMessage.getApplicationSpecificMessage();

        System.out.println(aisMessage);
    }

}
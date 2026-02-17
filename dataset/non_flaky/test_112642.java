class DummyClass_112642 {
    @Test
    public void canDecodeMultiSentenceUnknownApplicationSpecificMessage() {
        AISMessage aisMessage = AISMessage.create(
                NMEAMessage.fromString("!AIVDM,2,1,8,A,803Iw60F14m1CPH4mDT4RDi@000003RP9iHb@001irBQ0@4gAaI00000261Q,0*04"),
                NMEAMessage.fromString("!AIVDM,2,2,8,A,pGp07IiTPi@BkU5pSwrrbs8219RW=R19RV=R19RVER19RVKtDb>jq20000>4,0*47")
        );

        System.out.println(aisMessage.toString());

        assertEquals(88, ((BinaryBroadcastMessage) aisMessage).getDesignatedAreaCode().intValue());
        assertEquals(4, ((BinaryBroadcastMessage) aisMessage).getFunctionalId().intValue());
        assertEquals(UnknownApplicationSpecificMessage.class, ((BinaryBroadcastMessage) aisMessage).getApplicationSpecificMessage().getClass());
    }

}
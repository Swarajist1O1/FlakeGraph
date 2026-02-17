class DummyClass_112668 {
    @Test
    public void canFlushEmpty() {
        NMEAMessage unfragmentedNMEAMessage = NMEAMessage.fromString("!AIVDM,1,1,,B,15MqdBP000G@qoLEi69PVGaN0D0=,0*3A");
        NMEAMessage fragmentedNMEAMessage1 = NMEAMessage.fromString("!AIVDM,2,1,3,B,55DA><02=6wpPuID000qTf059@DlU<00000000171lMDD4q20LmDp3hB,0*27");
        NMEAMessage fragmentedNMEAMessage2 = NMEAMessage.fromString("!AIVDM,2,2,3,B,p=Mh00000000000,2*4C");

        final ArgumentCaptor<AISMessage> aisMessage = new ArgumentCaptor<>();

        context.checking(new Expectations() {{
            exactly(3).of(aisMessageHandler).accept(with(aisMessage.getMatcher()));
        }});

        aisMessageReceiver.accept(unfragmentedNMEAMessage);
        aisMessageReceiver.accept(fragmentedNMEAMessage1);
        aisMessageReceiver.accept(fragmentedNMEAMessage2);

        ArrayList<NMEAMessage> flush = aisMessageReceiver.flush();

        assertNotNull(flush);
        assertEquals(0, flush.size());
    }

}
class DummyClass_112632 {
    @Test
    public void canReturnRawNmeaMessages() {
        // Test one-liner
        AISMessage aisMessage = AISMessage.create(
            NMEAMessage.fromString("!BSVDM,1,1,,A,1:02Ih001U0d=V:Op85<2aT>0<0F,0*3B")
        );

        NMEAMessage[] nmeaMessages = aisMessage.getNmeaMessages();
        assertNotNull(nmeaMessages);
        assertEquals(1, nmeaMessages.length);
        assertEquals("!BSVDM,1,1,,A,1:02Ih001U0d=V:Op85<2aT>0<0F,0*3B", nmeaMessages[0].getRawMessage());

        // Test two-liner
        aisMessage =AISMessage.create(
            NMEAMessage.fromString("!BSVDM,2,1,5,A,5:02Ih01WrRsEH57J20H5P8u8N222222222222167H66663k085QBS1H,0*55"),
            NMEAMessage.fromString("!BSVDM,2,2,5,A,888888888888880,2*38")
        );

        nmeaMessages = aisMessage.getNmeaMessages();
        assertNotNull(nmeaMessages);
        assertEquals(2, nmeaMessages.length);
        assertEquals("!BSVDM,2,1,5,A,5:02Ih01WrRsEH57J20H5P8u8N222222222222167H66663k085QBS1H,0*55", nmeaMessages[0].getRawMessage());
        assertEquals("!BSVDM,2,2,5,A,888888888888880,2*38", nmeaMessages[1].getRawMessage());
    }

}
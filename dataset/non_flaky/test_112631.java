class DummyClass_112631 {
    @Test
    public void isSerializable() {
        // Type 1
        assertTrue(isSerializable(AISMessage.create(
            NMEAMessage.fromString("!BSVDM,1,1,,A,1:02Ih001U0d=V:Op85<2aT>0<0F,0*3B")
        )));

        // Type 4
        assertTrue(isSerializable(AISMessage.create(
            NMEAMessage.fromString("!AIVDM,1,1,,B,4h3Ovk1udp6I9o>jPHEdjdW000S:,0*0C")
        )));

        // Type 5
        assertTrue(isSerializable(AISMessage.create(
            NMEAMessage.fromString("!BSVDM,2,1,5,A,5:02Ih01WrRsEH57J20H5P8u8N222222222222167H66663k085QBS1H,0*55"),
            NMEAMessage.fromString("!BSVDM,2,2,5,A,888888888888880,2*38")
        )));
    }

}
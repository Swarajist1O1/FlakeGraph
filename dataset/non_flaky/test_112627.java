class DummyClass_112627 {
    @Test
    public void testEquals() {
          assertEquals(AISMessage.create(NMEAMessage.fromString("!AIVDM,1,1,,B,13AkSB0000PhAmJPoTMoiQFT0D1:,0*5E")), AISMessage.create(NMEAMessage.fromString("!AIVDM,1,1,,B,13AkSB0000PhAmJPoTMoiQFT0D1:,0*5E")));
          assertNotEquals(AISMessage.create(NMEAMessage.fromString("!AIVDM,1,1,,B,13AkSB0000PhAmJPoTMoiQFT0D1:,0*5E")), AISMessage.create(NMEAMessage.fromString("!AIVDM,1,1,,A,13AkSB0000PhAmHPoTNcp1Fp0D17,0*00")));
    }

}
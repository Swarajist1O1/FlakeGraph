class DummyClass_112630 {
    @Test(expected = NMEAParseException.class)
    public void canHandleUnparsableNMEAMessage() {
        AISMessage.create(NMEAMessage.fromString("!AIVDM,1,1,,B,13K6th002u9@8P0DEVv2M1up02Pl,0*740008,2*09"));
    }

}
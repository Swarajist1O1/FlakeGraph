class DummyClass_112658 {
    @Test(expected = InvalidMessage.class)
    public void invalid() {
        AISMessage.create(NMEAMessage.fromString("!AIVDM,1,1,,B,58LAM242B9POUKWWW<0a>0<4E<58,0*6E"));
    }

}
class DummyClass_112629 {
    @Test(expected = InvalidMessage.class)
    public void canHandleEmptyMessage() {
        AISMessage.create(NMEAMessage.fromString("!AIVDM,1,1,,B,00,4*21"));
    }

}
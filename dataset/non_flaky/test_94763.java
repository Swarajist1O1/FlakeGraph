class DummyClass_94763 {
    @Test
    public void testBasicListenerWithUnexpectedMessage() {

        BasicListener bl = new BasicListener();
        bl.sentenceRead(AIS_05_1);
        bl.sentenceRead(AIS_05_2);

        assertNull(bl.received);
    }

}
class DummyClass_94759 {
    @Test
    public void testOnMessageWithExpectedMessage() {

        BasicListener bl = new BasicListener();

        bl.sentenceRead(AIS_01);
        assertEquals(bl.received.toString(), MSG_01.toString());
    }

}
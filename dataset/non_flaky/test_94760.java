class DummyClass_94760 {
    @Test
    public void testSequenceListener() {

        SequenceListener sl = new SequenceListener();

        sl.sentenceRead(AIS_05_1);
        assertNull(sl.received);

        sl.sentenceRead(AIS_05_2);
        assertEquals(sl.received.toString(), MSG_05.toString());
    }

}
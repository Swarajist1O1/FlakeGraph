class DummyClass_94762 {
    @Test
    public void testSequenceListenerWithMixedOrder() {

        SequenceListener sl = new SequenceListener();

        sl.sentenceRead(AIS_05_1);
        assertNull(sl.received);

        sl.sentenceRead(AIS_01);
        assertNull(sl.received);

        sl.sentenceRead(AIS_05_2);
        assertNull(sl.received);
    }

}
class DummyClass_99759 {
    @Test(expected = EOFException.class)
    public void consumeUntil_MultipleBuffer_Fails() throws IOException
    {
        consumeUntilTestCycle(2, 8, 0, 17);
    }

}
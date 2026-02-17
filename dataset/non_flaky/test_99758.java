class DummyClass_99758 {
    @Test(expected = EOFException.class)
    public void consumeUntil_SingleBuffer_Fails() throws IOException
    {
        consumeUntilTestCycle(1, 8, 0, 9);
    }

}
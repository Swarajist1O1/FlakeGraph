class DummyClass_99708 {
    @Test
    public void delegatesToInitialPrintStream() throws Exception
    {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(output, true);
        MultiResultLogger underTest = new MultiResultLogger(printStream);

        underTest.println("Very important result");

        assertEquals("Very important result\n", output.toString());
    }

}
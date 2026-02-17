class DummyClass_99710 {
    @Test
    public void delegatesToAdditionalPrintStreams() throws Exception
    {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream additionalPrintStream = new PrintStream(output, true);
        MultiResultLogger underTest = new MultiResultLogger(new PrintStream(NOOP));

        underTest.addStream(additionalPrintStream);
        underTest.println("Very important result");

        assertEquals("Very important result\n", output.toString());
    }

}
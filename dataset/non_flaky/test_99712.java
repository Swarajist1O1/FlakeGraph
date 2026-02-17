class DummyClass_99712 {
    @Test
    public void delegatesPrintlnToAdditionalPrintStreams() throws Exception
    {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream additionalPrintStream = new PrintStream(output, true);
        MultiResultLogger underTest = new MultiResultLogger(new PrintStream(NOOP));

        underTest.addStream(additionalPrintStream);
        underTest.println();

        assertEquals("\n", output.toString());
    }

}
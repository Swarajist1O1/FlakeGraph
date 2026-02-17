class DummyClass_99711 {
    @Test
    public void delegatesPrintfToAdditionalPrintStreams() throws Exception
    {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream additionalPrintStream = new PrintStream(output, true);
        MultiResultLogger underTest = new MultiResultLogger(new PrintStream(NOOP));

        underTest.addStream(additionalPrintStream);
        underTest.printf("%s %s %s", "one", "two", "three");

        assertEquals("one two three", output.toString());
    }

}
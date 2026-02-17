class DummyClass_99709 {
    @Test
    public void printingExceptions() throws Exception
    {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(output, true);
        MultiResultLogger underTest = new MultiResultLogger(printStream);

        underTest.printException(new RuntimeException("Bad things"));

        String stackTrace = output.toString();
        assertTrue("Expected strack trace to be printed but got: " + stackTrace, stackTrace.startsWith("java.lang.RuntimeException: Bad things\n" +
                                                "\tat org.apache.cassandra.stress.util.MultiResultLoggerTest.printingExceptions"));
    }

}
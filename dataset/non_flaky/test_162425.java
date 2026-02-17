class DummyClass_162425 {
    @Test
    public void environmentAndCustomCommandTest() throws IOException {
        String line = getReaderForContainerPort80(alpineEnvVar).readLine();

        assertEquals("An environment variable can be passed into a command", "42", line);
    }

}
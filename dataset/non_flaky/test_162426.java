class DummyClass_162426 {
    @Test
    public void environmentFromMapTest() throws IOException {
        String line = getReaderForContainerPort80(alpineEnvVarFromMap).readLine();

        assertEquals("Environment variables can be passed into a command from a map", "42 and 50", line);
    }

}
class DummyClass_122553 {
    @Test
    public void testSpawn() {
        CommandLine commandLine = mock(CommandLine.class);
        when(commandLine.getArguments()).thenReturn(List.of("program"));
        when(commandLine.getRedirectStderrToStdoutInsteadOfDiscard()).thenReturn(true);
        when(commandLine.programName()).thenReturn("program");
        Path outputPath;
        try (ChildProcess2Impl child = processFactory.spawn(commandLine)) {
            outputPath = child.getOutputPath();
            assertTrue(Files.exists(outputPath));
            assertEquals("rw-------", new UnixPath(outputPath).getPermissions());
            ArgumentCaptor<ProcessBuilder> processBuilderCaptor =
                    ArgumentCaptor.forClass(ProcessBuilder.class);
            verify(starter).start(processBuilderCaptor.capture());
            ProcessBuilder processBuilder = processBuilderCaptor.getValue();
            assertTrue(processBuilder.redirectErrorStream());
            ProcessBuilder.Redirect redirect = processBuilder.redirectOutput();
            assertEquals(ProcessBuilder.Redirect.Type.WRITE, redirect.type());
            assertEquals(outputPath.toFile(), redirect.file());
        }

        assertFalse(Files.exists(outputPath));
    }

}
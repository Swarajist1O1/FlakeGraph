class DummyClass_122583 {
    @Test
    public void testMissingFile() {
        LineEditor lineEditor = mock(LineEditor.class);
        when(lineEditor.onComplete()).thenReturn(List.of("line"));

        TaskContext context = mock(TaskContext.class);
        var editor = new Editor(path.toPath(), lineEditor);
        editor.converge(context);

        assertEquals("line\n", path.readUtf8File());
    }

}
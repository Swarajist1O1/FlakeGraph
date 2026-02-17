class DummyClass_122582 {
    @Test
    public void noop() {
        path.writeUtf8File("line\n");

        LineEditor lineEditor = mock(LineEditor.class);
        when(lineEditor.edit(any())).thenReturn(LineEdit.none());

        Editor editor = new Editor(path.toPath(), lineEditor);
        TaskContext context = mock(TaskContext.class);

        assertFalse(editor.converge(context));

        verify(lineEditor, times(1)).edit(any());

        // Verify the system modification message
        verify(context, times(0)).recordSystemModification(any(), any());

        // Verify same contents
        assertEquals("line\n", path.readUtf8File());
    }

}
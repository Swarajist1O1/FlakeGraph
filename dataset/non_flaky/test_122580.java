class DummyClass_122580 {
    @Test
    public void testEdit() {
        path.writeUtf8File(joinLines("first", "second", "third"));

        LineEditor lineEditor = mock(LineEditor.class);
        when(lineEditor.edit(any())).thenReturn(
                LineEdit.none(), // don't edit the first line
                LineEdit.remove(), // remove the second
                LineEdit.replaceWith("replacement")); // replace the third

        Editor editor = new Editor(path.toPath(), lineEditor);
        TaskContext context = mock(TaskContext.class);

        assertTrue(editor.converge(context));

        verify(lineEditor, times(3)).edit(any());

        // Verify the system modification message
        ArgumentCaptor<String> modificationMessage = ArgumentCaptor.forClass(String.class);
        verify(context).recordSystemModification(any(), modificationMessage.capture());
        assertEquals(
                "Patching file /file:\n-second\n-third\n+replacement\n",
                modificationMessage.getValue());

        // Verify the new contents of the file:
        assertEquals(joinLines("first", "replacement"), path.readUtf8File());
    }

}
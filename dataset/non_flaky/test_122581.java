class DummyClass_122581 {
    @Test
    public void testInsert() {
        path.writeUtf8File(joinLines("second", "eight", "fifth", "seventh"));

        LineEditor lineEditor = mock(LineEditor.class);
        when(lineEditor.edit(any())).thenReturn(
                LineEdit.insertBefore("first"), // insert first, and keep the second line
                LineEdit.replaceWith("third", "fourth"), // remove eight, and replace with third and fourth instead
                LineEdit.none(), // Keep fifth
                LineEdit.insert(List.of("sixth"), // insert sixth before seventh
                        List.of("eight"))); // add eight after seventh

        Editor editor = new Editor(path.toPath(), lineEditor);
        TaskContext context = mock(TaskContext.class);

        assertTrue(editor.converge(context));

        // Verify the system modification message
        ArgumentCaptor<String> modificationMessage = ArgumentCaptor.forClass(String.class);
        verify(context).recordSystemModification(any(), modificationMessage.capture());
        assertEquals(
                "Patching file /file:\n" +
                        "+first\n" +
                        "-eight\n" +
                        "+third\n" +
                        "+fourth\n" +
                        "+sixth\n" +
                        "+eight\n",
                modificationMessage.getValue());

        // Verify the new contents of the file:
        assertEquals(joinLines("first", "second", "third", "fourth", "fifth", "sixth", "seventh", "eight"),
                path.readUtf8File());
    }

}
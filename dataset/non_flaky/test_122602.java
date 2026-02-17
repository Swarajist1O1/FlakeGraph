class DummyClass_122602 {
    @Test
    public void basic() {
        FileSystem fileSystem = TestFileSystem.create();
        Path templatePath = fileSystem.getPath("/example.vm");
        String templateContent = "a $x, $y b";
        new UnixPath(templatePath).writeUtf8File(templateContent);

        Path toPath = fileSystem.getPath("/example");
        TaskContext taskContext = mock(TaskContext.class);
        boolean converged = Template.at(templatePath)
                .set("x", "foo")
                .set("y", "bar")
                .getFileWriterTo(toPath)
                .converge(taskContext);

        assertTrue(converged);

        String actualContent = new UnixPath(toPath).readUtf8File();
        assertEquals("a foo, bar b", actualContent);
    }

}
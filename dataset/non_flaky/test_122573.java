class DummyClass_122573 {
    @Test
    public void okIfParentExists() {
        String path = "/dir";
        MakeDirectory makeDirectory = new MakeDirectory(fileSystem.getPath(path));
        assertTrue(makeDirectory.converge(context));
        assertTrue(Files.isDirectory(fileSystem.getPath(path)));

        MakeDirectory makeDirectory2 = new MakeDirectory(fileSystem.getPath(path));
        assertFalse(makeDirectory2.converge(context));
    }

}
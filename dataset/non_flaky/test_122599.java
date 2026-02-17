class DummyClass_122599 {
    @Test
    public void testWrite() {
        final String content = "content";
        final String permissions = "rwxr-xr-x";
        final String owner = "owner";
        final String group = "group";

        Path path = fileSystem.getPath("/opt/vespa/tmp/file.txt");
        FileWriter writer = new FileWriter(path, () -> content)
                .withPermissions(permissions)
                .withOwner(owner)
                .withGroup(group)
                .onlyIfFileDoesNotAlreadyExist();
        assertTrue(writer.converge(context));
        verify(context, times(1)).recordSystemModification(any(), eq("Creating file " + path));

        UnixPath unixPath = new UnixPath(path);
        assertEquals(content, unixPath.readUtf8File());
        assertEquals(permissions, unixPath.getPermissions());
        assertEquals(owner, unixPath.getOwner());
        assertEquals(group, unixPath.getGroup());
        Instant fileTime = unixPath.getLastModifiedTime();

        // Second time is a no-op.
        assertFalse(writer.converge(context));
        assertEquals(fileTime, unixPath.getLastModifiedTime());
    }

}
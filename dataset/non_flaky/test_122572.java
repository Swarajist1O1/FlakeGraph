class DummyClass_122572 {
    @Test
    public void exceptionIfMissingParent() {
        String path = "/parent/dir";
        MakeDirectory makeDirectory = new MakeDirectory(fileSystem.getPath(path));

        try {
            makeDirectory.converge(context);
        } catch (UncheckedIOException e) {
            if (e.getCause() instanceof NoSuchFileException) {
                return;
            }
            throw e;
        }
        fail();
    }

}
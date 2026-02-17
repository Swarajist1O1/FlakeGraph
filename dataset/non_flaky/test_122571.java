class DummyClass_122571 {
    @Test
    public void newDirectory() {
        verifySystemModifications(
                "Creating directory " + path,
                "Changing owner of /parent/dir from user to test-owner",
                "Changing group of /parent/dir from group to test-group");

        owner = "new-owner";
        verifySystemModifications("Changing owner of /parent/dir from test-owner to new-owner");

        group = "new-group";
        verifySystemModifications("Changing group of /parent/dir from test-group to new-group");

        permissions = "--x---r--";
        verifySystemModifications("Changing permissions of /parent/dir from rwxr----x to --x---r--");
    }

}
class DummyClass_122584 {
    @Test
    public void trivial() {
        assertConvergence("Creating file /dir/file.txt",
                "Changing owner of /dir/file.txt from user to owner",
                "Changing group of /dir/file.txt from group to group1",
                "Changing permissions of /dir/file.txt from rw-r--r-- to rw-r-xr--");

        content = "new-content";
        assertConvergence("Patching file /dir/file.txt");

        owner = "new-owner";
        assertConvergence("Changing owner of /dir/file.txt from owner to " +
                        owner);

        group = "new-group1";
        assertConvergence("Changing group of /dir/file.txt from group1 to new-group1");

        permissions = "rwxr--rwx";
        assertConvergence("Changing permissions of /dir/file.txt from rw-r-xr-- to " +
                permissions);
    }

}
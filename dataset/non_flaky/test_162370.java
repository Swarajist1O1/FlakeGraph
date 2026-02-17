class DummyClass_162370 {
    @Test
    public void validNames() {
        testValid("myname:latest");
        testValid("myname:latest");
        testValid("repo/my-name:1.0");
        testValid("repo.foo.com:1234/my-name:1.0");
        testValid("repo.foo.com/my-name:1.0");
        testValid("repo.foo.com:1234/repo_here/my-name:1.0");
        testValid("repo.foo.com:1234/repo-here/my-name@sha256:1234abcd1234abcd1234abcd1234abcd");
        testValid("repo.foo.com:1234/my-name@sha256:1234abcd1234abcd1234abcd1234abcd");
        testValid("1.2.3.4/my-name:1.0");
        testValid("1.2.3.4:1234/my-name:1.0");
        testValid("1.2.3.4/repo-here/my-name:1.0");
        testValid("1.2.3.4:1234/repo-here/my-name:1.0");
    }

}
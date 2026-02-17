class DummyClass_162371 {
    @Test
    public void invalidNames() {
        testInvalid("myname");
        testInvalid(":latest");
        testInvalid("/myname:latest");
        testInvalid("/myname@sha256:latest");
        testInvalid("/myname@sha256:gggggggggggggggggggggggggggggggg");
        testInvalid("repo:notaport/myname:latest");
    }

}
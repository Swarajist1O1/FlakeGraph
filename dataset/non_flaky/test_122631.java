class DummyClass_122631 {
    @Test
    public void testArchitectures() {
        assertEquals("x86_64", YumPackageName.fromString("docker.x86_64").getArchitecture().get());
        assertEquals("i686", YumPackageName.fromString("docker.i686").getArchitecture().get());
        assertEquals("noarch", YumPackageName.fromString("docker.noarch").getArchitecture().get());
    }

}
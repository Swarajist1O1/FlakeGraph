class DummyClass_122632 {
    @Test
    public void unrecognizedArchitectureGetsGobbledUp() {
        YumPackageName packageName = YumPackageName.fromString("docker-engine-selinux-1.12.6-1.el7.i486");
        // This is not a great feature - please use YumPackageName.Builder instead.
        assertEquals("1.el7.i486", packageName.getRelease().get());
    }

}
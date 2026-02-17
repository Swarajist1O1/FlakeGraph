class DummyClass_122629 {
    @Test
    public void testBuilder() {
        YumPackageName yumPackage = new YumPackageName.Builder("docker")
                .setEpoch("2")
                .setVersion("1.12.6")
                .setRelease("71.git3e8e77d.el7.centos.1")
                .setArchitecture("x86_64")
                .build();
        assertEquals("2:docker-1.12.6-71.git3e8e77d.el7.centos.1.x86_64", yumPackage.toName());
    }

}
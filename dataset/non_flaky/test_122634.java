class DummyClass_122634 {
    @Test
    public void testSubset() {
        YumPackageName yumPackage = new YumPackageName.Builder("docker")
                .setVersion("1.12.6")
                .build();

        assertTrue(yumPackage.isSubsetOf(yumPackage));
        assertTrue(yumPackage.isSubsetOf(new YumPackageName.Builder("docker")
                .setVersion("1.12.6")
                .setEpoch("2")
                .setRelease("71.git3e8e77d.el7.centos.1")
                .setArchitecture("x86_64")
                .build()));
        assertFalse(yumPackage.isSubsetOf(new YumPackageName.Builder("docker")
                .setVersion("1.13.1")
                .build()));
    }

}
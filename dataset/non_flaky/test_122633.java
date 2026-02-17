class DummyClass_122633 {
    @Test
    public void failParsingOfPackageNameWithEpochAndArchitecture() {
        try {
            YumPackageName.fromString("epoch:docker-engine-selinux-1.12.6-1.el7.x86_64");
            fail();
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), containsStringIgnoringCase("epoch"));
        }
    }

}
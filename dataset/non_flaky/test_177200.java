class DummyClass_177200 {
    @Test
    public void verifyTomcatVersion() {
        assertThat(TomcatVersion.major()).isEqualTo(tomcatMajorVersion);
        assertThat(TomcatVersion.minor()).isEqualTo(tomcatMinorVersion);
    }

}
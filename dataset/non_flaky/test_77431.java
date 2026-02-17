class DummyClass_77431 {
    @Test(expected = IllegalStateException.class)
    public void testExceptionOnTooManyMajors() {
        new BwcVersions(
            asList(formatVersionToLine("5.6.12"), formatVersionToLine("6.5.0"), formatVersionToLine("7.0.0")),
            Version.fromString("6.5.0")
        );
    }

}
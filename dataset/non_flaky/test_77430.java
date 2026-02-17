class DummyClass_77430 {
    @Test(expected = IllegalStateException.class)
    public void testExceptionOnNonCurrent() {
        new BwcVersions(singletonList(formatVersionToLine("6.5.0")), Version.fromString("7.0.0"));
    }

}
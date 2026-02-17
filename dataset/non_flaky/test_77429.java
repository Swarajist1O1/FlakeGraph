class DummyClass_77429 {
    @Test(expected = IllegalArgumentException.class)
    public void testExceptionOnEmpty() {
        new BwcVersions(asList("foo", "bar"), Version.fromString("7.0.0"));
    }

}
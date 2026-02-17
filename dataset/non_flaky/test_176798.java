class DummyClass_176798 {
    @Test
    public void shouldCaptureValuesFromMinimalPattern() throws Exception {
        capturer.captureValuesFromPattern("(.*)", Lists.newArrayList("hello"), "world");
        verify(world).put("hello", "world");
    }

}
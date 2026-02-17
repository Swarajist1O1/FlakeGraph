class DummyClass_176797 {
    @Test
    public void shouldCaptureValuesFromSimplePattern() throws Exception {
        capturer.captureValuesFromPattern("(.*) world", Lists.newArrayList("hello"), "Hi world");
        verify(world).put("hello", "Hi");
    }

}
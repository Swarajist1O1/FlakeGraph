class DummyClass_176799 {
    @Test
    public void shouldNotInvokeCaptureValuesFromPatternIfNoGroupsFound() throws Exception {
        capturer.capture("hello", "world");
        verify(capturer, never()).captureValuesFromPattern(anyString(), anyListOf(String.class), anyString());
    }

}
class DummyClass_176800 {
    @Test
    public void shouldInvokeCaptureValuesFromPatternIfAtLeastOneGroupFound() throws Exception {
        capturer.capture("{(hello)}", "world");
        verify(capturer).captureValuesFromPattern(anyString(), anyListOf(String.class), anyString());
    }

}
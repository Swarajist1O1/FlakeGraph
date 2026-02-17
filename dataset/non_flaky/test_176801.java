class DummyClass_176801 {
    @Test
    public void shouldNotInvokeCaptureValuesFromPatternIfRegexDoesNotMatchValue() throws Exception {
        capturer.capture("{(hello)} Riga", "hello world");
        verify(capturer, never()).captureValuesFromPattern(anyString(), anyListOf(String.class), anyString());
    }

}
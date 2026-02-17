class DummyClass_175816 {
  @Test
  public void testWidgetSelected_MalformedURL() {
    SelectionEvent selectionEvent = getEvent(MALFORMED_URL);

    new OpenUriSelectionListener(queryParameterProvider, errorHandler, browserSupport).widgetSelected(selectionEvent);
    verify(errorHandler).handle(captor.capture(), any(URI.class));
    assertThat(captor.getValue(), instanceOf(MalformedURLException.class));
  }

}
class DummyClass_175814 {
  @Test
  public void testWidgetSelected_InvalidURI() {
    SelectionEvent selectionEvent = getEvent(INVALID_URI);

    new OpenUriSelectionListener(queryParameterProvider, errorHandler, browserSupport).widgetSelected(selectionEvent);
    verify(errorHandler).handle(captor.capture(), any(URI.class));
    assertThat(captor.getValue(), instanceOf(URISyntaxException.class));
  }

}
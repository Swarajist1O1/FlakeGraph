class DummyClass_175815 {
  @Test
  public void testWidgetDefaultSelected_InvalidURI() {
    SelectionEvent selectionEvent = getEvent(INVALID_URI);

    new OpenUriSelectionListener(queryParameterProvider, errorHandler, browserSupport).widgetDefaultSelected(selectionEvent);
    verify(errorHandler).handle(captor.capture(), any(URI.class));
    assertThat(captor.getValue(), instanceOf(URISyntaxException.class));
  }

}
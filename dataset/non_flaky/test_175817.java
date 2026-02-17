class DummyClass_175817 {
  @Test
  public void testWidgetDefaultSelected_MalformedURL() {
    SelectionEvent selectionEvent = getEvent(MALFORMED_URL);

    new OpenUriSelectionListener(queryParameterProvider, errorHandler, browserSupport).widgetDefaultSelected(selectionEvent);
    verify(errorHandler).handle(captor.capture(), any(URI.class));
    assertThat(captor.getValue(), instanceOf(MalformedURLException.class));
  }

}
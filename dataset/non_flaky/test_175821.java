class DummyClass_175821 {
  @Test
  public void testWidgetDefaultSelected_successful() throws PartInitException, MalformedURLException {
    SelectionEvent selectionEvent = getEvent(VALID_URI);
    when(queryParameterProvider.getParameters()).thenReturn(Collections.singletonMap(URL_PARAM_PROJECT, PROJECT_ID));

    new OpenUriSelectionListener(queryParameterProvider, errorHandler, browserSupport)
      .widgetDefaultSelected(selectionEvent);
    verify(errorHandler, never()).handle(any(Exception.class), any(URI.class));
    verify(browser).openURL(new URL(VALID_URI + "?project=" + PROJECT_ID));
  }

}
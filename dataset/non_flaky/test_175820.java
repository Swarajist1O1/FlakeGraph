class DummyClass_175820 {
  @Test
  public void testWidgetSelected_successful() throws PartInitException, MalformedURLException {
    SelectionEvent selectionEvent = getEvent(VALID_URI);
    when(queryParameterProvider.getParameters()).thenReturn(Collections.singletonMap(URL_PARAM_PROJECT, PROJECT_ID));

    new OpenUriSelectionListener(queryParameterProvider, errorHandler, browserSupport).widgetSelected(selectionEvent);
    verify(errorHandler, never()).handle(any(Exception.class), any(URI.class));
    verify(browser).openURL(new URL(VALID_URI + "?project=" + PROJECT_ID));
  }

}
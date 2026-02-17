class DummyClass_175819 {
  @Test
  public void testWidgetDefaultSelected_errorInvokingBrowser() throws PartInitException {
    SelectionEvent selectionEvent = getEvent(VALID_URI);
    doThrow(new PartInitException("fake exception")).when(browser).openURL(any(URL.class));

    new OpenUriSelectionListener(queryParameterProvider, errorHandler, browserSupport)
      .widgetDefaultSelected(selectionEvent);
    verify(errorHandler).handle(captor.capture(), any(URI.class));
    assertThat(captor.getValue(), instanceOf(PartInitException.class));
  }

}
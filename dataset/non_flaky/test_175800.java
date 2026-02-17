class DummyClass_175800 {
  @Test
  public void testSelectionChanged_emptySelection() {
    when(event.getSelection()).thenReturn(new StructuredSelection());
    listener.selectionChanged(event);
    verify(projectSelector).clearStatusLink();
  }

}
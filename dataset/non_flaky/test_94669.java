class DummyClass_94669 {
  @Test public void readSettingsFrame() throws IOException {
      @Override public void settings(boolean clearPrevious, Settings settings) {
        assertFalse(clearPrevious); // No clearPrevious in HTTP/2.
        assertEquals(reducedTableSizeBytes, settings.getHeaderTableSize());
        assertEquals(false, settings.getEnablePush(true));
      }

}
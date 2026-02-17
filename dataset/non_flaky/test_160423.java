class DummyClass_160423 {
  @Test
  public void defaultBlockSelector_shouldThrowBadRequestException() {
    assertThrows(BadRequestException.class, () -> blockSelectorFactory.defaultBlockSelector("a"));
  }

}
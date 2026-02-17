class DummyClass_160436 {
  @Test
  public void defaultStateSelector_shouldThrowBadRequestException() {
    assertThrows(BadRequestException.class, () -> factory.defaultStateSelector("a"));
  }

}
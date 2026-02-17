class DummyClass_160437 {
  @Test
  public void byBlockRootSelector_shouldThrowBadRequestException() {
    assertThrows(BadRequestException.class, () -> factory.byBlockRootStateSelector("a"));
  }

}
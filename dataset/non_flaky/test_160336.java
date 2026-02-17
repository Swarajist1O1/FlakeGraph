class DummyClass_160336 {
  @Test
    public int increment(final int testVal) {
      senderNode = MessageContext.getSender();
      return testVal + 1;
    }

}
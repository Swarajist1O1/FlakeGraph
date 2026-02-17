class DummyClass_78284 {
  @Test
  public void testCoderIsSerializableWithWellKnownCoderType() {
    CoderProperties.coderSerializable(TimerDataCoder.of(GlobalWindow.Coder.INSTANCE));
  }

}
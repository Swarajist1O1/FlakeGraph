class DummyClass_175743 {
  @Test
  public void testSpecifyVersionTooltip() {
    Assert.assertEquals(
        "If checked, stops the previously running version when "
        + "deploying a new version that receives all traffic.",
        Messages.getString("tooltip.stop.previous.version"));
  }

}
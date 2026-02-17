class DummyClass_175748 {
  @Test
  public void testUncheckStopPreviousVersionButtonWhenDisabled() {
    deployPanel = createPanel(true /* requireValues */);

    Button promoteButton = getButtonWithText("Promote the deployed version to receive all traffic");
    Button stopButton = getButtonWithText("Stop previous version");
    SWTBotCheckBox promote = new SWTBotCheckBox(promoteButton);
    SWTBotCheckBox stop = new SWTBotCheckBox(stopButton);

    // Initially, everything is checked and enabled.
    assertTrue(promoteButton.getSelection());
    assertTrue(stopButton.getSelection());
    assertTrue(stopButton.getEnabled());

    promote.click();
    assertFalse(promoteButton.getSelection());
    assertFalse(stopButton.getSelection());
    assertFalse(stopButton.getEnabled());

    promote.click();
    assertTrue(promoteButton.getSelection());
    assertTrue(stopButton.getSelection());
    assertTrue(stopButton.getEnabled());

    stop.click();
    assertTrue(promoteButton.getSelection());
    assertFalse(stopButton.getSelection());
    assertTrue(stopButton.getEnabled());

    promote.click();
    assertFalse(promoteButton.getSelection());
    assertFalse(stopButton.getSelection());
    assertFalse(stopButton.getEnabled());

    promote.click();
    assertTrue(promoteButton.getSelection());
    assertFalse(stopButton.getSelection());
    assertTrue(stopButton.getEnabled());
  }

}
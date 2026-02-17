class DummyClass_176931 {
  @Test
  public void testServingLayerSecure() throws Exception {
    Path keystoreFile = SecureAPIConfigIT.buildKeystoreFile();
    Map<String,Object> overlay = buildOverlay();
    overlay.put("oryx.serving.api.keystore-file", "\"" + keystoreFile + "\"");
    overlay.put("oryx.serving.api.keystore-password", "oryxpass");
    Config config = ConfigUtils.overlayOn(overlay, ConfigUtils.getDefault());
    try {
      doTestServingLayer(config);
    } finally {
      Files.delete(Paths.get(config.getString("oryx.serving.api.keystore-file")));
    }
  }

}
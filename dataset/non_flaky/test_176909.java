class DummyClass_176909 {
  @Test
  public void testModelContent() throws Exception {
    Path tempDir = getTempDir();
    Path modelDir = tempDir.resolve("model");

    Map<String,Object> overlayConfig = new HashMap<>();
    overlayConfig.put("oryx.batch.update-class", ALSUpdate.class.getName());
    ConfigUtils.set(overlayConfig, "oryx.batch.storage.data-dir", tempDir.resolve("data"));
    ConfigUtils.set(overlayConfig, "oryx.batch.storage.model-dir", modelDir);
    overlayConfig.put("oryx.batch.streaming.generation-interval-sec", 10);
    overlayConfig.put("oryx.batch.streaming.block-interval-sec", 1);
    overlayConfig.put("oryx.ml.eval.test-fraction", 0);
    overlayConfig.put("oryx.als.implicit", false);
    overlayConfig.put("oryx.als.hyperparams.lambda", 0.0001);
    overlayConfig.put("oryx.als.hyperparams.features", 2);
    Config config = ConfigUtils.overlayOn(overlayConfig, getConfig());

    startMessaging();

    ModelContentDataGenerator generator = new ModelContentDataGenerator();
    List<Pair<String, String>> updates = startServerProduceConsumeTopics(
        config,
        generator,
        generator.getSentData().size(),
        20);

    Collection<String> modelUsers = null;
    Collection<String> modelItems = null;
    Map<String,Collection<String>> knownUsersItems = new HashMap<>();

    for (Pair<String, String> km : updates) {
      String type = km.getFirst();
      String value = km.getSecond();
      log.debug("{} = {}", type, value);

      if ("UP".equals(type)) {

        List<?> update = MAPPER.readValue(value, List.class);
        if ("X".equals(update.get(0).toString())) {
          String userID = update.get(1).toString();
          @SuppressWarnings("unchecked")
          Collection<String> userKnownItems = (Collection<String>) update.get(3);
          knownUsersItems.put(userID, new ArrayList<>(userKnownItems));
        }

      } else { // "MODEL"

        PMML pmml = PMMLUtils.fromString(value);
        modelUsers = AppPMMLUtils.getExtensionContent(pmml, "XIDs");
        modelItems = AppPMMLUtils.getExtensionContent(pmml, "YIDs");

      }

    }

    assertContainsSame(Arrays.asList("A0", "B1", "C2"), modelUsers);
    assertContainsSame(Arrays.asList("A0", "B1", "C2", "D3"), modelItems);
    assertContainsSame(Arrays.asList("A0", "B1", "C2", "D3"), knownUsersItems.get("A0"));
    assertContainsSame(Arrays.asList("C2", "D3"), knownUsersItems.get("B1"));
    assertContainsSame(Arrays.asList("D3"), knownUsersItems.get("C2"));
  }

}
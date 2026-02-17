class DummyClass_98050 {
  @Test
  public void testFileUploadWithOptions() {

    String fileName = createTempFileWithContent((1027) + 7000);

    AtomicReference<MongoGridFsClient> gridFsClient = new AtomicReference<>();

    Promise<MongoGridFsClient> gridFsClientPromise = Promise.promise();

    JsonObject meta = new JsonObject();
    meta.put("nick_name", "Puhi the eel");

    GridFsUploadOptions options = new GridFsUploadOptions();
    options.setMetadata(meta);

    mongoClient.createGridFsBucketService("fs", gridFsClientPromise);

    gridFsClientPromise.future().compose(mongoGridFsClient -> {
      assertNotNull(mongoGridFsClient);
      gridFsClient.set(mongoGridFsClient);
      Promise<Void> dropPromise = Promise.promise();
      mongoGridFsClient.drop(dropPromise);
      return dropPromise.future();
    }).compose(dropped -> {
      Promise<String> uploadPromise = Promise.promise();
      gridFsClient.get().uploadFileWithOptions(fileName, options, uploadPromise);
      return uploadPromise.future();
    }).compose(id -> {
      assertNotNull(id);
      testComplete();
      return Future.succeededFuture();
    }).onComplete(event -> {
      if (event.failed()) {
        fail(event.cause());
      }
    });
    await();
  }

}
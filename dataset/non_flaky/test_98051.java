class DummyClass_98051 {
  @Test
  public void testFindWithMetadata() {
    String fileName = createTempFileWithContent((1024 * 3) + 70);

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
      Promise<List<String>> findPromise = Promise.promise();
      JsonObject query = new JsonObject().put("metadata.nick_name", "Puhi the eel");
      gridFsClient.get().findIds(query, findPromise);
      return findPromise.future();
    }).compose(list -> {
      assertTrue(list.size() > 0);
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
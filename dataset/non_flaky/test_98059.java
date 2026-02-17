class DummyClass_98059 {
  @Test
  public void testStreamUploadWithOptions() {
    String fileName = createTempFileWithContent(1024);
    GridFsUploadOptions options = new GridFsUploadOptions();
    options.setChunkSizeBytes(1024);
    options.setMetadata(new JsonObject().put("meta_test", "Kamapua`a"));

    AtomicReference<MongoGridFsClient> gridFsClient = new AtomicReference<>();

    Promise<MongoGridFsClient> gridFsClientPromise = Promise.promise();

    mongoClient.createGridFsBucketService("fs", gridFsClientPromise);

    gridFsClientPromise.future().compose(mongoGridFsClient -> {
      assertNotNull(mongoGridFsClient);
      gridFsClient.set(mongoGridFsClient);
      Promise<Void> dropPromise = Promise.promise();
      mongoGridFsClient.drop(dropPromise);
      return dropPromise.future();
    }).compose(dropped -> {
      Promise<AsyncFile> openPromise = Promise.promise();
      vertx.fileSystem().open(fileName, new OpenOptions(), openPromise);
      return openPromise.future();
    }).compose(asyncFile -> {
      Promise<String> uploadedPromise = Promise.promise();
      gridFsClient.get().uploadByFileNameWithOptions(asyncFile, fileName, options, uploadedPromise);
      return uploadedPromise.future();
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
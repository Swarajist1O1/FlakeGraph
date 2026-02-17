class DummyClass_98058 {
  @Test
  public void testStreamUpload() {
    String fileName = createTempFileWithContent(1024);

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
      gridFsClient.get().uploadByFileName(asyncFile, fileName, uploadedPromise);
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
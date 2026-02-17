class DummyClass_98049 {
  @Test
  public void testBigFileUpload() {
    String originalFileName = createTempFileWithContent((1024 * 50) + 16);
    long originalLength = new File(originalFileName).length();
    String copiedFileName = createTempFile();

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
      Promise<String> uploadPromise = Promise.promise();
      gridFsClient.get().uploadFile(originalFileName, uploadPromise);
      return uploadPromise.future();
    }).compose(id -> {
      assertNotNull(id);
      Promise<Long> downloadPromise = Promise.promise();
      gridFsClient.get().downloadFileAs(originalFileName, copiedFileName, downloadPromise);
      return downloadPromise.future();
    }).compose(length -> {
      assertEquals(originalLength, length.longValue());
      return Future.succeededFuture();
    }).onComplete(event -> {
      if (event.failed()) {
        fail(event.cause());
      } else {
        testComplete();
      }
    });
    await();
  }

}
class DummyClass_98061 {
  @Test
  public void testFileDownloadById() {
    String fileName = createTempFileWithContent(1024);
    String asFileName = createTempFile();

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
      gridFsClient.get().uploadFile(fileName, uploadPromise);
      return uploadPromise.future();
    }).compose(id -> {
      Promise<Long> downloadPromise = Promise.promise();
      gridFsClient.get().downloadFileByID(id, asFileName, downloadPromise);
      return downloadPromise.future();
    }).compose(length -> {
      assertEquals(1024L, length.longValue());
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
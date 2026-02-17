class DummyClass_98048 {
  @Test
  public void testFileUpload() {

    long fileLength = (1024 * 3) + 70;
    String fileName = createTempFileWithContent(fileLength);
    String downloadFileName = createTempFile();

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
      assertNotNull(id);
      Promise<Long> downloadPromise = Promise.promise();
      gridFsClient.get().downloadFileAs(fileName, downloadFileName, downloadPromise);
      return downloadPromise.future();
    }).compose(length -> {
      assertEquals((long)length, fileLength);
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
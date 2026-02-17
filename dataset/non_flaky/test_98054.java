class DummyClass_98054 {
  @Test
  public void testDownloadStream() {
    long fileLength = (1024 * 3) + 70;
    String fileName = createTempFileWithContent(fileLength);
    String downloadFileName = createTempFile();

    AtomicReference<MongoGridFsClient> gridFsClient = new AtomicReference<>();

    Promise<MongoGridFsClient> gridFsClientPromise = Promise.promise();

    mongoClient.createDefaultGridFsBucketService(gridFsClientPromise);

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
      Promise<AsyncFile> openPromise = Promise.promise();
      vertx.fileSystem().open(downloadFileName, new OpenOptions().setWrite(true), openPromise);
      return openPromise.future();
    }).compose(asyncFile -> {
      Promise<Long> downloadedPromise = Promise.promise();
      gridFsClient.get().downloadByFileName(asyncFile, fileName, downloadedPromise);
      return downloadedPromise.future();
    }).compose(length -> {
      assertTrue(fileLength == length);
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
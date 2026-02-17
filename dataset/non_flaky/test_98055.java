class DummyClass_98055 {
  @Test
  public void testDownloadStreamById() {
    long fileLength = (1027) + 7000;
    String fileName = createTempFileWithContent(fileLength);
    String downloadFileName = createTempFile();

    AtomicReference<MongoGridFsClient> gridFsClient = new AtomicReference<>();
    AtomicReference<String> idCreated = new AtomicReference<>();

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
      idCreated.set(id);
      Promise<AsyncFile> openPromise = Promise.promise();
      vertx.fileSystem().open(downloadFileName, new OpenOptions().setWrite(true), openPromise);
      return openPromise.future();
    }).compose(asyncFile -> {
      Promise<Long> downloadedPromise = Promise.promise();
      gridFsClient.get().downloadById(asyncFile, idCreated.get(), downloadedPromise);
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
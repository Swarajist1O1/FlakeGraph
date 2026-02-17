class DummyClass_98053 {
  @Test
  public void testDrop() {
    createTempFileWithContent((1024 * 3) + 70);

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
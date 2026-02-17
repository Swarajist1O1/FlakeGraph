class DummyClass_98047 {
  @Test
  public void testDelete() {
    String fileName = createTempFileWithContent((1024 * 3) + 70);

    AtomicReference<MongoGridFsClient> gridFsClient = new AtomicReference<>();

    Promise<MongoGridFsClient> mongoGridFsPromise = Promise.promise();

    mongoClient.createGridFsBucketService("fs", mongoGridFsPromise);

    mongoGridFsPromise.future().compose(mongoGridFsClient -> {
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
      Promise<Void> deletePromise = Promise.promise();
      gridFsClient.get().delete(id, deletePromise);
      return deletePromise.future();
    }).onComplete(event -> {
      if (event.succeeded()) {
        testComplete();
      } else {
        fail(event.cause());
      }
    });
    await();
  }

}
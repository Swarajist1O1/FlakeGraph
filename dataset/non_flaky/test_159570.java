class DummyClass_159570 {
    @Test
    public void nonLooperThreadShouldNotifyLooperThreadAboutCommit() {
        final CountDownLatch mainThreadReady = new CountDownLatch(1);
        final CountDownLatch backgroundThreadClosed = new CountDownLatch(1);
        final CountDownLatch numberOfInvocation = new CountDownLatch(1);
        Thread thread = new Thread() {
            @Override
            public void run() {
                TestHelper.awaitOrFail(mainThreadReady);
                Realm realm = Realm.getInstance(realmConfig);
                realm.beginTransaction();
                realm.createObject(AllTypes.class);
                realm.commitTransaction();
                realm.close();
                backgroundThreadClosed.countDown();
            }

}
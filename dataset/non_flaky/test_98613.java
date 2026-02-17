class DummyClass_98613 {
    @Test
    public void testIssue306() throws InterruptedException {
        int size = 100;
        final CountDownLatch count = new CountDownLatch(size);
        final List<Integer> error = new ArrayList<Integer>();
        for (int index = 0; index < size; index++) {
            new Thread() {
                public void run() {
                    try {
                        El.eval("1+1");
                    }
                    catch (Exception e) {
                        error.add(1);
                    }
                    finally {
                        count.countDown();
                    }
                }

}
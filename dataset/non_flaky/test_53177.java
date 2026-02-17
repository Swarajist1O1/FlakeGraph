class DummyClass_53177 {
    @Test
    public void testParsingParallel() throws IOException, InterruptedException {
        Thread[] threadArray = new Thread[1000];
        for (int i = 0; i < 1000; i++) {

            threadArray[i] = new Thread(() -> {
                try {
                    Date d = JsonDateDeserializer.getDate(testDateString, new JsonLocation(null, 22, 0, 0));
                    if(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(testDateString).getTime() != d.getTime())
                    {
                        throw new Exception("Unexpected date");
                    }
                } catch (Exception e) {
                    exceptionOccured = e;
                }
            });
        }
        for (int i = 0; i < 1000; i++) {
            threadArray[i].start();
        }
        for (int i = 0; i < 1000; i++) {
            threadArray[i].join();
        }
        Assert.assertNull(exceptionOccured);
    }

}
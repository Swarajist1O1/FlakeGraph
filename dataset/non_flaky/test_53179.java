class DummyClass_53179 {
    @Test
    public void testFormattingParallel() throws IOException, InterruptedException {
        Thread[] threadArray = new Thread[1000];
        for (int i = 0; i < 1000; i++) {

            threadArray[i] = new Thread(() -> {
                try {
                    Date now = new Date();
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    JsonGenerator gen = new JsonFactory().createGenerator(bos);
                    new JsonDateSerializer().serialize(now, gen, null);
                    gen.close();
                    if (!String.format("\"%s\"", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(now))
                            .equals(bos.toString())) {
                        throw new Exception("Unexpected date");
                    }

                } catch (Exception e) {
                    exceptionOccured = e;
                }
            });
        }
        for (

                int i = 0; i < 1000; i++) {
            threadArray[i].start();
        }
        for (int i = 0; i < 1000; i++) {
            threadArray[i].join();
        }
        Assert.assertNull(exceptionOccured);
    }

}
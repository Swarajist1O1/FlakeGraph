class DummyClass_135033 {
    @Test
    public void testPerformance() {

        String ie9Header = "Wed, 12 Feb 2014 04:43:29 GMT; length=142951";

        long timestamp = System.currentTimeMillis();
        for (int i=0; i < 1000; i++) {
            ie9Header.replaceAll(";.*$", "");
        }
        long ts1 = System.currentTimeMillis() - timestamp;

        timestamp = System.currentTimeMillis();

        for (int i=0; i < 1000; i++) {
            int index = ie9Header.indexOf(';');
            final String trimmedDate = index >=0 ? ie9Header.substring(0, index) : ie9Header;
        }

        long ts2 = System.currentTimeMillis() - timestamp;

        Assert.assertTrue(ts2 < ts1);

    }

}
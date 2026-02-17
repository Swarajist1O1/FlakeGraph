class DummyClass_20924 {
    @Test
    public void testParseMalformatted() {

        MetricParser parser = new MetricParser();
        try {
            // parser should throw an exception
            parser.parse("put mymetric 12341234 5.0 tag1 tag2=value2");
            Assert.fail();
        } catch (IllegalArgumentException e) {

        }
    }

}
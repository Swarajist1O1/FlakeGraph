class DummyClass_96060 {
    @Test
    public void testInvalidOutputter() throws IOException {
        try {
            Annotation ann = new Annotation("CoNLL-U is neat. Better than XML.");
            pipeline.annotate(ann);
            String actual = new CoNLLUOutputter("this should fail").print(ann);
            throw new AssertionError("This should have failed");
        } catch (IllegalArgumentException e) {
            // yay
        }
    }

}
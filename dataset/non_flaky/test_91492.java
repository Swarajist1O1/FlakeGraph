class DummyClass_91492 {
@TestLogging("_root:DEBUG")
    public void testFailuresCauseAbortDefault() throws Exception {
        /*
         * Create the destination index such that the copy will cause a mapping
         * conflict on every request.
         */
        indexRandom(true,
                client().prepareIndex("dest", "test", "test").setSource("test", 10) /* Its a string in the source! */);

        indexDocs(100);

        ReindexRequestBuilder copy = reindex().source("source").destination("dest");
        /*
         * Set the search size to something very small to cause there to be
         * multiple batches for this request so we can assert that we abort on
         * the first batch.
         */
        copy.source().setSize(1);

        BulkByScrollResponse response = copy.get();
        assertThat(response, matcher()
                .batches(1)
                .failures(both(greaterThan(0)).and(lessThanOrEqualTo(maximumNumberOfShards()))));
        for (Failure failure: response.getBulkFailures()) {
            assertThat(failure.getMessage(), containsString("IllegalArgumentException[For input string: \"words words\"]"));
        }
    }

}
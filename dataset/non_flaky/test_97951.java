class DummyClass_97951 {
    @Test
    public void testCovarianceOfFrom() {
        Observable.<Movie> from(new HorrorMovie());
        Observable.<Movie> from(new ArrayList<HorrorMovie>());
        // Observable.<HorrorMovie>from(new Movie()); // may not compile
    }

}
class DummyClass_98001 {
    @Test
    public void reduceCovariance() {
        // must type it to <Movie>
        Observable<Movie> horrorMovies = Observable.<Movie> from(new HorrorMovie());
        libraryFunctionActingOnMovieObservables(horrorMovies);
    }

}
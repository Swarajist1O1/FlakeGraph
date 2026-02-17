class DummyClass_97999 {
    @Test
    public void reduceWithObjects() {
        Observable<Movie> horrorMovies = Observable.<Movie> from(new HorrorMovie());

        Func2<Movie, Movie, Movie> chooseSecondMovie =
                new Func2<Movie, Movie, Movie>() {
                    public Movie call(Movie t1, Movie t2) {
                        return t2;
                    }

}
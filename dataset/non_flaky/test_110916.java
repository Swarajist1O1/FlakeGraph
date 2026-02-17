class DummyClass_110916 {
    @Test
    public void insertObject() {
        final Book book = Book.builder()
                .id(1)
                .title("What a great book")
                .author("Somebody")
                .build();

        final PutResult putResult = storIOSQLite
                .put()
                .object(book)
                .prepare()
                .executeAsBlocking();

        assertThat(putResult.wasInserted()).isTrue();

        final List<Book> storedBooks = storIOSQLite
                .get()
                .listOfObjects(Book.class)
                .withQuery(Query.builder()
                        .table(BookTableMeta.TABLE)
                        .build())
                .prepare()
                .executeAsBlocking();

        assertThat(storedBooks).hasSize(1);

        assertThat(storedBooks.get(0)).isEqualTo(book);
    }

}
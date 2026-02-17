class DummyClass_110917 {
    @Test
    public void updateObject() {
        final Book book = Book.builder()
                .id(1)
                .title("What a great book")
                .author("Somebody")
                .build();

        final PutResult putResult1 = storIOSQLite
                .put()
                .object(book)
                .prepare()
                .executeAsBlocking();

        assertThat(putResult1.wasInserted()).isTrue();

        final Book bookWithUpdatedInfo = Book.builder()
                .id(1) // Same id, should be updated
                .title("Corrected title")
                .author("Corrected author")
                .build();

        final PutResult putResult2 = storIOSQLite
                .put()
                .object(bookWithUpdatedInfo)
                .prepare()
                .executeAsBlocking();

        assertThat(putResult2.wasUpdated()).isTrue();

        final List<Book> storedBooks = storIOSQLite
                .get()
                .listOfObjects(Book.class)
                .withQuery(Query.builder()
                        .table(BookTableMeta.TABLE)
                        .build())
                .prepare()
                .executeAsBlocking();

        assertThat(storedBooks).hasSize(1);

        assertThat(storedBooks.get(0)).isEqualTo(bookWithUpdatedInfo);
    }

}
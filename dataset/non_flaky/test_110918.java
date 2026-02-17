class DummyClass_110918 {
    @Test
    public void deleteObject() {
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

        final DeleteResult deleteResult = storIOSQLite
                .delete()
                .object(book)
                .prepare()
                .executeAsBlocking();

        assertThat(deleteResult.numberOfRowsDeleted()).isEqualTo(1);

        final List<Book> storedBooks = storIOSQLite
                .get()
                .listOfObjects(Book.class)
                .withQuery(Query.builder()
                        .table(BookTableMeta.TABLE)
                        .build())
                .prepare()
                .executeAsBlocking();

        assertThat(storedBooks).hasSize(0);
    }

}
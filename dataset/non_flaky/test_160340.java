class DummyClass_160340 {
    @BeforeEach
    public void givenNoBans() {
      givenNoMacIsBanned();
      givenNoUsernameIsBanned();
      when(databaseDao.getBadWordDao()).thenReturn(badWordDao);
    }

}
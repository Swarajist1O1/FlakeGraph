class DummyClass_160339 {
    @BeforeEach
    public void createLobbyLoginValidator() throws Exception {
      lobbyLoginValidator =
          new LobbyLoginValidator(
              databaseDao,
              new RsaAuthenticator(TestSecurityUtils.loadRsaKeyPair()),
              () -> bcryptSalt,
              failedLoginThrottle,
              tempPasswordVerification,
              new AllowLoginRules(databaseDao),
              AllowCreateUserRules.builder()
                  .userDao(userDao)
                  .nameValidator(PlayerNameValidation::validate)
                  .emailValidator(PlayerEmailValidation::validate)
                  .build());
    }

}
class DummyClass_53160 {
    @Test
    public void tokenPolicy_whenInvalidUniquenessValue_throwsException() throws Exception {

        TokenPolicy tokenPolicy = new TokenPolicy();
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Invalid refresh token format invalid. Acceptable values are: [opaque, jwt]");

        tokenPolicy.setRefreshTokenFormat("invalid");
    }

}
class DummyClass_77568 {
    @Test
        public String getMessage(@QueryParam("message") NonEmptyStringParam message) {
            return message.get().orElse("Hello");
        }

}
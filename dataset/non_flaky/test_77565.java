class DummyClass_77565 {
    @Test
        public String getMessage(@CookieParam("message") Optional<String> message) {
            return message.orElse("Default Message");
        }

}
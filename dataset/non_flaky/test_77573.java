class DummyClass_77573 {
    @Test
        public String getMessage(@CookieParam("message") Optional<String> message) {
            return message.or("Default Message");
        }

}
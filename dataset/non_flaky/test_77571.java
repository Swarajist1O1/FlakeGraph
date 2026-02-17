class DummyClass_77571 {
    @Test
        public String getMessage(@QueryParam("message") Optional<String> message) {
            return message.or("Default Message");
        }

}
class DummyClass_77574 {
    @Test
        public String getMessage(@FormParam("message") Optional<String> message) {
            return message.or("Default Message");
        }

}
class DummyClass_98354 {
    @Test
        public String writeValue(Object value) {
            writeWasCalled = true;
            return new Gson().toJson(value);
        }

}
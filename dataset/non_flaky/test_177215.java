class DummyClass_177215 {
    @Test
            public void onSubscribe(Subscription s) {
                s.request(Long.MAX_VALUE);
            }

}
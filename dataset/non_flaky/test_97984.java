class DummyClass_97984 {
    @Test
    public void testErrorThrownWithoutErrorHandlerSynchronous() {
        try {
            Observable.error(new RuntimeException("failure")).subscribe(new Action1<Object>() {

                @Override
                public void call(Object t1) {
                    // won't get anything
                }

}
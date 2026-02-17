class DummyClass_135753 {
    @Test
    public void testRequestContextPropagatesAcrossObserveOnPool() {
        new SimpleCommand().execute();
        new SimpleCommand().observe().map(new Func1<String, String>() {

            @Override
            public String call(String s) {
                System.out.println("Map => Commands: " + HystrixRequestLog.getCurrentRequest().getAllExecutedCommands());
                return s;
            }

}
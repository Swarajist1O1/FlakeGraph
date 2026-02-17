class DummyClass_33908 {
    @Test
            public void configure() {
                from("direct:start").to("beanstalk:" + tubeName + "?jobPriority=1000&jobTimeToRun=5").to("mock:result");
            }

}
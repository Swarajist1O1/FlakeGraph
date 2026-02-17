class DummyClass_33905 {
    @Test
            public void configure() {
                from("direct:start").to("beanstalk:" + tubeName + "?command=bury").to("mock:result");
            }

}
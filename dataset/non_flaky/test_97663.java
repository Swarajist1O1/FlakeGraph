class DummyClass_97663 {
    @Test
    public void testAnnotationUtils() {
        final Method greetingMethod = getMethod(SpringTestApplication.GreetingController.class, "greeting");
        final RequestMapping mapping = AnnotatedElementUtils.findMergedAnnotation(greetingMethod, RequestMapping.class);
        Assert.assertNotNull(mapping);
        Assert.assertEquals(0, mapping.method().length);
        Assert.assertEquals(1, mapping.path().length);
        Assert.assertEquals("/greeting", mapping.path()[0]);
    }

}
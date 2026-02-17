class DummyClass_114009 {
    @Test
    public void findAnnotationSingleAction() {
        StrutsTilesAnnotationProcessor annotationProcessor = new StrutsTilesAnnotationProcessor();
        TilesDefinition tilesDefinition = annotationProcessor.findAnnotation(new TilesTestActionSingleAnnotation(), null);
        Assert.assertNotNull(tilesDefinition);
        Assert.assertEquals("definition-name", tilesDefinition.name());
    }

}
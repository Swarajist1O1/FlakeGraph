class DummyClass_114011 {
    @Test
    public void findAnnotationMultipleActionNameGiven() {
        StrutsTilesAnnotationProcessor annotationProcessor = new StrutsTilesAnnotationProcessor();
        TilesDefinition tilesDefinition = annotationProcessor.findAnnotation(new TilesTestActionMultipleAnnotations(), "def2");
        Assert.assertNotNull(tilesDefinition);
        Assert.assertEquals("def2", tilesDefinition.name());
    }

}
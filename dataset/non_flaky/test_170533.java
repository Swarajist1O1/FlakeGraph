class DummyClass_170533 {
    @Test
    public void testAnnotationDecorator() throws Exception
    {
        assertThrows(NullPointerException.class, () ->
        {
            new AnnotationDecorator(null);
        });

        WebAppContext context = new WebAppContext();
        AnnotationDecorator decorator = new AnnotationDecorator(context);
        ServletE servlet = new ServletE();
        //test without BaseHolder metadata
        decorator.decorate(servlet);
        LifeCycleCallbackCollection callbacks = (LifeCycleCallbackCollection)context.getAttribute(LifeCycleCallbackCollection.LIFECYCLE_CALLBACK_COLLECTION);
        assertNotNull(callbacks);
        assertFalse(callbacks.getPreDestroyCallbacks().isEmpty());

        //reset
        context.removeAttribute(LifeCycleCallbackCollection.LIFECYCLE_CALLBACK_COLLECTION);

        //test with BaseHolder metadata, should not introspect with metdata-complete==true
        context.getMetaData().setWebDescriptor(new TestWebDescriptor(MetaData.Complete.True));
        assertTrue(context.getMetaData().isMetaDataComplete());
        ServletHolder holder = new ServletHolder(new Source(Source.Origin.DESCRIPTOR, ""));
        holder.setHeldClass(ServletE.class);
        context.getServletHandler().addServlet(holder);
        DecoratedObjectFactory.associateInfo(holder);
        decorator = new AnnotationDecorator(context);
        decorator.decorate(servlet);
        DecoratedObjectFactory.disassociateInfo();
        callbacks = (LifeCycleCallbackCollection)context.getAttribute(LifeCycleCallbackCollection.LIFECYCLE_CALLBACK_COLLECTION);
        assertNull(callbacks);

        //reset
        context.removeAttribute(LifeCycleCallbackCollection.LIFECYCLE_CALLBACK_COLLECTION);

        //test with BaseHolder metadata, should introspect with metadata-complete==false
        context.getMetaData().setWebDescriptor(new TestWebDescriptor(MetaData.Complete.False));
        DecoratedObjectFactory.associateInfo(holder);
        decorator = new AnnotationDecorator(context);
        decorator.decorate(servlet);
        DecoratedObjectFactory.disassociateInfo();
        callbacks = (LifeCycleCallbackCollection)context.getAttribute(LifeCycleCallbackCollection.LIFECYCLE_CALLBACK_COLLECTION);
        assertNotNull(callbacks);
        assertFalse(callbacks.getPreDestroyCallbacks().isEmpty());
    }

}